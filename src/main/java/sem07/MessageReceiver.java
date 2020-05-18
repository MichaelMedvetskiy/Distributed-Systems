package sem07;
import javax.jms.MapMessage;
import javax.jms.Message;
import javax.jms.MessageListener;

class MessageReceiver implements MessageListener {
@Override
public void onMessage(Message message) {
	try {
	if(message instanceof MapMessage) {
		MapMessage mapMsg = (MapMessage)message;
		String sender = mapMsg.getString("sender");
		String messages = mapMsg.getString("message");
		System.out.print(sender + " - ");
		System.out.println(messages);
}
	}catch(Exception e) {
		
	}
}


}