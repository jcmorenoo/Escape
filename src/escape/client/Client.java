package escape.client;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.InetAddress;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

import escape.event.ConnectionAcceptedEvent;
import escape.event.Event;
import escape.event.GameWorldUpdateEvent;
import escape.event.TestEvent;
import escape.server.Server;

/**
 * Class which is on the client side of the client-server. Responsible for networking between the user and the server by 
 * sending Events to the Server and also receiving Event updates from the server.
 * 
 * @author morenojuli
 */

public class Client extends Thread {

	private String id; 
	private ObjectOutputStream output;
	private ObjectInputStream input;
	private Socket socket;
	private String ipAddress;
	private User user;


	private boolean running = true;

	private BlockingQueue<Event> events = new LinkedBlockingQueue<Event>();

	public Client(String ipAddress, String id){
		this.id = id;
		if(ipAddress.equals("localhost")){
			try {
				this.ipAddress = InetAddress.getLocalHost().getHostAddress().toString();
			} catch (UnknownHostException e1) {
				e1.printStackTrace();
			}
		}
		else {
			this.ipAddress = ipAddress;

		}

		initialiseConnection();

	}
	
	public User getUser(){
		return this.user;
	}


	public void initialiseConnection(){
		
		try{
			InetAddress ip = InetAddress.getByName(ipAddress);
			this.socket = new Socket(ip, Server.PORT);
			System.out.println("Connection Made");
			this.output = new ObjectOutputStream(socket.getOutputStream());
			this.input = new ObjectInputStream(socket.getInputStream());
		}catch(IOException e){
			System.exit(0); 

		}
	}

	public void update(){
		Event e = null;
		try {
			e = events.take();
		} catch (InterruptedException e1) {
			e1.printStackTrace();
		}
		
		if(e instanceof TestEvent){
			//test
		}
		else if(e instanceof ConnectionAcceptedEvent){
			ConnectionAcceptedEvent event = ((ConnectionAcceptedEvent)e);
			this.user.setId(event.getId());
		}
		else if(e instanceof GameWorldUpdateEvent){
			//player changing direction.. changing the view of the user.
		}
		
		
	}
	
	//send event to the server.
	public void sendEvent(Event event){
		try {
			output.writeObject(event);
			output.flush();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void run(){
		while(running){
			//this will jst keep getting input from server and add it into the queue
			
			Object in = null;
			
			try {
				in = input.readObject();
			} catch (ClassNotFoundException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			}
			
			//if in is null, do nothing
			if(in == null){
				continue;
			}
			
			//if in is not an event... do nothing..
			if(!(in instanceof Event)){
				continue;
			}
			//else this event must be an actual event then add it into the events queue
			else{
				this.events.offer((Event) in);
			}
			//for testing only, will keep sending test event to server.
			testSend();
		}
	}
	
	public void testSend(){
		try {
			Event e = new TestEvent();
			output.writeObject(e);
			output.flush();
			
		} catch (IOException e) {
			e.printStackTrace();
		}
	}



}