package escape.gameworld;

import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

import escape.gameworld.Player.Direction;
import escape.ui.GameCanvas;
/**
 * Class extends Item and represents an Item which can hold one or more items.
 * @author semillkasz
 *
 */
public class Container extends Item {
	private ArrayList<Item> items = new ArrayList<Item>();
	private boolean locked;
	private String key;

	private Rectangle boundingBox;

	public Container(String n, String d, boolean pickable, boolean l, String key) {
		super(n, d, pickable);
		this.locked = l;
		this.key = key;
	}

/**
 * Add an item into the container
 * @param Item item
 */
	public void add(Item i) {
		items.add(i);
	}

	public ArrayList<Item> getItems() {
		return items;
	}
	
	
	public void setItems(ArrayList<Item> items) {
		this.items = items;
	}
	
	/**
	 * Method which returns if the container is locked. If locked, you need to have the right key to open.
	 * @return boolean 
	 */
	public boolean isLocked() {
		return locked;
	}

	/**
	 * Method to make the container locked.
	 * @param locked
	 */
	public void setLocked(boolean locked) {
		this.locked = locked;
	}

	/**
	 * Method which returns a String key for the container.
	 * @return String key
	 */
	public String getKey() {
		return key;
	}

	/**
	 * Method to set the Key for this container
	 * @param key
	 */
	public void setKey(String key) {
		this.key = key;
	}

	

	/**
	 * Method which returns the bounding box for this item.
	 * @return Rectangle boundingbox
	 */
	public Rectangle getBoundingBox() {
		return boundingBox;
	}
	
	public void setBoundingBox(Rectangle boundingBox) {
		this.boundingBox = boundingBox;
	}

	/**
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
		setBoundingBox(new Rectangle(x, y, w, h));
	}

	/*---------------DRAWING ITEM IMAGE---------------*/
	public void draw(Graphics g, Room r, Direction d, int w, int h) {
		// String roomName = r.getName();
		for (Container c : r.getContainer()) {
			drawContainer(g, c, d, w, h);
		}
	}

	public static void draw(Graphics g, String adjRoom, int cWidth, int cHeight) {
		switch (adjRoom) {
		case "Kitchen":
			g.drawImage(fridge, scaleInsideImgPos(0.8, cWidth), scaleInsideImgPos(0.33, cHeight),
					scaleInsideImgWidth(fridge), scaleInsideImgHeight(fridge),
					null);
			g.drawImage(bin, scaleInsideImgPos(0.16, cWidth), scaleInsideImgPos(0.5, cHeight),
					scaleInsideImgWidth(bin), scaleInsideImgHeight(bin), null);
			break;
		case "Living Room":
			g.drawImage(bin, scaleInsideImgPos(0.16, cWidth), scaleInsideImgPos(0.5, cHeight),
					scaleInsideImgWidth(bin), scaleInsideImgHeight(bin), null);
			break;
		}
	}

