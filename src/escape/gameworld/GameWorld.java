package escape.gameworld;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * This is the class the pulls all the game elements together
 * 
 * @author semillkasz
 *
 */
public class GameWorld {
	private String gameID;
	private ArrayList<Player> players = new ArrayList<>();
	private HashMap<String, Room> rooms = new HashMap<>();
	private HashMap<String, Container> containers = new HashMap<>();
	private HashMap<String, Item> items = new HashMap<>();
	
	private Item selectedInventory;
	private Item selectedItem;
	
	private Room kitchen;
	private Room study;
	private Room livingRoom;
	private Room bedroom;
	private Room mainHall;
	private Room hallLeftStudy;
	private Room hallRightBedroom;
	private Room hallLeftKitchen;
	private Room hallRightLivingRoom;

	public GameWorld(String id) {
		this.gameID = id;
		initialiseItems();
		initialiseContainers();
		initialiseRooms();
	}

	/**
	 * Adds a player in the game
	 * 
	 * @param p
	 */
	public void addPlayer(Player p) {
		players.add(p);

	}
	
	public Item getItem(String s){
		return items.get(s);
	}

	/**
	 * Initialises all the items in the game.
	 */
	public void initialiseItems() {
		// Pickable
		items.put("Paper", new Item("Paper", "A scrap of paper.", true));
		items.put("Key", new Item("Key", "A door key.", true));
		items.put("Study Room Safe Key", new Item("Study Room Safe Key",
				"A modern-looking key", true));
		items.put("Bedroom Safe Key", new Item("Bedroom Safe Key",
				"A modern-look key", true));
		items.put("Cupboard Key", new Item("Cupboard Key", "A small key",
				true));
		items.put("Study Room Key", new Item("Study Room Key", "A room key",
				true));
		// items.put("Lamp", new Item("Lamp", "An old-fashioned lamp", true,
		// true));
		items.put("Kitchen Picture", new Item("Kitchen Picture",
				"A family picture", true));
		items.put("Living Room Picture", new Item("Living Room Picture",
				"A family picture", true));
		items.put("Matches", new Item("Matches",
				"Matches! Might be useful in the future", true));
		// Unmovables
		items.put("Sofa", new Item("Sofa", "A red sofa fill of cat fur.",
				 false));
		items.put("Desk", new Item("Desk", "An office desk",  false));
		items.put("Chair", new Item("Chair", "An comfortable working chair",
				 false));
		items.put("Bed", new Item("Bed", "A neatly made bed",  false));
		items.put("Bedroom Lamp", new Item("Bedroom Lamp", "An old-fashioned lamp",
				false));
		items.put("Kitchen Table", new Item("Kitchen Table",
				"An old, wooden kitchen table", false));
		items.put("Frame", new Item("Frame", "An empty picture frame", 
				false));
		items.put("Portrait", new Item("Portrait", "A family portrait", 
				false));
		items.put("Long Table", new Item("Long Table", "A centre table", false));
		items.put("Short Table", new Item("Short Table", "A small modern table",  false));
		
		items.put("Study Door", new Item("Study Door", "Door leading back to the Hall", false));
		items.put("Bedroom Door", new Item("Bedroom Door", "Door leading back to the Hall",false));
		
	}

	/**
	 * Initialises all the containers in the game. Places items in their
	 * designated containers.
	 * 
	 */
	public void initialiseContainers() {
		// empty containers
		containers.put("Fridge", new Container("Fridge",
				"An old, empty fridge.", false, false, null));
		// rubbish bins
		containers.put("Study Room Bin", new Container("Study Room Bin",
				"A rubbish bin", false, false, null));
		containers.put("Living Room Bin", new Container("Living Room Bin",
				"A rubbish bin", false, false, null));
		containers.put("Bedroom Bin", new Container("Bedroom Bin",
				"A rubbish bin",  false, false, null));
		containers.put("Kitchen Bin", new Container("Kitchen Bin",
				"A rubbish bin", false, false, null));
		// Safe
		containers.put("Study Room Safe", new Container("Study Room Safe",
				"This safe can be opened by a number combination", 
				false, true, ("Paper")));
		containers.get("Study Room Safe").add(items.get("Bedroom Safe Key"));
		containers.put("Living Room Safe", new Container("Living Room Safe",
				"This safe can be opened by a photo",  false, true,
				("Kitchen Picture")));
		containers.get("Living Room Safe").add(items.get("Cupboard Key"));
		containers.put("Bedroom Safe", new Container("Bedroom Safe",
				"This safe can be opened by a key", false, true,
				("Study Rooms Safe Key")));
		containers.get("Bedroom Safe").add(items.get("Key"));
		// Clue containers
		containers.put("Bookshelf", new Container("Bookshelf",
				"A bookshelf with alphabetically arranged books.",
				false, false, null));
		containers.get("Bookshelf").add(items.get("Paper"));
		containers.put("Sidetable", new Container("Sidetable",
				"A wooden side table.", false, false, null));
		containers.get("Sidetable").add(items.get("Study Room Key"));
		containers
				.put("Cupboard", new Container("Cupboard",
						"A cupboard covered with spiderweb", false,
						false, null));
		containers.get("Cupboard").add(items.get("Matches"));
		containers.put("Lamp", new Container("Lamp", "An old-fashioned lamp",
			 true, true, "Matches"));
		
		
		
	}

