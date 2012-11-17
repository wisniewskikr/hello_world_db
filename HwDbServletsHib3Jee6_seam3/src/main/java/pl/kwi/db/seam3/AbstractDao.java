package pl.kwi.db.seam3;

import javax.enterprise.context.Dependent;
import javax.inject.Named;
import javax.persistence.EntityManager;

import org.jboss.seam.transaction.TransactionPropagation;
import org.jboss.seam.transaction.Transactional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Named
@Dependent
public abstract class AbstractDao<T extends AbstractEntity> {
	
	
	private static Logger LOG = LoggerFactory.getLogger(AbstractDao.class);

	
	@Transactional(TransactionPropagation.REQUIRED)
	public Long create(T entity) {		
		getEm().persist(entity);
		return entity.getId();
	}
	
	public T read(long id, Class<? extends AbstractEntity> entityClass){
		return (T)getEm().find(entityClass, id);
	}
	
	@Transactional(TransactionPropagation.REQUIRED)
	public T update(T entity){		
		return getEm().merge(entity);
	}
	
	@Transactional(TransactionPropagation.REQUIRED)
	public void delete(T entity){
		entity = getEm().merge(entity);
		getEm().remove(entity);
	}
	
	public abstract EntityManager getEm();
	
	public abstract void setEm(EntityManager em);
	

}
