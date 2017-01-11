package mailboxmanager;

import java.util.Collection;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import mailbox.Mail;
import mailbox.MailBox;
import mailbox.NewsBox;

/**
 * Session Bean implementation class AccessDBBean
 */
@Stateless
public class AccessMailBoxDBBean implements AccessMailBoxDB
{
	@PersistenceContext(unitName="pu1")
    private EntityManager em;

	@Override
	public Mail getMailById(int id) 
	{
		Mail mail = em.find(Mail.class, id);
		return mail;
	}
	
	//Should use the singleton pattern
	@Override
	public void createNewsBox(NewsBox newsbox) 
	{
		NewsBox n = em.find(NewsBox.class, 1);
		if(n == null)
		{
			em.persist(newsbox);
		}
	}
	
	@Override
	public void addMailBox(MailBox mailbox) 
	{
		em.persist(mailbox);
	}
	
	@Override
	public MailBox getMailBoxByName(String boxName)
	{
		Query q = em.createQuery("select m from MailBox m where m.boxName = :name");
		q.setParameter("name", boxName);
		
		return (MailBox) q.getSingleResult();
	}
	
	@Override
	public void removeMailBox(String boxName) 
	{
		MailBox mailbox = this.getMailBoxByName(boxName);
		MailBox m = em.merge(mailbox);
		em.remove(m);
	}
	
	@Override
	public int getMaxIdMail() 
	{
		Mail mail = em.find(Mail.class, 1);
		if(mail==null)
		{
			return 0;
		}
		else
		{
			Query q = em.createQuery("select max(m.id) from Mail m");
			return (Integer) q.getSingleResult();
		}
	}
	
	@Override
	public void sendAMessageToABox(Mail mail, String address) 
	{
		MailBox m = this.getMailBoxByName(address);
		
		m.addMessage(mail);
		mail.setMailbox(m);
	}
	
	public Collection<Mail> readAMailBoxAllMessages(int id)
	{
		MailBox m = this.getMailBox(id);
		
		Collection<Mail> mails = m.getMails();
		//eagerly create the instance
		mails.size();
		
		return mails;
	}
	
	public MailBox getMailBox(int id)
	{
		MailBox mailbox = em.find(MailBox.class, id);
		
		return mailbox;
	}
	
	@Override
	public void removeAMail(int id, int mailbox_id) 
	{
		MailBox mailbox = this.getMailBox(mailbox_id);
		Mail mail = this.getMailById(id);

		Mail m =em.merge(mail);
		
		em.remove(m);
		mailbox.getMails().remove(mail);
	}
	
	@Override
	public void upDateMailStatus(int id, boolean status) 
	{
		Mail mail = this.getMailById(id);
		mail.setAlreadyRead(status);
	}

	@Override
	public void sendNewsToNewsBox(Mail mail) 
	{
		NewsBox n = this.getNewsBox();
		
		n.getMails().add(mail);
		mail.setNewsbox(n);
	}

	@Override
	public NewsBox getNewsBox() 
	{
		return em.find(NewsBox.class, 1);
	}

	@Override
	public Collection<Mail> readNewsBoxAllMessages() 
	{
		NewsBox n = this.getNewsBox();
		
		Collection<Mail> mails = n.getMails();
		mails.size();
		
		return mails;
	}
}