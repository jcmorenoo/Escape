package escape.ui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.IOException;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

import escape.gameworld.GameWorld;
import escape.gameworld.Item;
import escape.gameworld.Player;
import escape.gameworld.Room;

public class GameFrame extends JFrame implements ActionListener {
	public static JFrame frame;
	private GameCanvas menuCanvas;
	private final JPanel btnPanel;
	private static Player player;
	private JPanel mouse = new JPanel();

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

		JMenuItem help = new JMenuItem("Help");
		JMenuItem exit = new JMenuItem("Exit");

		headerMenu.add(help);
		headerMenu.add(exit);


		
		// Initialise Menu Screen Buttons
		menuBtns();

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
//		JPanel mouse = new JPanel();
//		mouse.setBackground(Color.BLACK);
//		mouse.addMouseListener(new MouseEvents());
//		frame.add(mouse);

		frame.add(menuCanvas, BorderLayout.NORTH);
		frame.add(btnPanel);

		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setJMenuBar(headerMenu);

		frame.pack();
		frame.setLocationRelativeTo(null);
		frame.setResizable(false);
		frame.setVisible(true);
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

	void navBtns() {
		JButton northButton = new JButton("^");
		btnPanel.add(northButton);

		JButton southButton = new JButton("v");
		btnPanel.add(southButton);

		JButton eastButton = new JButton(">");
		btnPanel.add(eastButton);

		JButton westButton = new JButton("<");
		btnPanel.add(westButton);
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
					player = new Player(1, username, new Room("Main Hall", false, "No key"));


					//Lets the host to specify a unique Game ID for other players wanting to join, to enter in 
					JTextField gameIdInput = new JTextField();
					Object[] gameIdPrompt = { "Enter a Game ID: ",
							gameIdInput.getText() };
					final String gameID = JOptionPane.showInputDialog(null,
							gameIdPrompt, "Game ID",
							JOptionPane.INFORMATION_MESSAGE);
				

					// Testing
					GameWorld game = new GameWorld(gameID.toString());	
					game.addPlayer(player); //NullPointerException occurs when trying to add a player to the game
					System.out.println("Game Created");
					System.out.println("Number of players: " + numPlayers);
					System.out.println(username + " is in the "
							+ player.getRoom().getName());
					System.out.println("Game ID: " + gameID);
					
					System.out.println("[test] \nLiving Room Items:");
					ArrayList<Item> it = game.getRooms().get("Living Room").getItems(); 
					for(int i = 0; i < it.size(); i++){
						System.out.println(it.get(i).getName());
					}


					// Testing purposes
					setSt(1);
					System.out.println(state);

					frame.remove(menuCanvas);
					menuCanvas = new GameCanvas(player);
					btnPanel.removeAll();
					navBtns();
					frame.add(menuCanvas, BorderLayout.NORTH);
					frame.pack();
				}

				// Join a game
				if (gameType == 0) {
					System.out.println("Join a game");
					
					// Lets the player enter a username
					JTextField nameInput = new JTextField();
					Object[] usernamePrompt = { "Enter a username: ",
							nameInput.getText() };
					final String username = JOptionPane.showInputDialog(null,
							usernamePrompt, "Player Username",
							JOptionPane.INFORMATION_MESSAGE);
					player = new Player(1, username, new Room("Main Hall", false, "No key"));
					

					//Lets the player enter IP address of the server
					JTextField serverAddrInput = new JTextField();
					Object[] serverAddrPrompt = { "Enter IP address of server: ",
							serverAddrInput.getText() };
					final String serverAddr = JOptionPane.showInputDialog(null,
							serverAddrPrompt, "Server IP Address",
							JOptionPane.INFORMATION_MESSAGE);

					//Lets the player enter a Game ID to join that game 
					JTextField gameIdInput = new JTextField();
					Object[] gameIdPrompt = { "Enter a Game ID: ",
							gameIdInput.getText() };
					final String gameID = JOptionPane.showInputDialog(null,
							gameIdPrompt, "Game ID",
							JOptionPane.INFORMATION_MESSAGE);
					
					//Testing
					System.out.println(username + " is in the "
							+ player.getRoom().getName());
					System.out.println("Server IP Address: " + serverAddr);
					System.out.println("Game ID: " + gameID);
					
					// Testing purposes
					setSt(1);
					System.out.println(state);
					
					frame.remove(menuCanvas);
					menuCanvas = new GameCanvas(player);
					btnPanel.removeAll();
					navBtns();
					frame.add(menuCanvas, BorderLayout.NORTH);
					mouse.addMouseListener(new MouseEvents());
					frame.add(mouse);
					frame.pack();
				}

				// New Player - Name input
				// JTextField nameInput = new JTextField();
				// Object[] namePrompt = { "Enter a name: ", nameInput.getText()
				// };
				// final String name = JOptionPane.showInputDialog(null,
				// namePrompt,
				// "Player Name", JOptionPane.INFORMATION_MESSAGE);
				// player = new Player(name, new Room("Hall"));
				// System.out.println(name + " is in the "
				// + player.getRoom().getName());

			}
		});
	}

	public void loadGame(JButton loadGame) {
		loadGame.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				System.out.println("Load Game");
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

//			if (player.getRoom().getName().equals("Hall")){
//				if (e.getX() > 36 && e.getX() < 116 && e.getY() > 220 && e.getY() < 377){
//					System.out.println("Clicked on Living Room Door");
//					player.leaveRoom();
//					player.enterRoom(new Room("Living Room", false, "No key"));
//				}
//			}
		}

		@Override
		public void mouseClicked(MouseEvent e) {
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
	public static void main(String[] args) throws IOException {
		GameFrame display = new GameFrame();

	}
}
