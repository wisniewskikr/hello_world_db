package pl.kwi.db.hib3;

import org.hibernate.classic.Session;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public abstract class AbstractDao<T extends AbstractEntity> {
	
	
	private static Logger LOG = LoggerFactory.getLogger(AbstractDao.class);
	private Session session;
	
	
	public AbstractDao(Session session){
		this.session = session;
	}
	
	
	public Long create(T entity) {		
		getSession().persist(entity);
		return entity.getId();
	}
	
	public T read(long id, Class<? extends AbstractEntity> entityClass){
		return (T)getSession().get(entityClass, id);
	}
	
	public T update(T entity){		
		getSession().update(entity);
		return entity;
	}
	
	public void delete(T entity){
		getSession().delete(entity);
	}
	
	// **************************************** //
	// *********** GETTERS AND SETTERS ******** //
	// **************************************** //
	
	public Session getSession() {
		return session;
	}


}
