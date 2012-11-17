package pl.kwi.db.ejb3;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public abstract class AbstractDao<T extends AbstractEntity> {
	
	
	private static Logger LOG = LoggerFactory.getLogger(AbstractDao.class);
	@PersistenceContext
	private EntityManager em;
	
	
	public Long create(T entity) {		
		getEm().persist(entity);
		return entity.getId();
	}
	
	public T read(long id, Class<? extends AbstractEntity> entityClass){
		return (T)getEm().find(entityClass, id);
	}
	
	public T update(T entity){		
		return getEm().merge(entity);
	}
	
	public void delete(T entity){
		entity = getEm().merge(entity);
		getEm().remove(entity);
	}
	
	// **************************************** //
	// *********** GETTERS AND SETTERS ******** //
	// **************************************** //
	
	public EntityManager getEm() {
		return em;
	}
	public void setEm(EntityManager em) {
		this.em = em;
	}
	

}
