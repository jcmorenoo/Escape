package escape.gameworld;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.Serializable;
import java.util.ArrayList;

import escape.ui.GameCanvas;

public class Player implements Serializable {

	private int id;
	private String name;
	private Room room;
	private ArrayList<Item> items = new ArrayList<Item>();

	public enum Direction {
		NORTH, EAST, WEST, SOUTH
	}

	private Direction direction;

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

	/**
	 * Allows the player to pick up an item.
	 * If it is picked up, it will be removed to that room's list of items
	 *
	 * @param i
	 * @return true if picked up successfully, added to the player's list of items
	 * 		   false if item not pickable or player already has 5 items
	 */

	public boolean pickUpItem(Item i) {
		if (items.size() >= 5)
			return false;
		if (!i.isPickable())
			return false;

		if(i instanceof Container){
			this.items.add(i);
			this.room.removeContainer((Container)i);
			return true;
		}
		else{
			this.items.add(i);
			this.room.removeItem(i);
			return true;
		}
	}

	/**
	 * Remove item in the list
	 *
	 * @param s
	 * @return
	 */
	public boolean removeItem(String s) {
		for (Item i : getItems()) {
			if (i.getName().equals(s)) {
				getItems().remove(i);
				return true;
			}
		}
		return false;
	}

	/**
	 * Drops the item in the bin of the player's current room
	 *
	 * @param i
	 * @return
	 */
	public boolean dropItem(Item i) {
		if (room == null)
			return false;

		items.remove(i);
		room.getBin().add(i);
		return true;
	}

	/**
	 * Returns the item's description
	 *
	 * @param i
	 * @return
	 */
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

