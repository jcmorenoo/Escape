package escape.gameworld;

import java.io.Serializable;
import java.util.ArrayList;

public class Player implements Serializable {

	private int id;
	private String name;
	private Room room;
	private ArrayList<Item> items;
	
	public enum Direction {
		NORTH, EAST, WEST, SOUTH
	}

	private Direction direction;
	private int points;

	public Player(int id, String name, Room room) {
		this.setId(id);
		this.room = room;
		this.name = name;
		setDirection(Direction.NORTH);
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Room getRoom() {
		return room;
	}

	public void setRoom(Room room) {
		this.room = room;
	}

	public ArrayList<Item> getItems() {
		return items;
	}

	public void setItems(ArrayList<Item> items) {
		this.items = items;

	}

	public Direction getDirection() {
		return direction;
	}

	public void setDirection(Direction direction) {
		this.direction = direction;
	}

	public int getPoints() {
		return points;
	}

	public void setPoints(int points) {
		this.points = points;
	}

	public void movePlayer(Direction d) {
		switch (d) {
		case NORTH:
			this.direction = Direction.NORTH;
			break;
		case EAST:
			this.direction = Direction.EAST;
			break;
		case WEST:
			this.direction = Direction.WEST;
			break;
		case SOUTH:
			this.direction = Direction.SOUTH;
			break;
		}
	}

	public boolean pickUpItem(Item i) {
		if (!i.isPickable())
			return false;
		
		items.add(i);
		return true;
	}

	public boolean dropItem(Item i) {
		if (room == null)
			return false;
		
		items.remove(i);
		room.getBin().add(i);
		return true;
	}

	public String examineItem(Item i) {
		return i.getDescription();
	}

	public void enterRoom(Room r) {
		this.room = r;
	}

	public void leaveRoom() {
		this.room = null;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

}