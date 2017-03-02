package daos;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import exceptions.AdminRuntimeException;
import pojos.Admin;



public class AdminDao {
	public List<Admin> listAdmin() {
		List<Admin> admins = new ArrayList<Admin>();

		try (Connection connection = DataSourceProvider.getInstance().getDataSource().getConnection();
				Statement statement = connection.createStatement();
				ResultSet resultSet = statement.executeQuery("SELECT * FROM admin ORDER BY idadmin")) {
			while (resultSet.next()) {
				admins.add(
						new Admin(resultSet.getInt("idadmin"), resultSet.getString("nom"), resultSet.getString("mdp")));
			}
		} catch (SQLException e) {
			throw new AdminRuntimeException("Erreur lors du listing des administrateurs", e);
		}

		return admins;
	}
	
	public Admin getAdmin(Integer id) {
		try (Connection connection = DataSourceProvider.getInstance().getDataSource().getConnection();
				PreparedStatement statement = connection.prepareStatement("SELECT * FROM admin WHERE idadmin = ?")) {
			statement.setInt(1, id);
			try (ResultSet resultSet = statement.executeQuery()) {
				while (resultSet.next()) {
					return new Admin(resultSet.getInt("idadmin"), resultSet.getString("nom"), resultSet.getString("mdp"));
				}
			}
		} catch (SQLException e) {
			throw new AdminRuntimeException("Erreur en essayant de donner les informations sur cet administrateur", e);
		}
		return null;
	}
	
	public void addAdmin(Admin newAdmin) {
		try (Connection connection = DataSourceProvider.getInstance().getDataSource().getConnection();
				PreparedStatement statement = connection.prepareStatement("INSERT INTO admin(nom, mdp) VALUES (?, ?)")) {
			statement.setString(1, newAdmin.getName());
			statement.setString(2, newAdmin.getMdp());
			statement.executeUpdate();
		} catch (SQLException e) {
			throw new AdminRuntimeException("Erreur lors de l'ajout de l'administrateur", e);
		}
	}

}


