package daos;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import exceptions.AdminRuntimeException;
import exceptions.MediaRuntimeException;
import pojos.Media;


public class MediaDao {
		public List<Media> listMedias() {
			List<Media> medias = new ArrayList<Media>();

			try (Connection connection = DataSourceProvider.getInstance().getDataSource().getConnection();
					Statement statement = connection.createStatement();
					ResultSet resultSet = statement.executeQuery("SELECT * FROM media ORDER BY idMedia")) {
				while (resultSet.next()) {
					medias.add(
							new Media(resultSet.getInt("idMedia"), resultSet.getString("Type"), resultSet.getString("URL")));
				}
			} catch (SQLException e) {
				throw new MediaRuntimeException("Erreur lors du listing des médias", e);
			}

			return medias;
		}
		
		public Media getMedia(Integer id) {
			try (Connection connection = DataSourceProvider.getInstance().getDataSource().getConnection();
					PreparedStatement statement = connection.prepareStatement("SELECT * FROM media WHERE idMedia = ?")) {
				statement.setInt(1, id);
				try (ResultSet resultSet = statement.executeQuery()) {
					while (resultSet.next()) {
						return new Media(resultSet.getInt("idmedia"), resultSet.getString("type"), resultSet.getString("url"));
					}
				}
			} catch (SQLException e) {
				throw new MediaRuntimeException("Erreur en essayant de donner les informations sur ce média", e);
			}
			return null;
		}
		
		public void addMedia(Media newMedia) {
			try (Connection connection = DataSourceProvider.getInstance().getDataSource().getConnection();
					PreparedStatement statement = connection.prepareStatement("INSERT INTO media(Type, URL) VALUES (?, ?)")) {
				statement.setString(1, newMedia.getType());
				statement.setString(2, newMedia.getUrl());
				statement.executeUpdate();
			} catch (SQLException e) {
				throw new AdminRuntimeException("Erreur lors de l'ajout du Média", e);
			}
		}

	}

