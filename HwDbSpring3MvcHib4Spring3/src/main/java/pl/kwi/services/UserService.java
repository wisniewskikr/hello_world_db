package pl.kwi.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import pl.kwi.daos.UserDao;
import pl.kwi.entities.UserEntity;

@Service
public class UserService {

	@Autowired
	private UserDao userDao;
	
	@Transactional
	public Long createUser(UserEntity user){
		
		userDao.create(user);
		return user.getId();
		
	}
	
	@Transactional
	public UserEntity readUser(Long id){
		
		return userDao.findOne(id);
		
	}
	
	@Transactional
	public void updateUser(UserEntity user){
		
		userDao.update(user);
		
	}
	
	@Transactional
	public void deleteUser(UserEntity user){
		
		userDao.delete(user);
		
	}
	
	@Transactional
	public List<UserEntity> getUserList(){
		
		return userDao.findAll();
		
	}

}
