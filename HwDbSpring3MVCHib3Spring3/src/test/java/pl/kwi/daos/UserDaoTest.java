package pl.kwi.daos;

import java.util.List;

import junit.framework.Assert;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestExecutionListeners;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.support.DependencyInjectionTestExecutionListener;
import org.springframework.transaction.annotation.Transactional;

import pl.kwi.entities.UserEntity;

import com.github.springtestdbunit.DbUnitTestExecutionListener;
import com.github.springtestdbunit.annotation.DatabaseSetup;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations={
		"/conf/spring-conf.xml",
		"/conf/spring-db-test.xml"
		})
@Transactional
@TestExecutionListeners({ DependencyInjectionTestExecutionListener.class,
    						DbUnitTestExecutionListener.class })
public class UserDaoTest {

	@Autowired
	private UserDao dao;
	
	@Test
	@DatabaseSetup("/dbunit/input.xml")
	public void create() {

		long expected = 0;

		UserEntity entity = new UserEntity();
		entity.setName("Name4");
		dao.create(entity);

		long actual = entity.getId();

		Assert.assertNotSame(expected, actual);

	}

	@Test
	@DatabaseSetup("/dbunit/input.xml")
	public void read() {
		
		String expected = "Name1";
		
		long id = 1;
		UserEntity entity = dao.findOne(id);
		String actual = entity.getName();
		
		Assert.assertEquals(expected, actual);

	}

	@Test
	@DatabaseSetup("/dbunit/input.xml")
	public void update() {

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
	@DatabaseSetup("/dbunit/input.xml")
	public void delete() {
		
		long id = 1;
		
		UserEntity entity = dao.findOne(id);
		dao.delete(entity);

		entity = dao.findOne(id);
		Assert.assertNull(entity);

	}
	
	@Test
	@DatabaseSetup("/dbunit/input.xml")
	public void getAllSimpleEntity(){
		
		List<UserEntity> list = dao.findAll();
		
		Assert.assertEquals(3, list.size());
		
	}

}