	/*---------------ITEM IMAGE DIRECTION HELPER METHOD---------------*/
	private void drawContainer(Graphics g, Item i, Direction d, int cWidth, int cHeight) {
		String containerName = i.getName();
		switch (containerName) {
		case "Bedroom Bin":
			switch (d) {
			case NORTH:
				g.drawImage(bin, scaleImgPos(0.05, cWidth), scaleImgPos(0.48, cHeight),
						scaleImgWidth(bin), scaleImgHeight(bin), null);
				i.boundingBox(scaleImgPos(0.05, cWidth), scaleImgPos(0.48, cHeight),
						scaleImgWidth(bin), scaleImgHeight(bin));
				break;
			case WEST:
				g.drawImage(bin, scaleImgPos(0.85, cWidth), scaleImgPos(0.48, cHeight),
						scaleImgWidth(bin), scaleImgHeight(bin), null);
				i.boundingBox(scaleImgPos(0.85, cWidth), scaleImgPos(0.48, cHeight),
						scaleImgWidth(bin), scaleImgHeight(bin));
				break;
			}
			break;
		case "Bedroom Safe":
			switch (d) {
			case SOUTH:
				g.drawImage(safe, scaleImgPos(0.7, cWidth), scaleImgPos(0.52, cHeight),
						scaleImgWidth(safe), scaleImgHeight(safe), null);
				i.boundingBox(scaleImgPos(0.7, cWidth), scaleImgPos(0.52, cHeight),
						scaleImgWidth(safe), scaleImgHeight(safe));
				break;
			case WEST:
				g.drawImage(safeSide, scaleImgPos(0.05, cWidth), scaleImgPos(0.52, cHeight),
						scaleImgWidth(safeSide), scaleImgHeight(safeSide), null);
				i.boundingBox(scaleImgPos(0.05, cWidth), scaleImgPos(0.52, cHeight),
						scaleImgWidth(safeSide), scaleImgHeight(safeSide));
				break;
			}
			break;
		case "Bookshelf":
			switch (d) {
			case SOUTH:
				g.drawImage(bookshelfSide, scaleImgPos(.85,cWidth), scaleImgPos(.25,cHeight),
						scaleImgWidth(bookshelfSide),
						scaleImgHeight(bookshelfSide), null);
				i.boundingBox(scaleImgPos(.85,cWidth), scaleImgPos(.25,cHeight),
						scaleImgWidth(bookshelfSide),
						scaleImgHeight(bookshelfSide));
				break;
			case WEST:
				g.drawImage(bookshelf, scaleImgPos(.1,cWidth), scaleImgPos(0.23,cHeight),
						scaleImgWidth(bookshelf), scaleImgHeight(bookshelf),
						null);
				i.boundingBox(scaleImgPos(.1,cWidth), scaleImgPos(.23,cHeight),
						scaleImgWidth(bookshelf), scaleImgHeight(bookshelf));
				break;
			}
			break;
		case "Cupboard":
			switch (d) {
			case WEST:
				g.drawImage(cupboard, scaleImgPos(0.04, cWidth), scaleImgPos(0.42, cHeight),
						scaleImgWidth(cupboard), scaleImgHeight(cupboard), null);
				i.boundingBox(scaleImgPos(0.04, cWidth), scaleImgPos(0.42, cHeight),
						scaleImgWidth(cupboard), scaleImgHeight(cupboard));
				break;
			case SOUTH:
				g.drawImage(cupboardSide, scaleImgPos(0.9, cWidth), scaleImgPos(0.42, cHeight),
						scaleImgWidth(cupboardSide),
						scaleImgHeight(cupboardSide), null);
				i.boundingBox(scaleImgPos(0.9, cWidth), scaleImgPos(0.42, cHeight),
						scaleImgWidth(cupboardSide),
						scaleImgHeight(cupboardSide));
				break;
			}
			break;
		case "Fridge":
			switch (d) {
			case NORTH:
				g.drawImage(fridge, scaleImgPos(0.78, cWidth), scaleImgPos(0.30, cHeight),
						scaleImgWidth(fridge), scaleImgHeight(fridge), null);
				i.boundingBox(scaleImgPos(0.78, cWidth), scaleImgPos(0.30, cHeight),
						scaleImgWidth(fridge), scaleImgHeight(fridge));
				break;
			case EAST:
				g.drawImage(fridgeSide, scaleImgPos(0.001, cWidth), scaleImgPos(0.30, cHeight),
						scaleImgWidth(fridgeSide), scaleImgHeight(fridgeSide),
						null);
				i.boundingBox(scaleImgPos(0.001, cWidth), scaleImgPos(0.30, cHeight),
						scaleImgWidth(fridgeSide), scaleImgHeight(fridgeSide));
				break;
			}
			break;
		case "Kitchen Bin":
			switch (d) {
			case NORTH:
				g.drawImage(bin, scaleImgPos(0.04, cWidth), scaleImgPos(0.5, cHeight),
						scaleImgWidth(bin), scaleImgHeight(bin), null);
				i.boundingBox(scaleImgPos(0.04, cWidth), scaleImgPos(0.5, cHeight),
						scaleImgWidth(bin), scaleImgHeight(bin));
				break;
			case WEST:
				g.drawImage(bin, scaleImgPos(0.8, cWidth), scaleImgPos(0.48, cHeight),
						scaleImgWidth(bin), scaleImgHeight(bin), null);
				i.boundingBox(scaleImgPos(0.8, cWidth), scaleImgPos(0.48, cHeight),
						scaleImgWidth(bin), scaleImgHeight(bin));
				break;
			}
			break;
		case "Living Room Bin":
			switch (d) {
			case NORTH:
				g.drawImage(bin, scaleImgPos(0.05, cWidth), scaleImgPos(0.48, cHeight),
						scaleImgWidth(bin), scaleImgHeight(bin), null);
				i.boundingBox(scaleImgPos(0.05, cWidth), scaleImgPos(0.48, cHeight),
						scaleImgWidth(bin), scaleImgHeight(bin));
				break;
			case WEST:
				g.drawImage(bin, scaleImgPos(0.8, cWidth), scaleImgPos(0.48, cHeight),
						scaleImgWidth(bin), scaleImgHeight(bin), null);
				i.boundingBox(scaleImgPos(0.8, cWidth), scaleImgPos(0.48, cHeight),
						scaleImgWidth(bin), scaleImgHeight(bin));
				break;
			}
			break;
		case "Living Room Safe":
			switch (d) {
			case SOUTH:
				g.drawImage(safeSide, scaleImgPos(0.88, cWidth), scaleImgPos(0.52, cHeight),
						scaleImgWidth(safeSide), scaleImgHeight(safeSide), null);
				i.boundingBox(scaleImgPos(0.88, cWidth), scaleImgPos(0.52, cHeight),
						scaleImgWidth(safeSide), scaleImgHeight(safeSide));
				break;
			case WEST:
				g.drawImage(safe, scaleImgPos(0.1, cWidth), scaleImgPos(0.52, cHeight),
						scaleImgWidth(safe), scaleImgHeight(safe), null);
				i.boundingBox(scaleImgPos(0.1, cWidth), scaleImgPos(0.52, cHeight),
						scaleImgWidth(safe), scaleImgHeight(safe));
				break;
			}
			break;
		case "Sidetable":
			switch (d) {
			case NORTH:
				g.drawImage(sideTableSide, scaleImgPos(0.87, cWidth), scaleImgPos(0.52, cHeight),
						scaleImgWidth(sideTableSide),
						scaleImgHeight(sideTableSide), null);
				i.boundingBox(scaleImgPos(0.87, cWidth), scaleImgPos(0.52, cHeight),
						scaleImgWidth(sideTableSide),
						scaleImgHeight(sideTableSide));
				break;
			case EAST:
				g.drawImage(sideTable, scaleImgPos(0.27, cWidth), scaleImgPos(0.43,cHeight),
						scaleImgWidth(sideTable),
						scaleImgHeight(sideTableSide), null);
				i.boundingBox(scaleImgPos(0.27, cWidth), scaleImgPos(0.43,cHeight),
						scaleImgWidth(sideTable),
						scaleImgHeight(sideTableSide));
				break;
			}
			break;
		case "Study Room Bin":
			switch (d) {
			case NORTH:
				g.drawImage(bin, scaleImgPos(.05,cWidth), scaleImgPos(.48,cHeight),
						scaleImgWidth(bin), scaleImgHeight(bin), null);
				i.boundingBox(scaleImgPos(.05,cWidth), scaleImgPos(.48,cHeight),
						scaleImgWidth(bin), scaleImgHeight(bin));
				break;
			case WEST:
				g.drawImage(bin, scaleImgPos(.8,cWidth), scaleImgPos(.49,cHeight),
						scaleImgWidth(bin), scaleImgHeight(bin), null);
				i.boundingBox(scaleImgPos(.8,cWidth), scaleImgPos(.49,cHeight),
						scaleImgWidth(bin), scaleImgHeight(bin));
				break;
			}
			break;

		case "Lamp":
			switch (d) {
			case EAST:
				g.drawImage(lamp, scaleImgPos(.8,cWidth), scaleImgPos(.5,cHeight),
						scaleImgWidth(lamp), scaleImgHeight(lamp), null);
				i.boundingBox(scaleImgPos(.8,cWidth), scaleImgPos(.5,cHeight),
						scaleImgWidth(lamp), scaleImgHeight(lamp));
				break;
			case SOUTH:
				g.drawImage(lamp, scaleImgPos(.02,cWidth), scaleImgPos(.53,cHeight),
						scaleImgWidth(lamp), scaleImgHeight(lamp), null);
				i.boundingBox(scaleImgPos(.02,cWidth), scaleImgPos(.53,cHeight),
						scaleImgWidth(lamp), scaleImgHeight(lamp));
				break;
			}
			break;

		case "Study Room Safe":
			switch (d) {
			case NORTH:
				g.drawImage(safe, scaleImgPos(.85,cWidth), scaleImgPos(.52,cHeight),
						scaleImgWidth(safe), scaleImgHeight(safe), null);
				i.boundingBox(scaleImgPos(.85,cWidth), scaleImgPos(.52,cHeight),
						scaleImgWidth(safe), scaleImgHeight(safe));
				break;
			case EAST:
				g.drawImage(safeSide, scaleImgPos(.02,cWidth), scaleImgPos(.53,cHeight),
						scaleImgWidth(safeSide), scaleImgHeight(safeSide), null);
				i.boundingBox(scaleImgPos(.02,cWidth), scaleImgPos(.53,cHeight),
						scaleImgWidth(safeSide), scaleImgHeight(safeSide));
				break;
			}
			break;
		}
	}

