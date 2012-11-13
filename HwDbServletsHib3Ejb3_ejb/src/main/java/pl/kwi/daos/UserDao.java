package pl.kwi.daos;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.Query;

import pl.kwi.db.ejb3.AbstractDao;
import pl.kwi.db.ejb3.AbstractEntity;
import pl.kwi.entities.UserEntity;

@Stateless
public class UserDao extends AbstractDao<UserEntity> implements IUserDao {

	/* (non-Javadoc)
	 * @see pl.kwi.daos.IUserDao#getAllUserList()
	 */
	public List<UserEntity> getAllUserList(){
		
		Query q = getEm().createNamedQuery("UserEntity.getAll");
		return q.getResultList();
		
	}

	
}
