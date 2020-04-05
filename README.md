Definitely different "README" files

Some instructions to run a project:
To run this program you need a Maven.

1. Download(Clone) a branch.
2. Open your terminal and go to directory with files.
3. Use command - mvn compile.
4. After successful compilation use command - mvn exec:java -Dexec.mainClass="Seminar03.StartClient".
5. That's all, you successfully running a program.

In this project i realized all tasks except this few:
- Application prints the message when a new user logs in to the server and logs out of the server.
- Bonus task.

And the list of commands that realized in this project and their syntax:
- ping
- echo ***some text***
- exit
- login ***Login*** ***Password*** //Login or Register in Server
- list //Return the list of currently online Users
- msg ***Receiver*** ***Message*** //Send a message to User, working only when the receiver IS online
- rmsg //Command check if there any new messages in server and receiving them
- file ***Receiver*** ***Path to file*** //Send a file to currently online User, 
       when you use this command write a path like this: ***C:\\directory\\file.extension***
-rfile ***Path where to save the file*** //Command check if there any new file in server and receiving them,
       when you use this command write a path like this: ***C:\\directory\\
