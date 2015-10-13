package escape.gameworld;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;

/**
 * This class represents the rooms in the mansion. This is where a player can go
 * to explore the mansion. Inside a room are items and containers or clues. A
 * room can either be locked or unlocked; if it is locked, a key must be
 * provided.
 * 
 * @author semillkasz
 *
 */
public class Room implements Serializable {
	private String name;

	private ArrayList<Item> items = new ArrayList<Item>();
	private ArrayList<Container> container = new ArrayList<Container>();
	private ArrayList<Player> players = new ArrayList<Player>();
	private HashMap<String, String[][]> itemsByDirection = new HashMap<>(); 
	
	private Container bin;

	private boolean locked;
	private String key;

	public Room(String name, boolean locked, String key) {
		this.name = name;
		this.locked = locked;
		this.key = key;
	}
	

	
	public Item getItem(String s){
		for (Item i: items){
			if (i.getName().equals(s)){
				return i;
			}
		}
		for (Container con : container){
			if (con.getName().equals(s)){
				return con;
			}
		}
		return null;
	}

	/**
	 * Add player to the list of players.
	 * 
	 * @param p - player to be added to the list
	 */
	public void addPlayer(Player p) {
		players.add(p);
	}

	public ArrayList<Player> getPlayers() {
		return players;
	}

	public void setPlayers(ArrayList<Player> players) {
		this.players = players;
	}

	public boolean isLocked() {
		return locked;
	}

	public void setLocked(boolean locked) {
		this.locked = locked;
	}

	public String getKey() {
		return key;
	}

	public void setKey(String key) {
		this.key = key;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void addItem(Item item) {
		this.items.add(item);
	}
	
	/**
	 * Remove item in this room and in the 2D array
	 * 
	 * @param item
	 */
	public void removeItem(Item item) {
		this.items.remove(item);
		String[][] roomNorth = this.itemsByDirection.get("North");
		String[][] roomEast = this.itemsByDirection.get("East");
		String[][] roomWest = this.itemsByDirection.get("West");
		String[][] roomSouth = this.itemsByDirection.get("South");
		
		for(int i = 0; i != roomNorth.length; i++){
			for(int j = 0; j!= roomNorth[i].length; j++){
				if(roomNorth[i][j].equals(item.getName())){
					roomNorth[i][j] = "";
				}
			}
		}
		for(int i = 0; i != roomEast.length; i++){
			for(int j = 0; j!= roomEast[i].length; j++){
				if(roomEast[i][j].equals(item.getName())){
					roomEast[i][j] = "";
				}
			}
		}
		for(int i = 0; i != roomWest.length; i++){
			for(int j = 0; j!= roomWest[i].length; j++){
				if(roomWest[i][j].equals(item.getName())){
					roomWest[i][j] = "";
				}
			}
		}
		for(int i = 0; i != roomSouth.length; i++){
			for(int j = 0; j!= roomSouth[i].length; j++){
				if(roomSouth[i][j].equals(item.getName())){
					roomSouth[i][j] = "";
				}
			}
		}
	}

	public void addContainer(Container container) {
		this.container.add(container);
	}
	
	public void removeContainer(Container container) {
		this.container.remove(container);
	}

	public ArrayList<Item> getItems() {
		return items;
	}

	public void setItems(ArrayList<Item> item) {
		this.items = item;
	}

	public ArrayList<Container> getContainer() {
		return container;
	}

	public void setContainer(ArrayList<Container> container) {
		this.container = container;
	}

	public Container getBin() {
		return bin;
	}

	public void setBin(Container bin) {
		this.bin = bin;
	}

	public HashMap<String, String[][]> getItemsByDirection() {
		return itemsByDirection;
	}

	public void setItemsByDirection(HashMap<String, String[][]> itemsByDirection) {
		this.itemsByDirection = itemsByDirection;
	}
	
	/**
	 * Creates a new Hashmap that will be assigned to the
	 * itemsByDirection field 
	 * 
	 * @param s
	 * @param items
	 */
	public void setItemsByDirection(String s, String[][] items) {
		this.itemsByDirection.put(s, items);
	}
}