package escape.client;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.InetAddress;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

import javax.swing.JFrame;

import escape.event.ConnectionAcceptedEvent;
import escape.event.Event;
import escape.event.GameOverEvent;
import escape.event.GameWorldUpdateEvent;
import escape.event.TestEvent;
import escape.event.UserSetupEvent;
import escape.gameworld.Item;
import escape.gameworld.Player;
import escape.gameworld.Room;
import escape.server.Server;
import escape.ui.GameFrame;

/**
 * Class which is on the client side of the client-server. Responsible for networking between the user and the server by 
 * sending Events to the Server and also receiving Event updates from the server.
 * 
 * @author morenojuli
 */

public class Client extends Thread {

	private String username; 
	private int id;

	private ObjectOutputStream output;
	private ObjectInputStream input;

	private Socket socket;
	private String ipAddress;

	private GameFrame frame;
	
	private User user;
	private Player player = null;

	public Player getPlayer() {
		return player;
	}

	private Room room;
	private ArrayList<Item> items = new ArrayList<Item>();

	private boolean winner = false;
	private boolean running = true;

	private BlockingQueue<Event> events = new LinkedBlockingQueue<Event>();

	/**
	 * Constructor for Client
	 * @param ipAddress - empty means localhost
	 * @param id - username
	 */
	public Client(String ipAddress, String username){
		this.username = username;
		if(ipAddress.equals("localhost")){
			try {
				this.ipAddress = InetAddress.getLocalHost().getHostAddress().toString();
			} catch (UnknownHostException e1) {
				e1.printStackTrace();
			}
		}
		//empty string means local host
		else if(ipAddress.equals("")){
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

	/**
	 * Method returning the user. Probably not needed
	 * @return User
	 */
	public User getUser(){
		return this.user;
	}
	
	public boolean isRunning(){
		return running;
	}

	public void setFrame(GameFrame f){
		frame = f;
	}
	
	
	/**
	 * Method which initialises connection to the Server. Called when constructing a new Client.
	 */

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

	/**
	 * Called when trying to update the current state of the player of the client. This method tries to take an
	 * event from the queue of events and then updates the state of the player.
	 */
	public void update(){
		Event e = null;
		try {
			e = events.take();
		} catch (InterruptedException e1) {
			e1.printStackTrace();
		}

		if(e instanceof TestEvent){
			testSend();
			//test.. keep sending testevent
		}
		
		
		
		else if(e instanceof ConnectionAcceptedEvent){
			ConnectionAcceptedEvent event = ((ConnectionAcceptedEvent)e);
			//we prob wont need this user.setid thing..
			//			this.user.setId(event.getId()); //prob not need this one


			this.id = event.getId();

			//send this to the server so the server knows the name and can initiate player
			Event setup = new UserSetupEvent(this.username, this.id);
			sendEvent(setup);



		}
		else if(e instanceof GameWorldUpdateEvent){
			//player changing direction.. changing the view of the user.
			GameWorldUpdateEvent event = ((GameWorldUpdateEvent)e);
			System.out.println("gameworldupdate");
			//update the player and the room.
			if(this.player != null){
				if (event.getPlayer().getName().equals(this.player.getName())){
					this.player = event.getPlayer();
					this.room = event.getRoom();
				}
			}

			//if no player has been set to the client.
			else if(this.player == null && event.getPlayer().getId() == this.id){
				this.player = event.getPlayer();
				this.room = event.getRoom();
			}
			
			frame.updateFrame();
			System.out.println(player.getName() + " is in "
					+ player.getRoom().getName());
		}

		else if(e instanceof GameOverEvent){
			GameOverEvent event = ((GameOverEvent)e);
			//if the winner is this player then set player to winner. else false
			if(event.getPlayer().getName().equals(this.player.getName())){
				this.winner = true;
				//call some function to say that the player won...
			}

		}


	}

	/**
	 * Method to send an Event to the server. Called when the user makes a movement, pickup item, 
	 * drop item, or enter the room.
	 * @param event
	 */
	public void sendEvent(Event event){
		try {
			output.writeObject(event);
			output.flush();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	/**
	 * Client run method which will keep getting input from the server. If the input from the server is instance of
	 * an Event, then push it into the queue of events.
	 */
	public void run(){
		running = true;
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
			//			testSend();
			update();
		}
	}

	/**
	 * Send Event object TestEvent to the Server.
	 */
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