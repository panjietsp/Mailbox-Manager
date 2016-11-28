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

@Entity
@Table(name="newsbox")
public class NewsBox implements Serializable
{
	private static final long serialVersionUID = 1L;
	
	private int id = 1;
	private String boxName = "NewsBox@gmail.com";
	private Collection<Mail> mails = new ArrayList<Mail>();
	
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
	
	@OneToMany(cascade=ALL, mappedBy="newsbox")
	public Collection<Mail> getMails()
	{
		return mails;
	}
	
	public void setMails(Collection<Mail> mails) 
	{
		this.mails = mails;
	}
}