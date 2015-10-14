package escape.event;

import escape.gameworld.Player;
import escape.gameworld.Room;

/**
 * Class representing a PlayerSetup. Sent by the server to the client when the game is ready
 * This contains a Player which is allocated by the server to the client.
 * @author julian
 *
 */
public class PlayerSetupEvent implements Event {
	
	private Player player;
	private Room room;
	
	public PlayerSetupEvent(Player p, Room room){
		this.player = p;
	}
	
	/**
	 * Method returning the player 
	 * @return
	 */
	public Player getPlayer(){
		return this.player;
	}
	
	/**
	 * Method returning the room
	 * @return
	 */
	public Room getRoom(){
		return this.room;
	}
	
}
