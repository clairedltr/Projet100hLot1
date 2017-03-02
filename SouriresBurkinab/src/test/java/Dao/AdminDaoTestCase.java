package Dao;

import java.util.List;

import org.assertj.core.api.Assertions;
import org.junit.Before;
import org.junit.Test;

import com.mysql.jdbc.Connection;
import com.mysql.jdbc.Statement;

import daos.AdminDao;
import daos.DataSourceProvider;
import pojos.Admin;


public class AdminDaoTestCase {
		private AdminDao adminDao= new AdminDao();
		@Before
		public void initDataBase() throws Exception{
					Connection connection=(Connection) DataSourceProvider.getInstance().getDataSource().getConnection();
					Statement statement=(Statement) connection.createStatement();
				statement.executeUpdate("DELETE FROM admin");
				statement.executeUpdate("INSERT INTO admin(idadmin,nom,mail) VALUES (1,'Anthime','anthimeclaire')");
				statement.executeUpdate("INSERT INTO admin(idadmin,nom,mail) VALUES (2,'Julie','jujudu62')");
				statement.executeUpdate("INSERT INTO admin(idadmin,nom,mail) VALUES (3,'Charles','cacaprout')");
				statement.close();
				connection.close();
			
		}
		@Test
		public void shouldListMed() throws Exception{
			List<Admin> admins= adminDao.listAdmin();
			Assertions.assertThat(admins).hasSize(3);
			Assertions.assertThat(admins).extracting("idadmin","name","mdp").containsOnly(
					Assertions.tuple(1,"Anthime","anthimeclaire"),
					Assertions.tuple(2,"Julie","jujudu62"),
					Assertions.tuple(3,"Charles","cacaprout"));
		}
	}

