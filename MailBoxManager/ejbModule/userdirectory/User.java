package userdirectory;

import static javax.persistence.CascadeType.ALL;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import mailbox.Mail;
import mailbox.MailBox;
import mailbox.MailBoxManager;

@Entity
@Table(name="utilisateur")
public class User implements Serializable
{
	private static final long serialVersionUID = 1L;
	
	private int id;
	private String userName;
	private int userRight;
	private int newsRight;
	private MailBox mailbox;
	
	public User()
	{
		this.userRight = 0;
		this.newsRight = 0;
	}
	
	@Id
	@Column(name="id")
	public int getId() 
	{
		return id;
	}
	
	public void setId(int id)
	{
		this.id = id;
	}
	
	@Column(name="username")
	public String getUserName() 
	{
		return userName;
	}

	public void setUserName(String userName) 
	{
		this.userName = userName;
	}
	
	@OneToOne(cascade=ALL, mappedBy="user")
	public MailBox getMailbox()
	{
		return mailbox;
	}

	public void setMailbox(MailBox mailbox) 
	{
		this.mailbox = mailbox;
	}
	
	@Column(name="uright")
	public int getUserRight() 
	{
		return userRight;
	}

	public void setUserRight(int userRight) 
	{
		this.userRight = userRight;
	}

	@Column(name="newsright") 
	public int getNewsRight() 
	{
		return newsRight;
	}

	public void setNewsRight(int newsRight) 
	{
		this.newsRight = newsRight;
	}
	
	public void sendAMessage(String address, Mail mail)
	{
		String[] re_name = address.split("@");
		
		if("NewsBox@gmail.com".equals(address))
		{
			int right = UserDirectoryManager.lookupAUserNewsRight(this.getUserName());
			
			if(right == 1)
			{
				mail.setSenderName(this.getUserName());
				mail.setReceiverName(re_name[0]);
				
				MailBoxManager.sendNews(mail);
			}
			else
			{
				System.out.println("You can't send the message to NewsGroup!");
			}
		}
		else
		{
			mail.setSenderName(this.getUserName());
			mail.setReceiverName(re_name[0]);
			
			MailBoxManager.sendAMessageToABox(address, mail);
		}
	}
	
	public void listAllMessages()
	{
		MailBoxManager.getMailBoxById(this.getId()).listAllMessage();
	}
	
	public void readAllMessages()
	{
		MailBoxManager.getMailBoxById(this.getId()).readAllMessages();
	}
	
	public void readAMessage(int id)
	{
		MailBoxManager.getMailBoxById(this.getId()).readAMessage(id);
	}
	
	public void deleteAMessage(int id)
	{
		MailBoxManager.getMailBoxById(this.getId()).deleteAMessage(id);
	}
	
	public void deleteAllMessages()
	{
		MailBoxManager.getMailBoxById(this.getId()).deleteAllMessage();
	}
	
	public void readNewMessages()
	{
		MailBoxManager.getMailBoxById(this.getId()).readNewMessages();
	}
	
	public void deleteReadMessage()
	{
		MailBoxManager.getMailBoxById(this.getId()).deleteReadMessages();
	}
	
	public void listNewsAllMessages()
	{
		MailBoxManager.getMailBoxById(this.getId()).listNewsBoxAllMessages();
	}
	
	public void readNewsAllMessages()
	{
		MailBoxManager.getMailBoxById(this.getId()).readNewsAllMessages();
	}
}