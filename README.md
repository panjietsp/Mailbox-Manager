### Introduction

The aim of the project is to design and realize a mailbox manager. The mailbox manager handles classical user mailboxes and a common newsgroup which may be read by every user. A directory manages the users which may access the mailbox and their rights to access different mailboxes. Only some users have the right to send messages to the common newsgroup.

### Architecture

The general Architecture of the system is presented as Clients and Servers are distributed over the network.

(i)A MailBoxManager manages the mail boxes. There is one mail box by user of the system and one news-box shared by all the system’s users.
(ii)A UserDirectoryManager manages the users and their access to the system. In the final version of the system, the MailBoxManager verifies with the UserDirectoryManager the user rights concerning the access to the common news group.
(iii)A MailBoxClient accesses the MailBoxManager services.
(iiii)An AdministrationClient accesses the UserDirectoryManager services to add, remove and modify rights of the users.

### Technologies involved in the project

(i)The UserDirectoryManager and the MailBoxManager are developed using JavaEE technology. All the mailboxes, the newsgroup and the user rights are persistent.
(ii)The communication between the clients and the Managers will be realized through Java RMI for the administration server.
(iii)For the persistence of the mailboxes, the messages and the directory, use a derby database embedded in the glassfish application server.

### Environment

GlassFish3.1 and JAVA JRE1.6 used in the project.
MyEclipse as IDE.

To check the content of the Derby database, use the ij tool. In a terminal window, launch the following command:

```
$ ij
```

If the ij tool is working fine, see on the screen:

```
version ij 10.6 

ij>
```

Then connect to the Derby database and write SQL requests: 

```
connect 'jdbc:derby://localhost:1527/sun-appserv-samples'; 

show tables;
```

### Launch

We have two projects in total, an EJB project MailBoxManager and a JAVA project MailBoxManagerClient. So, import these two projects respectively into MyEclipse Workspace. Start the database and the server, deploy EJB project, then launch JAVA project.

### Yunkai LIU
### Versailles Saint-Quentin-en-Yvelines University

