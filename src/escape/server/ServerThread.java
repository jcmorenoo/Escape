package escape.server;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.net.Socket;

import escape.event.*;

/**
 * This is the support for the Multi-Threaded Socketing. Accepts input from the Client in form of Events and adds it to the queue of 
 * events in the server.
 * @author morenojuli
 *
 */
public class ServerThread extends Thread {

	private Server server;
	private Socket socket;
	private int id;
	private ObjectInputStream input;


	public ServerThread(Socket socket, Server server, int id){
		this.server = server;
		this.id = id;
		this.socket = socket;
	}
	public void run(){
		this.input = null;
		Object object = null;
		try{

			//ObjectInputStream objectInputStream = new ObjectInputStream(socket.getInputStream());
			//ObjectOutputStream objectOutputStream = new ObjectOutputStream(socket.getOutputStream());

			this.input = new ObjectInputStream(socket.getInputStream());
			

			//close socket.. close disconnect.

		}
		catch(IOException e){
			e.printStackTrace();
		}
		while(true){
			
			try {
				System.out.println("reading object");
				object = input.readObject();
				
			} catch (ClassNotFoundException | IOException e) {
				e.printStackTrace();
			}
			
			if(!(object instanceof Event)){
				continue;
				//meaning object is not an event and shouldnt be processed
			}
			
			else if(object instanceof Event){
				server.getUpdates().add(new Update((Event)object)); //add the event to the queue of updates
			}

		}


	}


}