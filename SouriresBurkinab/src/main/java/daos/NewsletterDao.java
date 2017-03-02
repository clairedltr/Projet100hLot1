package daos;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import exceptions.AdminRuntimeException;
import exceptions.NewsRuntimeException;
import pojos.Newsletter;

public class NewsletterDao {
		public List<Newsletter> listNewsletter() {
			List<Newsletter> newsletter = new ArrayList<Newsletter>();

			try (Connection connection = DataSourceProvider.getInstance().getDataSource().getConnection();
					Statement statement = connection.createStatement();
					ResultSet resultSet = statement.executeQuery("SELECT * FROM newsletter ORDER BY idNewsletter")) {
				while (resultSet.next()) {
					newsletter.add(
							new Newsletter(resultSet.getInt("idNewsLetter"), resultSet.getString("Contenu"), resultSet.getString("Titre"),resultSet.getDate("Date").toLocalDate()));
				}
			} catch (SQLException e) {
				throw new NewsRuntimeException("Erreur lors du listing des newsletters", e);
			}

			return newsletter;
		}
		
		public Newsletter getNews(Integer id) {
			try (Connection connection = DataSourceProvider.getInstance().getDataSource().getConnection();
					PreparedStatement statement = connection.prepareStatement("SELECT * FROM newsletter WHERE idNewsletter = ?")) {
				statement.setInt(1, id);
				try (ResultSet resultSet = statement.executeQuery()) {
					while (resultSet.next()) {
						return new Newsletter(resultSet.getInt("idNewsLetter"), resultSet.getString("Contenu"), resultSet.getString("Titre"),resultSet.getDate("Date").toLocalDate());
					}
				}
			} catch (SQLException e) {
				throw new AdminRuntimeException("Erreur en essayant de donner les informations sur cette newsletter", e);
			}
			return null;
		}
		
		public void addNews(Newsletter newNews) {
			try (Connection connection = DataSourceProvider.getInstance().getDataSource().getConnection();
					PreparedStatement statement = connection.prepareStatement("INSERT INTO newsletter(Contenu, Titre, Date) VALUES (?, ?,?)")) {
				statement.setString(1, newNews.getContenu());
				statement.setString(2, newNews.getTitrenews());
				statement.setDate(3, Date.valueOf(newNews.getDatenews()));
				statement.executeUpdate();
			} catch (SQLException e) {
				throw new AdminRuntimeException("Erreur lors de l'ajout de la Newsletter", e);
			}
		}

	}

