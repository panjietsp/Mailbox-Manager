package client;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import mailbox.Mail;
import mailbox.MailBoxManager;
import userdirectory.User;
import userdirectory.UserDirectoryManager;

public class MailBoxClient 
{
	BufferedReader brConsoleReader = null; 
	private User user = null;
	
	public static void main(String[] args) 
	{
		MailBoxClient controlClient = new MailBoxClient();
		controlClient.init();
		controlClient.workflow();
	}
	
	private void init() 
	{
	     System.out.print("Please input your name :");
	     
	     try 
		 {
			brConsoleReader = new BufferedReader(new InputStreamReader(System.in));
			String username = brConsoleReader.readLine();
			
			if(UserDirectoryManager.isUserAlreadyExist(username))
			{
				user = UserDirectoryManager.getUserByName(username);
			}
			else
			{
				System.out.println("This user is not exist!");
				System.exit(0);
			}
		 } 
		 catch (IOException e)
		 {
			e.printStackTrace();
		 }
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
			
		}
	}
	
	private void showGUI() 
	{
	     System.out.println("**********************");
	     System.out.println("Welcome to The Mailbox Manager Session : "+user.getUserName());
	     System.out.println("**********************");
	     System.out.print("Options \n1. Inbox\n2. Send a message\n3. Read NewsBox \n4. Send a message to NewsBox \n5. Exit \nEnter Choice: ");
	}
	
	private void path1()
	{
		user.listAllMessages();
		
		String strchoice = "";
		int id=0;
		System.out.println("\n1. Read a message \n2. Delete a message\n3. List all messages\n4. Delete all messages\n5. Read all new messages\n6. Delete all Readed Messages\nEnter choice: ");
		try
		{
			strchoice = brConsoleReader.readLine();
		}
		catch (IOException e) 
		{
			e.printStackTrace();
		}
		
		if("1".equals(strchoice))
		{
			System.out.println("Input id of the message you want to read : ");
			try
			{
				String i = brConsoleReader.readLine();
				id = Integer.parseInt(i);
			}
			catch (IOException e) 
			{
				e.printStackTrace();
			}
			user.readAMessage(id);
			waitEnter();
		}
		
		if("2".equals(strchoice))
		{
			System.out.println("Input id of the message you want to delete : ");
			try
			{
				String i = brConsoleReader.readLine();
				id = Integer.parseInt(i);
			}
			catch (IOException e) 
			{
				e.printStackTrace();
			}
			user.deleteAMessage(id);
			System.out.println("Successfully delete this message!");
			waitEnter();
		}
		
		if("3".equals(strchoice))
		{
			user.listAllMessages();
			waitEnter();
		}
		
		if("4".equals(strchoice))
		{
			user.deleteAllMessages();
			System.out.println("Successfully delete all messages!");
			waitEnter();
		}
		
		if("5".equals(strchoice))
		{
			user.readNewMessages();
			waitEnter();
		}
		
		if("6".equals(strchoice))
		{
			user.deleteReadMessage();
			System.out.println("Successfully delete readed messages!");
			waitEnter();
		}
	}
	
	public void path2()
	{
		String subject="";
		String body="";
		String desti="";
		
		System.out.println("Input subject : ");
		try
		{
			subject = brConsoleReader.readLine();
		}
		catch (IOException e) 
		{
			e.printStackTrace();
		}
		
		System.out.println("Input body : ");
		try
		{
			body = brConsoleReader.readLine();
		}
		catch (IOException e) 
		{
			e.printStackTrace();
		}
		
		System.out.println("Input receiver : ");
		try
		{
			desti = brConsoleReader.readLine(); 
		}
		catch (IOException e) 
		{
			e.printStackTrace();
		}
		
		Mail mail = new Mail();
		mail.setSubject(subject);
		mail.setBody(body);
		
		if(MailBoxManager.isMailBoxExist(desti))
		{
			user.sendAMessage(desti, mail);
			System.out.println("Successfully sent this message!");
		}
		else
		{
			System.out.println("This address mail isn't exist!");
		}
		
		waitEnter();
	}
	
	public void path3()
	{
		user.readNewsAllMessages();
		waitEnter();
	}
	
	public void path4() 
	{
		String subject="";
		String body="";
		String desti="NewsBox@gmail.com";
		
		System.out.println("Input subject : ");
		try
		{
			subject = brConsoleReader.readLine();
		}
		catch (IOException e) 
		{
			e.printStackTrace();
		}
		
		System.out.println("Input body : ");
		try
		{
			body = brConsoleReader.readLine();
		}
		catch (IOException e) 
		{
			e.printStackTrace();
		}
		
		Mail mail = new Mail();
		mail.setSubject(subject);
		mail.setBody(body);
		
		user.sendAMessage(desti, mail);
		waitEnter();
	}
	
	private void workflow()
	{
		int choice = 0;
		
		while(choice != 5)
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
			
			if(choice ==5) break;
			if(choice ==1) path1();
			if(choice ==2) path2();
			if(choice ==3) path3();
			if(choice ==4) path4();
		}
		System.out.println("The session is closed.");
	}
}