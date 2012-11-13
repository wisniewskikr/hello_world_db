package pl.kwi.services;

import java.util.List;

import javax.ejb.EJB;
import javax.ejb.Stateless;

import pl.kwi.daos.IUserDao;
import pl.kwi.entities.UserEntity;

@Stateless
public class UserService implements IUserService {

	@EJB
	private IUserDao dao;
	
	/* (non-Javadoc)
	 * @see pl.kwi.services.IUserService#createUser(pl.kwi.entities.UserEntity)
	 */
	public Long createUser(UserEntity user){		
		Long id;
		id = dao.create(user);
		return id;		
	}
	
	/* (non-Javadoc)
	 * @see pl.kwi.services.IUserService#readUser(java.lang.Long)
	 */
	public UserEntity readUser(Long id){		
		UserEntity entity;
		entity = dao.read(id, UserEntity.class);
		return entity;		
	}
	
	/* (non-Javadoc)
	 * @see pl.kwi.services.IUserService#updateUser(pl.kwi.entities.UserEntity)
	 */
	public void updateUser(UserEntity user){
		dao.update(user);
	}
	
	/* (non-Javadoc)
	 * @see pl.kwi.services.IUserService#deleteUser(pl.kwi.entities.UserEntity)
	 */
	public void deleteUser(UserEntity user){
		dao.delete(user);
	}
	
	/* (non-Javadoc)
	 * @see pl.kwi.services.IUserService#getUserList()
	 */
	public List<UserEntity> getUserList(){
		return dao.getAllUserList();
	}

}
