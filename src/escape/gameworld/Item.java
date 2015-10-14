package escape.gameworld;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.Rectangle;
import java.io.Serializable;

import escape.gameworld.Player.Direction;
import escape.ui.GameCanvas;

/**
 * Class which represents an item in the GameWorld. Implements serializable so it can be
 * passed along the network
 * @author semillkasz
 *
 */
public class Item implements Serializable {
	private double x;
	private double y;
	private String name;
	private String description;

	private boolean pickable;
	private Rectangle boundingBox;
	
	/**
	 * Item constructor
	 * @param String n - name
	 * @param String d - description
	 * @param Boolean pickable - pickable
	 */
	public Item(String n, String d, boolean pickable) {
		this.name = n;
		this.description = d;
		this.pickable = pickable;
	}
	
	/**
	 * Method returning the Rectangle boundingbox
	 * @return
	 */
	public Rectangle getBoundingBox() {
		return boundingBox;
	}
	
	/**
	 * Method which sets the bounding box for this item
	 * @param boundingBox
	 */
	public void setBoundingBox(Rectangle boundingBox) {
		this.boundingBox = boundingBox;
	}

	public double getX() {
		return x;
	}

	public void setX(double x) {
		this.x = x;
	}

	public double getY() {
		return y;
	}

	public void setY(double y) {
		this.y = y;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public boolean isPickable() {
		return pickable;
	}

	public void setPickable(boolean pickable) {
		this.pickable = pickable;
	}

	/**
	 * Allows player to pick up this item
	 *
	 * @param p
	 */
	public void pickUp(Player p) {
		p.pickUpItem(this);
	}

	/**
	 *
	 * Creates a bounding box for this item
	 *
	 * @param x
	 *            x position in the game canvas
	 *
	 * @param y
	 *            y position in the game canvas
	 *
	 * @param w
	 *            width of the item
	 *
	 * @param h
	 *            height of the item
	 */
	public void boundingBox(int x, int y, int w, int h) {
		this.boundingBox = new Rectangle(x, y, w, h);
	}

	/**
	 *
	 * Draws all the items in the room the player is currently in, and in the
	 *
	 * direction that the player is facing
	 *
	 * @param g
	 *            Graphics
	 *
	 * @param r
	 *            Current room player is in
	 *
	 * @param d
	 *            Direction player is facing
	 */
	public void draw(Graphics g, Room r, Direction d, int w, int h) {
		for (Item i : r.getItems()) {
			drawItem(g, i, d, w, h);
		}
	}

	/**
	 * Draws items in a room, from a distance
	 *
	 * @param g
	 *            - Graphics
	 *
	 * @param string
	 *            - Room name which items are drawn in
	 */
	public static void draw(Graphics g, String adjRoom, boolean display, int cWidth, int cHeight) {
		switch (adjRoom) {
		case "Kitchen":
			g.drawImage(kitchenTable, scaleInsideImgPos(0.4, cWidth),
					scaleInsideImgPos(0.49, cHeight), scaleInsideImgWidth(kitchenTable),
					scaleInsideImgHeight(kitchenTable), null);
			if (!display) {
				g.drawImage(kitchenPicture, scaleInsideImgPos(0.8, cWidth),
						scaleInsideImgPos(0.68, cHeight),
						scaleInsideImgWidth(kitchenPicture),
						scaleInsideImgHeight(kitchenPicture), null);
			}
			break;
		case "Living Room":
			g.drawImage(sofa, scaleInsideImgPos(0.5, cWidth),
					scaleInsideImgPos(0.44, cHeight),
					scaleInsideImgWidth(kitchenTable),
					scaleInsideImgHeight(kitchenTable), null);
			g.drawImage(longTable, scaleInsideImgPos(0.53, cWidth),
					scaleInsideImgPos(0.54, cHeight), scaleInsideImgWidth(longTable),
					scaleInsideImgHeight(longTable), null);
			break;
		}
	}

	/*---------------ITEM IMAGE DIRECTION HELPER METHOD---------------*/
	/**
	 *
	 * Draws an item depending on the player's direction *
	 *
	 * @param g
	 *            Graphics
	 *
	 * @param i
	 *            Item to be drawn
	 *
	 * @param d
	 *            Player's current direction
	 */
	private void drawItem(Graphics g, Item i, Direction d, int cWidth, int cHeight) {
		String itemName = i.getName();
		switch (itemName) {
		case "Bed":
			switch (d) {
			case NORTH:
				g.drawImage(bedSide, scaleImgPos(0.66, cWidth), scaleImgPos(0.4,cHeight),
						scaleImgWidth(bedSide), scaleImgHeight(bedSide), null);
				i.boundingBox(scaleImgPos(0.66, cWidth), scaleImgPos(0.4,cHeight),
						scaleImgWidth(bedSide), scaleImgHeight(bedSide));
				break;
			case EAST:
				g.drawImage(bed, scaleImgPos(0.06, cWidth), scaleImgPos(0.45,cHeight),
						scaleImgWidth(bed), scaleImgHeight(bed), null);
				i.boundingBox(scaleImgPos(0.06, cWidth), scaleImgPos(0.45,cHeight),
						scaleImgWidth(bed), scaleImgHeight(bed));
				break;
			}
			break;
		case "Bedroom Door":
			switch (d) {
			case SOUTH:
				g.drawImage(bedroomDoor, scaleImgPos(0.42, cWidth), scaleImgPos(0.28, cHeight),
						scaleImgWidth(bedroomDoor),
						scaleImgHeight(bedroomDoor), null);
				i.boundingBox(scaleImgPos(0.42, cWidth), scaleImgPos(0.28, cHeight),
						scaleImgWidth(bedroomDoor),
						scaleImgHeight(bedroomDoor));
				break;
			}
			break;
		case "Bedroom Lamp":
			switch (d) {
			case NORTH:
				g.drawImage(bedroomLamp, scaleImgPos(0.9, cWidth), scaleImgPos(0.3, cHeight),
						scaleImgWidth(bedroomLamp),
						scaleImgHeight(bedroomLamp), null);
				i.boundingBox(scaleImgPos(0.9, cWidth), scaleImgPos(0.3, cHeight),
						scaleImgWidth(bedroomLamp),
						scaleImgHeight(bedroomLamp));
				break;
			case EAST:
				g.drawImage(bedroomLamp, scaleImgPos(0.05, cWidth), scaleImgPos(0.3, cHeight),
						scaleImgWidth(bedroomLamp),
						scaleImgHeight(bedroomLamp), null);
				i.boundingBox(scaleImgPos(0.05, cWidth), scaleImgPos(0.3, cHeight),
						scaleImgWidth(bedroomLamp),
						scaleImgHeight(bedroomLamp));
				break;
			}
			break;
		case "Chair":
			switch (d) {
			case NORTH:
				g.drawImage(chair, scaleImgPos(.4,cWidth), scaleImgPos(.4,cHeight),
						scaleImgWidth(chair), scaleImgHeight(chair), null);
				i.boundingBox(scaleImgPos(.4,cWidth), scaleImgPos(.4,cHeight),
						scaleImgWidth(chair), scaleImgHeight(chair));
				break;
			case EAST:

				g.drawImage(chairSide, scaleImgPos(.15,cWidth), scaleImgPos(.41,cHeight),
						scaleImgWidth(chairSide), scaleImgHeight(chairSide),
						null);
				i.boundingBox(scaleImgPos(.15,cWidth), scaleImgPos(.41,cHeight),
						scaleImgWidth(chairSide), scaleImgHeight(chairSide));
				break;
			}
			break;
		case "Frame":
			switch (d) {
			case EAST:
				g.drawImage(pictureFrame, scaleImgPos(0.4, cWidth), scaleImgPos(0.12, cHeight),
						scaleImgWidth(pictureFrame),
						scaleImgHeight(pictureFrame), null);
				i.boundingBox(scaleImgPos(0.4, cWidth), scaleImgPos(0.12, cHeight),
						scaleImgWidth(pictureFrame),
						scaleImgHeight(pictureFrame));
				break;
			}
			break;
		case "Desk":
			switch (d) {
			case NORTH:
				g.drawImage(desk, scaleImgPos(.31,cWidth), scaleImgPos(.465,cHeight),
						scaleImgWidth(desk), scaleImgHeight(desk), null);
				i.boundingBox(scaleImgPos(.31,cWidth), scaleImgPos(.465,cHeight),
						scaleImgWidth(desk), scaleImgHeight(desk));
				break;
			case EAST:
				g.drawImage(deskSide, scaleImgPos(.24,cWidth), scaleImgPos(.46,cHeight),
						scaleImgWidth(deskSide), scaleImgHeight(deskSide), null);
				i.boundingBox(scaleImgPos(.24,cWidth), scaleImgPos(.46,cHeight),
						scaleImgWidth(deskSide), scaleImgHeight(deskSide));
				break;
			}
			break;
		case "Kitchen Picture":
			switch (d) {
			case EAST:
				g.drawImage(kitchenPicture, scaleImgPos(0.7, cWidth), scaleImgPos(0.64, cHeight),
						scaleImgWidth(kitchenPicture),
						scaleImgHeight(kitchenPicture), null);
				i.boundingBox( scaleImgPos(0.7, cWidth), scaleImgPos(0.64, cHeight),
						scaleImgWidth(kitchenPicture),
						scaleImgHeight(kitchenPicture));
				break;
			case SOUTH:
				g.drawImage(kitchenPicture, scaleImgPos(0.24, cWidth), scaleImgPos(0.64, cHeight),
						scaleImgWidth(kitchenPicture),
						scaleImgHeight(kitchenPicture), null);
				i.boundingBox(scaleImgPos(0.24, cWidth), scaleImgPos(0.64, cHeight),
						scaleImgWidth(kitchenPicture),
						scaleImgHeight(kitchenPicture));
				break;
			}
			break;
		case "Kitchen Table":
			switch (d) {
			case NORTH:
				g.drawImage(kitchenTable, scaleImgPos(0.30, cWidth), scaleImgPos(0.448, cHeight),
						scaleImgWidth(kitchenTable),
						scaleImgHeight(kitchenTable), null);
				i.boundingBox(scaleImgPos(0.30, cWidth), scaleImgPos(0.448, cHeight),
						scaleImgWidth(kitchenTable),
						scaleImgHeight(kitchenTable));
				break;
			case EAST:
				g.drawImage(kitchenTableLeft, scaleImgPos(0.34, cWidth),
						scaleImgPos(0.454, cHeight), scaleImgWidth(kitchenTableLeft),
						scaleImgHeight(kitchenTableLeft), null);
				i.boundingBox(scaleImgPos(0.34, cWidth),
						scaleImgPos(0.454, cHeight),
						scaleImgWidth(kitchenTableLeft),
						scaleImgHeight(kitchenTableLeft));
				break;
			}
			break;

		case "Living Room Picture":
			switch (d) {
			case EAST:
				g.drawImage(livingRoomPicture, scaleImgPos(0.7, cWidth), scaleImgPos(0.12, cHeight), scaleImgWidth(livingRoomPicture),
						scaleImgHeight(livingRoomPicture), null);
				i.boundingBox(scaleImgPos(0.7, cWidth), scaleImgPos(0.12, cHeight), scaleImgWidth(livingRoomPicture),
						scaleImgHeight(livingRoomPicture));
				break;
			}
			break;
		case "Long Table":
			switch (d) {
			case NORTH:
				g.drawImage(longTable, scaleImgPos(0.4, cWidth), scaleImgPos(0.52, cHeight),
						scaleImgWidth(longTable), scaleImgHeight(longTable),
						null);
				i.boundingBox(scaleImgPos(0.4, cWidth), scaleImgPos(0.52, cHeight),
						scaleImgWidth(longTable), scaleImgHeight(longTable));
				break;
			case EAST:
				g.drawImage(longTableSide, scaleImgPos(0.3, cWidth), scaleImgPos(0.52, cHeight),
						scaleImgWidth(longTableSide),
						scaleImgHeight(longTableSide), null);
				i.boundingBox(scaleImgPos(0.3, cWidth), scaleImgPos(0.52, cHeight),
						scaleImgWidth(longTableSide),
						scaleImgHeight(longTableSide));
				break;
			}
			break;
		case "Portrait":
			switch (d) {
			case EAST:
				g.drawImage(portrait, scaleImgPos(0.12, cWidth), scaleImgPos(0.12, cHeight),
						scaleImgWidth(portrait), scaleImgHeight(portrait), null);
				i.boundingBox(scaleImgPos(0.12, cWidth), scaleImgPos(0.12, cHeight),
						scaleImgWidth(portrait), scaleImgHeight(portrait));
				break;
			}
			break;
		case "Short Table":
			switch (d) {
			case NORTH:
				g.drawImage(shortTable, scaleImgPos(0.25, cWidth), scaleImgPos(0.52, cHeight),
						scaleImgWidth(shortTable), scaleImgHeight(shortTable),
						null);
				i.boundingBox(scaleImgPos(0.25, cWidth), scaleImgPos(0.52, cHeight),
						scaleImgWidth(shortTable), scaleImgHeight(shortTable));
				break;
			case WEST:
				g.drawImage(shortTableSide, scaleImgPos(0.4, cWidth), scaleImgPos(0.52, cHeight),
						scaleImgWidth(shortTableSide),
						scaleImgHeight(shortTableSide), null);
				i.boundingBox(scaleImgPos(0.4, cWidth), scaleImgPos(0.52, cHeight),
						scaleImgWidth(shortTableSide),
						scaleImgHeight(shortTableSide));
				break;
			}
			break;
		case "Sofa":
			switch (d) {
			case NORTH:
				g.drawImage(sofa, scaleImgPos(0.37, cWidth), scaleImgPos(0.41, cHeight),
						scaleImgWidth(sofa), scaleImgHeight(sofa), null);
				i.boundingBox(scaleImgPos(0.37, cWidth), scaleImgPos(0.41, cHeight),
						scaleImgWidth(sofa), scaleImgHeight(sofa));
				break;
			case EAST:
				g.drawImage(sofaLeft, scaleImgPos(0.08, cWidth), scaleImgPos(0.45, cHeight),
						scaleImgWidth(sofaLeft), scaleImgHeight(sofaLeft), null);
				i.boundingBox(scaleImgPos(0.08, cWidth), scaleImgPos(0.45, cHeight),
						scaleImgWidth(sofaLeft), scaleImgHeight(sofaLeft));
				break;
			}
			break;
		case "Study Door":
			switch (d) {
			case SOUTH:
				g.drawImage(studyDoor, scaleImgPos(.45,cWidth), scaleImgPos(.27,cHeight),
						scaleImgWidth(studyDoor), scaleImgHeight(studyDoor),
						null);
				i.boundingBox(scaleImgPos(.45,cWidth), scaleImgPos(.27,cHeight),
						scaleImgWidth(studyDoor), scaleImgHeight(studyDoor));
				break;
			}
			break;
		}
	}

	/**
	 * Draws the items in the inventory
	 *
	 * @param g
	 * @param itemOrder
	 */
	public void drawInventoryItem(Graphics g, int itemOrder, int cWidth, int cHeight) {
		String invItem = this.name;
		switch (invItem) {
		case "Kitchen Picture":
			switch (itemOrder) {
			case 0:
				g.drawImage(kitchenPicture, scaleImgPos(0.10, cWidth), scaleImgPos(0.88, cHeight),
						scaleImgWidth(kitchenPicture),
						scaleImgHeight(kitchenPicture), null);
				this.boundingBox(scaleImgPos(0.10, cWidth), scaleImgPos(0.88, cHeight),
						scaleImgWidth(kitchenPicture),
						scaleImgHeight(kitchenPicture));
				break;
			case 1:
				g.drawImage(kitchenPicture, scaleImgPos(0.280, cWidth), scaleImgPos(0.88, cHeight),
						scaleImgWidth(kitchenPicture),
						scaleImgHeight(kitchenPicture), null);
				this.boundingBox(scaleImgPos(0.280, cWidth), scaleImgPos(0.88, cHeight),
						scaleImgWidth(kitchenPicture),
						scaleImgHeight(kitchenPicture));
				break;
			case 2:
				g.drawImage(kitchenPicture, scaleImgPos(0.450, cWidth), scaleImgPos(0.88, cHeight),
						scaleImgWidth(kitchenPicture),
						scaleImgHeight(kitchenPicture), null);
				this.boundingBox(scaleImgPos(0.450, cWidth), scaleImgPos(0.88, cHeight),
						scaleImgWidth(kitchenPicture),
						scaleImgHeight(kitchenPicture));
				break;
			case 3:
				g.drawImage(kitchenPicture, scaleImgPos(0.629, cWidth), scaleImgPos(0.88, cHeight),
						scaleImgWidth(kitchenPicture),
						scaleImgHeight(kitchenPicture), null);
				this.boundingBox(scaleImgPos(0.629, cWidth), scaleImgPos(0.88, cHeight),
						scaleImgWidth(kitchenPicture),
						scaleImgHeight(kitchenPicture));
				break;
			case 4:
				g.drawImage(kitchenPicture, scaleImgPos(0.795, cWidth), scaleImgPos(0.88, cHeight),
						scaleImgWidth(kitchenPicture),
						scaleImgHeight(kitchenPicture), null);
				this.boundingBox(scaleImgPos(0.795, cWidth), scaleImgPos(0.88, cHeight),
						scaleImgWidth(kitchenPicture),
						scaleImgHeight(kitchenPicture));
				break;
			}
			break;
		case "Paper":
			switch (itemOrder) {
			case 0:
				g.drawImage(paper, scaleImgPos(0.12, cWidth), scaleImgPos(0.879, cHeight),
						scaleImgWidth(paper), scaleImgHeight(paper), null);
				this.boundingBox(scaleImgPos(0.12, cWidth), scaleImgPos(0.879, cHeight),
						scaleImgWidth(paper), scaleImgHeight(paper));
				break;
			case 1:
				g.drawImage(paper, scaleImgPos(0.29, cWidth), scaleImgPos(0.879, cHeight),
						scaleImgWidth(paper), scaleImgHeight(paper), null);
				this.boundingBox(scaleImgPos(0.29, cWidth), scaleImgPos(0.879, cHeight),
						scaleImgWidth(paper), scaleImgHeight(paper));
				break;
			case 2:
				g.drawImage(paper, scaleImgPos(0.46, cWidth), scaleImgPos(0.879, cHeight),
						scaleImgWidth(paper), scaleImgHeight(paper), null);
				this.boundingBox(scaleImgPos(0.46, cWidth), scaleImgPos(0.879, cHeight),
						scaleImgWidth(paper), scaleImgHeight(paper));
				break;
			case 3:
				g.drawImage(paper, scaleImgPos(0.63, cWidth), scaleImgPos(0.879, cHeight),
						scaleImgWidth(paper), scaleImgHeight(paper), null);
				this.boundingBox(scaleImgPos(0.63, cWidth), scaleImgPos(0.879, cHeight),
						scaleImgWidth(paper), scaleImgHeight(paper));
				break;
			case 4:
				g.drawImage(paper, scaleImgPos(0.82, cWidth), scaleImgPos(0.879, cHeight),
						scaleImgWidth(paper), scaleImgHeight(paper), null);
				this.boundingBox(scaleImgPos(0.82, cWidth), scaleImgPos(0.879, cHeight),
						scaleImgWidth(paper), scaleImgHeight(paper));
				break;
			}
			break;
		case "Key":
			switch (itemOrder) {
			case 0:
				g.drawImage(mainKey, scaleImgPos(0.11, cWidth), scaleImgPos(0.85, cHeight), 70, 70,
						null);
				this.boundingBox(scaleImgPos(0.11, cWidth), scaleImgPos(0.85, cHeight), 70, 70);
				break;
			case 1:
				g.drawImage(mainKey, scaleImgPos(0.28, cWidth), scaleImgPos(0.85, cHeight), 70, 70,
						null);
				this.boundingBox(scaleImgPos(0.28, cWidth), scaleImgPos(0.85, cHeight), 70, 70);
				break;
			case 2:
				g.drawImage(mainKey, scaleImgPos(0.46, cWidth), scaleImgPos(0.85, cHeight), 70, 70,
						null);
				this.boundingBox(scaleImgPos(0.46, cWidth), scaleImgPos(0.85, cHeight), 70, 70);
				break;
			case 3:
				g.drawImage(mainKey, scaleImgPos(0.63, cWidth), scaleImgPos(0.85, cHeight), 70, 70,
						null);
				this.boundingBox(scaleImgPos(0.63, cWidth), scaleImgPos(0.85, cHeight), 70, 70);
				break;
			case 4:
				g.drawImage(mainKey, scaleImgPos(0.80, cWidth), scaleImgPos(0.85, cHeight), 70, 70,
						null);
				this.boundingBox(scaleImgPos(0.80, cWidth), scaleImgPos(0.85, cHeight), 70, 70);
				break;
			}
			break;
		case "Bedroom Safe Key":
			switch (itemOrder) {
			case 0:
				g.drawImage(bedroomSafeKey, scaleImgPos(0.11, cWidth), scaleImgPos(0.85, cHeight),
						70, 70, null);
				this.boundingBox(scaleImgPos(0.11, cWidth), scaleImgPos(0.85, cHeight), 70, 70);
				break;
			case 1:
				g.drawImage(bedroomSafeKey, scaleImgPos(0.28, cWidth), scaleImgPos(0.85, cHeight),
						70, 70, null);
				this.boundingBox(scaleImgPos(0.28, cWidth), scaleImgPos(0.85, cHeight), 70, 70);
				break;
			case 2:
				g.drawImage(bedroomSafeKey, scaleImgPos(0.46, cWidth), scaleImgPos(0.85, cHeight),
						70, 70, null);
				this.boundingBox( scaleImgPos(0.46, cWidth), scaleImgPos(0.85, cHeight), 70, 70);
				break;
			case 3:
				g.drawImage(bedroomSafeKey, scaleImgPos(0.63, cWidth), scaleImgPos(0.85, cHeight),
						70, 70, null);
				this.boundingBox(scaleImgPos(0.63, cWidth), scaleImgPos(0.85, cHeight), 70, 70);
				break;
			case 4:
				g.drawImage(bedroomSafeKey, scaleImgPos(0.80, cWidth), scaleImgPos(0.85, cHeight),
						70, 70, null);
				this.boundingBox(scaleImgPos(0.80, cWidth), scaleImgPos(0.85, cHeight), 70, 70);
				break;
			}
			break;
		case "Cupboard Key":
			switch (itemOrder) {
			case 0:
				g.drawImage(cupboardKey, scaleImgPos(0.11, cWidth), scaleImgPos(0.85, cHeight), 70,
						70, null);
				this.boundingBox(scaleImgPos(0.11, cWidth), scaleImgPos(0.85, cHeight), 70, 70);
				break;
			case 1:
				g.drawImage(cupboardKey, scaleImgPos(0.28, cWidth), scaleImgPos(0.85, cHeight),
						70, 70, null);
				this.boundingBox(scaleImgPos(0.28, cWidth), scaleImgPos(0.85, cHeight), 70, 70);
				break;
			case 2:
				g.drawImage(cupboardKey, scaleImgPos(0.46, cWidth), scaleImgPos(0.85, cHeight),
						70, 70, null);
				this.boundingBox(scaleImgPos(0.46, cWidth), scaleImgPos(0.85, cHeight), 70, 70);
				break;
			case 3:
				g.drawImage(cupboardKey, scaleImgPos(0.63, cWidth), scaleImgPos(0.85, cHeight),
						70, 70, null);
				this.boundingBox(scaleImgPos(0.63, cWidth), scaleImgPos(0.85, cHeight), 70, 70);
				break;
			case 4:
				g.drawImage(cupboardKey, scaleImgPos(0.80, cWidth), scaleImgPos(0.85, cHeight),
						70, 70, null);
				this.boundingBox(scaleImgPos(0.80, cWidth), scaleImgPos(0.85, cHeight), 70, 70);
				break;
			}
			break;
		case "Study Room Key":
			switch (itemOrder) {
			case 0:
				g.drawImage(studyRoomKey, scaleImgPos(0.11, cWidth), scaleImgPos(0.85, cHeight),
						70, 70, null);
				this.boundingBox(scaleImgPos(0.11, cWidth), scaleImgPos(0.85, cHeight), 70, 70);
				break;
			case 1:
				g.drawImage(studyRoomKey, scaleImgPos(0.28, cWidth), scaleImgPos(0.85, cHeight),
						70, 70, null);
				this.boundingBox(scaleImgPos(0.28, cWidth), scaleImgPos(0.85, cHeight), 70, 70);
				break;
			case 2:
				g.drawImage(studyRoomKey, scaleImgPos(0.46, cWidth), scaleImgPos(0.85, cHeight),
						70, 70, null);
				this.boundingBox(scaleImgPos(0.46, cWidth), scaleImgPos(0.85, cHeight), 70, 70);
				break;
			case 3:
				g.drawImage(studyRoomKey, scaleImgPos(0.63, cWidth), scaleImgPos(0.85, cHeight),
						70, 70, null);
				this.boundingBox(scaleImgPos(0.63, cWidth), scaleImgPos(0.85, cHeight), 70, 70);
				break;
			case 4:
				g.drawImage(studyRoomKey, scaleImgPos(0.80, cWidth), scaleImgPos(0.85, cHeight),
						70, 70, null);
				this.boundingBox(scaleImgPos(0.80, cWidth), scaleImgPos(0.85, cHeight), 70, 70);
				break;
			}
			break;
		case "Study Room Safe Key":
			switch (itemOrder) {
			case 0:
				g.drawImage(studyRoomSafeKey, scaleImgPos(0.11, cWidth),
						scaleImgPos(0.85, cHeight), 70, 70, null);
				this.boundingBox(scaleImgPos(0.11, cWidth),
						scaleImgPos(0.85, cHeight), 70, 70);
				break;
			case 1:
				g.drawImage(studyRoomSafeKey, scaleImgPos(0.28, cWidth),
						scaleImgPos(0.85, cHeight), 70, 70, null);
				this.boundingBox(scaleImgPos(0.28, cWidth),
						scaleImgPos(0.85, cHeight), 70, 70);
				break;
			case 2:
				g.drawImage(studyRoomSafeKey, scaleImgPos(0.46, cWidth),
						scaleImgPos(0.85, cHeight), 70, 70, null);
				this.boundingBox(scaleImgPos(0.46, cWidth),
						scaleImgPos(0.85, cHeight), 70, 70);
				break;
			case 3:
				g.drawImage(studyRoomSafeKey, scaleImgPos(0.63, cWidth),
						scaleImgPos(0.85, cHeight), 70, 70, null);
				this.boundingBox(scaleImgPos(0.63, cWidth),
						scaleImgPos(0.85, cHeight), 70, 70);
				break;
			case 4:
				g.drawImage(studyRoomSafeKey, scaleImgPos(0.80, cWidth),
						scaleImgPos(0.85, cHeight), 70, 70, null);
				this.boundingBox(scaleImgPos(0.80, cWidth),
						scaleImgPos(0.85, cHeight), 70, 70);
				break;
			}
			break;
		case "Matches":
			switch (itemOrder) {
			case 0:
				g.drawImage(matches, scaleImgPos(.1,cWidth), scaleImgPos(.85,cHeight), 60, 70,
						null);
				this.boundingBox(scaleImgPos(.1,cWidth), scaleImgPos(.85,cHeight), 60, 70);
				break;
			case 1:
				g.drawImage(matches, scaleImgPos(.29,cWidth), scaleImgPos(.85,cHeight), 60, 70,
						null);

				this.boundingBox(scaleImgPos(.29,cWidth), scaleImgPos(.85,cHeight), 60, 70);
				break;
			case 2:
				g.drawImage(matches, scaleImgPos(.46,cWidth), scaleImgPos(.85,cHeight), 60, 70,
						null);

				this.boundingBox(scaleImgPos(.46,cWidth), scaleImgPos(.85,cHeight), 60, 70);
				break;
			case 3:
				g.drawImage(matches, scaleImgPos(.64,cWidth), scaleImgPos(.85,cHeight), 60, 70,
						null);

				this.boundingBox(scaleImgPos(.64,cWidth), scaleImgPos(.85,cHeight), 60, 70);
				break;
			case 4:
				g.drawImage(matches, scaleImgPos(.81,cWidth), scaleImgPos(.85,cHeight), 60, 70,
						null);
				this.boundingBox(scaleImgPos(.81,cWidth), scaleImgPos(.85,cHeight), 60, 70);
				break;
			}
			break;
		case "Lamp":
			switch (itemOrder) {
			case 0:
				g.drawImage(lamp, scaleImgPos(0.13, cWidth), scaleImgPos(0.86, cHeight), 30, 60,
						null);


				this.boundingBox(scaleImgPos(0.13, cWidth), scaleImgPos(0.86, cHeight), 30, 60);
				break;
			case 1:
				g.drawImage(lamp, scaleImgPos(0.30, cWidth), scaleImgPos(0.86, cHeight), 30, 60,
						null);

				this.boundingBox(scaleImgPos(0.30, cWidth), scaleImgPos(0.86, cHeight), 30, 60);
				break;
			case 2:
				g.drawImage(lamp, scaleImgPos(0.48, cWidth), scaleImgPos(0.86, cHeight), 30, 60,
						null);

				this.boundingBox(scaleImgPos(0.48, cWidth), scaleImgPos(0.86, cHeight), 30, 60);
				break;
			case 3:
				g.drawImage(lamp, scaleImgPos(0.65, cWidth), scaleImgPos(0.86, cHeight), 30, 60,
						null);

				this.boundingBox(scaleImgPos(0.65, cWidth), scaleImgPos(0.86, cHeight), 30, 60);
				break;
			case 4:
				g.drawImage(lamp, scaleImgPos(0.824, cWidth), scaleImgPos(0.86, cHeight), 30, 60,
						null);
				this.boundingBox(scaleImgPos(0.824, cWidth), scaleImgPos(0.86, cHeight), 30, 60);
				break;
			}

		}
	}


	/*---------------ITEM IMAGE SCALING---------------
	 * These constants and methods help with scaling the item in accordance
	 * to the player's screen resolution
	 */
	protected static final double IMG_POS_RESCALE = 0.8;
	protected static final double IMG_WIDTH_RESCALE = 0.8;
	protected static final double IMG_HEIGHT_RESCALE = 0.814;
	private static final double INNER_BACKGROUND_WIDTH_SCALE = 0.58;
	private static final double INNER_BACKGROUND_HEIGHT_SCALE = 0.58;

	protected static int scaleInsideImgPos(double imgPos, int i) {
		return (int) (imgPos * i * IMG_POS_RESCALE);
	}

	protected static int scaleInsideImgWidth(Image img) {
		return (int) (img.getWidth(null) * INNER_BACKGROUND_WIDTH_SCALE);
	}

	protected static int scaleInsideImgHeight(Image img) {
		return (int) (img.getHeight(null) * INNER_BACKGROUND_HEIGHT_SCALE);
	}

	protected static int scaleImgPos(double imgPos, int v) {
		return (int) (imgPos * v);
	}

	protected static int scaleImgWidth(Image img) {
		return (int) (img.getWidth(null) * IMG_WIDTH_RESCALE);
	}

	protected static int scaleImgHeight(Image img) {
		return (int) (img.getHeight(null) * IMG_HEIGHT_RESCALE);
	}

	/*---------------LOAD ITEM IMAGES---------------*/

	private static final Image bed = GameCanvas.loadImage("/images/bed.png");
	private static final Image bedroomDoor = GameCanvas
			.loadImage("/images/bedroomDoor.png");
	private static final Image bedroomLamp = GameCanvas
			.loadImage("/images/bedroomLamp.png");
	private static final Image bedSide = GameCanvas
			.loadImage("/images/bedSide.png");
	private static final Image chair = GameCanvas
			.loadImage("/images/chair.png");
	private static final Image chairSide = GameCanvas
			.loadImage("/images/chairSide.png");
	private static final Image desk = GameCanvas.loadImage("/images/desk.png");
	private static final Image deskSide = GameCanvas
			.loadImage("/images/deskSide.png");
	private static final Image kitchenPicture = GameCanvas
			.loadImage("/images/kitchenpicture.png");
	private static final Image kitchenTable = GameCanvas
			.loadImage("/images/kitchentable.png");
	private static final Image kitchenTableLeft = GameCanvas
			.loadImage("/images/kitchentableleft.png");
	private static final Image livingRoomPicture = GameCanvas
			.loadImage("/images/livingRoomPicture.png");
	private static final Image longTable = GameCanvas
			.loadImage("/images/longTable.png");
	private static final Image longTableSide = GameCanvas
			.loadImage("/images/longTableSide.png");
	private static final Image paper = GameCanvas
			.loadImage("/images/paper.png");
	private static final Image pictureFrame = GameCanvas
			.loadImage("/images/pictureFrame.png");
	private static final Image portrait = GameCanvas
			.loadImage("/images/portrait.png");
	private static final Image shortTable = GameCanvas
			.loadImage("/images/shortTable.png");
	private static final Image shortTableSide = GameCanvas
			.loadImage("/images/shortTableSide.png");
	private static final Image sofa = GameCanvas.loadImage("/images/sofa.png");
	private static final Image sofaLeft = GameCanvas
			.loadImage("/images/sofaLeftSide.png");
	private static final Image studyDoor = GameCanvas
			.loadImage("/images/studyDoor.png");
	private static final Image mainKey = GameCanvas
			.loadImage("/images/mainkey.png");
	private static final Image bedroomSafeKey = GameCanvas
			.loadImage("/images/bedroomsafekey.png");
	private static final Image cupboardKey = GameCanvas
			.loadImage("/images/cupboardkey.png");
	private static final Image studyRoomKey = GameCanvas
			.loadImage("/images/studyroomkey.png");
	private static final Image studyRoomSafeKey = GameCanvas
			.loadImage("/images/studyroomsafekey.png");
	private static final Image matches = GameCanvas
			.loadImage("/images/matches.png");
	private static final Image lamp = GameCanvas.loadImage("/images/lamp.png");

}
