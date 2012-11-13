package pl.kwi.daos;

import org.springframework.stereotype.Repository;

import pl.kwi.db.spring.AbstractHibernateTemplateDao;
import pl.kwi.entities.UserEntity;

@Repository
public class UserDao extends AbstractHibernateTemplateDao<UserEntity> {
	
	
	public UserDao(){
		setClazz(UserEntity.class);		
	}
	
}
