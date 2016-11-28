package mailboxmanager;

import java.util.Collection;

import javax.ejb.Remote;

import mailbox.Mail;
import mailbox.MailBox;
import mailbox.NewsBox;

@Remote
public interface AccessMailBoxDB 
{	
	public void createNewsBox(NewsBox newsbox);
	public NewsBox getNewsBox();
	public Collection<Mail> readNewsBoxAllMessages();
	public void sendNewsToNewsBox(Mail mail);
	public void addMailBox(MailBox mailbox);
	public MailBox getMailBoxByName(String boxName);
	public void removeMailBox(String boxName);
	public int getMaxIdMail();
	public void sendAMessageToABox(Mail mail, String address); 
	public Collection<Mail> readAMailBoxAllMessages(int id);
	public Mail getMailById(int id);
	public MailBox getMailBox(int id);
	public void removeAMail(int id, int user_id);
	public void upDateMailStatus(int id, boolean status);
}