package pl.kwi.daos;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.classic.Session;

import pl.kwi.db.hib3.AbstractDao;
import pl.kwi.entities.UserEntity;

public class UserDao extends AbstractDao<UserEntity> {
	
	
	public UserDao(Session session){
		super(session);		
	}
	

	public List<UserEntity> getAllUserList(){
		
		Query q = getSession().getNamedQuery("UserEntity.getAll");
		return q.list();
		
	}
	
}
