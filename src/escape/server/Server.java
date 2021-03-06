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
import escape.event.RemovePlayerEvent;
import escape.gameworld.GameWorld;
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
	private GameWorld game;
	private String ipAddress;
	private UpdateThread updateThread;

	//should pass game
	public Server(int players, String gameID ){
		this.limit = players;
		try {
			this.serverSocket = new ServerSocket();
			serverSocket.bind(new InetSocketAddress(PORT));
			System.out.println("Server up and ready + with IP Address of: " + serverSocket.getInetAddress().getLocalHost().getHostAddress());
			this.ipAddress = serverSocket.getInetAddress().getLocalHost().getHostAddress();
		} catch (IOException e) {
			e.printStackTrace();
		}

		//initialise gameworld
		game = new GameWorld(null);





	}

	public void run(){

		try {
			int id = 0;
			updateThread = new UpdateThread(this);
			updateThread.start();
			stopped = false;
			while(clients.size()<limit){
				Socket clientSocket = serverSocket.accept(); 
				this.clients.put(id, new Connection(clientSocket, new ObjectOutputStream(clientSocket.getOutputStream())));
				//only limited number of players.
				if(this.clients.size()>this.limit){
					updateThread.sendClient(new ConnectionDeniedEvent(id), getClients().get(id));
					this.clients.remove(id);
					continue;
				}
				ServerThread serverThread = new ServerThread(clientSocket,this,id);
				serverThread.start();

				//game will only start once we reach the limit of players. or everyone has joined.
				if(clients.size() >= this.limit){
					for(int i = 0; i<clients.size(); i++){
						ConnectionAcceptedEvent e = new ConnectionAcceptedEvent(i);
						updateThread.sendClient((Event)e, getClients().get(i));
					}
				}


				
				id++;


			}

		} catch (IOException e) {

			e.printStackTrace();
		} 



	}

	/**
	 * Method which returns the Queue of Updates
	 * @return BlockingQueue<Update>
	 */
	public BlockingQueue<Update> getUpdates(){
		return this.updates;
	}

	/**
	 * Methoc which returns a map of Connections to clients
	 * @return Map<Integer,Connection>
	 */
	public Map<Integer,Connection> getClients(){
		return this.clients;
	}

	/**
	 * Method to shut down the server and stop the threads.
	 */
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

	/**
	 * Method which sets game id.
	 * @param id
	 */
	public void setGameId(String id){
		this.game.setGameID(id);
	}

	/**
	 * Method which returns the current game.
	 * @return GameWorld
	 */
	public GameWorld getGameWorld(){
		return this.game;
	}

	public String getIp(){
		return this.ipAddress;
	}

	public void removePlayer(int id) {
		Player p = game.getPlayers().get(id);
		p.getRoom().getPlayers().remove(p);
		clients.get(id).stop();
		RemovePlayerEvent event = new RemovePlayerEvent(p);
		updateThread.sendToAllClients((Event)event);
		game.getPlayers().remove(id);
		
		// TODO remove player method
		//remove player in game method.
		
	}

}