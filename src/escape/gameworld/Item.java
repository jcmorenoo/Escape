package escape.gameworld;

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Image;

import escape.gameworld.Player.Direction;
import escape.ui.GameCanvas;

public class Item {
	private double x;
	private double y;
	private String name;
	private String description;
	private int positionX;
	private int positionY;

	private enum direction {
		NORTH, EAST, WEST, SOUTH
	}

	private boolean movable;
	private boolean pickable;

	public Item(String n, String d, boolean movable, boolean pickable) {
		this.name = n;
		this.description = d;
		this.movable = movable;
		this.pickable = pickable;
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

	public boolean isMovable() {
		return movable;
	}

	public void setMovable(boolean movable) {
		this.movable = movable;
	}

	public boolean isPickable() {
		return pickable;
	}

	public void setPickable(boolean pickable) {
		this.pickable = pickable;
	}

	public void pickUp(Player p) {
		p.pickUpItem(this);
	}

	/*---------------DRAWING ITEM IMAGE---------------*/
	public void draw(Graphics g, Room r, Direction d) {
		// String roomName = r.getName();

		for (Item i : r.getItems()) {
			drawItem(g, i, d);
		}
	}

	/*---------------ITEM IMAGE DIRECTION HELPER METHOD---------------*/

	private void drawItem(Graphics g, Item i, Direction d) {
		String itemName = i.getName();

		switch (itemName) {
		case "Chair":
			switch (d) {
			case NORTH:
				g.drawImage(chair, scaleImgPos(250), scaleImgPos(185),
						scaleImgWidth(chair), scaleImgHeight(chair), null);
				break;
			case EAST:
				g.drawImage(chairSide, scaleImgPos(100), scaleImgPos(220),
						scaleImgWidth(chairSide), scaleImgHeight(chairSide), null);
				break;
			}
			break;
		
		case "Frame":
			switch (d) {
			case EAST:
				g.drawImage(pictureFrame, scaleImgPos(224), scaleImgPos(80),
						scaleImgWidth(pictureFrame), scaleImgHeight(pictureFrame), null);
				break;
			}
			break;
			
		case "Desk":
			switch (d) {
			case NORTH:
				g.drawImage(desk, scaleImgPos(175), scaleImgPos(240),
						scaleImgWidth(desk), scaleImgHeight(desk), null);
				break;
			case EAST:
				g.drawImage(deskSide, scaleImgPos(160), scaleImgPos(260),
						scaleImgWidth(deskSide), scaleImgHeight(deskSide), null);
				break;
			}
			break;
			
		case "Lamp":
			switch (d) {
			case EAST:
				g.drawImage(lamp, scaleImgPos(490), scaleImgPos(275),
						scaleImgWidth(lamp), scaleImgHeight(lamp), null);
				break;
			case SOUTH:
				g.drawImage(lamp, scaleImgPos(10), scaleImgPos(275),
						scaleImgWidth(lamp), scaleImgHeight(lamp), null);
				break;
			}
			break;
			
		case "Living Room Picture":
			switch (d) {
			case EAST:
				g.drawImage(livingRoomPicture, scaleImgPos(400), scaleImgPos(80),
						scaleImgWidth(livingRoomPicture), scaleImgHeight(livingRoomPicture), null);
				break;
			}
			break;
			
		case "Long Table":
			switch (d) {
			case NORTH:
				g.drawImage(longTable, scaleImgPos(227), scaleImgPos(292),
						scaleImgWidth(longTable), scaleImgHeight(longTable),
						null);
				break;
			case EAST:
				g.drawImage(longTableSide, scaleImgPos(205), scaleImgPos(280),
						scaleImgWidth(longTableSide), scaleImgHeight(longTableSide),
						null);
				break;
			}
			break;
			
		case "Portrait":
			switch (d) {
			case EAST:
				g.drawImage(portrait, scaleImgPos(60), scaleImgPos(80),
						scaleImgWidth(portrait), scaleImgHeight(portrait), null);
				break;
			}
			break;

		case "Sofa":
			switch (d) {
			case NORTH:
				g.drawImage(sofa, scaleImgPos(200), scaleImgPos(200),
						scaleImgWidth(sofa), scaleImgHeight(sofa), null);
				break;
			case EAST:
				g.drawImage(sofaLeft, scaleImgPos(15), scaleImgPos(220),
						scaleImgWidth(sofaLeft), scaleImgHeight(sofaLeft), null);
				break;
			}
			break;

		case "Study Door":
			switch (d) {
			case SOUTH:
				g.drawImage(studyDoor, scaleImgPos(239), scaleImgPos(95),
						scaleImgWidth(studyDoor), scaleImgHeight(studyDoor), null);
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
	private static final Image chair = GameCanvas.loadImage("/images/chair.png");
	private static final Image chairSide = GameCanvas.loadImage("/images/chairSide.png");
	private static final Image desk = GameCanvas.loadImage("/images/desk.png");
	private static final Image deskSide = GameCanvas.loadImage("/images/deskSide.png");
	private static final Image lamp = GameCanvas.loadImage("/images/lamp.png");
	private static final Image livingRoomPicture = GameCanvas.loadImage("/images/livingRoomPicture.png");
	private static final Image longTable = GameCanvas.loadImage("/images/longTable.png");
	private static final Image longTableSide = GameCanvas.loadImage("/images/longTableSide.png");
	private static final Image mainDoor = GameCanvas.loadImage("/images/mainDoor.png");
	private static final Image pictureFrame = GameCanvas.loadImage("/images/pictureFrame.png");
	private static final Image portrait = GameCanvas.loadImage("/images/portrait.png");
	private static final Image sofa = GameCanvas.loadImage("/images/sofa.png");
	private static final Image sofaLeft = GameCanvas.loadImage("/images/sofaLeftSide.png");
	private static final Image studyDoor = GameCanvas.loadImage("/images/studyDoor.png");


}
