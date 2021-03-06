package escape.ui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.HashMap;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;

import escape.client.Client;
import escape.data.XMLFile;
import escape.event.ChangeDirectionEvent;
import escape.event.EnterRoomEvent;
import escape.event.WinnerEvent;
import escape.gameworld.Container;
import escape.gameworld.GameWorld;
import escape.gameworld.Item;
import escape.gameworld.Player;
import escape.gameworld.Player.Direction;
import escape.server.Server;

public class GameFrame extends JFrame implements ActionListener {
	public GameFrame f;

	public static JFrame frame;
	private GameCanvas canvas;
	private final JPanel btnPanel;
	private static Player player = null;
	private JPanel mouse = new JPanel();
	private GameWorld game;
	private String currentRoom;
	private Server server;
	private Client client;

	private Item selectedItem;
	private Item selectedInventory;

	private static int state;

	static int state() {
		return state;
	}

	void setSt(int i) {
		state = i;
	}

	public GameFrame() {
		frame = new JFrame();
		canvas = new GameCanvas(player);
		btnPanel = new JPanel();

		btnPanel.setBackground(Color.BLACK);

		setTitle("Escape!");
		JMenuBar headerMenu = new JMenuBar();

		JMenuItem saveGame = new JMenuItem("Save Game");
		JMenuItem loadGame = new JMenuItem("Load Item");
		JMenuItem help = new JMenuItem("Help");
		JMenuItem exit = new JMenuItem("Exit");

		headerMenu.add(saveGame);
		headerMenu.add(help);
		headerMenu.add(exit);

		// Initialise Menu Screen Buttons
		initialiseMenuBtns();

		saveGame.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (game == null) {
					return;
				}
				XMLFile xml = new XMLFile();
				xml.saveGame(game, player);

			}
		});

		loadGame.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {

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

		frame.add(canvas, BorderLayout.NORTH);
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

	void initialiseMenuBtns() {
		JButton newGameButton = new JButton("New Game");
		btnPanel.add(newGameButton);
		this.newGame(newGameButton);

		JButton loadGameButton = new JButton("Load Game");
		btnPanel.add(loadGameButton);
		this.loadGame(loadGameButton);
	}

	void initialiseGameBtns() {
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
					// player = new Player(1, username, new Room("Main
					// Hall",
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

					String url = server.getIp();
					JOptionPane.showMessageDialog(null, "URL: " + url,
							"Server URL", JOptionPane.NO_OPTION);
					while (client.getPlayer() == null) {

						canvas.drawLoadScreen(canvas.getGraphics());
						
						if (client.getPlayer() != null) {
							player = client.getPlayer();
							game.addPlayer(player);
							break;
						}
					}

					setSt(1);
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

					game = new GameWorld(gameID);
					client = new Client(serverAddr.toString(), username);
					client.setFrame(f);
					client.start();

					while (client.getPlayer() == null) {
						canvas.drawLoadScreen(canvas.getGraphics());
						if (client.getPlayer() != null) {
							player = client.getPlayer();
							game.addPlayer(player);
							break;
						}
					}
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
				XMLFile xml = new XMLFile();
				game = xml.loadGame("SavedGame.xml");
				setSt(1);
				player = game.getPlayers().get(0);
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
				switch (player.getRoom().getName()) {
				case "Main Hall":
					if (game.enterRoom(player, game.getRooms().get("Exit Door"))) {
						client.sendEvent(new WinnerEvent(player));
						player.enterRoom(game.getRooms().get("Exit Door"));
						client.setWinner();
						endGame();
					} else {
						JOptionPane
								.showMessageDialog(
										null,
										"The Door is locked. You need to find the key!",
										"Locked Door",
										JOptionPane.WARNING_MESSAGE);
					}
					break;
				case "Hall - Bedroom":
					client.sendEvent(new EnterRoomEvent(player, "Bedroom"));
					player.enterRoom(game.getRooms().get("Bedroom"));
					break;
				case "Hall - Living Room":
					client.sendEvent(new EnterRoomEvent(player, "Living Room"));
					player.enterRoom(game.getRooms().get("Living Room"));
					break;
				case "Hall - Study":
					if (game.enterRoom(player, game.getRooms().get("Study"))) {
						client.sendEvent(new EnterRoomEvent(player, "Study"));
						game.setSelectedInventory(null);
					} else {
						JOptionPane
								.showMessageDialog(
										null,
										"The Door is locked. You need to find the key!",
										"Locked Room",
										JOptionPane.WARNING_MESSAGE);
					}
					break;
				case "Hall - Kitchen":
					client.sendEvent(new EnterRoomEvent(player, "Kitchen"));
					player.enterRoom(game.getRooms().get("Kitchen"));
					break;
				}

				// If player is leaving any of these rooms: Kitchen, Living
				// Room, Study, Bedroom
				if (player.getDirection().equals(Direction.SOUTH)) {
					switch (currentRoom) {
					case "Kitchen":
						client.sendEvent(new EnterRoomEvent(player,
								"Hall - Kitchen"));
						player.enterRoom(game.getRooms().get("Hall - Kitchen"));
						break;
					case "Living Room":
						client.sendEvent(new EnterRoomEvent(player,
								"Hall - Living Room"));
						player.enterRoom(game.getRooms().get(
								"Hall - Living Room"));
						break;
					case "Study":
						client.sendEvent(new EnterRoomEvent(player,
								"Hall - Study"));
						player.enterRoom(game.getRooms().get("Hall - Study"));
						break;
					case "Bedroom":
						client.sendEvent(new EnterRoomEvent(player,
								"Hall - Bedroom"));
						player.enterRoom(game.getRooms().get("Hall - Bedroom"));
						break;
					}

					// Changes player's direction back to NORTH
					client.sendEvent(new ChangeDirectionEvent(player, "NORTH"));
					player.setDirection(Direction.NORTH);
				}
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
				switch (player.getRoom().getName()) {
				case "Living Room":
				case "Kitchen":
				case "Bedroom":
				case "Study":
					player.setDirection(Direction.NORTH);
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
				switch (player.getRoom().getName()) {
				case "Main Hall":
					client.sendEvent(new EnterRoomEvent(player,
							"Hall - Bedroom"));
					player.enterRoom(game.getRooms().get("Hall - Bedroom"));
					break;
				case "Hall - Bedroom":
					client.sendEvent(new EnterRoomEvent(player,
							"Hall - Living Room"));
					player.enterRoom(game.getRooms().get("Hall - Living Room"));
					break;
				case "Hall - Living Room":
					JOptionPane.showMessageDialog(null,
							"No more rooms that way!", "Ooops",
							JOptionPane.WARNING_MESSAGE);
					break;
				case "Hall - Study":
					client.sendEvent(new EnterRoomEvent(player, "Main Hall"));
					player.enterRoom(game.getRooms().get("Main Hall"));
					break;
				case "Hall - Kitchen":
					client.sendEvent(new EnterRoomEvent(player, "Hall - Study"));
					player.enterRoom(game.getRooms().get("Hall - Study"));
					break;
				}

				switch (player.getRoom().getName()) {
				case "Living Room":
				case "Kitchen":
				case "Bedroom":
				case "Study":
					player.setDirection(Direction.EAST);
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
				switch (player.getRoom().getName()) {
				case "Living Room":
				case "Kitchen":
				case "Bedroom":
				case "Study":
					player.setDirection(Direction.SOUTH);
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
					player.enterRoom(game.getRooms().get("Hall - Study"));
					break;
				case "Hall - Study":
					client.sendEvent(new EnterRoomEvent(player,
							"Hall - Kitchen"));
					player.enterRoom(game.getRooms().get("Hall - Kitchen"));
					break;
				case "Hall - Kitchen":
					JOptionPane.showMessageDialog(null,
							"No more rooms that way!", "Ooops",
							JOptionPane.WARNING_MESSAGE);
					break;
				case "Hall - Bedroom":
					client.sendEvent(new EnterRoomEvent(player, "Main Hall"));
					player.enterRoom(game.getRooms().get("Main Hall"));
					break;
				case "Hall - Living Room":
					client.sendEvent(new EnterRoomEvent(player,
							"Hall - Bedroom"));
					player.enterRoom(game.getRooms().get("Hall - Bedroom"));
					break;
				}

				switch (player.getRoom().getName()) {
				case "Living Room":
				case "Kitchen":
				case "Bedroom":
				case "Study":
					player.setDirection(Direction.WEST);
					client.sendEvent(new ChangeDirectionEvent(player, "WEST"));
				}
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

		}

		@Override
		public void mouseClicked(MouseEvent e) {
			if (game == null || player == null)
				return;
			String currentRoom = player.getRoom().getName();
			if ((currentRoom.equals("Main Hall")
					|| currentRoom.equals("Hall - Bedroom")
					|| currentRoom.equals("Hall - Kitchen")
					|| currentRoom.equals("Hall - Study")
					|| currentRoom.equals("Hall - Living Room") || currentRoom
						.equals("Exit Door")) && e.getY() < 410) {
				return;
			}
			// for inventory box
			else if (e.getY() >= canvas.getHeight() * 0.85) {
				for (Item i : player.getItems()) {
					if (i.getBoundingBox().contains(e.getX(), e.getY() - 40)) {
						game.setSelectedInventory(i);
						Item selectedInventory = game.getSelectedInventory();
						JOptionPane.showMessageDialog(
								null,
								"You have selected "
										+ selectedInventory.getName()
										+ " from your inventory.",
								"Selected Inventory Item",
								JOptionPane.NO_OPTION);
						if (SwingUtilities.isRightMouseButton(e)) {
							JOptionPane.showMessageDialog(null,
									selectedInventory.getDescription(),
									selectedInventory.getName(),
									JOptionPane.NO_OPTION);
						} else {
							return;
						}
					} else if (player.getItems() == null) {
						return;
					}
				}
			} else {
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

				// checking if point is in an item's bounding box
				for (int i = 5; i >= 0; i--) {
					for (int j = 2; j >= 0; j--) {
						if (!items[j][i].equals("")) {
							Item it = player.getRoom().getItem(items[j][i]);
							if (it == null) {
								continue;
							}
							if (it.getBoundingBox().contains(e.getX(),
									e.getY() - 40)) {
								game.setSelectedItem(it);
								Item selectedItem = game.getSelectedItem();
								// inspecting an item
								if (selectedItem == null) {
									return;
								}
								if (SwingUtilities.isRightMouseButton(e)) {
									// Examining item
									JOptionPane.showMessageDialog(null,
											selectedItem.getDescription(),
											selectedItem.getName(),
											JOptionPane.NO_OPTION);
									// if it's not locked, get whatever is
									// inside
									if (selectedItem instanceof Container
											&& !((Container) selectedItem)
													.isLocked()) {
										game.openContainer(player,
												(Container) selectedItem);
										return;
									}
								} else {
									if (player.pickUpItem(selectedItem)) {
										JOptionPane.showMessageDialog(
												null,
												"You picked up "
														+ selectedItem
																.getName(),
												"Picked up an item!",
												JOptionPane.NO_OPTION);
										return;
									} else if (game.getSelectedInventory() != null
											&& selectedItem instanceof Container) {
										if (((Container) selectedItem)
												.isLocked()) {
											if (game.useItem(player,
													(Container) selectedItem,
													game.getSelectedInventory()
															.getName())) {
												JOptionPane
														.showMessageDialog(
																null,
																"You opened "
																		+ selectedItem
																				.getName()
																		+ " using "
																		+ game.getSelectedInventory()
																				.getName(),
																"Got an item!",
																JOptionPane.NO_OPTION);
												game.setSelectedInventory(null);
												return;
											}
										}
										// unlocked container
										else {
											game.useItem(player,
													(Container) selectedItem,
													"");
											for (Item k : ((Container) selectedItem)
													.getItems()) {
											}
											game.setSelectedInventory(null);
											// added return statement, in order
											// for duplicate items to not occur
											return;
										}
									} else {
										JOptionPane
												.showMessageDialog(
														null,
														"Can't pick up that item!",
														"Oooops",
														JOptionPane.NO_OPTION);
										return;
									}

								}
								if (selectedItem != null)
									return;
							} else if (game.getSelectedInventory() != null
									&& !(game.getSelectedItem() instanceof Container)) {
								int n = JOptionPane
										.showConfirmDialog(
												null,
												"Are you sure you want to drop "
														+ game.getSelectedInventory()
																.getName()
														+ "?", "Drop item?",
												JOptionPane.YES_NO_OPTION);
								if (n == JOptionPane.YES_OPTION) {
									game.dropItem(player);
									game.setSelectedInventory(null);
									return;
								} else {
									return;
								}
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
		frame.remove(canvas);
		canvas = new GameCanvas(client);
		btnPanel.removeAll();
		initialiseGameBtns();
		frame.add(canvas, BorderLayout.NORTH);
		mouse.addMouseListener(new MouseEvents());
		frame.add(mouse);
		frame.pack();

		if (state == 3) {
			frame.remove(canvas);
			canvas = new GameCanvas(client);
			btnPanel.removeAll();
			frame.add(canvas, BorderLayout.NORTH);
			mouse.addMouseListener(new MouseEvents());
			frame.add(mouse);
			frame.pack();
		}
	}

	public GameWorld getGame() {
		return this.game;
	}

	public void setClient(Client c) {
		this.client = c;
	}

	public Client getClient() {
		return this.client;
	}

	public void endGame() {
		setSt(3);
		updateFrame();
		// menuCanvas.drawEndGameScreen(menuCanvas.getGraphics());
	}

	public void newGame() {
		// updateFrame();
		setSt(0);

		// frame.dispatchEvent(new WindowEvent(frame,
		// WindowEvent.WINDOW_CLOSING));
		frame.setVisible(false);
		frame.dispose();
		f = new GameFrame();

	}

}