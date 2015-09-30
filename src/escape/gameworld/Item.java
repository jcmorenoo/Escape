package escape.gameworld;

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
	 * Creates a bounding box for this item
	 * 
	 * 
	 * @param x
	 *            x position in the game canvas
	 * @param y
	 *            y position in the game canvas
	 * @param w
	 *            width of the item
	 * @param h
	 *            height of the item
	 */
	public void boundingBox(int x, int y, int w, int h) {
		this.boundingBox = new Rectangle(x, y, w, h);
	}

	/**
	 * Draws all the items in the room the player is currently in, and in the
	 * direction that the player is facing
	 * 
	 * @param g
	 *            Graphics
	 * @param r
	 *            Current room player is in
	 * @param d
	 *            Direction player is facing
	 */
	public void draw(Graphics g, Room r, Direction d) {

		for (Item i : r.getItems()) {
			drawItem(g, i, d);
		}
	}

	/*---------------ITEM IMAGE DIRECTION HELPER METHOD---------------*/

	/**
	 * Draws an item depending on the player's direction
	 * 
	 * @param g
	 *            Graphics
	 * @param i
	 *            Item to be drawn
	 * @param d
	 *            Player's current direction
	 */

	private void drawItem(Graphics g, Item i, Direction d) {
		String itemName = i.getName();

		switch (itemName) {
		case "Bed":
			switch (d) {
			case NORTH:
				g.drawImage(bedSide, scaleImgPos(300), scaleImgPos(220),
						scaleImgWidth(bedSide), scaleImgHeight(bedSide), null);
				boundingBox(scaleImgPos(300), scaleImgPos(220),
						scaleImgWidth(bedSide), scaleImgHeight(bedSide));
				break;
			case EAST:
				g.drawImage(bed, scaleImgPos(40), scaleImgPos(260),
						scaleImgWidth(bed), scaleImgHeight(bed), null);
				boundingBox(scaleImgPos(40), scaleImgPos(260),
						scaleImgWidth(bed), scaleImgHeight(bed));
				break;
			}
			break;

		case "Bedroom Door":
			switch (d) {
			case SOUTH:
				g.drawImage(bedroomDoor, scaleImgPos(239), scaleImgPos(102),
						scaleImgWidth(bedroomDoor), scaleImgHeight(bedroomDoor), null);
				boundingBox(scaleImgPos(239), scaleImgPos(102),
						scaleImgWidth(bedroomDoor), scaleImgHeight(bedroomDoor));
				break;
			}
			break;

		case "Bedroom Lamp":
			switch (d) {
			case NORTH:
				g.drawImage(bedroomLamp, scaleImgPos(490), scaleImgPos(120),
						scaleImgWidth(bedroomLamp),
						scaleImgHeight(bedroomLamp), null);
				boundingBox(scaleImgPos(490), scaleImgPos(120), scaleImgWidth(bedroomLamp),
						scaleImgHeight(bedroomLamp));
				
				
				break;
			case EAST:
				g.drawImage(bedroomLamp, scaleImgPos(18), scaleImgPos(120),
						scaleImgWidth(bedroomLamp),
						scaleImgHeight(bedroomLamp), null);
				boundingBox(scaleImgPos(18), scaleImgPos(120), scaleImgWidth(bedroomLamp),
						scaleImgHeight(bedroomLamp));
				break;
			}
			break;

		case "Chair":
			switch (d) {
			case NORTH:
				g.drawImage(chair, scaleImgPos(250), scaleImgPos(185),
						scaleImgWidth(chair), scaleImgHeight(chair), null);
				boundingBox(scaleImgPos(250), scaleImgPos(185),
						scaleImgWidth(chair), scaleImgHeight(chair));
				break;
			case EAST:
				g.drawImage(chairSide, scaleImgPos(100), scaleImgPos(220),
						scaleImgWidth(chairSide), scaleImgHeight(chairSide), null);
				boundingBox(scaleImgPos(100), scaleImgPos(220),
						scaleImgWidth(chairSide), scaleImgHeight(chairSide));


				break;
			}
			break;

		case "Frame":
			switch (d) {
			case EAST:
				g.drawImage(pictureFrame, scaleImgPos(224), scaleImgPos(80),
						scaleImgWidth(pictureFrame), scaleImgHeight(pictureFrame), null);
				boundingBox(scaleImgPos(224), scaleImgPos(80),
						scaleImgWidth(pictureFrame), scaleImgHeight(pictureFrame));
				break;
			}
			break;

		case "Desk":
			switch (d) {
			case NORTH:
				g.drawImage(desk, scaleImgPos(175), scaleImgPos(240),
						scaleImgWidth(desk), scaleImgHeight(desk), null);
				boundingBox(scaleImgPos(175), scaleImgPos(240),
						scaleImgWidth(desk), scaleImgHeight(desk));
				break;
			case EAST:
				g.drawImage(deskSide, scaleImgPos(160), scaleImgPos(260),
						scaleImgWidth(deskSide), scaleImgHeight(deskSide), null);
				boundingBox(scaleImgPos(160), scaleImgPos(260),
						scaleImgWidth(deskSide), scaleImgHeight(deskSide));
				break;
			}
			break;

		case "Kitchen Picture":
			switch (d) {
			case EAST:
				g.drawImage(kitchenPicture, scaleImgPos(490), scaleImgPos(370),
						scaleImgWidth(kitchenPicture), scaleImgHeight(kitchenPicture), null);
				boundingBox(scaleImgPos(490), scaleImgPos(370),
						scaleImgWidth(kitchenPicture), scaleImgHeight(kitchenPicture));
				break;
			case SOUTH:
				g.drawImage(kitchenPicture, scaleImgPos(100), scaleImgPos(370),
						scaleImgWidth(kitchenPicture), scaleImgHeight(kitchenPicture), null);
				boundingBox(scaleImgPos(100), scaleImgPos(370),
						scaleImgWidth(kitchenPicture), scaleImgHeight(kitchenPicture));

				break;
			}
			break;

		case "Kitchen Table":
			switch (d) {
			case NORTH:
				g.drawImage(kitchenTable, scaleImgPos(140), scaleImgPos(250),
						scaleImgWidth(kitchenTable), scaleImgHeight(kitchenTable), null);
				boundingBox(scaleImgPos(140), scaleImgPos(250),
						scaleImgWidth(kitchenTable), scaleImgHeight(kitchenTable));
				break;
			case EAST:
				g.drawImage(kitchenTableLeft, scaleImgPos(160), scaleImgPos(250),
						scaleImgWidth(kitchenTableLeft), scaleImgHeight(kitchenTableLeft), null);
				boundingBox(scaleImgPos(160), scaleImgPos(250),
						scaleImgWidth(kitchenTableLeft), scaleImgHeight(kitchenTableLeft));

				break;
			}
			break;

		case "Lamp":
			switch (d) {
			case EAST:
				g.drawImage(lamp, scaleImgPos(490), scaleImgPos(275),
						scaleImgWidth(lamp), scaleImgHeight(lamp), null);
				boundingBox(scaleImgPos(490), scaleImgPos(275),
						scaleImgWidth(lamp), scaleImgHeight(lamp));
				break;
			case SOUTH:
				g.drawImage(lamp, scaleImgPos(10), scaleImgPos(275),
						scaleImgWidth(lamp), scaleImgHeight(lamp), null);
				boundingBox(scaleImgPos(10), scaleImgPos(275),
						scaleImgWidth(lamp), scaleImgHeight(lamp));
				break;
			}
			break;

		case "Living Room Picture":
			switch (d) {
			case EAST:
				g.drawImage(livingRoomPicture, scaleImgPos(400), scaleImgPos(80),
						scaleImgWidth(livingRoomPicture), scaleImgHeight(livingRoomPicture), null);
				boundingBox(scaleImgPos(400), scaleImgPos(80),
						scaleImgWidth(livingRoomPicture), scaleImgHeight(livingRoomPicture));
				break;
			}
			break;

		case "Long Table":
			switch (d) {
			case NORTH:
				g.drawImage(longTable, scaleImgPos(227), scaleImgPos(292),
						scaleImgWidth(longTable), scaleImgHeight(longTable),
						null);
				boundingBox(scaleImgPos(227), scaleImgPos(292),
						scaleImgWidth(longTable), scaleImgHeight(longTable));
				break;
			case EAST:
				g.drawImage(longTableSide, scaleImgPos(205), scaleImgPos(280),
						scaleImgWidth(longTableSide), scaleImgHeight(longTableSide),
						null);
				boundingBox(scaleImgPos(205), scaleImgPos(280),
						scaleImgWidth(longTableSide), scaleImgHeight(longTableSide));
				break;
			}
			break;

		case "Portrait":
			switch (d) {
			case EAST:
				g.drawImage(portrait, scaleImgPos(60), scaleImgPos(80),
						scaleImgWidth(portrait), scaleImgHeight(portrait), null);
				boundingBox(scaleImgPos(60), scaleImgPos(80),
						scaleImgWidth(portrait), scaleImgHeight(portrait));
				break;
			}
			break;

		case "Short Table":
			switch (d) {
			case NORTH:
				g.drawImage(shortTable, scaleImgPos(125), scaleImgPos(285),
						scaleImgWidth(shortTable), scaleImgHeight(shortTable), null);
				boundingBox(scaleImgPos(125), scaleImgPos(285),
						scaleImgWidth(shortTable), scaleImgHeight(shortTable));
				break;
			case WEST:
				g.drawImage(shortTableSide, scaleImgPos(325), scaleImgPos(285),
						scaleImgWidth(shortTableSide), scaleImgHeight(shortTableSide), null);
				boundingBox(scaleImgPos(325), scaleImgPos(285),
						scaleImgWidth(shortTableSide), scaleImgHeight(shortTableSide));

				break;
			}
			break;

		case "Sofa":
			switch (d) {
			case NORTH:
				g.drawImage(sofa, scaleImgPos(200), scaleImgPos(200),
						scaleImgWidth(sofa), scaleImgHeight(sofa), null);
				boundingBox(scaleImgPos(200), scaleImgPos(200),
						scaleImgWidth(sofa), scaleImgHeight(sofa));
				break;
			case EAST:
				g.drawImage(sofaLeft, scaleImgPos(15), scaleImgPos(220),
						scaleImgWidth(sofaLeft), scaleImgHeight(sofaLeft), null);
				boundingBox(scaleImgPos(15), scaleImgPos(220),
						scaleImgWidth(sofaLeft), scaleImgHeight(sofaLeft));
				break;
			}
			break;

		case "Study Door":
			switch (d) {
			case SOUTH:
				g.drawImage(studyDoor, scaleImgPos(239), scaleImgPos(95),
						scaleImgWidth(studyDoor), scaleImgHeight(studyDoor), null);
				boundingBox(scaleImgPos(239), scaleImgPos(95),
						scaleImgWidth(studyDoor), scaleImgHeight(studyDoor));

				break;
			}
			break;
		}
	}

	public void drawInventoryItem(Graphics g) {

	}

	/*---------------ITEM IMAGE SCALING---------------
	 * These constants and methods help with scaling the item in accordance
	 * to the player's screen resolution
	 */

	private static final double IMG_POS_RESCALE = 0.8;
	private static final double IMG_WIDTH_RESCALE = 0.8;
	private static final double IMG_HEIGHT_RESCALE = 0.814;

	private static int scaleImgPos(int imgPos) {
		return (int) (imgPos * IMG_POS_RESCALE);
	}

	private static int scaleImgWidth(Image img) {
		return (int) (img.getWidth(null) * IMG_WIDTH_RESCALE);
	}

	private static int scaleImgHeight(Image img) {
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
	private static final Image lamp = GameCanvas.loadImage("/images/lamp.png");
	private static final Image livingRoomPicture = GameCanvas.loadImage("/images/livingRoomPicture.png");
	private static final Image longTable = GameCanvas.loadImage("/images/longTable.png");
	private static final Image longTableSide = GameCanvas.loadImage("/images/longTableSide.png");
	private static final Image mainDoor = GameCanvas.loadImage("/images/mainDoor.png");
	private static final Image pictureFrame = GameCanvas.loadImage("/images/pictureFrame.png");
	private static final Image portrait = GameCanvas.loadImage("/images/portrait.png");
	private static final Image shortTable = GameCanvas.loadImage("/images/shortTable.png");
	private static final Image shortTableSide = GameCanvas.loadImage("/images/shortTableSide.png");
	private static final Image sofa = GameCanvas.loadImage("/images/sofa.png");
	private static final Image sofaLeft = GameCanvas.loadImage("/images/sofaLeftSide.png");
	private static final Image studyDoor = GameCanvas.loadImage("/images/studyDoor.png");

}
