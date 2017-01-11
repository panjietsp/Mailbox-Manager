This is a small user manuel of the directory manager and mailbox manager.

1.System requirements:
Java EE 6.0
Java SE 1.6
Glassfish 3.1



2.Envrionment Configuration

Depress the file “.zip”. You will find two files “MailBoxManager” and “MailBoxManagerClient”in the root directory. The MailBoxManager is an EJB project. The MailBoxManagerClient is a java project. Import these two project in Eclipse.

You can find “appserv-rt.jar”, ”gf-client.jar”, ”javaee.jar” in the lib directory of glass fish. Added them as external jars into the buildpath of the project MailBoxClient. Add the project MailBoxManager into the buildpath of the project MailBoxClient.



3.The directory manager

Choose the file “AdministrationClient.java” and run it as “java application”.
You will have six choices. Make your choice by giving the number of one choice.

For the choice “Create User”, you should then give the name and rights of the user you want to add in the user directory.

For the choice “Delete User”, you should then give the name of the user you want to delete from the user directory.

For the choice “List All Users”, you can see a list of all users in the user directory.

For the choice “Lookup User Rights”, you should then give the name of the user and choose which right you want to lookup.

For the choice “Update User Rights”, you should then give the name of the user and the level of one right you want to change.



4.The mailbox manager

Choose the file “MailBoxClient.java” and run it as “java application”.
You will have five choices. Make your choice by giving the number of one choice.

For the choice “Inbox”, you can enter the inbox to do the following options:

“Read a message”: you should give the id of the message you want to read.

“Delete a message”: you should give the id of the message you want to delete.

“List all messages”: you can see a list of all messages in your inbox.

“Delete all messages”: you can delete all messages in your inbox.

“Read all new messages”: you can see a list of unread messages in your inbox.

“Delete all readed messages”: you can delete all messages you already read in your inbox.

For the choice “Send a message”, you should then give the subject and the body of the message. Also you should give the receiver of the message.

For the choice “Read Newsbox”, you can read the messages in your newsbox.

For the choice “Send a message to NewsBox”, if you have the right to write the newsbox, you should then give the subject and the body of the message to be sent to the newsbox.



5.Tips

The default format of mail address of the user “firstnamelastname” is “firstnamelastname@gmail.com“.