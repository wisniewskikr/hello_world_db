package pl.kwi.services;

import java.util.List;

import org.hibernate.classic.Session;

import pl.kwi.daos.UserDao;
import pl.kwi.db.hib3.Hib3Util;
import pl.kwi.entities.UserEntity;

public class UserService {

	
	private Session session;
	
	
	public Long createUser(UserEntity user){
		
		Long id;
		
		session = Hib3Util.createSession("/hibernate.cfg.xml");
		Hib3Util.beginTransaction(session);
		
		UserDao dao = new UserDao(session);		
		id = dao.create(user);
		
		Hib3Util.commitTransaction(session);
		
		return id;
		
	}
	
	public UserEntity readUser(Long id){
		
		UserEntity entity;
		
		session = Hib3Util.createSession("/hibernate.cfg.xml");
		Hib3Util.beginTransaction(session);
		
		UserDao dao = new UserDao(session);		
		entity = dao.read(id, UserEntity.class);
		
		Hib3Util.commitTransaction(session);
		
		return entity;
		
	}
	
	public void updateUser(UserEntity user){
		
		session = Hib3Util.createSession("/hibernate.cfg.xml");
		Hib3Util.beginTransaction(session);
		
		UserDao dao = new UserDao(session);		
		dao.update(user);
		
		Hib3Util.commitTransaction(session);
		
	}
	
	public void deleteUser(UserEntity user){
		
		session = Hib3Util.createSession("/hibernate.cfg.xml");
		Hib3Util.beginTransaction(session);
		
		UserDao dao = new UserDao(session);		
		dao.delete(user);
		
		Hib3Util.commitTransaction(session);
		
	}
	
	public List<UserEntity> getUserList(){
		
		List<UserEntity> list;
		
		session = Hib3Util.createSession("/hibernate.cfg.xml");
		Hib3Util.beginTransaction(session);
		
		UserDao dao = new UserDao(session);
		list = dao.getAllUserList();
		
		Hib3Util.commitTransaction(session);
		
		return list;
		
	}

}
