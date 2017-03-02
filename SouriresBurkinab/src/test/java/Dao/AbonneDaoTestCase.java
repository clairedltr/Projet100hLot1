package Dao;

import java.util.List;

import org.assertj.core.api.Assertions;
import org.junit.Before;
import org.junit.Test;

import com.mysql.jdbc.Connection;
import com.mysql.jdbc.Statement;

import daos.AbonneDao;
import daos.DataSourceProvider;
import pojos.Abonne;


public class AbonneDaoTestCase {
	private AbonneDao abonne= new AbonneDao();
	@Before
	public void initDataBase() throws Exception{
				Connection connection=(Connection) DataSourceProvider.getInstance().getDataSource().getConnection();
				Statement statement=(Statement) connection.createStatement();
			statement.executeUpdate("DELETE FROM abonne");
			statement.executeUpdate("INSERT INTO abonne(idAbonne,nom,mail,newsletterabo) VALUES (1,'Claire','claire@hei.fr',true)");
			statement.executeUpdate("INSERT INTO abonne(idAbonne,nom,mail,newsletterabo) VALUES (2,'Claire2','claire2@hei.fr',false)");
			statement.executeUpdate("INSERT INTO abonne(idAbonne,nom,mail,newsletterabo) VALUES (3,'Claire3','claire3@hei.fr',true)");
			statement.close();
			connection.close();
		
	}
	@Test
	public void shouldListMed() throws Exception{
		List<Abonne> abonnes= abonne.listAbo();
		Assertions.assertThat(abonnes).hasSize(3);
		Assertions.assertThat(abonnes).extracting("idAbo","nom","mail","newsletterabo").containsOnly(
				Assertions.tuple(1,"Claire","claire@hei.fr",true),
				Assertions.tuple(2,"Claire2","claire2@hei.fr",false),
				Assertions.tuple(3,"Claire3","claire3@hei.fr",true));
	}
}