	/**
	 * Initialises all the rooms in the game. Places all the items and
	 * containers in designated rooms.
	 */
	public void initialiseRooms() {
		
		rooms.put("Kitchen", new Room("Kitchen", false, null));
		this.kitchen = rooms.get("Kitchen");
		rooms.put("Study", new Room("Study", true, "Study Room Key"));
		this.study = rooms.get("Study");
		rooms.put("Living Room", new Room("Living Room", false, null));
		this.livingRoom = rooms.get("Living Room");
		rooms.put("Bedroom", new Room("Bedroom", false, null));
		this.bedroom = rooms.get("Bedroom");
		//EXIT DOOR
		rooms.put("Exit Door", new Room("Exit Door", true, "Key"));
		// Hall Rooms
		rooms.put("Main Hall", new Room("Main Hall", false, null));
		this.mainHall = rooms.get("Main Hall");
		rooms.put("Hall - Study", new Room("Hall - Study", false, null));
		this.hallLeftStudy = rooms.get("Hall - Study");
		rooms.put("Hall - Bedroom", new Room("Hall - Bedroom", false, null));
		this.hallRightBedroom = rooms.get("Hall - Bedroom");
		rooms.put("Hall - Living Room", new Room("Hall - Living Room", false,
				null));
		this.hallRightLivingRoom = rooms.get("Hall - Living Room");
		rooms.put("Hall - Kitchen", new Room("Hall - Kitchen", false, null));
		this.hallLeftKitchen = rooms.get("Hall - Kitchen");

		//DOOR ????
		//cant go outside
		kitchen.addItem(items.get("Kitchen Table"));
		kitchen.addItem(items.get("Kitchen Picture"));
		kitchen.addContainer(containers.get("Fridge"));
		kitchen.addContainer(containers.get("Kitchen Bin"));
		kitchen.addContainer(containers.get("Cupboard"));
		//kitchen.addContainer(containers.get("Lamp"));

		//DOOR
		//STUDY SOUTH
		study.addItem(items.get("Chair"));
		study.addItem(items.get("Desk"));
		study.addItem(items.get("Study Door"));
		study.addContainer(containers.get("Study Room Bin"));
		study.addContainer(containers.get("Bookshelf"));
		study.addContainer(containers.get("Study Room Safe"));
		study.addContainer(containers.get("Lamp"));
		study.addItem(items.get("Study Door"));

		livingRoom.addItem(items.get("Sofa"));
		livingRoom.addItem(items.get("Long Table"));
		livingRoom.addItem(items.get("Living Room Picture"));
		livingRoom.addItem(items.get("Portrait"));
		livingRoom.addItem(items.get("Frame"));
		livingRoom.addContainer(containers.get("Living Room Bin"));
		livingRoom.addContainer(containers.get("Living Room Safe"));
		

		bedroom.addItem(items.get("Bedroom Lamp"));
		bedroom.addItem(items.get("Bed"));
		bedroom.addItem(items.get("Short Table"));
		bedroom.addItem(items.get("Bedroom Door"));
		bedroom.addContainer(containers.get("Bedroom Bin"));
		bedroom.addContainer(containers.get("Bedroom Safe"));
		bedroom.addContainer(containers.get("Sidetable"));
		bedroom.addItem(items.get("Bedroom Door"));
		
		assignItemsToRooms();

	}

