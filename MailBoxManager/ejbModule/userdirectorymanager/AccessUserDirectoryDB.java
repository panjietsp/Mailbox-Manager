package userdirectorymanager;

import java.util.List;
import javax.ejb.Remote;

import userdirectory.User;

@Remote
public interface AccessUserDirectoryDB 
{
	public int getMaxIdUser();
	public User getUserByName(String name);
	
	public void addUserRb(User user);
	public void removeUserRb(String name);
	public List<User> lookupAllUsersRb();
	public int lookupAUserUserRightRb(String name);
	public void updateAUserUserRightRb(String name, int level);
	public int lookupAUserNewsRightRb(String name);
	public void updateAUserNewsRightRb(String name, int level);
}