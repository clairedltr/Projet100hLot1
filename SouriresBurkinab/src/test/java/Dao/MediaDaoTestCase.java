package Dao;

import java.util.List;

import org.assertj.core.api.Assertions;
import org.junit.Before;
import org.junit.Test;

import com.mysql.jdbc.Connection;
import com.mysql.jdbc.Statement;

import daos.DataSourceProvider;
import daos.MediaDao;
import pojos.Media;


public class MediaDaoTestCase {
	private MediaDao mediaDao= new MediaDao();
	@Before
	public void initDataBase() throws Exception{
				Connection connection=(Connection) DataSourceProvider.getInstance().getDataSource().getConnection();
				Statement statement=(Statement) connection.createStatement();
			statement.executeUpdate("DELETE FROM media");
			statement.executeUpdate("INSERT INTO media(idMedia,Type,URL) VALUES (1,'Photo','document/claire/doc.jpg')");
			statement.executeUpdate("INSERT INTO media(idMedia,Type,URL) VALUES (2,'Musique','document/claire2/doc.mp3')");
			statement.executeUpdate("INSERT INTO media(idMedia,Type,URL) VALUES (3,'Photo','document/claire3/doc.jpg')");
			statement.close();
			connection.close();
		
	}
	@Test
	public void shouldListMed() throws Exception{
		List<Media> medias= mediaDao.listMedias();
		Assertions.assertThat(medias).hasSize(3);
		Assertions.assertThat(medias).extracting("idmedia","type","url").containsOnly(
				Assertions.tuple(1,"Photo","document/claire/doc.jpg"),
				Assertions.tuple(2,"Musique","document/claire2/doc.mp3"),
				Assertions.tuple(3,"Photo","document/claire3/doc.jpg"));
	}
}
