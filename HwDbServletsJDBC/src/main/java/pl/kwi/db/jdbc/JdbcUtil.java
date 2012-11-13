package pl.kwi.db.jdbc;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Properties;

public class JdbcUtil {

	
	private static String DB_DRIVER = null;
	private static String DB_URL = null;
	private static String DB_USERNAME = null;
	private static String DB_PASSWORD = null;
	
	
	public static Connection createConnection(String jdbcPropertiesPath) throws Exception{
			
		loadProperties(jdbcPropertiesPath);
		
		Class.forName(DB_DRIVER);
		
		return DriverManager
		          .getConnection(DB_URL, DB_USERNAME, DB_PASSWORD);
		
	}
	
	public static void closeConnection(Connection conn){
		
		if(conn != null){
			try {
				conn.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
	}	
	
	public static void closeResultSet(ResultSet rs) throws Exception{
		
		if (rs != null) {
	        rs.close();
	     }
		
	}
	
	public static void closePreparedStatement(PreparedStatement ps) throws Exception{
		
		if (ps != null) {
	        ps.close();
	      }
		
	}
	
	public static void closeResultSetAndPrepStat(ResultSet rs, PreparedStatement ps) throws Exception{
		
		if (rs != null) {
	        rs.close();
	     }
		
		if (ps != null) {
	        ps.close();
	      }
		
	}
	
	private static void loadProperties(String jdbcPropertiesPath){
		
		InputStream is = JdbcUtil.class.getResourceAsStream(jdbcPropertiesPath);
		Properties prop = new Properties();
		try {
			prop.load(is);
		} catch (IOException e) {
			e.printStackTrace();
			return;
		}
		DB_DRIVER = prop.getProperty("db.driver");
		DB_URL = prop.getProperty("db.url");
		DB_USERNAME = prop.getProperty("db.username");
		DB_PASSWORD = prop.getProperty("db.password");
		
	}

}
