package Dao;

import java.sql.ResultSet;
import java.time.LocalDate;
import java.util.List;

import org.assertj.core.api.Assertions;
import org.junit.Before;
import org.junit.Test;

import com.mysql.jdbc.Connection;
import com.mysql.jdbc.Statement;

import daos.ArticleDao;
import daos.DataSourceProvider;
import pojos.Article;



public class ArticleDaoTestCase {
	private ArticleDao artDao= new ArticleDao();
	@Before
	public void initDataBase() throws Exception{
				Connection connection=(Connection) DataSourceProvider.getInstance().getDataSource().getConnection();
				Statement statement=(Statement) connection.createStatement();
			statement.executeUpdate("DELETE FROM article");
			statement.executeUpdate("INSERT INTO article(idArticle,Type,Titre,Contenu,DatePubli, deleted) VALUES (1, 'Article','Test1','Ceci est un testCeci est un testCeci est un testCeci est un testCeci est un testCeci est un testCeci est un testCeci est un testCeci est un testCeci est un test','2017-02-21', false)");
			statement.executeUpdate("INSERT INTO article(idArticle,Type,Titre,Contenu,DatePubli, deleted) VALUES (2,'Projets Réalisés','Test2','Ceci est un test','2017-02-24', false)");
			statement.executeUpdate("INSERT INTO article(idArticle,Type,Titre,Contenu,DatePubli, deleted) VALUES (3,'Article','Test3','Ceci est un test','2017-03-04', true)");
			statement.executeUpdate("INSERT INTO article(idArticle,Type,Titre,Contenu,DatePubli, deleted) VALUES (5,'Association','Test5','Ceci est un test','2017-03-04', false)");
			statement.close();
			connection.close();
		
	}
	@Test
	public void shouldListArticles() throws Exception{
		List<Article> articles= artDao.listArticles();
		Assertions.assertThat(articles).hasSize(3);
		Assertions.assertThat(articles).extracting("idArticle","Type","Titre","Contenu","DatePubli").containsOnly(
				Assertions.tuple(1,"Article","Test1","Ceci est un testCeci est un testCeci est un testCeci est un testCeci est un testCeci est un testCeci est un testCeci est un testCeci est un testCeci est un test",LocalDate.of(2017, 02, 21)),
				Assertions.tuple(2,"Projets Réalisés","Test2","Ceci est un test",LocalDate.of(2017, 02, 24)),
				Assertions.tuple(5,"Association","Test5","Ceci est un test", LocalDate.of(2017, 03, 04)));

	}
	
	@Test
	public void shouldAddArticle() throws Exception {
		// GIVEN 	
		Article newArticle = new Article(4,"Article", "New","My new article", LocalDate.of(2017, 02, 23));
		// WHEN
		ArticleDao.addArticle(newArticle);
		// THEN
		try(Connection connection = (Connection) DataSourceProvider.getInstance().getDataSource().getConnection();
				Statement statement = (Statement) connection.createStatement();
				ResultSet resultSet = statement.executeQuery("SELECT * FROM article WHERE titre='New'")){
			Assertions.assertThat(resultSet.next()).isTrue();
			Assertions.assertThat(resultSet.getInt("idArticle")).isNotNull();
			Assertions.assertThat(resultSet.getString("Type")).isEqualTo("Article");
			Assertions.assertThat(resultSet.getString("Titre")).isEqualTo("New");
			Assertions.assertThat(resultSet.getString("Contenu")).isEqualTo("My new article");
			Assertions.assertThat(resultSet.getDate("DatePubli").toLocalDate()).isEqualTo(LocalDate.of(2017, 02, 23));
			Assertions.assertThat(resultSet.next()).isFalse();
		}
	}
	
	@Test
	public void shouldListProjetRéalisé() throws Exception {
		// When
		List<Article> projetreal = ArticleDao.listProjetRéalisé();
		//THEN
		Assertions.assertThat(projetreal).hasSize(1);
		Assertions.assertThat(projetreal).extracting("idArticle", "Type", "Titre", "Contenu","DatePubli").containsOnly(
				Assertions.tuple(2, "Projets Réalisés", "Test2", "Ceci est un test", LocalDate.of(2017, 02, 24)));
	}
	
	@Test
	public void shouldListArticle() throws Exception {
		// When
		List<Article> arti = ArticleDao.listArticle();
		//THEN
		Assertions.assertThat(arti).hasSize(2);
		Assertions.assertThat(arti).extracting("idArticle", "Type", "Titre", "Contenu","DatePubli").containsOnly(
				Assertions.tuple(1, "Article", "Test1", "Ceci est un testCeci est un testCeci est un testCeci est un testCeci est un testCeci est un testCeci est un testCeci est un testCeci est un testCeci est un test", LocalDate.of(2017, 02, 21)),
				Assertions.tuple(3, "Article", "Test3", "Ceci est un test", LocalDate.of(2017, 03, 04)));

	}
	@Test
	public void shouldListAsso() throws Exception {
		// When
		List<Article> asso = ArticleDao.listAsso();
		//THEN
		Assertions.assertThat(asso).hasSize(1);
		Assertions.assertThat(asso).extracting("idArticle", "Type", "Titre", "Contenu","DatePubli").containsOnly(
				Assertions.tuple(5, "Association", "Test5", "Ceci est un test", LocalDate.of(2017, 03, 04)));

	}
	
	@Test
	public void shouldGetArticle() throws Exception {
		// WHEN
		Article article = artDao.getArticle(1);
		// THEN
		Assertions.assertThat(article).isNotNull();
		Assertions.assertThat(article.getId()).isEqualTo(1);
		Assertions.assertThat(article.getType()).isEqualTo("Article");
		Assertions.assertThat(article.getTitre()).isEqualTo("Test1");
		Assertions.assertThat(article.getContenu()).isEqualTo("Ceci est un testCeci est un testCeci est un testCeci est un testCeci est un testCeci est un testCeci est un testCeci est un testCeci est un testCeci est un test");
		Assertions.assertThat(article.getDatePubli()).isEqualTo(LocalDate.of(2017, 02, 21));
		
	}
	
	@Test
	public void shouldNotGetArticle() throws Exception {
		// WHEN
		Article article = artDao.getArticle(3);
		// THEN
		Assertions.assertThat(article).isNull();
		
	}

}