	/*---------------LOAD CONTAINER IMAGES---------------*/
	private static final BufferedImage bookshelf = GameCanvas
			.loadImage("/images/bookshelf.png");
	private static final BufferedImage bookshelfSide = GameCanvas
			.loadImage("/images/bookshelfSide.png");
	private static final BufferedImage bin = GameCanvas.loadImage("/images/bin.png");
	private static final BufferedImage cupboard = GameCanvas
			.loadImage("/images/moderncupboard.png");
	private static final BufferedImage cupboardSide = GameCanvas
			.loadImage("/images/moderncupboardSide.png");
	private static final BufferedImage fridge = GameCanvas
			.loadImage("/images/fridge.png");
	private static final BufferedImage fridgeSide = GameCanvas
			.loadImage("/images/fridgeleft.png");
	private static final BufferedImage safe = GameCanvas.loadImage("/images/safe.png");
	private static final BufferedImage safeSide = GameCanvas
			.loadImage("/images/safeSide.png");
	private static final BufferedImage sideTable = GameCanvas
			.loadImage("/images/sideTable.png");
	private static final BufferedImage sideTableSide = GameCanvas
			.loadImage("/images/sideTableSide.png");
	private static final BufferedImage lamp = GameCanvas.loadImage("/images/lamp.png");
}
