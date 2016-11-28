package mailbox;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name="mail")
public class Mail implements Serializable
{
	private static final long serialVersionUID = 1L;
	
	private int id;
	private String senderName;
	private String receiverName;
	private String sendingDate;
	private String subject;
	private String body;
	private boolean alreadyRead;
	private MailBox mailbox;
	private NewsBox newsbox;

	public Mail()
	{
		this.alreadyRead = false;
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
	
	@Column(name="body")
	public String getBody() 
	{
		return body;
	}
	
	public void setBody(String body) 
	{
		this.body = body;
	}
	
	@ManyToOne()
    @JoinColumn(name="mailbox_id")
	public MailBox getMailbox() 
	{
		return mailbox;
	}
	
	public void setMailbox(MailBox mailbox)
	{
		this.mailbox = mailbox;
	}
	
	@Column(name="senderName")
	public String getSenderName() 
	{
		return senderName;
	}

	public void setSenderName(String senderName) 
	{
		this.senderName = senderName;
	}
	
	@Column(name="receiverName")
	public String getReceiverName() 
	{
		return receiverName;
	}

	public void setReceiverName(String receiverName) 
	{
		this.receiverName = receiverName;
	}
	
	@Column(name="sendingDate")
	public String getSendingDate() 
	{
		return sendingDate;
	}

	public void setSendingDate(String sendingDate) 
	{
		this.sendingDate = sendingDate;
	}
	
	@Column(name="subject")
	public String getSubject() 
	{
		return subject;
	}

	public void setSubject(String subject) 
	{
		this.subject = subject;
	}

	@Column(name="alreadyRead")
	public boolean getAlreadyRead() 
	{
		return alreadyRead;
	}
	
	public void setAlreadyRead(boolean alreadyRead) 
	{
		this.alreadyRead = alreadyRead;
	}
	
	@ManyToOne()
    @JoinColumn(name="newsbox_id")
	public NewsBox getNewsbox() 
	{
		return newsbox;
	}

	public void setNewsbox(NewsBox newsbox) 
	{
		this.newsbox = newsbox;
	}
	
	public String displayOneMail()
	{
		String sender_address = this.getSenderName()+"@gmail.com";
		String receiver_address = this.getReceiverName()+"@gmail.com";
		
		return "De "+this.getSenderName()+"("+sender_address+")"+" À "+this.getReceiverName()+"("+receiver_address+")"+"\n"+"Subject : "+this.getSubject()+"\n"+this.getBody()+"\n"+"SendingDate : "+this.getSendingDate()+"\n";
	}
	
	public String describeOneMail()
	{
		String sender_address = this.getSenderName()+"@gmail.com";
		String receiver_address = this.getReceiverName()+"@gmail.com";
		String status = "Not read";
		if(this.getAlreadyRead()==true)
		{
			status = "Already read";
		}
		return "Id : "+this.getId()+", De "+this.getSenderName()+"("+sender_address+")"+" À "+this.getReceiverName()+"("+receiver_address+")"+", "+"Subject : "+this.getSubject()+", Status : "+status+"\n"+", SendingDate : "+this.getSendingDate();
	}
	
	public String describeOneNewsMail()
	{
		String sender_address = this.getSenderName()+"@gmail.com";
		String receiver_address = this.getReceiverName()+"@gmail.com";

		return "Id : "+this.getId()+", De "+this.getSenderName()+"("+sender_address+")"+" À "+this.getReceiverName()+"("+receiver_address+")"+", "+"Subject : "+this.getSubject()+", SendingDate : "+this.getSendingDate();
	}
}