	/**
	 * Assigns a 2D array of item Strings for each direction in every room
	 * 
	 */
	public void assignItemsToRooms() {
		study.setItemsByDirection("North", STUDY_NORTH);
		study.setItemsByDirection("East", STUDY_EAST);
		study.setItemsByDirection("South", STUDY_SOUTH);
		study.setItemsByDirection("West", STUDY_WEST);

		livingRoom.setItemsByDirection("North", LIVING_NORTH);
		livingRoom.setItemsByDirection("East", LIVING_EAST);
		livingRoom.setItemsByDirection("South", LIVING_SOUTH);
		livingRoom.setItemsByDirection("West", LIVING_WEST);
		
		bedroom.setItemsByDirection("North", BEDROOM_NORTH);
		bedroom.setItemsByDirection("East", BEDROOM_EAST);
		bedroom.setItemsByDirection("South", BEDROOM_SOUTH);
		bedroom.setItemsByDirection("West", BEDROOM_WEST);
		
		kitchen.setItemsByDirection("North", KITCHEN_NORTH);
		kitchen.setItemsByDirection("East", KITCHEN_EAST);
		kitchen.setItemsByDirection("South", KITCHEN_SOUTH);
		kitchen.setItemsByDirection("West", KITCHEN_WEST);
	}

	public String getGameID() {
		return gameID;
	}

	public void setGameID(String gameID) {
		this.gameID = gameID;
	}

	/**
	 * Allows the user to use an item (e.g. Use key to open safe)
	 * 
	 * @param con
	 *            container to be opened
	 * @param key
	 *            key to open container
	 * @return if opened
	 */
	public boolean useItem(Container con, String key) {
		if (containers.get(con.getName()).getKey().equals(key)) {
			return true;
		}
		return false;
	}

	/* GETTERS AND SETTERS */
	
	/**
	 * Returns the list of rooms from the hashmap
	 * 
	 * @return
	 */
	public ArrayList<Room> getRoomList() {
		ArrayList<Room> r = new ArrayList<Room>();
		for(Room rm : rooms.values()){
			r.add(rm);
		}
		return r;
	}

	
	public ArrayList<Player> getPlayers() {
		return players;
	}

	public void setPlayers(ArrayList<Player> players) {
		this.players = players;
	}

	public HashMap<String, Room> getRooms() {
		return this.rooms;
	}
	
	public void setRooms(HashMap<String, Room> rooms) {
		this.rooms = rooms;
	}

	public HashMap<String, Container> getContainers() {
		return containers;
	}

	public void setContainers(HashMap<String, Container> containers) {
		this.containers = containers;
	}

	public HashMap<String, Item> getItems() {
		return items;
	}

	public void setItems(HashMap<String, Item> items) {
		this.items = items;
	}
	
	public void setSelectedItem(Item i){
		selectedItem = i; 
	}
	public Item getSelectedItem(){
		return this.selectedItem; 
	}
	
	public void setSelectedInventory(Item i){
		selectedInventory = i; 
	}


	/* 2D ARRAYS FOR EACH DIRECTION IN THE STUDY ROOM */
	private String[][] STUDY_NORTH = {
			{"Study Room Bin", "", "", "", "", "Study Room Safe"},
			{"", "", "", "Chair", "", ""},
			{"", "", "", "Desk", "Desk", ""}
		};
	private String[][] STUDY_EAST = {
			{"Study Room Safe", "", "", "", "", "Lamp"},
			{"", "", "", "Desk", "", "", ""},
			{"", "Chair", "Desk", "", "", ""}
		};
	private String[][] STUDY_SOUTH = {
			{"Lamp", "", "Study Door", "Study Door", "", ""},
			{"", "", "", "", "", "Bookshelf"},
			{"", "", "", "", "", "Bookshelf"}
		};
	private String[][] STUDY_WEST = {
			{"", "Bookshelf", "Bookshelf", "", "", "Study Room Bin"},
			{"", "", "", "", "", ""},
			{"", "", "", "", "", ""}
		};
	
	/* 2D ARRAYS FOR EACH DIRECTION IN THE LIVING ROOM */
	private String[][] LIVING_NORTH = {
			{"Living Room Bin", "", "", "Sofa", "Sofa", ""},
			{"", "", "", "", "", ""},
			{"", "", "", "Long Table", "Long Table", ""}
		};
	private String[][] LIVING_EAST = {	//added a family portrait 
			{"", "Portrait", "", "Frame", "", "Living Room Picture"},
			{"Sofa", "", "Long Table", "", "", ""},
			{"Sofa", "", "Long Table", "", "", ""}
		};
	private String[][] LIVING_SOUTH = {
			{"", "", "", "", "", "Living Room Safe"},
			{"", "", "", "", "", ""},
			{"", "", "", "", "", ""}
		};
	private String[][] LIVING_WEST = {
			{"Safe", "", "", "", "", "Living Room Bin"},
			{"", "", "", "", "", ""},
			{"", "", "", "", "", ""}
		};

