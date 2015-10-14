package escape.ui;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.geom.AffineTransform;
import java.awt.image.AffineTransformOp;
import java.awt.image.BufferedImage;
import java.io.FileInputStream;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JPanel;

import escape.client.Client;
import escape.gameworld.Container;
import escape.gameworld.Item;
import escape.gameworld.Player;
import escape.gameworld.Player.Direction;
import escape.gameworld.Room;

/**
 * The GameCanvas is responsible for drawing the wall backgrounds of the rooms
 * in the game. It resizes the game window, wall backgrounds, and items in the
 * game according to the computer resolution of the user. *
 *
 * @author Trisha*
 */

public class GameCanvas extends JPanel {
	private static Image wall = loadImage("/images/wall.png");
	private static Image wallToHall = loadImage("/images/wallToHall.png");
	private static Image darkWall = loadImage("/images/darkWall.png");
	private static Image hall = loadImage("/images/hall.png");
	private static Image hallMain = loadImage("/images/hallMain.png");
	private static Image hallLeftStudy = loadImage("/images/hallLeftStudy.png");
	private static Image hallRightBedroom = loadImage("/images/hallRightBedroom.png");
	private static Image hallLeftKitchen = loadImage("/images/hallLeftKitchen.png");
	private static Image hallRightLivingRoom = loadImage("/images/hallRightLivingRoom.png");
	private static Image loadScreen = loadImage("/images/loadScreen.png");
	private static Image mainMenu = loadImage("/images/mainMenu.png");
	private static Image lost = loadImage("/images/lost.png");
	private static Image won = loadImage("/images/won.png");

	private Player player;
	private Client client;
	private Room currentRoom;
	private Player.Direction currentDirection;
	private int state;

	// Values used to scale Game Window & wall backgrounds
	private static final double WINDOW_HEIGHT_SCALE = 0.6;
	private static final double WINDOW_WIDTH_SCALE = 0.375;
	private static final double BACKGROUND_WIDTH_SCALE = 0.8;
	private static final double BACKGROUND_HEIGHT_SCALE = 0.814;
	private static final double INNER_BACKGROUND_WIDTH_SCALE = 0.71;
	private static final double INNER_BACKGROUND_HEIGHT_SCALE = 0.71;

	private Toolkit t = Toolkit.getDefaultToolkit();;
	private Dimension d = t.getScreenSize();

	private int h = (int) (d.height * WINDOW_HEIGHT_SCALE);
	private int w = (int) (d.width * WINDOW_WIDTH_SCALE);

	// Creates a new Canvas using a client
	public GameCanvas(Client c) {
		client = c;
		player = c.getPlayer();
		getPreferredSize();
	}

	// Creates a new Canvas using a player
	public GameCanvas(Player p) {
		player = p;
		getPreferredSize();
	}

