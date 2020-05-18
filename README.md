Some instructions to run a project:
To run this program you need a Maven.

1. Download(Clone) a branch.
2. Open your terminal and go to directory with files.
3. Use command - mvn compile.
4. After successful compilation use command - mvn exec:java -Dexec.mainClass="sem07.StartClient".
5. That's all, you successfully running a program.

And the list of commands that implemented in this project and their syntax:
- ping
- echo ***some text***
- exit
- login ***Login*** ***Password*** //Login or Register in Server
- list //Return the list of currently online Users
- msg ***Receiver*** ***Message*** //Send a message to User, working only when the receiver IS online
- rmsg //Command check if there any new messages in server and receiving them 
