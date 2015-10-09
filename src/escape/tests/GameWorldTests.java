package escape.tests;

import static org.junit.Assert.*;

import org.junit.*;

import escape.gameworld.GameWorld;
import escape.gameworld.Item;
import escape.gameworld.Player;
import escape.gameworld.Player.Direction;
import escape.gameworld.Room;

public class GameWorldTests {

	@Test
	public void testChangeRoom(){
		GameWorld g = new GameWorld(null);
		Room r = g.getRooms().get("Main Hall");
		Room newRoom = g.getRooms().get("Hall - Study");
		int id = 0;
		Player p = new Player(id,"test",r);
		g.addPlayer(p);
		p.enterRoom(newRoom);

		assertTrue(p.getRoom().getName().equals(newRoom.getName()));


	}

	@Test
	public void testChangeDirection(){
		GameWorld g = new GameWorld(null);
		Room r = g.getRooms().get("Main Hall");
		Room newRoom = g.getRooms().get("Kitchen");
		int id = 0;
		Player p = new Player(id,"test",r);
		g.addPlayer(p);
		p.enterRoom(newRoom);

		assertTrue(p.getRoom().getName().equals(newRoom.getName()));
		assertTrue(p.getDirection() == Direction.NORTH);
		p.setDirection(Direction.SOUTH);
		assertTrue(p.getDirection() == Direction.SOUTH);
		p.setDirection(Direction.WEST);
		assertTrue(p.getDirection() == Direction.WEST);
		p.setDirection(Direction.EAST);
		assertTrue(p.getDirection() == Direction.EAST);
	}
	@Test
	public void testPickUpItem(){
		GameWorld g = new GameWorld(null);
		Room r = g.getRooms().get("Main Hall");
		int id = 0;
		Player p = new Player(id,"test",r);
		g.addPlayer(p);
		Item item = g.getItems().get("Paper");
		p.pickUpItem(item);
		
		Item i = p.getItems().get(0);	
		
		assertTrue(i == item);
		
	}
}
