package pl.kwi.daos;

import static junit.framework.Assert.assertEquals;
import static junit.framework.Assert.assertNull;
import static junit.framework.Assert.assertNotNull;

import java.util.List;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

import pl.kwi.db.jdbc.AbstractDaoTest;
import pl.kwi.entities.UserEntity;

public class UserDaoTest extends AbstractDaoTest{
	
	
	private static UserDao dao;
	
	@BeforeClass
	public static void setUp() throws Exception{
		openTestConnections("/jdbc_test.properties");
		dao = new UserDao(getConn());
	}
	
	@AfterClass
	public static void after(){
		closeTestConnections();
	}

	@Test
	public void createUser() throws Exception{
		
		executeDataFile("/dbunit/input.xml");
		
		UserEntity user = new UserEntity();
		user.setName("Chris");
		
		dao.createUser(user);
		
		Long id = user.getId();
		assertNotNull(id);
		user = dao.readUser(id);		
		assertEquals(id, user.getId());
		assertEquals("Chris", user.getName());
		
	}
	
	@Test
	public void readUser() throws Exception{
		
		executeDataFile("/dbunit/input.xml");
		
		UserEntity user = null;		
		user = dao.readUser(1L);
		
		assertEquals(Long.valueOf(1), user.getId());
		assertEquals("Name1", user.getName());
		
	}
	
	@Test
	public void updateUser() throws Exception{
		
		executeDataFile("/dbunit/input.xml");
		
		UserEntity user = new UserEntity();
		user.setId(1L);
		user.setName("Test");
		
		dao.updateUser(user);
		
		user = dao.readUser(1L);		
		assertEquals(Long.valueOf(1), user.getId());
		assertEquals("Test", user.getName());
		
	}
	
	@Test
	public void deleteUser() throws Exception{
		
		executeDataFile("/dbunit/input.xml");
		
		dao.deleteUser(1L);
		
		UserEntity user = dao.readUser(1L);		
		assertNull(user.getId());
		assertNull(user.getName());
		
	}
	
	@Test
	public void getUsers() throws Exception{
		
		executeDataFile("/dbunit/input.xml");
		
		List<UserEntity> users = dao.getUsers();
		
		assertEquals(3, users.size());
		
	}

}
