package pl.kwi.services;

import java.sql.Connection;
import java.util.List;

import pl.kwi.daos.UserDao;
import pl.kwi.db.jdbc.JdbcUtil;
import pl.kwi.entities.UserEntity;


public class UserService {
	
	public void createUser(UserEntity user) throws Exception{
		
		Connection conn = JdbcUtil.createConnection("/jdbc.properties");
				
		UserDao dao = new UserDao(conn);
		dao.createUser(user);
		
		JdbcUtil.closeConnection(conn);
		
	}
	
	public UserEntity readUser(Long id) throws Exception{
		
		UserEntity user;
		
		Connection conn = JdbcUtil.createConnection("/jdbc.properties");
				
		UserDao dao = new UserDao(conn);
		user = dao.readUser(id);
		
		JdbcUtil.closeConnection(conn);
		
		return user;
		
	}
	
	public void updateUser(UserEntity user) throws Exception{
		
		Connection conn = JdbcUtil.createConnection("/jdbc.properties");
				
		UserDao dao = new UserDao(conn);
		dao.updateUser(user);
		
		JdbcUtil.closeConnection(conn);
		
	}
	
	public void deleteUser(Long id) throws Exception{
		
		Connection conn = JdbcUtil.createConnection("/jdbc.properties");
				
		UserDao dao = new UserDao(conn);
		dao.deleteUser(id);
		
		JdbcUtil.closeConnection(conn);
		
	}
	
	public List<UserEntity> getUsers() throws Exception{
		
		List<UserEntity> users;
		
		Connection conn = JdbcUtil.createConnection("/jdbc.properties");
				
		UserDao dao = new UserDao(conn);
		users = dao.getUsers();
		
		JdbcUtil.closeConnection(conn);
		
		return users;
		
	}

}
