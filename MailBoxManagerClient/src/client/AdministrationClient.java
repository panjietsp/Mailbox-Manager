package client;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import mailbox.MailBoxManager;
import userdirectory.User;
import userdirectory.UserDirectoryManager;

public class AdministrationClient 
{
	BufferedReader brConsoleReader = null;

	public static void main(String[] args) 
	{
		MailBoxManager.createNewsBox();
		AdministrationClient controlAdministration = new AdministrationClient();

		controlAdministration.workflow();
	}

	private void showGUI() 
	{
		System.out.println("**********************");
		System.out.println("Welcome to The Directoty Manager Session");
		System.out.println("**********************");
		System.out.print(
				"Options \n1. Create User\n2. Delete User \n3. List All Users \n4. Update User Rights \n5. Lookup User Rights \n6. Exit \nEnter Choice: ");
	}
	
	private void waitEnter() 
	{
		System.out.println("Press Enter to continue.");
		try
		{
			System.in.read();
			}
		
		catch(Exception e)
		{
			e.printStackTrace();
		}
	}
	
	private void path1() 
	{
		User fu = new User();
		System.out.println("Please fill in the user info:");

		System.out.println("name:");
		try 
		{
			String strname = brConsoleReader.readLine();
			fu.setUserName(strname);
		}
		catch (IOException e) 
		{
			e.printStackTrace();
		}

		System.out.println("uright:");
		try 
		{
			String struright = brConsoleReader.readLine();
			fu.setUserRight(Integer.parseInt(struright));
		} 
		catch (IOException e) 
		{
			e.printStackTrace();
		}

		System.out.println("newsright:");
		try 
		{
			String strnright = brConsoleReader.readLine();
			fu.setNewsRight(Integer.parseInt(strnright));
		} 
		catch (IOException e) 
		{
			e.printStackTrace();
		}
		
		if(UserDirectoryManager.isUserAlreadyExist(fu.getUserName()))
		{
			System.out.println("This User already exist!");
		}
		else
		{
			UserDirectoryManager.addUser(fu);
			System.out.println("Successfully added!");
		}
		
		waitEnter();
	}

	private void path2() 
	{
		System.out.println("Please give the name of the user to be removed:");
		try 
		{
			String strname = brConsoleReader.readLine();
			UserDirectoryManager.removeUser(strname);
		} 
		catch (IOException e)
		{
			e.printStackTrace();
		}
		
		System.out.println("Successfully removed!");
		waitEnter();
	}

	private void path3() 
	{
		System.out.println("The list of all users:");
		UserDirectoryManager.lookupAllUsers();
		waitEnter();
	}

	private void path4() 
	{
		String strchoice = "";
		String strlevel = "";
		String strname = "";
		System.out.println("1. UserRight \n2. NewsRight \nEnter choice: ");
		try 
		{
			strchoice = brConsoleReader.readLine();
		}
		catch (IOException e)
		{
			e.printStackTrace();
		}

		System.out.println("Set Right Level:");
		try 
		{
			strlevel = brConsoleReader.readLine();
		} 
		catch (IOException e) 
		{
			e.printStackTrace();
		}

		System.out.println("Please give the name of the user to be updated:");
		try {
			strname = brConsoleReader.readLine();
		} catch (IOException e) {
			e.printStackTrace();
		}

		if (Integer.parseInt(strchoice) == 1) {
			UserDirectoryManager.updateAUserUserRight(strname, Integer.parseInt(strlevel));
		} else {
			UserDirectoryManager.updateAUserNewsRight(strname, Integer.parseInt(strlevel));
		}
		
		System.out.println("Successfully updated!");
		waitEnter();
	}

	private void path5() {
		String strchoice = "";
		String strname = "";
		System.out.println("1. UserRight \n2. NewsRight \nEnter choice: ");
		try {
			strchoice = brConsoleReader.readLine();
		} catch (IOException e) {
			e.printStackTrace();
		}

		System.out.println("Please give the name of the user to be showed:");
		try 
		{
			strname = brConsoleReader.readLine();
		} 
		catch (IOException e)
		{
			e.printStackTrace();
		}

		if (Integer.parseInt(strchoice) == 1) 
		{
			System.out.println(UserDirectoryManager.lookupAUserUserRight(strname));
		}
		else 
		{
			System.out.println(UserDirectoryManager.lookupAUserNewsRight(strname));
		}
		
		waitEnter();
	}

	private void workflow() 
	{
		int choice = 0;

		while (choice != 6)
		{
			showGUI();
			try
			{
				brConsoleReader = new BufferedReader(new InputStreamReader(System.in));
				String strChoice = brConsoleReader.readLine();
				choice = Integer.parseInt(strChoice);
			}
			catch (IOException e) 
			{
				e.printStackTrace();
			}

			if (choice == 6)
				break;
			if (choice == 1)
				path1();
			if (choice == 2)
				path2();
			if (choice == 3)
				path3();
			if (choice == 4)
				path4();
			if (choice == 5)
				path5();
		}
	
		System.out.println("The session is closed.");
	}
}