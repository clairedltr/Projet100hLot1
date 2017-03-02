package daos;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import exceptions.AbonneRuntimeException;
import pojos.Abonne;


public class AbonneDao {
	
	public List<Abonne> listAbo() {
		List<Abonne> abonnes = new ArrayList<Abonne>();

		try (Connection connection = DataSourceProvider.getInstance().getDataSource().getConnection();
				Statement statement = connection.createStatement();
				ResultSet resultSet = statement.executeQuery("SELECT * FROM abonne ORDER BY idAbonne")) {
			while (resultSet.next()) {
				abonnes.add(
						new Abonne(resultSet.getInt("idAbonne"), resultSet.getString("nom"), resultSet.getString("mail"),resultSet.getBoolean("newsletterabo")));
			}
		} catch (SQLException e) {
			throw new AbonneRuntimeException("Erreur lors du listing d'abonnés", e);
		}

		return abonnes;
	}
	
	public Abonne getAbonne(Integer id) {
		try (Connection connection = DataSourceProvider.getInstance().getDataSource().getConnection();
				PreparedStatement statement = connection.prepareStatement("SELECT * FROM abonne WHERE idAbonne = ?")) {
			statement.setInt(1, id);
			try (ResultSet resultSet = statement.executeQuery()) {
				while (resultSet.next()) {
					return new Abonne(resultSet.getInt("idAbonne"), resultSet.getString("nom"), resultSet.getString("mail"),resultSet.getBoolean("newsletterabo"));
				}
			}
		} catch (SQLException e) {
			throw new AbonneRuntimeException("Erreur en essayant de donner les informations sur cet abonné", e);
		}
		return null;
	}
	
	public void addAbonne(Abonne newAbonne) {
		try (Connection connection = DataSourceProvider.getInstance().getDataSource().getConnection();
				PreparedStatement statement = connection.prepareStatement("INSERT INTO abonne(nom, mail, newsletterabo) VALUES (?, ?, ?)")) {
			statement.setString(1, newAbonne.getNom());
			statement.setString(2, newAbonne.getMail());
			statement.setBoolean(3, newAbonne.isNewsletterabo());
			statement.executeUpdate();
		} catch (SQLException e) {
			throw new AbonneRuntimeException("Error when getting cities", e);
		}
	}

}
