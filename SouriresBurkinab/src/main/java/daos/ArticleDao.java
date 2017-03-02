package daos;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import exceptions.AbonneRuntimeException;
import exceptions.ArticleRuntimeException;
import pojos.Abonne;
import pojos.Article;



public class ArticleDao {

	public Article getArticle(Integer id) {
		try (Connection connection = DataSourceProvider.getInstance().getDataSource().getConnection();
				PreparedStatement statement = connection.prepareStatement("SELECT * FROM article WHERE idArticle = ? AND deleted = false")) {
			statement.setInt(1, id);
			try (ResultSet resultSet = statement.executeQuery()) {
				while (resultSet.next()) {
					return new Article(resultSet.getInt("idArticle"), resultSet.getString("Type"), resultSet.getString("Titre"), resultSet.getString("Contenu"),resultSet.getDate("DatePubli").toLocalDate());
				}
			}
		} catch (SQLException e) {
			throw new ArticleRuntimeException("Error when getting article", e);
		}
		return null;
	}
	
	public static void addArticle(Article newArticle) {
		try (Connection connection = DataSourceProvider.getInstance().getDataSource().getConnection();
				PreparedStatement statement = connection.prepareStatement("INSERT INTO article(idArticle, Type, Titre, Contenu, DatePubli) VALUES (?,?,?,?,?)")) {
			statement.setInt(1, newArticle.getId());
			statement.setString(2, newArticle.getType());
			statement.setString(3, newArticle.getTitre());
			statement.setString(4, newArticle.getContenu());
			statement.setDate(5, Date.valueOf(newArticle.getDatePubli()));
			statement.executeUpdate();
		} catch (SQLException e) {
			throw new ArticleRuntimeException("Error when getting article", e);
		}
	}

	public List<Article> listArticles() {
		List<Article> listmed = new ArrayList<Article>();
		try (Connection connection = DataSourceProvider.getInstance().getDataSource().getConnection();
				Statement statement = connection.createStatement();
				ResultSet resultSet = statement.executeQuery("SELECT * FROM article WHERE deleted=false")) {
			while (resultSet.next()) {
				listmed.add(new Article(resultSet.getInt("idArticle"), resultSet.getString("Type"), resultSet.getString("Titre"),resultSet.getString("Contenu"),resultSet.getDate("DatePubli").toLocalDate()));			}
		} catch (SQLException e) {
			throw new ArticleRuntimeException("Erreur dans le retour des Articles", e);
		}

		return listmed;
		
	}
	public static List<Article> listProjetRéalisé(){
		List<Article> projetreal = new ArrayList<>();
		try (Connection connection = DataSourceProvider.getInstance().getDataSource().getConnection();
				Statement statement = connection.createStatement();
					ResultSet resultSet = statement.executeQuery("SELECT * FROM article WHERE Type='Projets Réalisés'")){
							while(resultSet.next()){
								projetreal.add(new Article(resultSet.getInt("idArticle"), resultSet.getString("Type"), resultSet.getString("Titre"), resultSet.getString("Contenu"), resultSet.getDate("DatePubli").toLocalDate()));	
							}
			
		} catch (SQLException e) {
			throw new ArticleRuntimeException("Error when getting projet réalisés", e);
		}
		return projetreal;
	}
	
	public static List<Article> listArticle(){
		List<Article> arti = new ArrayList<>();
		try (Connection connection = DataSourceProvider.getInstance().getDataSource().getConnection();
				Statement statement = connection.createStatement();
					ResultSet resultSet = statement.executeQuery("SELECT * FROM article WHERE Type='Article'")){
							while(resultSet.next()){
								arti.add(new Article(resultSet.getInt("idArticle"), resultSet.getString("Type"), resultSet.getString("Titre"), resultSet.getString("Contenu"), resultSet.getDate("DatePubli").toLocalDate()));	
							}
			
		} catch (SQLException e) {
			throw new ArticleRuntimeException("Error when getting Article", e);
		}
		return arti;
	}
	
	public static List<Article> listAsso(){
		List<Article> asso = new ArrayList<>();
		try (Connection connection = DataSourceProvider.getInstance().getDataSource().getConnection();
				Statement statement = connection.createStatement();
					ResultSet resultSet = statement.executeQuery("SELECT * FROM article WHERE Type='Association'")){
							while(resultSet.next()){
								asso.add(new Article(resultSet.getInt("idArticle"), resultSet.getString("Type"), resultSet.getString("Titre"), resultSet.getString("Contenu"), resultSet.getDate("DatePubli").toLocalDate()));	
							}
			
		} catch (SQLException e) {
			throw new ArticleRuntimeException("Error when getting Article", e);
		}
		return asso;
	}
	
	
	public void deleteArticle(Integer articleId) {
		try (Connection connection = DataSourceProvider.getInstance().getDataSource().getConnection();
				PreparedStatement statement = connection.prepareStatement("UPDATE article SET deleted=true WHERE id=?")) {
			statement.setInt(1, articleId);
			statement.executeUpdate();	
		} catch (SQLException e) {
			throw new ArticleRuntimeException("Error when deleting article", e);
		}
	}
	
	
	
	
	
	
	}


