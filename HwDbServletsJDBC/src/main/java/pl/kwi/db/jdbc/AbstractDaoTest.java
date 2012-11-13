package pl.kwi.db.jdbc;


import java.sql.Connection;

import org.dbunit.database.IDatabaseConnection;

public abstract class AbstractDaoTest {
	
	
	private static Connection jdbcConnection;
	private static IDatabaseConnection dbUnitConnection;
	
	
	public static void openTestConnections(String jdbcPropertiesPath) throws Exception{
		jdbcConnection = JdbcUtil.createConnection(jdbcPropertiesPath);
		dbUnitConnection = DBUnitUtil.createConnection(jdbcConnection);
	}
	
	public static void closeTestConnections(){
		DBUnitUtil.closeConnection(dbUnitConnection);
		JdbcUtil.closeConnection(jdbcConnection);
	}
	
	
	public void executeDataFile(String path){
		DBUnitUtil.executeDataFile(path, dbUnitConnection);
	}
	
	public static Connection getConn(){
		return jdbcConnection;
	}	
	

}
