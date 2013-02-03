package pl.kwi.daos;

import java.sql.Connection;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import pl.kwi.db.jdbc.AbstractDao;
import pl.kwi.entities.UserEntity;

public class UserDao extends AbstractDao implements IUserDao {
	
	
	public UserDao(){
		super(null);
	}
	public UserDao(Connection conn){
		super(conn);
	}
	
	
	/* (non-Javadoc)
	 * @see pl.kwi.daos.IUserDao#createUser(pl.kwi.entities.UserEntity)
	 */
	public void createUser(UserEntity user) throws Exception{
				
		String sql = "INSERT INTO users(name) VALUES(?)";
		ps = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
		ps.setString(1, user.getName());
	    ps.executeUpdate();
	    rs = ps.getGeneratedKeys();
	    
	    while(rs.next()){
	    	user.setId(rs.getLong(1));
	    }
	    
	    rs.close();
		ps.close();
	    
	}
	
	/* (non-Javadoc)
	 * @see pl.kwi.daos.IUserDao#readUser(java.lang.Long)
	 */
	public UserEntity readUser(Long id) throws Exception{
		
		UserEntity user = null;
		
		String sql = "SELECT id, name FROM users WHERE id = ?";
		ps = conn.prepareStatement(sql);
		ps.setLong(1, id);
		rs = ps.executeQuery();
		
		while(rs.next()){
			user = new UserEntity();
			user.setId(rs.getLong(1));
			user.setName(rs.getString(2));
		}
		
		rs.close();
		ps.close();
		
		return user;
		
	}
	
	/* (non-Javadoc)
	 * @see pl.kwi.daos.IUserDao#updateUser(pl.kwi.entities.UserEntity)
	 */
	public void updateUser(UserEntity user) throws Exception{
		
		String sql = "UPDATE users SET name = ? WHERE id = ?";
		ps = conn.prepareStatement(sql);
		ps.setString(1, user.getName());
		ps.setLong(2, user.getId());
	    ps.executeUpdate();
		ps.close();
		
	}
	
	/* (non-Javadoc)
	 * @see pl.kwi.daos.IUserDao#deleteUser(java.lang.Long)
	 */
	public void deleteUser(Long id) throws Exception {
		
		String sql = "DELETE FROM users WHERE id = ?";
		ps = conn.prepareStatement(sql);
		ps.setLong(1, id);
	    ps.executeUpdate();
		ps.close();
		
	}
	
	/* (non-Javadoc)
	 * @see pl.kwi.daos.IUserDao#getUsers()
	 */
	public List<UserEntity> getUsers() throws Exception{
		
		List<UserEntity> users = new ArrayList<UserEntity>();
		UserEntity user = null;
		
		String sql = "SELECT id, name FROM users";
		ps = conn.prepareStatement(sql);
		rs = ps.executeQuery();
		
		while(rs.next()){
			user = new UserEntity();
			user.setId(rs.getLong(1));
			user.setName(rs.getString(2));
			users.add(user);
		}
		
		rs.close();
		ps.close();
		
		return users;
		
	}
	
	@Override
	public void setConn(Connection conn) {
		this.conn = conn;		
	}

}
