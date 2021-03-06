package pl.kwi.db.seam3;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

public class JPAUtil {
	
	public static EntityManager createEntityManager(String persistenceUnitName){		
		return Persistence.createEntityManagerFactory(persistenceUnitName).createEntityManager();		
	}
	
	public static void closeEntityManager(EntityManager em){
		em.close();
	}
	
	public static EntityTransaction beginTransaction(EntityManager em){
		EntityTransaction transaction = em.getTransaction();
		transaction.begin();
		return transaction;
	}
	
	public static void commitTransaction(EntityTransaction transaction){
		transaction.commit();
	}

}
