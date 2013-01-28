package pl.kwi.daos;

import org.springframework.stereotype.Repository;

import pl.kwi.db.spring.AbstractDao;
import pl.kwi.entities.UserEntity;

@Repository
public class UserDao extends AbstractDao<UserEntity> {
	
	
	public UserDao(){
		setClazz(UserEntity.class);		
	}
	
}
