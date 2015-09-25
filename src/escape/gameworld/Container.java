package escape.gameworld;

import java.util.ArrayList;

public class Container extends Item {

	// private String name;
	// private String description;
	//
	private ArrayList<Item> items = new ArrayList<Item>();

	private boolean locked;
	private String key;

	private enum direction {
		NORTH, EAST, WEST, SOUTH
	}

	public Container(String n, String d, boolean movable, boolean pickable, boolean l, String key) {
		super(n, d, movable, pickable);
		this.locked = l;
		this.key = key;
		
	}
	
	public void add(Item i) {
		items.add(i);
	}
	
	public ArrayList<Item> getItems() {
		return items;
	}

	public void setItems(ArrayList<Item> items) {
		this.items = items;
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
	
}