	/**
	 * Draws players in a room according to room's current list of players, and the direction they are facing
	 * @param g	- Graphics
	 * @param pId - Other player's ID
	 * @param currentDirection - Other player's current direction
	 * @param currentPlayerDirection - Current player's current direction
	 */
	public void draw(Graphics g, int pId, Direction currentDirection, Direction currentPlayerDirection, int cWidth, int cHeight) {
		switch (pId) {
		case 0:
			switch (currentPlayerDirection) {
			case NORTH:
				if (currentDirection.equals(Direction.SOUTH)) {
					g.drawImage(avatar1, scaleImgPos(.44, cWidth), scaleImgPos(.37, cHeight),
							scaleImgWidth(avatar1), scaleImgHeight(avatar1),
							null);
				}
				break;
			case EAST:
				if (currentDirection.equals(Direction.WEST)) {
					g.drawImage(avatar1, scaleImgPos(.44, cWidth), scaleImgPos(.37, cHeight),
							scaleImgWidth(avatar1), scaleImgHeight(avatar1),
							null);
				}
				break;
			case SOUTH:
				if (currentDirection.equals(Direction.NORTH)) {
					g.drawImage(avatar1, scaleImgPos(.44, cWidth), scaleImgPos(.37, cHeight),
							scaleImgWidth(avatar1), scaleImgHeight(avatar1),
							null);
				}
				break;
			case WEST:
				if (currentDirection.equals(Direction.EAST)) {
					g.drawImage(avatar1, scaleImgPos(.44, cWidth), scaleImgPos(.37, cHeight),
							scaleImgWidth(avatar1), scaleImgHeight(avatar1),
							null);
				}
				break;
			}
			break;
		case 1:
			switch (currentPlayerDirection) {
			case NORTH:
				if (currentDirection.equals(Direction.SOUTH)) {
					g.drawImage(avatar2, scaleImgPos(.44, cWidth), scaleImgPos(.37, cHeight),
							scaleImgWidth(avatar2), scaleImgHeight(avatar2),
							null);

				}
				break;
			case EAST:
				if (currentDirection.equals(Direction.WEST)) {
					g.drawImage(avatar2, scaleImgPos(.44, cWidth), scaleImgPos(.37, cHeight),
							scaleImgWidth(avatar2), scaleImgHeight(avatar2),
							null);
				}
				break;
			case SOUTH:
				if (currentDirection.equals(Direction.NORTH)) {
					g.drawImage(avatar2, scaleImgPos(.44, cWidth), scaleImgPos(.37, cHeight),
							scaleImgWidth(avatar2), scaleImgHeight(avatar2),
							null);
				}
				break;
			case WEST:
				if (currentDirection.equals(Direction.EAST)) {
					g.drawImage(avatar2, scaleImgPos(.44, cWidth), scaleImgPos(.37, cHeight),
							scaleImgWidth(avatar2), scaleImgHeight(avatar2),
							null);
				}
				break;
			}
			break;
		case 2:
			switch (currentPlayerDirection) {
			case NORTH:
				if (currentDirection.equals(Direction.SOUTH)) {
					g.drawImage(avatar3, scaleImgPos(.44, cWidth), scaleImgPos(.37, cHeight),
							scaleImgWidth(avatar3), scaleImgHeight(avatar3),
							null);

				}
				break;
			case EAST:
				if (currentDirection.equals(Direction.WEST)) {
					g.drawImage(avatar3, scaleImgPos(.44, cWidth), scaleImgPos(.37, cHeight),
							scaleImgWidth(avatar3), scaleImgHeight(avatar3),
							null);
				}
				break;
			case SOUTH:
				if (currentDirection.equals(Direction.NORTH)) {
					g.drawImage(avatar3, scaleImgPos(.44, cWidth), scaleImgPos(.37, cHeight),
							scaleImgWidth(avatar3), scaleImgHeight(avatar3),
							null);
				}
				break;
			case WEST:
				if (currentDirection.equals(Direction.EAST)) {
					g.drawImage(avatar3, scaleImgPos(.44, cWidth), scaleImgPos(.37, cHeight),
							scaleImgWidth(avatar3), scaleImgHeight(avatar3),
							null);
				}
				break;
			}
			break;
		case 3:
			switch (currentPlayerDirection) {
			case NORTH:
				if (currentDirection.equals(Direction.SOUTH)) {
					g.drawImage(avatar4, scaleImgPos(.44, cWidth), scaleImgPos(.37, cHeight),
							scaleImgWidth(avatar4), scaleImgHeight(avatar4),
							null);

				}
				break;
			case EAST:
				if (currentDirection.equals(Direction.WEST)) {
					g.drawImage(avatar4, scaleImgPos(.44, cWidth), scaleImgPos(.37, cHeight),
							scaleImgWidth(avatar4), scaleImgHeight(avatar4),
							null);
				}
				break;
			case SOUTH:
				if (currentDirection.equals(Direction.NORTH)) {
					g.drawImage(avatar4, scaleImgPos(.44, cWidth), scaleImgPos(.37, cHeight),
							scaleImgWidth(avatar4), scaleImgHeight(avatar4),
							null);
				}
				break;
			case WEST:
				if (currentDirection.equals(Direction.EAST)) {
					g.drawImage(avatar4, scaleImgPos(.44, cWidth), scaleImgPos(.37, cHeight),
							scaleImgWidth(avatar4), scaleImgHeight(avatar4),
							null);
				}
				break;
			}
			break;
		case 4:
			switch (currentPlayerDirection) {
			case NORTH:
				if (currentDirection.equals(Direction.SOUTH)) {
					g.drawImage(avatar5, scaleImgPos(.44, cWidth), scaleImgPos(.37, cHeight),
							scaleImgWidth(avatar5), scaleImgHeight(avatar5),
							null);

				}
				break;
			case EAST:
				if (currentDirection.equals(Direction.WEST)) {
					g.drawImage(avatar5, scaleImgPos(.44, cWidth), scaleImgPos(.37, cHeight),
							scaleImgWidth(avatar5), scaleImgHeight(avatar5),
							null);
				}
				break;
			case SOUTH:
				if (currentDirection.equals(Direction.NORTH)) {
					g.drawImage(avatar5, scaleImgPos(.44, cWidth), scaleImgPos(.37, cHeight),
							scaleImgWidth(avatar5), scaleImgHeight(avatar5),
							null);
				}
				break;
			case WEST:
				if (currentDirection.equals(Direction.EAST)) {
					g.drawImage(avatar5, scaleImgPos(.44, cWidth), scaleImgPos(.37, cHeight),
							scaleImgWidth(avatar5), scaleImgHeight(avatar5),
							null);
				}
				break;
			}
			break;
		case 5:
			switch (currentPlayerDirection) {
			case NORTH:
				if (currentDirection.equals(Direction.SOUTH)) {
					g.drawImage(avatar6, scaleImgPos(.44, cWidth), scaleImgPos(.37, cHeight),
							scaleImgWidth(avatar6), scaleImgHeight(avatar6),
							null);

				}
				break;
			case EAST:
				if (currentDirection.equals(Direction.WEST)) {
					g.drawImage(avatar6, scaleImgPos(.44, cWidth), scaleImgPos(.37, cHeight),
							scaleImgWidth(avatar6), scaleImgHeight(avatar6),
							null);
				}
				break;
			case SOUTH:
				if (currentDirection.equals(Direction.NORTH)) {
					g.drawImage(avatar6, scaleImgPos(.44, cWidth), scaleImgPos(.37, cHeight),
							scaleImgWidth(avatar6), scaleImgHeight(avatar6),
							null);
				}
				break;
			case WEST:
				if (currentDirection.equals(Direction.EAST)) {
					g.drawImage(avatar6, scaleImgPos(.44, cWidth), scaleImgPos(.37, cHeight),
							scaleImgWidth(avatar6), scaleImgHeight(avatar6),
							null);
				}
				break;
			}
			break;
		}
	}

	/*---------------ITEM IMAGE SCALING---------------
	 * These methods help with scaling the item in accordance to the player's screen resolution
	 */
	protected static int scaleImgPos(double imgPos, int i) {
		return (int) (imgPos * i);
	}

	protected static int scaleImgWidth(Image img) {
		return (int) (img.getWidth(null) * Item.IMG_WIDTH_RESCALE);
	}

	protected static int scaleImgHeight(Image img) {
		return (int) (img.getHeight(null) * Item.IMG_HEIGHT_RESCALE);
	}

	/*---------------LOAD PLAYER IMAGES---------------*/
	static final BufferedImage avatar1 = GameCanvas
			.loadImage("/images/avatar1.png");
	private static final BufferedImage avatar2 = GameCanvas
			.loadImage("/images/avatar2.png");
	 private static final BufferedImage avatar3 =
	 GameCanvas.loadImage("/images/avatar3.png");
	 private static final BufferedImage avatar4 =
	 GameCanvas.loadImage("/images/avatar4.png");
	 private static final BufferedImage avatar5 =
	 GameCanvas.loadImage("/images/avatar5.png");
	 private static final BufferedImage avatar6 =
	 GameCanvas.loadImage("/images/avatar6.png");
}