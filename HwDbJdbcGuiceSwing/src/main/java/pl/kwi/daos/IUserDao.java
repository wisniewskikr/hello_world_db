package pl.kwi.daos;

import java.sql.Connection;
import java.util.List;

import com.google.inject.ImplementedBy;

import pl.kwi.entities.UserEntity;

@ImplementedBy(UserDao.class)
public interface IUserDao {

	public void setConn(Connection conn);
	
	public void createUser(UserEntity user) throws Exception;

	public UserEntity readUser(Long id) throws Exception;

	public void updateUser(UserEntity user) throws Exception;

	public void deleteUser(Long id) throws Exception;

	public List<UserEntity> getUsers() throws Exception;

}