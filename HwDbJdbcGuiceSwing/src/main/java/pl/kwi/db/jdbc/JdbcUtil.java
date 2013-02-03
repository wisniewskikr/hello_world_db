package pl.kwi.db.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.util.Properties;

public class JdbcUtil {
	
	
	public final static String PROPS_MAIN_FILE_NAME = "project.properties";
	public final static String PROP_MAIN_DB_URL = "main.db.url";
	public final static String PROP_MAIN_DB_DRIVER = "main.db.driver";
	public final static String PROP_MAIN_DB_USERNAME = "main.db.username";
	public final static String PROP_MAIN_DB_PASSWORD = "main.db.password";
	private static Properties props;
	
	
	static {
		
		try {
			props = new Properties();
			props.load(JdbcUtil.class.getResourceAsStream("/" + PROPS_MAIN_FILE_NAME));
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}
	
	
	public static JdbcTransManagement beginTransaction(){
		
		JdbcTransManagement management = new JdbcTransManagement();
		
		try {
			openConnection(management);
		} catch (Exception e) {
			management.addException(e);
		}
		
		return management;
	}
	
	public static void finishTransaction(JdbcTransManagement management) {
		
		if(management.isException()){
			rollbackConnection(management);
		}else {
			commitConnection(management);
		}
		
		closeConnection(management);		
	}
	
	// ************************************************************************************************************ //
	// *********************************************** HELP METHODS *********************************************** //
	// ************************************************************************************************************ //
	
	public static Connection beginConncetion(){
		
		try {
			String dbUrl = props.getProperty(PROP_MAIN_DB_URL);
			String dbDriver = props.getProperty(PROP_MAIN_DB_DRIVER);
			String dbUser = props.getProperty(PROP_MAIN_DB_USERNAME);
			String dbPassword = props.getProperty(PROP_MAIN_DB_PASSWORD);
			
			Class.forName(dbDriver).newInstance();
			return DriverManager.getConnection(dbUrl, dbUser, dbPassword);
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
		
	}
	
	private static void openConnection(JdbcTransManagement management) throws Exception{
				
		Connection conn = beginConncetion();
		conn.setAutoCommit(false);
		management.setConn(conn);
		
		
	}
	
	private static void commitConnection(JdbcTransManagement management){
		try {
			management.getConn().commit();
		} catch (Exception e) {
			management.addException(e);
		}
	}
	
	private static void rollbackConnection(JdbcTransManagement management){
		try {
			management.getConn().rollback();
		} catch (Exception e) {
			management.addException(e);
		}
	}
	
	private static void closeConnection(JdbcTransManagement management){
		try {
			if(management.getConn() != null){
				management.getConn().close();
			}
		} catch (Exception e) {
			management.addException(e);
		}
	}
	
}
