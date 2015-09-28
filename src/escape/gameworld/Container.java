package escape.gameworld;

import java.awt.Graphics;
import java.awt.Image;
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

	public Container(String n, String d, boolean movable, boolean pickable,
			boolean l, String key) {
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

	/*---------------DRAWING ITEM IMAGE---------------*/
	public void draw(Graphics g, Room r, Direction d) {
		// String roomName = r.getName();

		for (Container c : r.getContainer()) {
			drawContainer(g, c, d);
		}
	}

	/*---------------ITEM IMAGE DIRECTION HELPER METHOD---------------*/

	private void drawContainer(Graphics g, Item i, Direction d) {
		String containerName = i.getName();

		switch (containerName) {
		case "Bookshelf":
			switch (d) {
			case SOUTH:
				g.drawImage(bookshelfSide, scaleImgPos(490), scaleImgPos(55),
						scaleImgWidth(bookshelfSide), scaleImgHeight(bookshelfSide), null);
				break;
			case WEST:
				g.drawImage(bookshelf, scaleImgPos(20), scaleImgPos(35),
						scaleImgWidth(bookshelf), scaleImgHeight(bookshelf), null);
				break;
			}
			break;
		
		case "Living Room Bin":
			switch (d) {
			case NORTH:
				g.drawImage(bin, scaleImgPos(18), scaleImgPos(255),
						scaleImgWidth(bin), scaleImgHeight(bin), null);
				break;
			case WEST:
				g.drawImage(bin, scaleImgPos(490), scaleImgPos(255),
						scaleImgWidth(bin), scaleImgHeight(bin), null);
				break;
			}
			break;
			
		case "Living Room Safe":
			switch (d) {
			case SOUTH:
				g.drawImage(safe, scaleImgPos(480), scaleImgPos(255),
						scaleImgWidth(safe), scaleImgHeight(safe), null);
				break;
			case WEST:
				g.drawImage(safe, scaleImgPos(18), scaleImgPos(255),
						scaleImgWidth(safe), scaleImgHeight(safe), null);
				break;
			}
			break;
			
		case "Study Room Bin":
			switch (d) {
			case NORTH:
				g.drawImage(bin, scaleImgPos(18), scaleImgPos(255),
						scaleImgWidth(bin), scaleImgHeight(bin), null);
				break;
			case WEST:
				g.drawImage(bin, scaleImgPos(490), scaleImgPos(255),
						scaleImgWidth(bin), scaleImgHeight(bin), null);
				break;
			}
			break;
			
		case "Study Room Safe":
			switch (d) {
			case NORTH:
				g.drawImage(safe, scaleImgPos(480), scaleImgPos(265),
						scaleImgWidth(safe), scaleImgHeight(safe), null);
				break;
			case EAST:
				g.drawImage(safe, scaleImgPos(15), scaleImgPos(255),
						scaleImgWidth(safe), scaleImgHeight(safe), null);
				break;
			}
			break;

		}

	}

	/*---------------ITEM IMAGE SCALING---------------*/
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
	private static final Image bookshelf = GameCanvas.loadImage("/images/bookshelf.png");
	private static final Image bookshelfSide = GameCanvas.loadImage("/images/bookshelfSide.png");
	private static final Image bin = GameCanvas.loadImage("/images/bin.png");
	private static final Image safe = GameCanvas.loadImage("/images/safe.png");

}