	public void paint(Graphics g) {
		g.setColor(Color.BLACK);
		g.fillRect(0, 0, getWidth(), getHeight());
		state = GameFrame.state();

		// Draws wall backgrounds depending on player's current room
		if (state == 0) { // Main Menu - Game has not been created
			// g.drawImage(mainMenu, 0, 0, scaleImgWidth(mainMenu),
			// scaleImgHeight(mainMenu), null);
			mainMenu = convertImage(mainMenu);
			g.drawImage(mainMenu, 0, 0, null);
		} else if (state == 1) { // Game has been created and started
			if (player.getRoom() != null) {
				currentRoom = player.getRoom();
				currentDirection = player.getDirection();
				switch (currentRoom.getName()) {

				case "Main Hall":
					// g.drawImage(hallMain, 0, 0, scaleImgWidth(hallMain),
					// scaleImgHeight(hallMain), null);
					hallMain = convertImage(hallMain);
					g.drawImage(hallMain, 0, 0, null);
					break;
				case "Hall - Study":
					// g.drawImage(hallLeftStudy, 0, 0,
					// scaleImgWidth(hallLeftStudy),
					// scaleImgHeight(hallLeftStudy), null);
					hallLeftStudy = convertImage(hallLeftStudy);
					g.drawImage(hallLeftStudy, 0, 0, null);
					break;
				case "Hall - Bedroom":
					// g.drawImage(hallRightBedroom, 0, 0,
					// scaleImgWidth(hallRightBedroom),
					// scaleImgHeight(hallRightBedroom), null);
					hallRightBedroom = convertImage(hallRightBedroom);
					g.drawImage(hallRightBedroom, 0, 0, null);
					break;
				case "Hall - Kitchen":
					// g.drawImage(wall, scaleInsideImgPos(58),
					// scaleInsideImgPos(0), scaleInsideImgWidth(wall),
					// scaleInsideImgHeight(wall), null);
					wall = convertImage(wall);
					g.drawImage(wall, scaleInsideImgPos((int) (w * 0.19)),
							scaleInsideImgPos((int) (h * 0.08)),
							scaleInsideImgWidth(wall),
							scaleInsideImgHeight(wall), null);
					boolean displayPicture = false;
					if (player.getItems() != null) {
						for (Item i : player.getItems())
							if (i.getName().equals("Kitchen Picture")) {
								displayPicture = true;
							} else {
								displayPicture = true;
							}
					}
					Item.draw(g, "Kitchen", displayPicture);
					Container.draw(g, "Kitchen");
					// g.drawImage(hallLeftKitchen, 0, 0,
					// scaleImgWidth(hallLeftStudy),
					// scaleImgHeight(hallLeftStudy), null);
					hallLeftKitchen = convertImage(hallLeftKitchen);
					g.drawImage(hallLeftKitchen, 0, 0, null);
					break;
				case "Hall - Living Room":
					// g.drawImage(wall, scaleInsideImgPos(58),
					// scaleInsideImgPos(0), scaleInsideImgWidth(wall),
					// scaleInsideImgHeight(wall), null);
					g.drawImage(wall, scaleInsideImgPos((int) (w * 0.19)),
							scaleInsideImgPos((int) (h * 0.08)),
							scaleInsideImgWidth(wall),
							scaleInsideImgHeight(wall), null);
					Item.draw(g, "Living Room", true);
					Container.draw(g, "Living Room");
					// g.drawImage(hallRightLivingRoom, 0, 0,
					// scaleImgWidth(hallRightLivingRoom),
					// scaleImgHeight(hallRightLivingRoom), null);
					hallRightLivingRoom = convertImage(hallRightLivingRoom);
					g.drawImage(hallRightLivingRoom, 0, 0, null);
					break;
				case "Bedroom":
					// g.drawImage(darkWall, 0, 0, scaleImgWidth(darkWall),
					// scaleImgHeight(darkWall), null);
					darkWall = convertImage(darkWall);
					g.drawImage(darkWall, 0, 0, null);
					break;
				case "Living Room":
				case "Kitchen":
					// Draws the hall inside the entrance way when facing SOUTH
					// in the Kitchen or Living Room
					if (player.getDirection().equals(Direction.SOUTH)) {
						// g.drawImage(hall, scaleInsideImgPos(58),
						// scaleInsideImgPos(0),
						// scaleInsideImgWidth(hall),
						// scaleInsideImgHeight(hall), null);
						hall = convertImage(hall);
						g.drawImage(hall, scaleInsideImgPos((int) (w * 0.185)),
								scaleInsideImgPos((int) (h * 0.08)),
								scaleInsideImgWidth(wall),
								scaleInsideImgHeight(wall), null);
						// g.drawImage(wallToHall, 0, 0,
						// scaleImgWidth(wallToHall),
						// scaleImgHeight(wallToHall), null);
						wallToHall = convertImage(wallToHall);
						g.drawImage(wallToHall, 0, 0, null);
					} else {
						wall = convertImage(wall);
						g.drawImage(wall, 0, 0, null);
					}
					break;
				case "Study":
					g.drawImage(wall, 0, 0, null);
					break;
				}

				for (Item i : currentRoom.getItems()) {
					i.draw(g, currentRoom, currentDirection);
				}
				for (Container c : currentRoom.getContainer()) {
					c.draw(g, currentRoom, currentDirection);
				}
				for (Player p : currentRoom.getPlayers()) {
					if (p.getId() != player.getId()) {
						p.draw(g, p.getId(), p.getDirection(),
								player.getDirection());
					}
				}

				int itemOrder = 0;
				if (player.getItems() != null) {
					for (Item i : player.getItems()) {
						i.drawInventoryItem(g, itemOrder);
						itemOrder++;
					}

				}
			}
		}
		// Game is finished, canvas will display appropriate end-game message to
		// player
		else if (state == 3) {
			if (client.isWinner()) {
				g.drawImage(won, 0, 0, scaleImgWidth(won), scaleImgHeight(won),
						null);
			} else {
				g.drawImage(lost, 0, 0, scaleImgWidth(lost),
						scaleImgHeight(lost), null);
			}
		}
		repaint();
	}

	public Dimension getPreferredSize() {
		d.setSize(w, h);
		return d;
	}

	/**
	 * Draws the load screen only for the host while they wait for other players
	 * to connect
	 *
	 * @param g
	 *            - Graphics
	 */
	protected void drawLoadScreen(Graphics g) {
		g.drawImage(loadScreen, 0, 0, scaleImgWidth(loadScreen),
				scaleImgHeight(loadScreen), null);
	}

	public void setResizable(boolean b) {
		getPreferredSize();
	}

	/*---------------SCALE IMAGE---------------*/

	private static int scaleInsideImgPos(int i) {
		return (int) (i * BACKGROUND_WIDTH_SCALE);
	}

	private static int scaleInsideImgWidth(Image img) {
		return (int) (img.getWidth(null) * INNER_BACKGROUND_WIDTH_SCALE);
	}

	private static int scaleInsideImgHeight(Image img) {
		return (int) (img.getHeight(null) * INNER_BACKGROUND_HEIGHT_SCALE);
	}

	private static int scaleImgWidth(Image img) {
		return (int) (img.getWidth(null) * BACKGROUND_WIDTH_SCALE);
	}

	private static int scaleImgHeight(Image img) {
		return (int) (img.getHeight(null) * BACKGROUND_HEIGHT_SCALE);
	}

	/*---------------LOAD IMAGE---------------*/

	public static BufferedImage loadImage(String source) {
		try {
			BufferedImage image = ImageIO.read(new FileInputStream("src/"
					+ source));
			return image;
		} catch (IOException e) {
			throw new RuntimeException("Unable to load image");
		}
	}

	public static BufferedImage getScaledImage(BufferedImage image, int width,
			int height) throws IOException {
		int imageWidth = image.getWidth();
		int imageHeight = image.getHeight();

		double scaleX = (double) width / imageWidth;
		double scaleY = (double) height / imageHeight;
		AffineTransform scaleTransform = AffineTransform.getScaleInstance(
				scaleX, scaleY);
		AffineTransformOp bilinearScaleOp = new AffineTransformOp(
				scaleTransform, AffineTransformOp.TYPE_BILINEAR);

		return bilinearScaleOp.filter(image, new BufferedImage(width, height,
				image.getType()));
	}

	private BufferedImage convertImage(Image img) {
		BufferedImage image = null;
		try {
			image = getScaledImage((BufferedImage) img, w, h);
		} catch (IOException e) {

		}
		return image;
	}

}