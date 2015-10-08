package escape.ui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

import escape.client.Client;
import escape.event.ChangeDirectionEvent;
import escape.event.EnterRoomEvent;
import escape.event.PickUpItemEvent;
import escape.gameworld.Container;
import escape.gameworld.GameWorld;
import escape.gameworld.Item;
import escape.gameworld.Player;
import escape.gameworld.Player.Direction;
import escape.gameworld.Room;
import escape.server.Server;
import escape.ui.GameFrame.MouseEvents;

public class GameFrame extends JFrame implements ActionListener {
	public GameFrame f;

	public static JFrame frame;
	private GameCanvas menuCanvas;
	private final JPanel btnPanel;
	private static Player player;
	private JPanel mouse = new JPanel();
	private GameWorld game;
	private String currentRoom;

	private Server server;
	private Client client;

	// Testing purposes for now
	private static int state;

	static int state() {
		return state;
	}

	void setSt(int i) {
		state = i;
	}

	public GameFrame() {
		frame = new JFrame();
		menuCanvas = new GameCanvas(player);
		btnPanel = new JPanel();

		btnPanel.setBackground(Color.BLACK);

		setTitle("Escape!");
		JMenuBar headerMenu = new JMenuBar();

		JMenuItem saveGame = new JMenuItem("Save Game");
		JMenuItem help = new JMenuItem("Help");
		JMenuItem exit = new JMenuItem("Exit");

		headerMenu.add(saveGame);
		headerMenu.add(help);
		headerMenu.add(exit);

		// Initialise Menu Screen Buttons
		menuBtns();

		saveGame.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.out.println("Save Game");
			}
		});

		exit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String ObjButtons[] = { "Yes", "No" };
				int PromptResult = JOptionPane.showOptionDialog(null,
						"Are you sure you want to exit?", "Exit 'Escape!'?",
						JOptionPane.DEFAULT_OPTION,
						JOptionPane.WARNING_MESSAGE, null, ObjButtons,
						ObjButtons[1]);
				if (PromptResult == 0) {
					System.exit(0);
				}
			}
		});

		frame.addMouseListener(new MouseEvents());
		// JPanel mouse = new JPanel();
		// mouse.setBackground(Color.BLACK);
		// mouse.addMouseListener(new MouseEvents());
		// frame.add(mouse);

		frame.add(menuCanvas, BorderLayout.NORTH);
		frame.add(btnPanel, BorderLayout.SOUTH);

		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setJMenuBar(headerMenu);

		frame.pack();
		frame.setLocationRelativeTo(null);
		frame.setResizable(false);
		frame.setVisible(true);
		f = this;
	}

	/*---------------BUTTON INITIALISATION---------------*/

	void menuBtns() {
		JButton newGameButton = new JButton("New Game");
		btnPanel.add(newGameButton);
		this.newGame(newGameButton);

		JButton loadGameButton = new JButton("Load Game");
		btnPanel.add(loadGameButton);
		this.loadGame(loadGameButton);
	}

	void gameBtns() {
		initialiseCurrentRoom(client);
		player = client.getPlayer();

		JButton goForwardButton = new JButton("Forward");
		btnPanel.add(goForwardButton);
		forward(goForwardButton);

		JButton northButton = new JButton("^");
		btnPanel.add(northButton);
		north(northButton);

		JButton southButton = new JButton("v");
		btnPanel.add(southButton);
		south(southButton);

		JButton eastButton = new JButton(">");
		btnPanel.add(eastButton);
		east(eastButton);

		JButton westButton = new JButton("<");
		btnPanel.add(westButton);
		west(westButton);
	}

	/*---------------BUTTON LISTENERS---------------*/
	public void newGame(JButton newGame) {
		newGame.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				String ObjButtons[] = { "Join", "Host" };
				int gameType = JOptionPane.showOptionDialog(null,
						"Do you want to host or join a game?", "New Game",
						JOptionPane.DEFAULT_OPTION,
						JOptionPane.WARNING_MESSAGE, null, ObjButtons,
						ObjButtons[1]);

				// Host a game
				if (gameType == 1) {
					Object[] numOptions = { 1, 2, 3, 4, 5, 6 };
					int numPlayers = (int) JOptionPane.showInputDialog(frame,
							"How many players?", "Choose a number",
							JOptionPane.QUESTION_MESSAGE, null, numOptions,
							numOptions[0]);

					// Lets player enter a username
					JTextField nameInput = new JTextField();
					Object[] usernamePrompt = { "Enter a username: ",
							nameInput.getText() };
					final String username = JOptionPane.showInputDialog(null,
							usernamePrompt, "Player Username",
							JOptionPane.INFORMATION_MESSAGE);
					// player = new Player(1, username, new Room("Main Hall",
					// false, "No key"));

					// Lets the host to specify a unique Game ID for other
					// players wanting to join, to enter in
					JTextField gameIdInput = new JTextField();
					Object[] gameIdPrompt = { "Enter a Game ID: ",
							gameIdInput.getText() };
					final String gameID = JOptionPane.showInputDialog(null,
							gameIdPrompt, "Game ID",
							JOptionPane.INFORMATION_MESSAGE);

					game = new GameWorld(gameID);

					server = new Server(numPlayers, gameID.toString());
					server.start();

					client = new Client("localhost", username);
					client.setFrame(f);
					client.start();

					setSt(2);
					System.out.println("State: " + state);
					updateFrame();
					System.out.println("State: " + state);
					while (client.getPlayer() == null) {
//						System.out.println("Client has no player");
						if (client.getPlayer() != null) {
							System.out.println("Client player name:"
									+ client.getPlayer().getName());
							player = client.getPlayer();
							break;
						}
					}
					
					

					// TESTING: rooms, containers, items
					// ArrayList<Room> it = game.getRoomList();
					// for (int i = 0; i < it.size(); i++) {
					// Room r = it.get(i);
					// System.out.println("\n  ROOMS:");
					// System.out.println(r.getName());
					// System.out.println("  ITEMS IN ROOM:");
					// for (Item item : r.getItems()) {
					// System.out.println(item.getName());
					// }
					// for (Container c : r.getContainer()) {
					// System.out.println("  CONTAINERS:");
					// System.out.println(c.getName());
					// System.out.println("  ITEMS IN CONTAINER:");
					// for (Item item : c.getItems()) {
					// System.out.println(item.getName());
					// }
					// }
					// }

					// Testing purposes
					setSt(1);
					// client.sendEvent(new PickUpItemEvent(client.getPlayer(),
					// new Item("Paper", "A piece of paper.", true)));
					client.sendEvent(new PickUpItemEvent(client.getPlayer(),
							new Item("Kitchen Picture", "Family Picture", true)));
				}

				// Join a game
				if (gameType == 0) {

					// Lets the player enter a username
					JTextField nameInput = new JTextField();
					Object[] usernamePrompt = { "Enter a username: ",
							nameInput.getText() };
					final String username = JOptionPane.showInputDialog(null,
							usernamePrompt, "Player Username",
							JOptionPane.INFORMATION_MESSAGE);

					// Lets the player enter IP address of the server
					JTextField serverAddrInput = new JTextField();
					Object[] serverAddrPrompt = {
							"Enter IP address of server: ",
							serverAddrInput.getText() };
					final String serverAddr = JOptionPane.showInputDialog(null,
							serverAddrPrompt, "Server IP Address",
							JOptionPane.INFORMATION_MESSAGE);

					// Lets the player enter a Game ID to join that game
					JTextField gameIdInput = new JTextField();
					Object[] gameIdPrompt = { "Enter a Game ID: ",
							gameIdInput.getText() };
					final String gameID = JOptionPane.showInputDialog(null,
							gameIdPrompt, "Game ID",
							JOptionPane.INFORMATION_MESSAGE);

					client = new Client(serverAddr.toString(), username);
					client.setFrame(f);
					client.start();

					while (client.getPlayer() == null) {
						System.out.println("Client has no player");
						if (client.getPlayer() != null) {
							System.out.println("Client player name:"
									+ client.getPlayer().getName());
							player = client.getPlayer();
							break;
						}
					}
					// Testing purposes
					setSt(1);
				}
			}
		});
	}

	/*
	 * Stores the room a player is currently in, as a String
	 */
	protected void initialiseCurrentRoom(Client c) {
		currentRoom = c.getPlayer().getRoom().getName();
	}

	public void loadGame(JButton loadGame) {
		loadGame.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				System.out.println("Load Game");
			}
		});
	}

	/*
	 * Moves player forward to enter/leave a room
	 */
	public void forward(JButton goForward) {
		goForward.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				System.out.println("Walking Forward");

				switch (currentRoom) {
				case "Main Hall":
					System.out.println("Going through Main Door");
					break;
				case "Hall - Bedroom":
					client.sendEvent(new EnterRoomEvent(player, "Bedroom"));
					break;
				case "Hall - Living Room":
					client.sendEvent(new EnterRoomEvent(player, "Living Room"));
					break;
				case "Hall - Study":
					client.sendEvent(new EnterRoomEvent(player, "Study"));
					break;
				case "Hall - Kitchen":
					client.sendEvent(new EnterRoomEvent(player, "Kitchen"));
//					 client.sendEvent(new PickUpItemEvent(client.getPlayer(),
//					 new Item("Kitchen Picture", "Family Picture", true)));
					break;
				}

				// TESTING
				System.out.println(player.getName() + " is facing "
						+ player.getDirection().toString());

				// If player is leaving any of these rooms: Kitchen, Living
				// Room, Study, Bedroom
				if (player.getDirection().equals(Direction.SOUTH)) {
					switch (currentRoom) {
					case "Kitchen":
						client.sendEvent(new EnterRoomEvent(player,
								"Hall - Kitchen"));
						break;
					case "Living Room":
						client.sendEvent(new EnterRoomEvent(player,
								"Hall - Living Room"));
						break;
					case "Study":
						client.sendEvent(new EnterRoomEvent(player,
								"Hall - Study"));
						break;
					case "Bedroom":
						client.sendEvent(new EnterRoomEvent(player,
								"Hall - Bedroom"));
						break;
					}

					// !----Direction not changing back to NORTH when entering
					// room----!
					client.sendEvent(new ChangeDirectionEvent(player, "NORTH"));
				}

				// TESTING
				System.out.println(player.getName() + " is in the "
						+ currentRoom);
			}
		});
	}

	/*
	 * Changes the player's direction to face NORTH when inside a room
	 */
	public void north(JButton north) {
		north.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TESTING
				System.out.println(player.getName() + " is facing "
						+ player.getDirection().toString());

				switch (currentRoom) {
				case "Living Room":
				case "Kitchen":
				case "Bedroom":
				case "Study":
					client.sendEvent(new ChangeDirectionEvent(player, "NORTH"));
				}
			}
		});
	}

	/*
	 * Function depends on player's current room: -> IN THE HALL - Moves a
	 * player to the right of the hall -> IN A ROOM - Changes the player's
	 * direction to face EAST
	 */
	public void east(JButton east) {
		east.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				switch (currentRoom) {
				case "Main Hall":
					client.sendEvent(new EnterRoomEvent(player,
							"Hall - Bedroom"));
					break;
				case "Hall - Bedroom":
					client.sendEvent(new EnterRoomEvent(player,
							"Hall - Living Room"));
					break;
				case "Hall - Living Room":
					JOptionPane.showMessageDialog(null,
							"No more rooms that way!", "Ooops",
							JOptionPane.WARNING_MESSAGE);
					break;
				case "Hall - Study":
					client.sendEvent(new EnterRoomEvent(player, "Main Hall"));
					break;
				case "Hall - Kitchen":
					client.sendEvent(new EnterRoomEvent(player, "Hall - Study"));
					break;
				}

				// TESTING
				System.out.println(player.getName() + " is facing "
						+ player.getDirection().toString());
				switch (currentRoom) {
				case "Living Room":
				case "Kitchen":
				case "Bedroom":
				case "Study":
					client.sendEvent(new ChangeDirectionEvent(player, "EAST"));
				}
			}
		});
	}

	/*
	 * Changes the player's direction to face SOUTH when inside a room
	 */
	public void south(JButton south) {
		south.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TESTING
				System.out.println(player.getName() + " is facing "
						+ player.getDirection().toString());

				switch (currentRoom) {
				case "Living Room":
				case "Kitchen":
				case "Bedroom":
				case "Study":
					client.sendEvent(new ChangeDirectionEvent(player, "SOUTH"));
				}
			}
		});
	}

	/*
	 * Function depends on player's current room: -> IN THE HALL - Moves a
	 * player to the left of the hall -> IN A ROOM - Changes the player's
	 * direction to face WEST
	 */
	public void west(JButton west) {
		west.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				switch (player.getRoom().getName()) {
				case "Main Hall":
					client.sendEvent(new EnterRoomEvent(player, "Hall - Study"));
					break;
				case "Hall - Study":
					client.sendEvent(new EnterRoomEvent(player,
							"Hall - Kitchen"));
					break;
				case "Hall - Kitchen":
					JOptionPane.showMessageDialog(null,
							"No more rooms that way!", "Ooops",
							JOptionPane.WARNING_MESSAGE);
					break;
				case "Hall - Bedroom":
					client.sendEvent(new EnterRoomEvent(player, "Main Hall"));
					break;
				case "Hall - Living Room":
					client.sendEvent(new EnterRoomEvent(player,
							"Hall - Bedroom"));
					break;
				}

				switch (player.getRoom().getName()) {
				case "Living Room":
				case "Kitchen":
				case "Bedroom":
				case "Study":
					client.sendEvent(new ChangeDirectionEvent(player, "WEST"));
				}

				// TESTING
				System.out.println(player.getName() + " is facing "
						+ player.getDirection().toString());
			}
		});
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
	}

	/*---------------MOUSE LISTENER---------------*/

	class MouseEvents implements MouseListener {
		public void mousePressed(MouseEvent e) {

			// if (player.getRoom().getName().equals("Hall")) {
			// if (e.getX() > 36 && e.getX() < 116 && e.getY() > 220
			// && e.getY() < 377) {
			// System.out.println("Clicked on Living Room Door");
			// player.leaveRoom();
			// player.enterRoom(new Room("Living Room", false, "No key"));
			// }
			// }

		}

		@Override
		public void mouseClicked(MouseEvent e) {
			if (game == null)
				return;
			String currentRoom = player.getRoom().getName();
			// System.out.print("You are in " + currentRoom);
			if (currentRoom.equals("Main Hall")
					|| currentRoom.equals("Hall - Bedroom")
					|| currentRoom.equals("Hall - Kitchen")
					|| currentRoom.equals("Hall - Study")
					|| currentRoom.equals("Hall - Living Room")) {
				return;
			} else {
				System.out.println("Mouse x: " + e.getX() + "\nMouse Y: "
						+ e.getY());
				HashMap<String, String[][]> currentLoc = player.getRoom()
						.getItemsByDirection();
				Direction d = player.getDirection();
				String[][] items = null;
				switch (d) {
				case NORTH:
					items = (String[][]) currentLoc.get("North");
					break;
				case SOUTH:
					items = (String[][]) currentLoc.get("South");
					break;
				case EAST:
					items = (String[][]) currentLoc.get("East");
					break;
				case WEST:
					items = (String[][]) currentLoc.get("West");
					break;
				}

				// checking if point is in an items bounding box
				for (int i = 5; i >= 0; i--) {
					for (int j = 2; j >= 0; j--) {
						if (!items[j][i].equals("")) {
							Item it = player.getRoom().getItem(items[j][i]);
							if (it.getBoundingBox().contains(e.getX(),
									e.getY() - 40)) {
								System.out.println("Bounding Box X: "
										+ it.getBoundingBox().x
										+ "\nBounding Box Y: "
										+ it.getBoundingBox().y
										+ "\nBounding Box Width: "
										+ it.getBoundingBox().width
										+ "\nBounding Box Height: "
										+ it.getBoundingBox().height);
								game.setSelectedItem(it);
								System.out.println(game.getSelectedItem()
										.getName());
								return;
							}
						}

					}
				}

			}
		}

		@Override
		public void mouseReleased(MouseEvent e) {
		}

		@Override
		public void mouseEntered(MouseEvent e) {
		}

		@Override
		public void mouseExited(MouseEvent e) {
		}
	}

	public void updateFrame() {
		if (state == 1){
			frame.remove(menuCanvas);
			menuCanvas = new GameCanvas(client);
			btnPanel.removeAll();
			gameBtns();
			frame.add(menuCanvas, BorderLayout.NORTH);
			mouse.addMouseListener(new MouseEvents());
			frame.add(mouse);
			frame.pack();
		}
		else if (state == 2){
			frame.remove(menuCanvas);
			menuCanvas = new GameCanvas(client);
			btnPanel.removeAll();
			frame.add(menuCanvas, BorderLayout.NORTH);
			mouse.addMouseListener(new MouseEvents());
			frame.add(mouse);
			frame.pack();
		}
	}

	public void setClient(Client c) {
		this.client = c;
	}

	public Client getClient() {
		return this.client;
	}

}