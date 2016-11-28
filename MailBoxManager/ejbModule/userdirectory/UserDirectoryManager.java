package userdirectory;

import java.util.List;

import javax.naming.InitialContext;
import javax.naming.NamingException;

import mailbox.MailBoxManager;
import userdirectorymanager.AccessUserDirectoryDB;

public class UserDirectoryManager
{	
	private static AccessUserDirectoryDB man = null;
	
	static
	{
		try 
		{
			InitialContext ic = new InitialContext();
			man = (AccessUserDirectoryDB) ic.lookup(userdirectorymanager.AccessUserDirectoryDB.class.getName());
		} 
		catch (NamingException e) 
		{
				e.printStackTrace();
		}
	}
	
	public static void addUser(User fuser) 
	{
		int id_user = man.getMaxIdUser()+1;
		fuser.setId(id_user);
		
		man.addUserRb(fuser);
		MailBoxManager.addMailBox(fuser);
	}

	public static void removeUser(String name) 
	{
		MailBoxManager.removeMailBox(name+"@gmail.com");
		man.removeUserRb(name);
	}
	
	public static boolean isUserAlreadyExist(String username)
	{
		boolean aluser = false;
		List<User> models = man.lookupAllUsersRb();
		
		if(models != null)
		{
			for(User u : models)
			{
				if(username.equals(u.getUserName()))
				{
					aluser = true;
					break;
				}
			}
		}
		
		return aluser;
	}

	public static void lookupAllUsers() 
	{
		List<User> models = man.lookupAllUsersRb();
		for(User model : models) 
		{
				System.out.print("id:");
				System.out.println(model.getId());
				System.out.print("name:");
	            System.out.println(model.getUserName());
	            System.out.print("uright:");
	            System.out.println(model.getUserRight());
	            System.out.print("newsright:");
	            System.out.println(model.getNewsRight());
	     }
	}

	public static int lookupAUserUserRight(String name) 
	{
		return man.lookupAUserUserRightRb(name);
	}

	public static int lookupAUserNewsRight(String name) 
	{
		return man.lookupAUserNewsRightRb(name);
	}
	
	public static void updateAUserUserRight(String name, int right)
	{
		man.updateAUserUserRightRb(name, right);
	}
	
	public static void updateAUserNewsRight(String name, int right)
	{
		man.updateAUserNewsRightRb(name, right);
	}
	
	public static User getUserByName(String name)
	{
		return man.getUserByName(name);
	}
}