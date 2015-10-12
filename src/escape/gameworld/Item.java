package escape.gameworld;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Rectangle;
import java.io.Serializable;

import escape.gameworld.Player.Direction;
import escape.ui.GameCanvas;

public class Item implements Serializable {
	private double x;
	private double y;
	private String name;
	private String description;
	// private int positionX;
	// private int positionY;

	// private enum direction {
	// NORTH, EAST, WEST, SOUTH
	// }

	// private boolean movable;

	private boolean pickable;
	private Rectangle boundingBox;

	public Item(String n, String d, boolean pickable) {
		this.name = n;
		this.description = d;
		// this.movable = movable;
		this.pickable = pickable;
	}

	public Rectangle getBoundingBox() {
		return boundingBox;
	}

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

	// public boolean isMovable() {
	// return movable;
	// }
	//

	// public void setMovable(boolean movable) {
	// this.movable = movable;
	// }

	public boolean isPickable() {
		return pickable;
	}

	public void setPickable(boolean pickable) {
		this.pickable = pickable;
	}

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
	public void draw(Graphics g, Room r, Direction d) {
		for (Item i : r.getItems()) {
			drawItem(g, i, d);
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
	public static void draw(Graphics g, String adjRoom) {
		switch (adjRoom) {
		case "Kitchen":
			g.drawImage(kitchenTable, scaleInsideImgPos(225), scaleInsideImgPos(250), scaleInsideImgWidth(kitchenTable),
					scaleInsideImgHeight(kitchenTable), null);

			g.drawImage(kitchenPicture, scaleInsideImgPos(500), scaleInsideImgPos(400),
					scaleInsideImgWidth(kitchenPicture), scaleInsideImgHeight(kitchenPicture), null);
			break;
		case "Living Room":
			g.drawImage(sofa, scaleInsideImgPos(285), scaleInsideImgPos(215), scaleInsideImgWidth(kitchenTable),
					scaleInsideImgHeight(kitchenTable), null);
			g.drawImage(longTable, scaleInsideImgPos(310), scaleInsideImgPos(300), scaleInsideImgWidth(longTable),
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
	private void drawItem(Graphics g, Item i, Direction d) {
		String itemName = i.getName();
		switch (itemName) {
		case "Bed":
			switch (d) {
			case NORTH:
				g.drawImage(bedSide, scaleImgPos(300), scaleImgPos(220), scaleImgWidth(bedSide),
						scaleImgHeight(bedSide), null);
				i.boundingBox(scaleImgPos(300), scaleImgPos(220), scaleImgWidth(bedSide), scaleImgHeight(bedSide));
				break;
			case EAST:
				g.drawImage(bed, scaleImgPos(40), scaleImgPos(260), scaleImgWidth(bed), scaleImgHeight(bed), null);
				i.boundingBox(scaleImgPos(40), scaleImgPos(260), scaleImgWidth(bed), scaleImgHeight(bed));
				break;
			}
			break;
		case "Bedroom Door":
			switch (d) {
			case SOUTH:
				g.drawImage(bedroomDoor, scaleImgPos(239), scaleImgPos(102), scaleImgWidth(bedroomDoor),
						scaleImgHeight(bedroomDoor), null);
				i.boundingBox(scaleImgPos(239), scaleImgPos(102), scaleImgWidth(bedroomDoor),
						scaleImgHeight(bedroomDoor));
				break;
			}
			break;
		case "Bedroom Lamp":
			switch (d) {
			case NORTH:
				g.drawImage(bedroomLamp, scaleImgPos(490), scaleImgPos(120), scaleImgWidth(bedroomLamp),
						scaleImgHeight(bedroomLamp), null);

				i.boundingBox(scaleImgPos(490), scaleImgPos(120), scaleImgWidth(bedroomLamp),
						scaleImgHeight(bedroomLamp));
				break;
			case EAST:
				g.drawImage(bedroomLamp, scaleImgPos(18), scaleImgPos(120), scaleImgWidth(bedroomLamp),
						scaleImgHeight(bedroomLamp), null);
				i.boundingBox(scaleImgPos(18), scaleImgPos(120), scaleImgWidth(bedroomLamp),
						scaleImgHeight(bedroomLamp));
				break;
			}
			break;
		case "Chair":
			switch (d) {
			case NORTH:
				g.drawImage(chair, scaleImgPos(250), scaleImgPos(185), scaleImgWidth(chair), scaleImgHeight(chair),
						null);
				i.boundingBox(scaleImgPos(250), scaleImgPos(185), scaleImgWidth(chair), scaleImgHeight(chair));
				break;
			case EAST:

				g.drawImage(chairSide, scaleImgPos(100), scaleImgPos(220), scaleImgWidth(chairSide),
						scaleImgHeight(chairSide), null);
				i.boundingBox(scaleImgPos(100), scaleImgPos(220), scaleImgWidth(chairSide), scaleImgHeight(chairSide));
				break;
			}
			break;
		case "Frame":
			switch (d) {
			case EAST:
				g.drawImage(pictureFrame, scaleImgPos(224), scaleImgPos(80), scaleImgWidth(pictureFrame),
						scaleImgHeight(pictureFrame), null);
				i.boundingBox(scaleImgPos(224), scaleImgPos(80), scaleImgWidth(pictureFrame),
						scaleImgHeight(pictureFrame));
				break;
			}
			break;
		case "Desk":
			switch (d) {
			case NORTH:
				g.drawImage(desk, scaleImgPos(175), scaleImgPos(240), scaleImgWidth(desk), scaleImgHeight(desk), null);
				i.boundingBox(scaleImgPos(175), scaleImgPos(240), scaleImgWidth(desk), scaleImgHeight(desk));
				break;
			case EAST:
				g.drawImage(deskSide, scaleImgPos(160), scaleImgPos(260), scaleImgWidth(deskSide),
						scaleImgHeight(deskSide), null);
				i.boundingBox(scaleImgPos(160), scaleImgPos(260), scaleImgWidth(deskSide), scaleImgHeight(deskSide));
				break;
			}
			break;
		case "Kitchen Picture":
			switch (d) {
			case EAST:
				g.drawImage(kitchenPicture, scaleImgPos(490), scaleImgPos(370), scaleImgWidth(kitchenPicture),
						scaleImgHeight(kitchenPicture), null);
				i.boundingBox(scaleImgPos(490), scaleImgPos(370), scaleImgWidth(kitchenPicture),
						scaleImgHeight(kitchenPicture));
				break;
			case SOUTH:
				g.drawImage(kitchenPicture, scaleImgPos(100), scaleImgPos(370), scaleImgWidth(kitchenPicture),
						scaleImgHeight(kitchenPicture), null);
				i.boundingBox(scaleImgPos(100), scaleImgPos(370), scaleImgWidth(kitchenPicture),
						scaleImgHeight(kitchenPicture));
				break;
			}
			break;
		case "Kitchen Table":
			switch (d) {
			case NORTH:
				g.drawImage(kitchenTable, scaleImgPos(140), scaleImgPos(250), scaleImgWidth(kitchenTable),
						scaleImgHeight(kitchenTable), null);
				i.boundingBox(scaleImgPos(140), scaleImgPos(250), scaleImgWidth(kitchenTable),
						scaleImgHeight(kitchenTable));
				break;
			case EAST:
				g.drawImage(kitchenTableLeft, scaleImgPos(160), scaleImgPos(250), scaleImgWidth(kitchenTableLeft),
						scaleImgHeight(kitchenTableLeft), null);
				i.boundingBox(scaleImgPos(160), scaleImgPos(250), scaleImgWidth(kitchenTableLeft),
						scaleImgHeight(kitchenTableLeft));
				break;
			}
			break;

		case "Living Room Picture":
			switch (d) {
			case EAST:
				g.drawImage(livingRoomPicture, scaleImgPos(400), scaleImgPos(80), scaleImgWidth(livingRoomPicture),
						scaleImgHeight(livingRoomPicture), null);
				i.boundingBox(scaleImgPos(400), scaleImgPos(80), scaleImgWidth(livingRoomPicture),
						scaleImgHeight(livingRoomPicture));
				break;
			}
			break;
		case "Long Table":
			switch (d) {
			case NORTH:
				g.drawImage(longTable, scaleImgPos(227), scaleImgPos(292), scaleImgWidth(longTable),
						scaleImgHeight(longTable), null);
				i.boundingBox(scaleImgPos(227), scaleImgPos(292), scaleImgWidth(longTable), scaleImgHeight(longTable));
				break;
			case EAST:
				g.drawImage(longTableSide, scaleImgPos(205), scaleImgPos(280), scaleImgWidth(longTableSide),
						scaleImgHeight(longTableSide), null);
				i.boundingBox(scaleImgPos(205), scaleImgPos(280), scaleImgWidth(longTableSide),
						scaleImgHeight(longTableSide));
				break;
			}
			break;
		case "Portrait":
			switch (d) {
			case EAST:
				g.drawImage(portrait, scaleImgPos(60), scaleImgPos(80), scaleImgWidth(portrait),
						scaleImgHeight(portrait), null);
				i.boundingBox(scaleImgPos(60), scaleImgPos(80), scaleImgWidth(portrait), scaleImgHeight(portrait));
				break;
			}
			break;
		case "Short Table":
			switch (d) {
			case NORTH:
				g.drawImage(shortTable, scaleImgPos(125), scaleImgPos(285), scaleImgWidth(shortTable),
						scaleImgHeight(shortTable), null);
				i.boundingBox(scaleImgPos(125), scaleImgPos(285), scaleImgWidth(shortTable),
						scaleImgHeight(shortTable));
				break;
			case WEST:
				g.drawImage(shortTableSide, scaleImgPos(325), scaleImgPos(285), scaleImgWidth(shortTableSide),
						scaleImgHeight(shortTableSide), null);
				i.boundingBox(scaleImgPos(325), scaleImgPos(285), scaleImgWidth(shortTableSide),
						scaleImgHeight(shortTableSide));
				break;
			}
			break;
		case "Sofa":
			switch (d) {
			case NORTH:
				g.drawImage(sofa, scaleImgPos(200), scaleImgPos(200), scaleImgWidth(sofa), scaleImgHeight(sofa), null);
				i.boundingBox(scaleImgPos(200), scaleImgPos(200), scaleImgWidth(sofa), scaleImgHeight(sofa));
				break;
			case EAST:
				g.drawImage(sofaLeft, scaleImgPos(15), scaleImgPos(220), scaleImgWidth(sofaLeft),
						scaleImgHeight(sofaLeft), null);
				i.boundingBox(scaleImgPos(15), scaleImgPos(220), scaleImgWidth(sofaLeft), scaleImgHeight(sofaLeft));
				break;
			}
			break;
		case "Study Door":
			switch (d) {
			case SOUTH:
				g.drawImage(studyDoor, scaleImgPos(239), scaleImgPos(95), scaleImgWidth(studyDoor),
						scaleImgHeight(studyDoor), null);
				i.boundingBox(scaleImgPos(239), scaleImgPos(95), scaleImgWidth(studyDoor), scaleImgHeight(studyDoor));
				break;
			}
			break;
		}
	}

	public void drawInventoryItem(Graphics g, int itemOrder) {

		String invItem = this.name;
		switch (invItem) {
		case "Kitchen Picture":
			switch (itemOrder) {
			case 0:
				g.drawImage(kitchenPicture, scaleImgPos(48), scaleImgPos(525), scaleImgWidth(kitchenPicture),
						scaleImgHeight(kitchenPicture), null);
				this.boundingBox(scaleImgPos(48), scaleImgPos(525), scaleImgWidth(kitchenPicture),
						scaleImgHeight(kitchenPicture));
				break;
			case 1:
				g.drawImage(kitchenPicture, scaleImgPos(147), scaleImgPos(525), scaleImgWidth(kitchenPicture),
						scaleImgHeight(kitchenPicture), null);
				this.boundingBox(scaleImgPos(147), scaleImgPos(525), scaleImgWidth(kitchenPicture),
						scaleImgHeight(kitchenPicture));
				break;
			case 2:
				g.drawImage(kitchenPicture, scaleImgPos(259), scaleImgPos(525), scaleImgWidth(kitchenPicture),
						scaleImgHeight(kitchenPicture), null);
				this.boundingBox(scaleImgPos(259), scaleImgPos(525), scaleImgWidth(kitchenPicture),
						scaleImgHeight(kitchenPicture));
				break;
			case 3:
				g.drawImage(kitchenPicture, scaleImgPos(362), scaleImgPos(525), scaleImgWidth(kitchenPicture),
						scaleImgHeight(kitchenPicture), null);
				this.boundingBox(scaleImgPos(362), scaleImgPos(525), scaleImgWidth(kitchenPicture),
						scaleImgHeight(kitchenPicture));
				break;
			case 4:
				g.drawImage(kitchenPicture, scaleImgPos(463), scaleImgPos(525), scaleImgWidth(kitchenPicture),
						scaleImgHeight(kitchenPicture), null);
				this.boundingBox(scaleImgPos(463), scaleImgPos(525), scaleImgWidth(kitchenPicture),
						scaleImgHeight(kitchenPicture));
				break;
			}
			break;
		case "Paper":
			switch (itemOrder) {
			case 0:
				g.drawImage(paper, scaleImgPos(50), scaleImgPos(530), scaleImgWidth(paper), scaleImgHeight(paper),
						null);
				this.boundingBox(scaleImgPos(50), scaleImgPos(530), scaleImgWidth(paper), scaleImgHeight(paper));
				break;
			case 1:
				g.drawImage(paper, scaleImgPos(147), scaleImgPos(530), scaleImgWidth(paper), scaleImgHeight(paper),
						null);
				this.boundingBox(scaleImgPos(147), scaleImgPos(530), scaleImgWidth(paper), scaleImgHeight(paper));
				break;
			case 2:
				g.drawImage(paper, scaleImgPos(259), scaleImgPos(530), scaleImgWidth(paper), scaleImgHeight(paper),
						null);
				this.boundingBox(scaleImgPos(259), scaleImgPos(530), scaleImgWidth(paper), scaleImgHeight(paper));
				break;
			case 3:
				g.drawImage(paper, scaleImgPos(362), scaleImgPos(530), scaleImgWidth(paper), scaleImgHeight(paper),
						null);
				this.boundingBox(scaleImgPos(362), scaleImgPos(530), scaleImgWidth(paper), scaleImgHeight(paper));
				break;
			case 4:
				g.drawImage(paper, scaleImgPos(465), scaleImgPos(530), scaleImgWidth(paper), scaleImgHeight(paper),
						null);
				this.boundingBox(scaleImgPos(465), scaleImgPos(530), scaleImgWidth(paper), scaleImgHeight(paper));
				break;
			}
			break;
		case "Key":
			switch (itemOrder) {
			case 0:
				g.drawImage(mainKey, scaleImgPos(50), scaleImgPos(502), 70, 70, null);
				this.boundingBox(scaleImgPos(50), scaleImgPos(502), 70, 70);
				break;
			case 1:
				g.drawImage(mainKey, scaleImgPos(147), scaleImgPos(502), 70, 70, null);
				this.boundingBox(scaleImgPos(147), scaleImgPos(502), 70, 70);
				break;
			case 2:
				g.drawImage(mainKey, scaleImgPos(259), scaleImgPos(502), 70, 70, null);
				this.boundingBox(scaleImgPos(259), scaleImgPos(502), 70, 70);
				break;
			case 3:
				g.drawImage(mainKey, scaleImgPos(362), scaleImgPos(502), 70, 70, null);
				this.boundingBox(scaleImgPos(362), scaleImgPos(502), 70, 70);
				break;
			case 4:
				g.drawImage(mainKey, scaleImgPos(465), scaleImgPos(502), 70, 70, null);
				this.boundingBox(scaleImgPos(465), scaleImgPos(502), 70, 70);
				break;
			}
			break;
		case "Bedroom Safe Key":
			switch (itemOrder) {
			case 0:
				g.drawImage(bedroomSafeKey, scaleImgPos(50), scaleImgPos(502), 70, 70, null);
				this.boundingBox(scaleImgPos(50), scaleImgPos(502), 70, 70);
				break;
			case 1:
				g.drawImage(bedroomSafeKey, scaleImgPos(147), scaleImgPos(502), 70, 70, null);
				this.boundingBox(scaleImgPos(147), scaleImgPos(502), 70, 70);
				break;
			case 2:
				g.drawImage(bedroomSafeKey, scaleImgPos(259), scaleImgPos(502), 70, 70, null);
				this.boundingBox(scaleImgPos(259), scaleImgPos(502), 70, 70);
				break;
			case 3:
				g.drawImage(bedroomSafeKey, scaleImgPos(362), scaleImgPos(502), 70, 70, null);
				this.boundingBox(scaleImgPos(362), scaleImgPos(502), 70, 70);
				break;
			case 4:
				g.drawImage(bedroomSafeKey, scaleImgPos(465), scaleImgPos(502), 70, 70, null);
				this.boundingBox(scaleImgPos(465), scaleImgPos(502), 70, 70);
				break;
			}
			break;
		case "Cupboard Key":
			switch (itemOrder) {
			case 0:
				g.drawImage(cupboardKey, scaleImgPos(50), scaleImgPos(502), 70, 70, null);
				this.boundingBox(scaleImgPos(50), scaleImgPos(502), 70, 70);
				break;
			case 1:
				g.drawImage(cupboardKey, scaleImgPos(147), scaleImgPos(502), 70, 70, null);
				this.boundingBox(scaleImgPos(147), scaleImgPos(502), 70, 70);
				break;
			case 2:
				g.drawImage(cupboardKey, scaleImgPos(259), scaleImgPos(502), 70, 70, null);
				this.boundingBox(scaleImgPos(259), scaleImgPos(502), 70, 70);
				break;
			case 3:
				g.drawImage(cupboardKey, scaleImgPos(362), scaleImgPos(502), 70, 70, null);
				this.boundingBox(scaleImgPos(362), scaleImgPos(502), 70, 70);
				break;
			case 4:
				g.drawImage(cupboardKey, scaleImgPos(465), scaleImgPos(502), 70, 70, null);
				this.boundingBox(scaleImgPos(465), scaleImgPos(502), 70, 70);
				break;
			}
			break;
		case "Study Room Key":
			switch (itemOrder) {
			case 0:
				g.drawImage(studyRoomKey, scaleImgPos(50), scaleImgPos(502), 70, 70, null);
				this.boundingBox(scaleImgPos(50), scaleImgPos(502), 70, 70);
				break;
			case 1:
				g.drawImage(studyRoomKey, scaleImgPos(147), scaleImgPos(502), 70, 70, null);
				this.boundingBox(scaleImgPos(147), scaleImgPos(502), 70, 70);
				break;
			case 2:
				g.drawImage(studyRoomKey, scaleImgPos(259), scaleImgPos(502), 70, 70, null);
				this.boundingBox(scaleImgPos(259), scaleImgPos(502), 70, 70);
				break;
			case 3:
				g.drawImage(studyRoomKey, scaleImgPos(362), scaleImgPos(502), 70, 70, null);
				this.boundingBox(scaleImgPos(362), scaleImgPos(502), 70, 70);
				break;
			case 4:
				g.drawImage(studyRoomKey, scaleImgPos(465), scaleImgPos(502), 70, 70, null);
				this.boundingBox(scaleImgPos(465), scaleImgPos(502), 70, 70);
				break;
			}
			break;
		case "Study Room Safe Key":
			switch (itemOrder) {
			case 0:
				g.drawImage(studyRoomSafeKey, scaleImgPos(50), scaleImgPos(502), 70, 70, null);
				this.boundingBox(scaleImgPos(50), scaleImgPos(502), 70, 70);
				break;
			case 1:
				g.drawImage(studyRoomSafeKey, scaleImgPos(147), scaleImgPos(502), 70, 70, null);
				this.boundingBox(scaleImgPos(147), scaleImgPos(502), 70, 70);
				break;
			case 2:
				g.drawImage(studyRoomSafeKey, scaleImgPos(259), scaleImgPos(502), 70, 70, null);
				this.boundingBox(scaleImgPos(259), scaleImgPos(502), 70, 70);
				break;
			case 3:
				g.drawImage(studyRoomSafeKey, scaleImgPos(362), scaleImgPos(502), 70, 70, null);
				this.boundingBox(scaleImgPos(362), scaleImgPos(502), 70, 70);
				break;
			case 4:
				g.drawImage(studyRoomSafeKey, scaleImgPos(465), scaleImgPos(502), 70, 70, null);
				this.boundingBox(scaleImgPos(465), scaleImgPos(502), 70, 70);
				break;
			}
			break;
		case "Matches":
			switch (itemOrder) {
			case 0:
				g.drawImage(matches, scaleImgPos(50), scaleImgPos(502), 60, 70, null);
				this.boundingBox(scaleImgPos(50), scaleImgPos(502), 60, 70);
				break;
			case 1:
				g.drawImage(matches, scaleImgPos(147), scaleImgPos(502), 60, 70, null);
				this.boundingBox(scaleImgPos(147), scaleImgPos(502), 60, 70);
				break;
			case 2:
				g.drawImage(matches, scaleImgPos(259), scaleImgPos(502), 60, 70, null);
				this.boundingBox(scaleImgPos(257), scaleImgPos(502), 60, 70);
				break;
			case 3:
				g.drawImage(matches, scaleImgPos(362), scaleImgPos(502), 60, 70, null);
				this.boundingBox(scaleImgPos(362), scaleImgPos(502), 60, 70);
				break;
			case 4:
				g.drawImage(matches, scaleImgPos(465), scaleImgPos(502), 60, 70, null);
				this.boundingBox(scaleImgPos(465), scaleImgPos(502), 60, 70);
				break;
			}
			break;
		case "Lamp":
			switch (itemOrder) {
			case 0:
				g.drawImage(lamp, scaleImgPos(65), scaleImgPos(508), 30, 60, null);
				this.boundingBox(scaleImgPos(65), scaleImgPos(508), 30, 60);
				break;
			case 1:
				g.drawImage(lamp, scaleImgPos(176), scaleImgPos(508), 30, 60, null);
				this.boundingBox(scaleImgPos(176), scaleImgPos(508), 30, 60);
				break;
			case 2:
				g.drawImage(lamp, scaleImgPos(284), scaleImgPos(508), 30, 60, null);
				this.boundingBox(scaleImgPos(284), scaleImgPos(508), 30, 60);
				break;
			case 3:
				g.drawImage(lamp, scaleImgPos(388), scaleImgPos(508), 30, 60, null);
				this.boundingBox(scaleImgPos(388), scaleImgPos(508), 30, 60);
				break;
			case 4:
				g.drawImage(lamp, scaleImgPos(490), scaleImgPos(508), 30, 60, null);
				this.boundingBox(scaleImgPos(490), scaleImgPos(508), 30, 60);
				break;
			}

		}

		// OTHER PICKABLE ITEMS
		// Key to kitchen cupboard - living room
		// Matches? - kitchen
		// Key to study room - bedroom sidetable
		// Paper? - study room
		// Key to bedroom safe - study room
		// Key to main door - bedroom safe
		//
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

	protected static int scaleInsideImgPos(int i) {
		return (int) (i * IMG_POS_RESCALE * IMG_POS_RESCALE);
	}

	protected static int scaleInsideImgWidth(Image img) {
		return (int) (img.getWidth(null) * INNER_BACKGROUND_WIDTH_SCALE);
	}

	protected static int scaleInsideImgHeight(Image img) {
		return (int) (img.getHeight(null) * INNER_BACKGROUND_HEIGHT_SCALE);
	}

	protected static int scaleImgPos(int imgPos) {
		return (int) (imgPos * IMG_POS_RESCALE);
	}

	protected static int scaleImgWidth(Image img) {
		return (int) (img.getWidth(null) * IMG_WIDTH_RESCALE);
	}

	protected static int scaleImgHeight(Image img) {
		return (int) (img.getHeight(null) * IMG_HEIGHT_RESCALE);
	}

	/*---------------LOAD ITEM IMAGES---------------*/

	private static final Image bed = GameCanvas.loadImage("/images/bed.png");
	private static final Image bedroomDoor = GameCanvas.loadImage("/images/bedroomDoor.png");
	private static final Image bedroomLamp = GameCanvas.loadImage("/images/bedroomLamp.png");
	private static final Image bedSide = GameCanvas.loadImage("/images/bedSide.png");
	private static final Image chair = GameCanvas.loadImage("/images/chair.png");
	private static final Image chairSide = GameCanvas.loadImage("/images/chairSide.png");
	private static final Image desk = GameCanvas.loadImage("/images/desk.png");
	private static final Image deskSide = GameCanvas.loadImage("/images/deskSide.png");
	private static final Image kitchenPicture = GameCanvas.loadImage("/images/kitchenpicture.png");
	private static final Image kitchenTable = GameCanvas.loadImage("/images/kitchentable.png");
	private static final Image kitchenTableLeft = GameCanvas.loadImage("/images/kitchentableleft.png");
	private static final Image livingRoomPicture = GameCanvas.loadImage("/images/livingRoomPicture.png");
	private static final Image longTable = GameCanvas.loadImage("/images/longTable.png");
	private static final Image longTableSide = GameCanvas.loadImage("/images/longTableSide.png");
	private static final Image paper = GameCanvas.loadImage("/images/paper.png");
	private static final Image pictureFrame = GameCanvas.loadImage("/images/pictureFrame.png");
	private static final Image portrait = GameCanvas.loadImage("/images/portrait.png");
	private static final Image shortTable = GameCanvas.loadImage("/images/shortTable.png");
	private static final Image shortTableSide = GameCanvas.loadImage("/images/shortTableSide.png");
	private static final Image sofa = GameCanvas.loadImage("/images/sofa.png");
	private static final Image sofaLeft = GameCanvas.loadImage("/images/sofaLeftSide.png");
	private static final Image studyDoor = GameCanvas.loadImage("/images/studyDoor.png");
	private static final Image mainKey = GameCanvas.loadImage("/images/mainkey.png");
	private static final Image bedroomSafeKey = GameCanvas.loadImage("/images/bedroomsafekey.png");
	private static final Image cupboardKey = GameCanvas.loadImage("/images/cupboardkey.png");
	private static final Image studyRoomKey = GameCanvas.loadImage("/images/studyroomkey.png");
	private static final Image studyRoomSafeKey = GameCanvas.loadImage("/images/studyRoomSafeKey.png");
	private static final Image matches = GameCanvas.loadImage("/images/matches.png");
	private static final Image lamp = GameCanvas.loadImage("/images/lamp.png");

}
