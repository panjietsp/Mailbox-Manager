package mailbox;

import java.util.Collection;

import javax.naming.InitialContext;
import javax.naming.NamingException;

import mailboxmanager.AccessMailBoxDB;
import userdirectory.User;
import userdirectory.UserDirectoryManager;

public class MailBoxManager 
{
	private static AccessMailBoxDB sb = null;
	
	static
	{
		try 
		{
			InitialContext  ic = new InitialContext();
			sb = (AccessMailBoxDB) ic.lookup(mailboxmanager.AccessMailBoxDB.class.getName());	
		} 
		catch (NamingException e) 
		{
			e.printStackTrace();
		}
	}
	
	public static void createNewsBox()
	{
		sb.createNewsBox(new NewsBox());
	}

	public static void addMailBox(User user)
	{
		MailBox mailbox = new MailBox();
		mailbox.setId(user.getId());
		mailbox.setBoxName(user.getUserName()+"@gmail.com");
		
		user.setMailbox(mailbox);
		mailbox.setUser(user);
		sb.addMailBox(mailbox);
	}

	public static void removeMailBox(String boxName)
	{
		sb.removeMailBox(boxName);
	}

	public static void sendAMessageToABox(String boxName, Mail mail) 
	{
		mail.setSendingDate(new java.util.Date().toString());
		int mail_id = sb.getMaxIdMail()+1;
		mail.setId(mail_id);
		
		sb.sendAMessageToABox(mail, boxName);
	}
	
	public static Collection<Mail> readABoxAllMessages(int id)
	{
		return sb.readAMailBoxAllMessages(id);
	}
	
	public static void deleteABoxMessage(int id, int mailbox_id)
	{
		sb.removeAMail(id, mailbox_id);
	}
	
	public static Mail readAMessage(int id)
	{
		return sb.getMailById(id);
	}
	
	public static void upDateMailStatus(int id, boolean status)
	{
		sb.upDateMailStatus(id, status);
	}
	
	public static void sendNews(Mail mail)
	{
		mail.setSendingDate(new java.util.Date().toString());
		mail.setReceiverName("NewsBox");
		int mail_id = sb.getMaxIdMail()+1;
		mail.setId(mail_id);
		
		sb.sendNewsToNewsBox(mail);
	}
	
	public static Collection<Mail> readNewsBoxAllMessages()
	{
		return sb.readNewsBoxAllMessages();
	}
	
	public static MailBox getMailBoxById(int id)
	{
		return sb.getMailBox(id);
	}
	
	public static boolean isMailBoxExist(String mailbox)
	{
		boolean isMailBox = false;
		String[] mail_user = mailbox.split("@");
		
		if(UserDirectoryManager.isUserAlreadyExist(mail_user[0]))
		{
			isMailBox = true;
		}
		
		return isMailBox;
	}
}