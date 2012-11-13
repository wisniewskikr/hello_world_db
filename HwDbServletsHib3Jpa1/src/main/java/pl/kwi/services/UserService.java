package pl.kwi.services;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;

import pl.kwi.daos.UserDao;
import pl.kwi.db.jpa.JPAUtil;
import pl.kwi.entities.UserEntity;

public class UserService {

	
	private EntityManager em;
	private EntityTransaction transaction;
	
	
	public Long createUser(UserEntity user){
		
		Long id;
		
		em = JPAUtil.createEntityManager("pu");
		transaction = JPAUtil.beginTransaction(em);
		
		UserDao dao = new UserDao(em);		
		id = dao.create(user);
		
		JPAUtil.commitTransaction(transaction);
		JPAUtil.closeEntityManager(em);
		
		return id;
		
	}
	
	public UserEntity readUser(Long id){
		
		UserEntity entity;
		
		em = JPAUtil.createEntityManager("pu");
		transaction = JPAUtil.beginTransaction(em);
		
		UserDao dao = new UserDao(em);		
		entity = dao.read(id, UserEntity.class);
		
		JPAUtil.commitTransaction(transaction);
		JPAUtil.closeEntityManager(em);
		
		return entity;
		
	}
	
	public void updateUser(UserEntity user){
		
		em = JPAUtil.createEntityManager("pu");
		transaction = JPAUtil.beginTransaction(em);
		
		UserDao dao = new UserDao(em);		
		dao.update(user);
		
		JPAUtil.commitTransaction(transaction);
		JPAUtil.closeEntityManager(em);
		
	}
	
	public void deleteUser(UserEntity user){
		
		em = JPAUtil.createEntityManager("pu");
		transaction = JPAUtil.beginTransaction(em);
		
		UserDao dao = new UserDao(em);		
		dao.delete(user);
		
		JPAUtil.commitTransaction(transaction);
		JPAUtil.closeEntityManager(em);
		
	}
	
	public List<UserEntity> getUserList(){
		
		List<UserEntity> list;
		
		em = JPAUtil.createEntityManager("pu");
		transaction = JPAUtil.beginTransaction(em);
		
		UserDao dao = new UserDao(em);
		list = dao.getAllUserList();
		
		JPAUtil.commitTransaction(transaction);
		JPAUtil.closeEntityManager(em);
		
		return list;
		
	}

}
