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
		case "Bedroom Bin":
			switch (d) {
			case NORTH:
				g.drawImage(bin, scaleImgPos(18), scaleImgPos(260), scaleImgWidth(bin), scaleImgHeight(bin), null);
				break;
			case WEST:
				g.drawImage(bin, scaleImgPos(490), scaleImgPos(260), scaleImgWidth(bin), scaleImgHeight(bin), null);
				break;
			}
			break;
			
		case "Bedroom Safe":
			switch (d) {
			case SOUTH:
				g.drawImage(safe, scaleImgPos(480), scaleImgPos(275), scaleImgWidth(safe), scaleImgHeight(safe), null);
				break;
			case WEST:
				g.drawImage(safeSide, scaleImgPos(18), scaleImgPos(275), scaleImgWidth(safeSide), scaleImgHeight(safeSide), null);
				break;
			}
			break;
		
		case "Bookshelf":
			switch (d) {
			case SOUTH:
				g.drawImage(bookshelfSide, scaleImgPos(490), scaleImgPos(55), scaleImgWidth(bookshelfSide),
						scaleImgHeight(bookshelfSide), null);
				break;
			case WEST:
				g.drawImage(bookshelf, scaleImgPos(20), scaleImgPos(35), scaleImgWidth(bookshelf),
						scaleImgHeight(bookshelf), null);
				break;
			}
			break;
			
		case "Cupboard":
			switch(d){
			case WEST:
				g.drawImage(cupboard,  scaleImgPos(30), scaleImgPos(220), scaleImgWidth(cupboard), scaleImgHeight(cupboard), null);
				break;
			case SOUTH: //needs to be side view
				g.drawImage(cupboardSide,  scaleImgPos(450), scaleImgPos(220), scaleImgWidth(cupboardSide), scaleImgHeight(cupboardSide), null);
				break;
			}
			break;
			
		case "Fridge": 
			switch(d) {
			case NORTH:
				g.drawImage(fridge, scaleImgPos(345), scaleImgPos(110), scaleImgWidth(fridge), scaleImgHeight(fridge), null);
				break;
			case EAST: 
				g.drawImage(fridgeSide, scaleImgPos(-70), scaleImgPos(110), scaleImgWidth(fridgeSide), scaleImgHeight(fridgeSide), null);
				break;
			}
			break;
			
		case "Kitchen Bin":
			switch (d) {
			case NORTH:
				g.drawImage(bin, scaleImgPos(18), scaleImgPos(255), scaleImgWidth(bin), scaleImgHeight(bin), null);
				break;
			case WEST:
				g.drawImage(bin, scaleImgPos(490), scaleImgPos(255), scaleImgWidth(bin), scaleImgHeight(bin), null);
				break;
			}
			break;

		case "Living Room Bin":
			switch (d) {
			case NORTH:
				g.drawImage(bin, scaleImgPos(18), scaleImgPos(255), scaleImgWidth(bin), scaleImgHeight(bin), null);
				break;
			case WEST:
				g.drawImage(bin, scaleImgPos(490), scaleImgPos(255), scaleImgWidth(bin), scaleImgHeight(bin), null);
				break;
			}
			break;

		case "Living Room Safe":
			switch (d) {
			case SOUTH:
				g.drawImage(safeSide, scaleImgPos(480), scaleImgPos(255), scaleImgWidth(safeSide), scaleImgHeight(safeSide), null);
				break;
			case WEST:
				g.drawImage(safe, scaleImgPos(18), scaleImgPos(255), scaleImgWidth(safe), scaleImgHeight(safe), null);
				break;
			}
			break;
			
		case "Sidetable":
			switch (d) {
			case NORTH:
				g.drawImage(sideTableSide, scaleImgPos(485), scaleImgPos(300), scaleImgWidth(sideTableSide), scaleImgHeight(sideTableSide), null);
				break;
			case EAST:
				g.drawImage(sideTable, scaleImgPos(225), scaleImgPos(240), scaleImgWidth(sideTable), scaleImgHeight(sideTableSide), null);
				break;
			}
			break;

		case "Study Room Bin":
			switch (d) {
			case NORTH:
				g.drawImage(bin, scaleImgPos(18), scaleImgPos(255), scaleImgWidth(bin), scaleImgHeight(bin), null);
				break;
			case WEST:
				g.drawImage(bin, scaleImgPos(490), scaleImgPos(255), scaleImgWidth(bin), scaleImgHeight(bin), null);
				break;
			}
			break;

		case "Study Room Safe":
			switch (d) {
			case NORTH:
				g.drawImage(safe, scaleImgPos(480), scaleImgPos(255), scaleImgWidth(safe), scaleImgHeight(safe), null);
				break;
			case EAST:
				g.drawImage(safeSide, scaleImgPos(15), scaleImgPos(255), scaleImgWidth(safeSide), scaleImgHeight(safeSide), null);
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
	private static final Image cupboard = GameCanvas.loadImage("/images/moderncupboard.png");
	private static final Image cupboardSide = GameCanvas.loadImage("/images/moderncupboardSide.png");
	private static final Image fridge = GameCanvas.loadImage("/images/fridge.png");
	private static final Image fridgeSide = GameCanvas.loadImage("/images/fridgeleft.png");
	private static final Image safe = GameCanvas.loadImage("/images/safe.png");
	private static final Image safeSide = GameCanvas.loadImage("/images/safeSide.png");
	private static final Image sideTable = GameCanvas.loadImage("/images/sideTable.png");
	private static final Image sideTableSide = GameCanvas.loadImage("/images/sideTableSide.png");


}
