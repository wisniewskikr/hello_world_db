package pl.kwi.daos;

import java.util.List;

import javax.enterprise.context.RequestScoped;
import javax.enterprise.inject.Produces;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.PersistenceUnit;
import javax.persistence.Query;

import org.jboss.seam.solder.core.ExtensionManaged;

import pl.kwi.db.seam3.AbstractDao;
import pl.kwi.entities.UserEntity;

public class UserDao extends AbstractDao<UserEntity> {
	
	
	@ExtensionManaged
    @Produces
    @PersistenceUnit(unitName="pu")
    @RequestScoped    
    public EntityManagerFactory entityManagerFactory;
	
	@Inject
	public EntityManager em;
	

	/* (non-Javadoc)
	 * @see pl.kwi.daos.IUserDao#getAllUserList()
	 */
	public List<UserEntity> getAllUserList(){
		
		Query q = getEm().createNamedQuery("UserEntity.getAll");
		return q.getResultList();
		
	}
	
	@Override
	public EntityManager getEm() {
		return em;
	}

	@Override
	public void setEm(EntityManager em) {
		this.em = em;		
	}

	
}
