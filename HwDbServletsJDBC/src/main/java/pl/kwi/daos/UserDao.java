package pl.kwi.daos;

import java.sql.Connection;
import java.util.ArrayList;
import java.util.List;

import com.mysql.jdbc.Statement;

import pl.kwi.db.jdbc.AbstractDao;
import pl.kwi.db.jdbc.JdbcUtil;
import pl.kwi.entities.UserEntity;

public class UserDao extends AbstractDao {
	
	
	public UserDao(Connection conn){
		super(conn);
	}
	
	
	public void createUser(UserEntity user) throws Exception{
				
		String sql = "INSERT INTO users(name) VALUES(?)";
		ps = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
		ps.setString(1, user.getName());
	    ps.executeUpdate();
	    rs = ps.getGeneratedKeys();
	    
	    while(rs.next()){
	    	user.setId(rs.getLong(1));
	    }
	    
		JdbcUtil.closeResultSetAndPrepStat(rs, ps);
	    
	}
	
	public UserEntity readUser(Long id) throws Exception{
		
		UserEntity user = new UserEntity();
		
		String sql = "SELECT id, name FROM users WHERE id = ?";
		ps = conn.prepareStatement(sql);
		ps.setLong(1, id);
		rs = ps.executeQuery();
		
		while(rs.next()){
			user.setId(rs.getLong(1));
			user.setName(rs.getString(2));
		}
		
		JdbcUtil.closeResultSetAndPrepStat(rs, ps);
		return user;
		
	}
	
	public void updateUser(UserEntity user) throws Exception{
		
		String sql = "UPDATE users SET name = ? WHERE id = ?";
		ps = conn.prepareStatement(sql);
		ps.setString(1, user.getName());
		ps.setLong(2, user.getId());
	    ps.executeUpdate();
	    JdbcUtil.closePreparedStatement(ps);
		
	}
	
	public void deleteUser(Long id) throws Exception {
		
		String sql = "DELETE FROM users WHERE id = ?";
		ps = conn.prepareStatement(sql);
		ps.setLong(1, id);
	    ps.executeUpdate();
	    JdbcUtil.closePreparedStatement(ps);
		
	}
	
	public List<UserEntity> getUsers() throws Exception{
		
		List<UserEntity> users = new ArrayList<UserEntity>();
		UserEntity user = null;
		
		String sql = "SELECT * FROM users";
		ps = conn.prepareStatement(sql);
		rs = ps.executeQuery();
		
		while(rs.next()){
			user = new UserEntity();
			user.setId(rs.getLong(1));
			user.setName(rs.getString(2));
			users.add(user);
		}
		
		JdbcUtil.closeResultSetAndPrepStat(rs, ps);
		return users;
		
	}

}
