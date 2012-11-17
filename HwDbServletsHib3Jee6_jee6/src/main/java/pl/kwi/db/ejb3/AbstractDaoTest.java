package pl.kwi.db.ejb3;


import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import org.dbunit.database.IDatabaseConnection;
import org.junit.After;
import org.junit.Before;

public abstract class AbstractDaoTest {
	
	
	private static EntityManager em;
	private static EntityTransaction tx;
	private static IDatabaseConnection dbUnitConnection;
	
	
	public static void openTestConnections(String persistenceUnitName) throws Exception{
		em = JPAUtil.createEntityManager(persistenceUnitName);
		dbUnitConnection = DBUnitUtil.createConnection(em);
	}
	
	public static void closeTestConnections(){
		DBUnitUtil.closeConnection(dbUnitConnection);
		JPAUtil.closeEntityManager(em);
	}
	
	@Before
	public void beforeEveryMethod(){
		tx = JPAUtil.beginTransaction(em);
	}
	
	@After
	public void afterEveryMethod(){
		JPAUtil.commitTransaction(tx);
	}	
	
	public void executeDataFile(String path){
		DBUnitUtil.executeDataFile(path, dbUnitConnection);
	}
	
	public static EntityManager getEm(){
		return em;
	}	

}
