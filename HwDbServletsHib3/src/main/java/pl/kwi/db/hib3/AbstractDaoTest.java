package pl.kwi.db.hib3;


import org.dbunit.database.IDatabaseConnection;
import org.hibernate.classic.Session;

public abstract class AbstractDaoTest {
	
	
	private static IDatabaseConnection dbUnitConnection;

	
	public void executeDataFile(String path, Session s){
		dbUnitConnection = DBUnitUtil.createConnection(s.connection());
		DBUnitUtil.executeDataFile(path, dbUnitConnection);
		DBUnitUtil.closeConnection(dbUnitConnection);
	}


}
