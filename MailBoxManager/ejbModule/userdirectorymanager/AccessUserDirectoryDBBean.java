package userdirectorymanager;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.TypedQuery;

import userdirectory.User;

/**
 * Session Bean implementation class DirectoryManager
 */
@Stateless
public class AccessUserDirectoryDBBean implements AccessUserDirectoryDB
{
	@PersistenceContext(unitName="pu1")
	private EntityManager em;

	@Override
	public int lookupAUserUserRightRb(String name) 
	{
		 String ejbQL = "select uright from utilisateur where username = ?1";
		 Query query = em.createNativeQuery(ejbQL);

		 query.setParameter(1, name);  
		 return (Integer) query.getSingleResult();
	}
	
	@Override
	public int lookupAUserNewsRightRb(String name) 
	{
		 String ejbQL = "select newsright from utilisateur where username = ?1";
		 Query query = em.createNativeQuery(ejbQL);

		 query.setParameter(1, name);  
		 return (Integer) query.getSingleResult();
	}
	
	@Override
	public void addUserRb(User user) 
	{
		em.persist(user);
	}
	
	@Override
	public void updateAUserUserRightRb(String name, int level) 
	{
		 User user = this.getUserByName(name);
		 
		 user.setUserRight(level);
	}

	@Override
	public void updateAUserNewsRightRb(String name, int level)
	{
		 User user = this.getUserByName(name);
		 
		 user.setNewsRight(level);
	}
	
	@Override
	public int getMaxIdUser() 
	{
		User user = em.find(User.class, 1);
		if(user==null)
		{
			return 0;
		}
		else
		{
			Query q = em.createQuery("select max(u.id) from User u");
			return (Integer) q.getSingleResult();
		}
	}
	
	@Override
	public User getUserByName(String name)
	{
		Query q = em.createQuery("select u from User u where u.userName = :name");
		q.setParameter("name", name);
		User user = (User) q.getSingleResult();
		
		return user;
	}
	
	@Override
	public List<User> lookupAllUsersRb()
	{
	    TypedQuery<User> query = em.createQuery("SELECT c FROM User c", User.class);

	    List<User> resultList = query.getResultList(); 
	    return resultList;
	}
	
	@Override
	public void removeUserRb(String name) 
	{
	    User user = this.getUserByName(name);
	    User u =em.merge(user);
	    em.remove(u);
	}
}