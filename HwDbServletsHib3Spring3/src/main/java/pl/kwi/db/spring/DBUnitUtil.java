package pl.kwi.db.spring;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import javax.persistence.EntityManager;

import junit.framework.Assert;

import org.dbunit.database.DatabaseConnection;
import org.dbunit.database.IDatabaseConnection;
import org.dbunit.dataset.IDataSet;
import org.dbunit.dataset.xml.FlatXmlDataSet;
import org.dbunit.operation.DatabaseOperation;
import org.hibernate.impl.SessionImpl;

public class DBUnitUtil {
	
	
	public static IDatabaseConnection createConnection() {

		try {
			String dbDriver = System.getProperty("dbunit.db.driver");
			String dbUrl = System.getProperty("dbunit.db.url");
			String dbUser = System.getProperty("dbunit.db.username");
			String dbPassword = System.getProperty("dbunit.db.password");
			
			Class.forName(dbDriver).newInstance();
			Connection conn = DriverManager.getConnection(dbUrl, dbUser, dbPassword);
			return new DatabaseConnection(conn);
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
		
	}
	
	public static IDatabaseConnection createConnection(Connection conn) {

		try {
			return new DatabaseConnection(conn);
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
		
	}
	
	public static IDatabaseConnection createConnection(EntityManager em) {

		try {
			SessionImpl session = (SessionImpl) em.getDelegate();
			Connection jdbcConn = session.connection();
			return new DatabaseConnection(jdbcConn);
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
		
	}
	
	public static void closeConnection(IDatabaseConnection conn){
		
		if(conn != null){
			try {
				conn.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
	}
	
	public static void executeDataFile(String path, IDatabaseConnection conn) {

		try {
			IDataSet dataSet = new FlatXmlDataSet(DBUnitUtil.class
					.getResourceAsStream(path));

			Assert.assertNotNull(conn);
			Assert.assertNotNull(dataSet);

			DatabaseOperation.CLEAN_INSERT.execute(conn, dataSet);

		} catch (Exception e) {
			e.printStackTrace();
		}

	}
	

}
