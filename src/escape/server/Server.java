package escape.server;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

import escape.event.ConnectionAcceptedEvent;
import escape.event.ConnectionDeniedEvent;
import escape.event.Event;
import escape.gameworld.Player;

/**
 * Represents the Server side of the Client-Server architecture. 
 * 
 * @author morenojuli
 *
 */

public class Server extends Thread{

	//should also have the game as a field
	public static final int PORT = 12608;
	
	
	
	private Map<Integer,Connection> clients = new HashMap<Integer,Connection>();
	private ServerSocket serverSocket;
	private BlockingQueue<Update> updates = new LinkedBlockingQueue<Update>();
	private boolean stopped = true;
	private int limit;

	//should pass game
	public Server(int players){
		this.limit = players;
		try {
			this.serverSocket = new ServerSocket();
			serverSocket.bind(new InetSocketAddress(PORT));
			System.out.println("Server up and ready");
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		//initialise gameworld




	}

	public void run(){

		try {
			int id = 0;
			UpdateThread updateThread = new UpdateThread(this);
			updateThread.start();
			System.out.println("Waiting for connections");
			stopped = false;
			while(!stopped){
				Socket clientSocket = serverSocket.accept(); 
				System.out.println("Client + " + id + " connected");
				this.clients.put(id, new Connection(clientSocket, new ObjectOutputStream(clientSocket.getOutputStream())));
				//only limited number of players.
				if(this.clients.size()>this.limit){
					updateThread.sendClient(new ConnectionDeniedEvent(id), getClients().get(id));
					this.clients.remove(id);
					continue;
				}
				ServerThread serverThread = new ServerThread(clientSocket,this,id);
				serverThread.start();
				System.out.println("ServerThread started");
				
				ConnectionAcceptedEvent e = new ConnectionAcceptedEvent(id);
				updateThread.sendClient((Event)e, getClients().get(id));
				//create a new player with an id.
				//then we should add this player into the game.
//				Player player = new Player(id);
				
				//add new player
				//send a player to client.
				//with a default starting room.
				
				//setUp players..??
				id++;
				
				
			}

		} catch (IOException e) {

			e.printStackTrace();
		} 



	}

	public BlockingQueue<Update> getUpdates(){
		return this.updates;
	}

	public Map<Integer,Connection> getClients(){
		return this.clients;
	}

	public synchronized void shutdown(){
		stopped= true;
		try { 
			serverSocket.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		try{
			this.join(); 
		} catch (InterruptedException e) { e.printStackTrace();}
	}


}