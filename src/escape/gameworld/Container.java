package escape.gameworld;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Rectangle;
import java.util.ArrayList;

import escape.gameworld.Player.Direction;
import escape.ui.GameCanvas;

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

	private Rectangle boundingBox;

	public Container(String n, String d, boolean pickable, boolean l, String key) {
		super(n, d, pickable);
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

	public Rectangle getBoundingBox() {
		return boundingBox;
	}

	public void setBoundingBox(Rectangle boundingBox) {
		this.boundingBox = boundingBox;
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
		setBoundingBox(new Rectangle(x, y, w, h));
	}

	/*---------------DRAWING ITEM IMAGE---------------*/
	public void draw(Graphics g, Room r, Direction d) {
		// String roomName = r.getName();

		for (Container c : r.getContainer()) {
			drawContainer(g, c, d);
		}
	}

	public static void draw(Graphics g, String adjRoom) {
		switch (adjRoom) {
		case "Kitchen":
			g.drawImage(fridge, scaleInsideImgPos(485), scaleInsideImgPos(135),
					scaleInsideImgWidth(fridge), scaleInsideImgHeight(fridge),
					null);
			g.drawImage(bin, scaleInsideImgPos(90), scaleInsideImgPos(265),
					scaleInsideImgWidth(bin), scaleInsideImgHeight(bin), null);
		}
	}

	/*---------------ITEM IMAGE DIRECTION HELPER METHOD---------------*/

	private void drawContainer(Graphics g, Item i, Direction d) {
		String containerName = i.getName();
		switch (containerName) {
		case "Bedroom Bin":
			switch (d) {
			case NORTH:
				g.drawImage(bin, scaleImgPos(18), scaleImgPos(260),
						scaleImgWidth(bin), scaleImgHeight(bin), null);
				g.setColor(Color.RED);
				//System.out.println("Bounding Box: " + scaleImgPos(18) + " " + scaleImgPos(260) + " " + scaleImgWidth(bin) + " " + scaleImgHeight(bin));
				g.drawRect(scaleImgPos(18), scaleImgPos(260),
						scaleImgWidth(bin), scaleImgHeight(bin));
				
				g.drawRect(12, 33, 12, 12);
				boundingBox(scaleImgPos(18), scaleImgPos(260),
						scaleImgWidth(bin), scaleImgHeight(bin));
				break;
			case WEST:
				g.drawImage(bin, scaleImgPos(490), scaleImgPos(260),
						scaleImgWidth(bin), scaleImgHeight(bin), null);
				boundingBox(scaleImgPos(490), scaleImgPos(260),
						scaleImgWidth(bin), scaleImgHeight(bin));
				break;
			}
			break;
		case "Bedroom Safe":
			switch (d) {
			case SOUTH:
				g.drawImage(safe, scaleImgPos(480), scaleImgPos(275),
						scaleImgWidth(safe), scaleImgHeight(safe), null);
				boundingBox(scaleImgPos(480), scaleImgPos(275),
						scaleImgWidth(safe), scaleImgHeight(safe));
				break;
			case WEST:
				g.drawImage(safeSide, scaleImgPos(18), scaleImgPos(275),
						scaleImgWidth(safeSide), scaleImgHeight(safeSide), null);
				boundingBox(scaleImgPos(18), scaleImgPos(275),
						scaleImgWidth(safeSide), scaleImgHeight(safeSide));
				break;
			}
			break;
		case "Bookshelf":
			switch (d) {
			case SOUTH:
				g.drawImage(bookshelfSide, scaleImgPos(490), scaleImgPos(55),
						scaleImgWidth(bookshelfSide),
						scaleImgHeight(bookshelfSide), null);
				boundingBox(scaleImgPos(490), scaleImgPos(55),
						scaleImgWidth(bookshelfSide),
						scaleImgHeight(bookshelfSide));
				break;
			case WEST:
				g.drawImage(bookshelf, scaleImgPos(20), scaleImgPos(35),
						scaleImgWidth(bookshelf), scaleImgHeight(bookshelf),
						null);
				boundingBox(scaleImgPos(20), scaleImgPos(35),
						scaleImgWidth(bookshelf), scaleImgHeight(bookshelf));
				break;
			}
			break;
		case "Cupboard":
			switch (d) {
			case WEST:
				g.drawImage(cupboard, scaleImgPos(30), scaleImgPos(220),
						scaleImgWidth(cupboard), scaleImgHeight(cupboard), null);
				boundingBox(scaleImgPos(30), scaleImgPos(220),
						scaleImgWidth(cupboard), scaleImgHeight(cupboard));
				break;
			case SOUTH:
				g.drawImage(cupboardSide, scaleImgPos(450), scaleImgPos(220),
						scaleImgWidth(cupboardSide),
						scaleImgHeight(cupboardSide), null);
				boundingBox(scaleImgPos(450), scaleImgPos(220),
						scaleImgWidth(cupboardSide),
						scaleImgHeight(cupboardSide));
				break;
			}
			break;
		case "Fridge":
			switch (d) {
			case NORTH:
				g.drawImage(fridge, scaleImgPos(430), scaleImgPos(110),
						scaleImgWidth(fridge), scaleImgHeight(fridge), null);
				g.drawRect(14, 204, 65, 92);
				boundingBox(scaleImgPos(430), scaleImgPos(110),
						scaleImgWidth(fridge), scaleImgHeight(fridge));
				break;
			case EAST:
				g.drawImage(fridgeSide, scaleImgPos(-70), scaleImgPos(110),
						scaleImgWidth(fridgeSide), scaleImgHeight(fridgeSide),
						null);
				boundingBox(scaleImgPos(-70), scaleImgPos(110),
						scaleImgWidth(fridgeSide), scaleImgHeight(fridgeSide));
				break;
			}
			break;
		case "Kitchen Bin":
			switch (d) {
			case NORTH:
				g.drawImage(bin, scaleImgPos(18), scaleImgPos(255),
						scaleImgWidth(bin), scaleImgHeight(bin), null);
				boundingBox(scaleImgPos(18), scaleImgPos(255),
						scaleImgWidth(bin), scaleImgHeight(bin));
				break;
			case WEST:
				g.drawImage(bin, scaleImgPos(490), scaleImgPos(255),
						scaleImgWidth(bin), scaleImgHeight(bin), null);
				boundingBox(scaleImgPos(490), scaleImgPos(255),
						scaleImgWidth(bin), scaleImgHeight(bin));
				break;
			}
			break;

		case "Living Room Bin":
			switch (d) {
			case NORTH:
				g.drawImage(bin, scaleImgPos(18), scaleImgPos(255),
						scaleImgWidth(bin), scaleImgHeight(bin), null);
				boundingBox(scaleImgPos(18), scaleImgPos(255),
						scaleImgWidth(bin), scaleImgHeight(bin));
				break;
			case WEST:
				g.drawImage(bin, scaleImgPos(490), scaleImgPos(255),
						scaleImgWidth(bin), scaleImgHeight(bin), null);
				g.setColor(Color.RED);
				g.drawRect(490, 255,
						scaleImgWidth(bin), scaleImgHeight(bin));
				boundingBox(scaleImgPos(490), scaleImgPos(255),
						scaleImgWidth(bin), scaleImgHeight(bin));
				break;
			}
			break;

		case "Living Room Safe":
			switch (d) {
			case SOUTH:
				g.drawImage(safeSide, scaleImgPos(480), scaleImgPos(255),
						scaleImgWidth(safeSide), scaleImgHeight(safeSide), null);
				boundingBox(scaleImgPos(480), scaleImgPos(255),
						scaleImgWidth(safeSide), scaleImgHeight(safeSide));
				break;
			case WEST:
				g.drawImage(safe, scaleImgPos(18), scaleImgPos(255),
						scaleImgWidth(safe), scaleImgHeight(safe), null);
				boundingBox(scaleImgPos(18), scaleImgPos(255),
						scaleImgWidth(safe), scaleImgHeight(safe));
				break;
			}
			break;
		case "Sidetable":
			switch (d) {
			case NORTH:
				g.drawImage(sideTableSide, scaleImgPos(485), scaleImgPos(300),
						scaleImgWidth(sideTableSide),
						scaleImgHeight(sideTableSide), null);
				boundingBox(scaleImgPos(485), scaleImgPos(300),
						scaleImgWidth(sideTableSide),
						scaleImgHeight(sideTableSide));
				break;
			case EAST:
				g.drawImage(sideTable, scaleImgPos(225), scaleImgPos(240),
						scaleImgWidth(sideTable),
						scaleImgHeight(sideTableSide), null);
				boundingBox(scaleImgPos(225), scaleImgPos(240),
						scaleImgWidth(sideTable), scaleImgHeight(sideTableSide));
				break;
			}
			break;

		case "Study Room Bin":
			switch (d) {
			case NORTH:
				g.drawImage(bin, scaleImgPos(18), scaleImgPos(255),
						scaleImgWidth(bin), scaleImgHeight(bin), null);
				boundingBox(scaleImgPos(18), scaleImgPos(255),
						scaleImgWidth(bin), scaleImgHeight(bin));
				break;
			case WEST:
				g.drawImage(bin, scaleImgPos(490), scaleImgPos(255),
						scaleImgWidth(bin), scaleImgHeight(bin), null);
				boundingBox(scaleImgPos(490), scaleImgPos(255),
						scaleImgWidth(bin), scaleImgHeight(bin));
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

		case "Study Room Safe":
			switch (d) {
			case NORTH:
				g.drawImage(safe, scaleImgPos(480), scaleImgPos(255),
						scaleImgWidth(safe), scaleImgHeight(safe), null);
				boundingBox(scaleImgPos(480), scaleImgPos(255),
						scaleImgWidth(safe), scaleImgHeight(safe));
				break;
			case EAST:
				g.drawImage(safeSide, scaleImgPos(15), scaleImgPos(255),
						scaleImgWidth(safeSide), scaleImgHeight(safeSide), null);
				boundingBox(scaleImgPos(15), scaleImgPos(255),
						scaleImgWidth(safeSide), scaleImgHeight(safeSide));
				break;
			}
			break;
		}
	}

	/*---------------LOAD CONTAINER IMAGES---------------*/
	private static final Image bookshelf = GameCanvas
			.loadImage("/images/bookshelf.png");
	private static final Image bookshelfSide = GameCanvas
			.loadImage("/images/bookshelfSide.png");
	private static final Image bin = GameCanvas.loadImage("/images/bin.png");
	private static final Image cupboard = GameCanvas
			.loadImage("/images/moderncupboard.png");
	private static final Image cupboardSide = GameCanvas
			.loadImage("/images/moderncupboardSide.png");
	private static final Image fridge = GameCanvas
			.loadImage("/images/fridge.png");
	private static final Image fridgeSide = GameCanvas
			.loadImage("/images/fridgeleft.png");
	private static final Image safe = GameCanvas.loadImage("/images/safe.png");
	private static final Image safeSide = GameCanvas
			.loadImage("/images/safeSide.png");
	private static final Image sideTable = GameCanvas
			.loadImage("/images/sideTable.png");
	private static final Image sideTableSide = GameCanvas
			.loadImage("/images/sideTableSide.png");
	private static final Image lamp = GameCanvas.loadImage("/images/lamp.png");

}
