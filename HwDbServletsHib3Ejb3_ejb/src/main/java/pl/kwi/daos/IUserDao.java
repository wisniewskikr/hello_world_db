package pl.kwi.daos;

import java.util.List;

import javax.ejb.Local;

import pl.kwi.db.ejb3.AbstractEntity;
import pl.kwi.entities.UserEntity;

@Local
public interface IUserDao {

	public List<UserEntity> getAllUserList();
	
	public Long create(UserEntity user);
	
	public UserEntity read(long id, Class<? extends AbstractEntity> clazz);
	
	public UserEntity update(UserEntity user);
	
	public void delete(UserEntity user);

}