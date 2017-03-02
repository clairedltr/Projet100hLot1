package Dao;

import java.time.LocalDate;
import java.util.List;

import org.assertj.core.api.Assertions;
import org.junit.Before;
import org.junit.Test;

import com.mysql.jdbc.Connection;
import com.mysql.jdbc.Statement;

import daos.DataSourceProvider;
import daos.NewsletterDao;
import pojos.Newsletter;


public class NewsletterDaoTestCase {
	private NewsletterDao newsDao= new NewsletterDao();
	@Before
	public void initDataBase() throws Exception{
				Connection connection=(Connection) DataSourceProvider.getInstance().getDataSource().getConnection();
				Statement statement=(Statement) connection.createStatement();
			statement.executeUpdate("DELETE FROM newsletter");
			statement.executeUpdate("INSERT INTO newsletter(idNewsletter,Contenu,Titre,Date) VALUES (1,'Test1','Ceci est un test','2017-02-21')");
			statement.executeUpdate("INSERT INTO newsletter(idNewsletter,Contenu,Titre,Date) VALUES (2,'Test2','Ceci est un test','2017-02-24')");
			statement.executeUpdate("INSERT INTO newsletter(idNewsletter,Contenu,Titre,Date) VALUES (3,'Test3','Ceci est un test','2017-03-04')");
			statement.close();
			connection.close();
		
	}
	@Test
	public void shouldListMed() throws Exception{
		List<Newsletter> news= newsDao.listNewsletter();
		Assertions.assertThat(news).hasSize(3);
		Assertions.assertThat(news).extracting("idnews","contenu","titrenews","datenews").containsOnly(
				Assertions.tuple(1,"Test1","Ceci est un test", LocalDate.of(2017, 02, 21)),
				Assertions.tuple(2,"Test2","Ceci est un test", LocalDate.of(2017, 02, 24)),
				Assertions.tuple(3,"Test3","Ceci est un test", LocalDate.of(2017, 03, 04)));
	}
}
