package mailbox;

import static javax.persistence.CascadeType.ALL;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import userdirectory.User;

@Entity
@Table(name="mailbox")
public class MailBox implements Serializable
{
	private static final long serialVersionUID = 1L;
	
	private int id;
	private String boxName;
	private Collection<Mail> mails = new ArrayList<Mail>();
	private User user;

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
	
	@Column(name="boxName")
	public String getBoxName() 
	{
		return boxName;
	}
	
	public void setBoxName(String boxName)
	{
		this.boxName = boxName;
	}
	
	@OneToMany(cascade=ALL, mappedBy="mailbox")
	public Collection<Mail> getMails()
	{
		return mails;
	}
	
	public void setMails(Collection<Mail> mails) 
	{
		this.mails = mails;
	}
	
	public User getUser() 
	{
		return user;
	}

	public void setUser(User user) 
	{
		this.user = user;
	}
	
	public void readAllMessages()
	{
		Collection<Mail> mails = MailBoxManager.readABoxAllMessages(this.getId());
		
		for(Mail mail : mails)
		{
			System.out.println(mail.displayOneMail());
			
			MailBoxManager.upDateMailStatus(mail.getId(), true);
		}
	}

	public void addMessage(Mail mail)
	{
		this.mails.add(mail);
	}
	
	public boolean isOwner(int id)
	{
		boolean flag = false;
		
		Collection<Mail> mails = MailBoxManager.readABoxAllMessages(this.getId());
		
		for(Mail m : mails)
		{
			if(m.getId() == id)
			{
				flag = true;
				break;
			}
		}
		
		return flag;
	}
	
	public void readAMessage(int id)
	{
		if(this.isOwner(id) == true)
		{
			Mail mail = MailBoxManager.readAMessage(id);
			System.out.println(mail.displayOneMail());
			
			MailBoxManager.upDateMailStatus(mail.getId(), true);
		}
		else
		{
			System.out.println("You can't read this message!");
		}
	}
	
	public void deleteAMessage(int id)
	{
		if(this.isOwner(id) == true)
		{
			MailBoxManager.deleteABoxMessage(id, this.getId());
		}
		else
		{
			System.out.println("You can't delete this message!");
		}
	}
	
	public void deleteAllMessage()
	{
		Collection<Mail> mails = MailBoxManager.readABoxAllMessages(this.getId());
		
		for(Mail mail : mails)
		{
			MailBoxManager.deleteABoxMessage(mail.getId(), this.getId());
		}
	}
	
	public void listAllMessage()
	{
		Collection<Mail> mails = MailBoxManager.readABoxAllMessages(this.getId());
		
		for(Mail mail : mails)
		{
			System.out.println(mail.describeOneMail());
		}
	}
	
	public void deleteReadMessages()
	{
		Collection<Mail> mails = MailBoxManager.readABoxAllMessages(this.getId());
		
		for(Mail mail : mails)
		{
			if(mail.getAlreadyRead()==true)
			{
				MailBoxManager.deleteABoxMessage(mail.getId(), this.getId());
			}
		}
	}
	
	public void readNewMessages()
	{
		Collection<Mail> mails = MailBoxManager.readABoxAllMessages(this.getId());
		
		for(Mail mail : mails)
		{
			if(mail.getAlreadyRead()==false)
			{
				System.out.println(mail.displayOneMail());
				
				MailBoxManager.upDateMailStatus(mail.getId(), true);
			}
		}
	}
	
	public void listNewsBoxAllMessages()
	{
		Collection<Mail> mails = MailBoxManager.readNewsBoxAllMessages();
		
		for(Mail mail : mails)
		{
			System.out.println(mail.describeOneNewsMail());
		}
	}
	
	public void readNewsAllMessages()
	{
		Collection<Mail> mails = MailBoxManager.readNewsBoxAllMessages();
		
		for(Mail mail : mails)
		{
			System.out.println(mail.displayOneMail());
		}
	}
}