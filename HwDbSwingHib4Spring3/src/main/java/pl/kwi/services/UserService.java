package pl.kwi.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import pl.kwi.daos.UserDao;
import pl.kwi.entities.UserEntity;

@Service
@Transactional
public class UserService {

	@Autowired
	private UserDao userDao;
	
	public Long createUser(UserEntity user){
		
		userDao.create(user);
		return user.getId();
		
	}
	
	public UserEntity readUser(Long id){
		
		return userDao.findOne(id);
		
	}
	
	public void updateUser(UserEntity user){
		
		userDao.update(user);
		
	}
	
	public void deleteUser(UserEntity user){
		
		userDao.delete(user);
		
	}
	
	public List<UserEntity> getUserList(){
		
		return userDao.findAll();
		
	}

}
