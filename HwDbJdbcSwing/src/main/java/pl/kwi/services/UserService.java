package pl.kwi.services;

import java.util.ArrayList;
import java.util.List;

import pl.kwi.daos.UserDao;
import pl.kwi.db.jdbc.JdbcTransManagement;
import pl.kwi.db.jdbc.JdbcUtil;
import pl.kwi.entities.UserEntity;


public class UserService {
		
		
	public void createUser(UserEntity user) throws Exception{
				
		JdbcTransManagement management = JdbcUtil.beginTransaction();
		
		try{
			
			UserDao userDao = new UserDao(management.getConn());
			userDao.createUser(user);
			
		}catch (Exception e){
			management.addException(e);
		}
			
		JdbcUtil.finishTransaction(management);
		if(management.isException()){
			throw management.crateJoinedException();
		}
		
	}
	
	public UserEntity readUser(Long id) throws Exception{
		
		UserEntity user = null;		
		JdbcTransManagement management = JdbcUtil.beginTransaction();
		
		try{
			
			UserDao userDao = new UserDao(management.getConn());
			user = userDao.readUser(id);
			
		}catch (Exception e){
			management.addException(e);
		}
			
		JdbcUtil.finishTransaction(management);
		if(management.isException()){
			throw management.crateJoinedException();
		}
		
		return user;
		
	}
	
	public void updateUser(UserEntity user) throws Exception{
		
		JdbcTransManagement management = JdbcUtil.beginTransaction();
		
		try{
			
			UserDao userDao = new UserDao(management.getConn());
			userDao.updateUser(user);
			
		}catch (Exception e){
			management.addException(e);
		}
			
		JdbcUtil.finishTransaction(management);
		if(management.isException()){
			throw management.crateJoinedException();
		}
						
	}
	
	public void deleteUser(Long id) throws Exception{
		
		JdbcTransManagement management = JdbcUtil.beginTransaction();
		
		try{
			
			UserDao userDao = new UserDao(management.getConn());
			userDao.deleteUser(id);
			
		}catch (Exception e){
			management.addException(e);
		}
			
		JdbcUtil.finishTransaction(management);
		if(management.isException()){
			throw management.crateJoinedException();
		}
				
	}
	
	public List<UserEntity> getUsers() throws Exception{
		
		List<UserEntity> users = new ArrayList<UserEntity>();
		JdbcTransManagement management = JdbcUtil.beginTransaction();
		
		try{
			
			UserDao userDao = new UserDao(management.getConn());
			users = userDao.getUsers();
			
			
		}catch (Exception e){
			management.addException(e);
		}
			
		JdbcUtil.finishTransaction(management);
		if(management.isException()){
			throw management.crateJoinedException();
		}
		
		return users;
		
	}

}
