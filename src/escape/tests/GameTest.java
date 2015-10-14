package escape.tests;

import static org.junit.Assert.*;

import org.junit.Test;

import escape.client.Client;
import escape.event.EnterRoomEvent;
import escape.event.Event;
import escape.gameworld.Container;
import escape.gameworld.EscapeException;
import escape.gameworld.GameWorld;
import escape.gameworld.Item;
import escape.gameworld.Player;
import escape.gameworld.Room;
import escape.server.Server;
import escape.ui.GameFrame;

public class GameTest {

private GameWorld game = new GameWorld(null);
	
	@Test
	public void enterRoom1(){
		// enter an unlocked room
		Player p = new Player(0, "player 1", null);
		Room r = game.getRooms().get("Kitchen");
		game.addPlayer(p);
		game.enterRoom(p,r);
		assert(p.getRoom()==r);
	}
	
	@Test
	public void enterRoom2(){
		//enter a locked room without key
		Player p = new Player(0, "player 1", null);
		Room r = game.getRooms().get("Study");
		game.addPlayer(p);
		game.setSelectedInventory(null);
		game.enterRoom(p,r);
		assert((p.getRoom()== null));
	}
	
	@Test
	public void enterRoom3(){
		//enter a locked room with key
		Player p = new Player(0, "player 1", null);
		Room r = game.getRooms().get("Study");
		Item i = game.getItems().get("Study Room Key");
		p.getItems().add(i);
		game.setSelectedInventory(i);
		assert(game.enterRoom(p,r));
		assert(p.getRoom()==r);
	}
	
	@Test
	public void leaveRoom() throws EscapeException{
		//leave a room
		Player p = new Player(0, "Player 1", null);
		Room r = game.getRooms().get("Kitchen");
		game.addPlayer(p);
		game.enterRoom(p,r);
		game.leaveRoom(p);
		assert(p.getRoom()==null);
	}
	
	@Test
	public void pickUpItem(){
		Player p = new Player(0, "Player 1", null);
		Room r = game.getRooms().get("Kitchen");
		Item i = game.getItems().get("Kitchen Picture");
		game.enterRoom(p, r);
		assert(game.pickUpItem(p,i));
	}
	
	@Test
	public void pickUpItem2(){
		// pick up item that cannot be picked up
		Player p = new Player(0, "Player 1", null);
		Item i = game.getItems().get("Sofa");
		assert(!game.pickUpItem(p, i));
	}
	
	@Test
	public void pickUpItem3(){
		Player p = new Player(0, "Player 1", null);
		Item i = game.getItems().get("Study Room Key");
		assert(game.pickUpItem(p, i));
		assert(!game.pickUpItem(p, i));
	}
	
	@Test
	public void dropItem1(){
		// drop an item in the hallway
		Player p = new Player(0, "Player 1", null);
		Item i = game.getItems().get("Study Room Key");
		p.getItems().add(i);
		game.setSelectedInventory(i);
		assert(game.dropItem(p));
		assert(p.getItems().contains(i));
	}
	
	@Test
	public void dropItem2(){
		// drop Item inside a room
		Player p = new Player(0, "Player 1", null);
		Room r = game.getRooms().get("Study");
		Item i = game.getItems().get("Study Room Key");
		game.addPlayer(p);
		game.enterRoom(p,r);
		p.getItems().add(i);
		game.setSelectedInventory(i);
		game.dropItem(p);
		assert(!p.getItems().contains(i));
		assert(r.getBin().getItems().contains(i));
	}
	
	@Test
	public void dropItem3(){
		// drop an item that is not inside player's inventory
		Player p = new Player(0, "Player 1", null);
		Room r = game.getRooms().get("Kitchen");
		Item i = game.getItems().get("Study Room Key");
		game.addPlayer(p);
		game.enterRoom(p,r);
		game.setSelectedInventory(i);
		assert(!game.dropItem(p));
	}
	
	@Test
	public void openContainer1(){
		//open an unlocked container
		Player p = new Player(0, "Player 1", null);
		Container con = game.getContainers().get("Fridge");
		assert(game.openContainer(p, con));
	}
	
	@Test
	public void openContainer2(){
		// open a locked container without key
		Player p = new Player(0, "Player 1", null);
		Container con = game.getContainers().get("Bedroom Safe");
		assert(!game.openContainer(p, con));
	}
	
	@Test
	public void openContainer3(){
		//open a locked container with key
		Player p = new Player(0, "Player 1", null);
		Room r = game.getRooms().get("Bedroom");
		Container con = game.getContainers().get("Bedroom Safe");
		Item i = game.getItem("Bedroom Safe Key");
		game.enterRoom(p, r);
		game.pickUpItem(p, i);
		assert(game.openContainer(p, con));
	}
	
	@Test
	public void client_serverTest(){
		GameWorld clientGame=new GameWorld("testC");
		GameWorld serverGame=null;
		Client c1 = null;
		Player p1 = null;
		Server server = null;
		GameFrame f = null;
		
		//test for server with 2 players
		server = new Server(1,"test");
		server.start();
		c1 = new Client("", "testClient");
		c1.start();
		
		boolean i = true;
		while(i){
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			if(c1.getPlayer()!=null){
				p1=c1.getPlayer();
				i = false;
				break;
			}
			else{
				i=true;
			}
			
		}
		serverGame = server.getGameWorld();
		
		clientGame.addPlayer(p1);
		
		//test if server player1 has same name as client player 
		assertTrue(serverGame.getPlayers().get(0).getName().equals(p1.getName()));
		
		
		//test if player changes room..
		
		clientGame.enterRoom(p1, clientGame.getRooms().get("Kitchen"));
		p1.enterRoom(clientGame.getRooms().get("Kitchen"));
		EnterRoomEvent enter = new EnterRoomEvent(p1,"Kitchen");
		c1.sendEvent((Event)enter);
		
		
		//wait please cos server and client sending info...
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		// here server game is updated.. Player is in different room now.
		assertTrue(serverGame.getPlayers().get(0).getRoom().getName().equals(p1.getRoom().getName()));
		
		
		
		c1.stop();
		server.shutdown();
		
		
	}
	

//	
//	@Test
//	public void isGameOver1(){
//		Player p = new Player(0, "Player 1", null);
//		Room r = game.getRooms().get("Study");
//		Item i = game.getItems().get("Study Room Key");
//		game.pickUpItem(p,i);
//		game.enterRoom(p, r);
//		assert(!game.isGameOver());
//	}
//	
//	@Test
//	public void isGameOver2(){
//		Player p = new Player(0, "Player 1", null);
//		Room r = game.getRooms().get("Exit Door");
//		Item i = game.getItems().get("Key");
//		game.pickUpItem(p,i);
//		game.enterRoom(p, r);
//		assert(game.isGameOver());
//	}
}