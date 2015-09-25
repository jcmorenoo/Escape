package escape.client;

import java.util.ArrayList;

import escape.gameworld.Item;
import escape.gameworld.Player;
import escape.gameworld.Room;

/**
 * Represents the user of the client. Maintains player and current states of the player.
 * Also contains the Room which the player is in.
 * @author morenojuli
 *
 */

public class User {
	
	//contains fields Player, Room, Client
	
	//contains methods get Player, get Room, setPlayer, setROom,
	
	private String username;
	private Client client;
	private Room room;
	private boolean winner = false;
	private ArrayList<Item> items = new ArrayList<Item>();
	private Player player;
	private int id;
	
	public User(Client client, String username){
		this.client = client;
		this.username = username;
	}
	
	public Client getClient(){
		return this.client;
	}
	
	public String getUsername(){
		return this.username;
	}
	
	public ArrayList<Item> getItems(){
		return this.items;
	}
	
	public Player getPlayer(){
		return this.player;
	}
	
	public Room getRoom(){
		return this.room;
	}
	
	public void setRoom(Room room){
		this.room = room;
	}
	
	public void setId(int id){
		this.id = id;
	}
	
	public int getId(){
		return this.id;
	}

}