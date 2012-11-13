package pl.kwi.daos;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import pl.kwi.db.jpa.AbstractDao;
import pl.kwi.entities.UserEntity;

public class UserDao extends AbstractDao<UserEntity> {
	
	
	public UserDao(EntityManager em){
		super(em);		
	}
	

	public List<UserEntity> getAllUserList(){
		
		Query q = getEm().createNamedQuery("UserEntity.getAll");
		return q.getResultList();
		
	}
	
}
