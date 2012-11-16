package pl.kwi.daos;

import java.util.List;

import junit.framework.Assert;

import org.hibernate.classic.Session;
import org.junit.Test;

import pl.kwi.db.hib3.AbstractDaoTest;
import pl.kwi.db.hib3.Hib3Util;
import pl.kwi.entities.UserEntity;

public class UserDaoTest extends AbstractDaoTest{
		
	
	@Test
	public void create() {
		
		Session session = Hib3Util.buildSessionFactory("/hibernate.cfg.test.xml").getCurrentSession();
		Hib3Util.beginTransaction(session);
		
		executeDataFile("/dbunit/input.xml", session);
		
		UserEntity user = new UserEntity();
		user.setName("Chris");
		
		UserDao dao = new UserDao(session);
		dao.create(user);
		
		Hib3Util.commitTransaction(session);

		Assert.assertNotNull(user.getId());
		
	}	
	
	@Test
	public void read() {
		
		Session session = Hib3Util.buildSessionFactory("/hibernate.cfg.test.xml").getCurrentSession();
		Hib3Util.beginTransaction(session);
		
		executeDataFile("/dbunit/input.xml", session);
		
		Long id = 1l;		
		UserDao dao = new UserDao(session);
		UserEntity user = dao.read(id, UserEntity.class);
				
		Hib3Util.commitTransaction(session);
		
		Assert.assertEquals(Long.valueOf(id), user.getId());
		
	}
	
	@Test
	public void update() {
		
		Session session = Hib3Util.buildSessionFactory("/hibernate.cfg.test.xml").getCurrentSession();
		Hib3Util.beginTransaction(session);

		executeDataFile("/dbunit/input.xml", session);
		String expected = "Name2";

		int id = 1;
		UserDao dao = new UserDao(session);
		UserEntity entity = dao.read(id, UserEntity.class);
		entity.setName(expected);
		dao.update(entity);

		entity = dao.read(id, UserEntity.class);
		String actual = entity.getName();

		Hib3Util.commitTransaction(session);
		
		Assert.assertEquals(expected, actual);

	}

	@Test
	public void delete() {
		
		Session session = Hib3Util.buildSessionFactory("/hibernate.cfg.test.xml").getCurrentSession();
		Hib3Util.beginTransaction(session);
		
		executeDataFile("/dbunit/input.xml", session);
		int id = 1;
		
		UserDao dao = new UserDao(session);
		UserEntity entity = dao.read(id, UserEntity.class);
		dao.delete(entity);

		entity = dao.read(id, UserEntity.class);
		
		Hib3Util.commitTransaction(session);
		
		Assert.assertNull(entity);

	}
	
	@Test
	public void getAllUsers(){
		
		Session session = Hib3Util.buildSessionFactory("/hibernate.cfg.test.xml").getCurrentSession();
		Hib3Util.beginTransaction(session);
		
		executeDataFile("/dbunit/input.xml", session);
		
		UserDao dao = new UserDao(session);
		List<UserEntity> list = dao.getAllUserList();
		
		Hib3Util.commitTransaction(session);
		
		Assert.assertEquals(3, list.size());
		
	}


}
