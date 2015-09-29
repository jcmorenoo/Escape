package escape.event;

import escape.gameworld.Player;
import escape.gameworld.Room;
/**
 * An Event sent by the Client to the Server when the Client tries to enter a room.
 * @author morenojuli
 *
 */
public class EnterRoomEvent implements Event {

	private Room room;
	private Player player;
	
	/**
	 * Constructor for EnterRoomEvent
	 * @param player
	 * @param room
	 */
	public EnterRoomEvent(Player player, Room room){
		this.player = player;
		this.room = room;
	}
	
	/**
	 * Method returning the player who wants to enter a room
	 * @return player
	 */
	public Player getPlayer(){
		return this.player;
	}
	
	/**
	 * Method returning the Room which the player wants to enter.
	 * @return Room
	 */
	public Room getRoom(){
		return this.room;
	}
	
}