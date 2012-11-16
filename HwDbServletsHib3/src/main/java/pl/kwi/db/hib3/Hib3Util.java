package pl.kwi.db.hib3;
	
import org.hibernate.SessionFactory;
import org.hibernate.cfg.AnnotationConfiguration;
import org.hibernate.classic.Session;
 
public class Hib3Util {
    
	public static Session createSession(String hibernateCfgPath){	
		return buildSessionFactory(hibernateCfgPath).getCurrentSession();
	}
	
	public static void closeSession(Session session){
		session.close();
	}
	
	public static void beginTransaction(Session session){
		session.beginTransaction();
	}
	
	public static void commitTransaction(Session session){
		session.getTransaction().commit();
	}
	
    public static SessionFactory buildSessionFactory(String hibernateCfgPath) {
        try {
            return new AnnotationConfiguration().configure(hibernateCfgPath)
                    .buildSessionFactory();
        } catch (Throwable ex) {
            System.err.println("Initial SessionFactory creation failed." + ex);
            throw new ExceptionInInitializerError(ex);
        }
    }
    
}


