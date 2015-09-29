package escape.event;

import escape.gameworld.Player;
import escape.gameworld.Room;

/**
 * Update from the Server to the Client which contains Player and the Room it is in.
 * 
 * @author morenojuli
 *
 */
public class GameWorldUpdateEvent implements Event {

	private Player player;
	private Room room;
	
	/**
	 * Constructor for GameWorldUpdateEvent
	 * @param player
	 * @param room
	 */
	public GameWorldUpdateEvent(Player player, Room room){
		this.player = player;
		this.room = room;
	}
	
	/**
	 * Method which returns the Room which the player is in
	 * @return
	 */
	public Room getRoom(){
		return this.room;
	}
	
	/**
	 * Method which returns the Player
	 * @return
	 */
	public Player getPlayer(){
		return this.player;
	}
}