	/* 2D ARRAYS FOR EACH DIRECTION IN THE KITCHEN */
	private String[][] KITCHEN_NORTH = {
			{"Kitchen Bin", "", "", "", "", "Fridge"},
			{"", "", "", "Kitchen Table", "Kitchen Table", ""},
			{"", "", "", "", "", ""}
		};
	private String[][] KITCHEN_EAST = {
			{"Fridge", "", "", "", "", "Kitchen Picture"},
			{"", "Kitchen Table", "", "", "", ""},
			{"", "Kitchen Table", "", "", "", ""}
		};
	private String[][] KITCHEN_SOUTH = {
			{"Kitchen Picture", "", "", "", "", ""},
			{"", "", "", "", "", "Cupboard"},
			{"", "", "", "", "", "Cupboard"}
		};
	private String[][] KITCHEN_WEST = {
			{"", "Cupboard", "Cupboard", "", "", "Kitchen Bin"},
			{"", "", "", "", "", ""},
			{"", "", "", "", "", ""}
		};
	
	/* 2D ARRAYS FOR EACH DIRECTION IN THE BEDROOM */
	private String[][] BEDROOM_NORTH = {
			{"Bedroom Bin", "", "", "", "", "Bedroom Lamp"},
			{"", "Short Table", "", "", "Bed", "Bed"},
			{"", "", "", "", "", "Sidetable"}
		};
	private String[][] BEDROOM_EAST = {
			{"Bedroom Lamp", "Bed", "Sidetable", "", "", ""},
			{"", "Bed", "", "", "", ""},
			{"", "", "", "", "", ""}
		};
	private String[][] BEDROOM_SOUTH = {
			{"", "", "Bedroom Door", "Bedroom Door", "", "Bedroom Safe"},
			{"", "", "", "", "", ""},
			{"", "", "", "", "", ""}
		};
	private String[][] BEDROOM_WEST = {
			{"Bedroom Safe", "", "", "", "", "Bedroom Bin"},
			{"", "", "", "", "Short Table", ""},
			{"", "", "", "", "", ""}
		};

	

	/**
	 * Allow player to enter room
	 * 
	 * @param p	the player
	 * @param r	the room that player wants to enter
	 * 
	 * @return true if the player enter the room, false if the player cannot
	 */
	public boolean enterRoom(Player p, Room r){
		// if the room is a locked door, check if the player have the key
		if (r.isLocked()){
			//selectedInventory = items.get("Study Room Key");
			if(selectedInventory == null){
				System.out.println("Find the key!");
				return false;
			}
			if (selectedInventory.getName().equals(r.getKey())){
				p.enterRoom(r);
				return true;
			}
			return false;
		}
		// if not locked, let the player enter the room
		p.enterRoom(r);
		return true;
	}
	
	/**
	 * Allow player to leave the room
	 * 
	 * @param p the player
	 * 
	 * @return true if the player leave the room
	 */
	public void leaveRoom(Player p) throws EscapeException{
		//if the player is not in a room, throw exception
		if (p.getRoom() == null){
			throw new EscapeException("Player is in the hallway!!!");
		}
		p.leaveRoom();
		selectedItem = null;
	}
	
	/**
	 * Allow player to open container and get items inside
	 * 
	 * @param p the player
	 * @param con the container that needs to be opened
	 * 
	 * @return true if player can open the container, false if the player cannot
	 */
	public boolean openContainer(Player p, Container con){
		// check if the container is locked
		if (con.isLocked()){
			if (useItem(con, selectedInventory.getName())){
				if (con.getItems().isEmpty()){
					return true;//if there is no item in the container, do nothing
				}
				for (Item j: con.getItems()){
					p.pickUpItem(j);
				}
				return true;
			}
			return false;//if there is no appropriate key, do nothing.
		}
		
		if (con.getItems().isEmpty()){
			return false;
		}
		for (Item j: con.getItems()){
			p.pickUpItem(j);
		}
		return true;
	}
	
	/**
	 * Allows player to pick up item
	 * 
	 * @param p the player
	 * @param i the item player want to pick up
	 * 
	 * @return true if player can pick up item, false if the player cannot
	 */
	public boolean pickUpItem(Player p, Item i){
		if (!i.isPickable()){
			return false; // return false if item cannot be picked up;
		}
		p.pickUpItem(i);
		return true;
	}
	
	/**
	 * Allows player to drop item
	 * 
	 * @param p the player
	 * @param i the item player want to drop
	 * 
	 * @return true if player can drop item, false if the player cannot
	 */
	public boolean dropItem(Player p){
		if(selectedInventory == null) return false;
		if (p.getRoom() == null){
			return false;//cannot drop item in the hallway
		}
		p.getRoom().getBin().add(selectedInventory);
		p.getItems().remove(selectedInventory);
		selectedInventory = null;
		return true;
	}
}
