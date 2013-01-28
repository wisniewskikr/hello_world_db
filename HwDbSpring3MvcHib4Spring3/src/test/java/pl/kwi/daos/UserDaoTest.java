package pl.kwi.daos;

import java.util.List;

import junit.framework.Assert;

import org.hibernate.SessionFactory;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import pl.kwi.db.spring.DbUnitUtil;
import pl.kwi.entities.UserEntity;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations={
		"/conf/spring-conf.xml",
		"/conf/spring-db-test.xml"
		})
@Transactional
public class UserDaoTest {

	@Autowired
	private UserDao dao;
	
	@Autowired
	public SessionFactory sessionFactory;
	
	@Test
	public void create() {
		
		DbUnitUtil.executeDataFile("/dbunit/input.xml", sessionFactory);

		long expected = 0;

		UserEntity entity = new UserEntity();
		entity.setName("Name4");
		dao.create(entity);

		long actual = entity.getId();

		Assert.assertNotSame(expected, actual);

	}

	@Test
	public void read() {
		
		DbUnitUtil.executeDataFile("/dbunit/input.xml", sessionFactory);
		
		String expected = "Name1";
		
		long id = 1;
		UserEntity entity = dao.findOne(id);
		String actual = entity.getName();
		
		Assert.assertEquals(expected, actual);

	}

	@Test
	public void update() {
		
		DbUnitUtil.executeDataFile("/dbunit/input.xml", sessionFactory);

		String expected = "Name2";

		long id = 1;
		UserEntity entity = dao.findOne(id);
		entity.setName(expected);
		dao.update(entity);

		entity = dao.findOne(id);
		String actual = entity.getName();

		Assert.assertEquals(expected, actual);

	}

	@Test
	public void delete() {
		
		DbUnitUtil.executeDataFile("/dbunit/input.xml", sessionFactory);
		
		long id = 1;
		
		UserEntity entity = dao.findOne(id);
		dao.delete(entity);

		entity = dao.findOne(id);
		Assert.assertNull(entity);

	}
	
	@Test
	public void getAllSimpleEntity(){
		
		DbUnitUtil.executeDataFile("/dbunit/input.xml", sessionFactory);
		
		List<UserEntity> list = dao.findAll();
		
		Assert.assertEquals(3, list.size());
		
	}

}
