package pl.kwi.daos;

import static junit.framework.Assert.assertNotNull;

import java.sql.Connection;
import java.util.List;

import junit.framework.Assert;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

import pl.kwi.db.jdbc.JdbcUtilTest;
import pl.kwi.entities.UserEntity;

public class UserDaoTest {

	private static UserDao dao;
	private static Connection conn;
	
	@BeforeClass
	public static void setUp() throws Exception{
		conn = JdbcUtilTest.beginConncetion();
		dao = new UserDao(conn);
	}
	
	@AfterClass
	public static void after(){
		JdbcUtilTest.finishConnection(conn);
	}
	
	@Test
	public void createUser() throws Exception{
		
		JdbcUtilTest.executeDataFile("/dbunit/userDaoTest.xml", conn);
		
		UserEntity user = new UserEntity();
		user.setName("name4");
		
		dao.createUser(user);
		
		assertNotNull(user.getId());
		
	}
	
	@Test
	public void readUser() throws Exception {
		
		JdbcUtilTest.executeDataFile("/dbunit/userDaoTest.xml", conn);
		
		UserEntity user = dao.readUser(1L);
		
		Assert.assertEquals(Long.valueOf(1), user.getId());
		Assert.assertEquals("name1", user.getName());
		
	}
	
	@Test
	public void updateUser() throws Exception {
		
		JdbcUtilTest.executeDataFile("/dbunit/userDaoTest.xml", conn);
		
		UserEntity user = new UserEntity();
		user.setId(1L);
		user.setName("name4");
		
		dao.updateUser(user);
		
		user = dao.readUser(1L);
		
		Assert.assertEquals(Long.valueOf(1), user.getId());
		Assert.assertEquals("name4", user.getName());
		
	}
	
	@Test
	public void deleteUser() throws Exception {
		
		JdbcUtilTest.executeDataFile("/dbunit/userDaoTest.xml", conn);
		
		dao.deleteUser(1L);
		
		List<UserEntity> users = dao.getUsers();
		
		Assert.assertEquals(2, users.size());
		
	}
	
	@Test
	public void getUsers() throws Exception {
		
		JdbcUtilTest.executeDataFile("/dbunit/userDaoTest.xml", conn);
		
		List<UserEntity> users = dao.getUsers();
		
		Assert.assertEquals(3, users.size());
		
	}

}
