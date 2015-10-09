package escape.server;

import java.io.IOException;

import escape.event.ChangeDirectionEvent;
import escape.event.DropItemEvent;
import escape.event.EnterRoomEvent;
import escape.event.Event;
import escape.event.GameOverEvent;
import escape.event.GameWorldUpdateEvent;
import escape.event.InspectItemEvent;
import escape.event.PickUpItemEvent;
import escape.event.PlayerSetupEvent;
import escape.event.TestEvent;
import escape.event.UserSetupEvent;
import escape.gameworld.GameWorld;
import escape.gameworld.Item;
import escape.gameworld.Player;
import escape.gameworld.Player.Direction;
import escape.gameworld.Room;

/**
 * A Thread which will keep accepting events from client and updating the game
 * and will keep sending Events to the Clients when needed.
 * 
 * @author morenojuli
 *
 */

public class UpdateThread extends Thread {

	private Server server;
	private GameWorld game;

	// private Game game; field containing the game

	/**
	 * Constructor for UpdateThread
	 * 
	 * @param server
	 */
	public UpdateThread(Server server) {
		this.server = server;
		setDaemon(true);// daemon meaning low prio, and stops when no user
		// thread running... idk what it means really
		this.game = server.getGameWorld();

	}

	/**
	 * Method which starts the UpdateThread. This will handle all the updates
	 * and what will be done when the update is received.
	 */
	public void run() {

		while (true) {

			Update update = null;
			Event event = null;
			// handles all updates?? updates to game and then send game updates
			// to players?
			try {
				update = server.getUpdates().take();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}

			event = update.getEvent();

			if (event instanceof TestEvent) {
				TestEvent testEvent = (TestEvent) event;
				System.out.println(testEvent.message);

			}

			else if (event instanceof UserSetupEvent) {
				System.out.println("setup received");
				UserSetupEvent setup = (UserSetupEvent) event;
				int id = setup.getId();
				String username = setup.getName();
				Room startingRoom = game.getRooms().get("Main Hall");
				// initialise a new player ..
				Player p = new Player(id, username, startingRoom);

				game.addPlayer(p);
				// hmm maybe we should send this to everyone??
				PlayerSetupEvent e = new PlayerSetupEvent(p,
						startingRoom);

				// send to client
				sendToAllClients((Event) e);

			}

			else if (event instanceof EnterRoomEvent) {
				EnterRoomEvent e = (EnterRoomEvent) event;
				Player player = e.getPlayer();
				String roomName = e.getRoom();
				Room room = game.getRooms().get(roomName);

				if (game.enterRoom(player, room)) {
					if(room.getName().equals("Exit Door")){
						GameOverEvent gameOver = new GameOverEvent(player);
						sendToAllClients((Event)gameOver);
					}
					else{
						player.enterRoom(room);
						GameWorldUpdateEvent enterRoom = new GameWorldUpdateEvent(
								player, player.getRoom());
						sendToAllClients((Event) enterRoom);
					}
				}


				// try to make the player enter the room
				// if exception.. send new event containing current room.
			} else if (event instanceof ChangeDirectionEvent) {
				ChangeDirectionEvent e = (ChangeDirectionEvent) event;
				Player player = e.getPlayer();
				String playerDirection = e.getDirection();

				switch (playerDirection) {
				case "NORTH":
					player.setDirection(Direction.NORTH);
					break;
				case "EAST":
					player.setDirection(Direction.EAST);
					break;
				case "SOUTH":
					player.setDirection(Direction.SOUTH);
					break;
				case "WEST":
					player.setDirection(Direction.WEST);
					break;
				}
				GameWorldUpdateEvent changeDirection = new GameWorldUpdateEvent(
						player, player.getRoom());
				sendToAllClients((Event) changeDirection);

			}else if (event instanceof PickUpItemEvent){
				PickUpItemEvent e = (PickUpItemEvent) event;
				Player player = e.getPlayer();
				Item item = e.getItem();

				if(item!=null){
					if(player.pickUpItem(item)){
						GameWorldUpdateEvent pickUpEvent = new GameWorldUpdateEvent(player, player.getRoom());
						sendClient((Event) pickUpEvent, server.getClients().get(player.getId()));
					}
				}
				//else ??

			} 

			else if (event instanceof DropItemEvent) {
				DropItemEvent e = (DropItemEvent) event;
				Player player = e.getPlayer();
				Item item = e.getItem();

				if(item!=null){
					if(player.dropItem(item)){
						GameWorldUpdateEvent dropItemEvent = new GameWorldUpdateEvent(player, player.getRoom());
						sendClient((Event)dropItemEvent, server.getClients().get(player.getId()));
					}
				}
			}
			// else if(event instanceof EnterRoomEvent){
			// EnterRoomEvent e = (EnterRoomEvent) event;
			// String roomName = e.getRoom();
			// Room room = game.getRooms().get(roomName);
			// Player p = e.getPlayer();
			//
			// game.enterRoom(p, room);
			//
			// Room newRoom = p.getRoom();
			//
			// GameWorldUpdateEvent ev = new GameWorldUpdateEvent(p,newRoom);
			// sendClient((Event)ev,server.getClients().get(p.getId()));
			//
			// }
			else if (event instanceof InspectItemEvent) {
				InspectItemEvent e = (InspectItemEvent) event;

			} else if (event instanceof PickUpItemEvent) {
				PickUpItemEvent e = (PickUpItemEvent) event;
			}

		}
	}

	/**
	 * Send an event to the player.
	 * 
	 * @param event
	 * @param player
	 */
	public void sendClient(Event event, Connection player) {
		try {
			// player.getOutput().reset();
			player.getOutput().writeObject(event);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	/**
	 * Method to send an event to all players in the game.
	 * 
	 * @param event
	 * 
	 */
	public void sendToAllClients(Event event) {
		for (int i = 0; i < server.getClients().size(); i++) {
			Connection p = server.getClients().get(i);
			try {
				p.getOutput().reset();
				p.getOutput().writeObject(event);
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

}