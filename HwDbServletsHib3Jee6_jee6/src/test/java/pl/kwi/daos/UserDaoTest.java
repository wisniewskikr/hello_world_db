package pl.kwi.daos;

import java.util.List;

import junit.framework.Assert;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

import pl.kwi.daos.UserDao;
import pl.kwi.db.ejb3.AbstractDaoTest;
import pl.kwi.entities.UserEntity;

public class UserDaoTest extends AbstractDaoTest {

	static UserDao dao;
	
	@BeforeClass
	public static void setUp() throws Exception{
		openTestConnections("pu_test");
		dao = new UserDao();
		dao.setEm(getEm());
	}
	
	@AfterClass
	public static void after(){
		closeTestConnections();
	}

	@Test
	public void create() {

		executeDataFile("/dbunit/input.xml");
		long expected = 0;

		UserEntity entity = new UserEntity();
		entity.setName("Name4");
		dao.create(entity);

		long actual = entity.getId();

		Assert.assertNotSame(expected, actual);

	}

	@Test
	public void read() {
		
		executeDataFile("/dbunit/input.xml");
		String expected = "Name1";
		
		long id = 1;
		UserEntity entity = dao.read(id, UserEntity.class);
		String actual = entity.getName();
		
		Assert.assertEquals(expected, actual);

	}

	@Test
	public void update() {

		executeDataFile("/dbunit/input.xml");
		String expected = "Name2";

		int id = 1;
		UserEntity entity = dao.read(id, UserEntity.class);
		entity.setName(expected);
		dao.update(entity);

		entity = dao.read(id, UserEntity.class);
		String actual = entity.getName();

		Assert.assertEquals(expected, actual);

	}

	@Test
	public void delete() {
		
		executeDataFile("/dbunit/input.xml");
		int id = 1;
		
		UserEntity entity = dao.read(id, UserEntity.class);
		dao.delete(entity);

		entity = dao.read(id, UserEntity.class);
		Assert.assertNull(entity);

	}
	
	@Test
	public void getAllSimpleEntity(){
		
		executeDataFile("/dbunit/input.xml");
		
		List<UserEntity> list = dao.getAllUserList();
		
		Assert.assertEquals(3, list.size());
		
	}

}
