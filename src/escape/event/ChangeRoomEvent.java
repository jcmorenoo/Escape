package escape.event;

import escape.gameworld.Player;
import escape.gameworld.Room;
/**
 * Represents a player changing Room. This implements Event. This is sent by the Client to the Server.
 * @author morenojuli
 *
 */
public class ChangeRoomEvent implements Event {

	private Room room;
	private Player player;
	
	/**
	 * Constructor for ChangeRoomEvent
	 * @param player
	 * @param room
	 */
	public ChangeRoomEvent(Player player, Room room){
		this.player = player;
		this.room = room;
	}
	
	/**
	 * Method returning the Room that the player wants to go to
	 * @return Room
	 */
	public Room getRoom(){
		return this.room;
	}
	
	/**
	 * Method returning the Player created the event
	 * @return Player
	 */
	public Player getPlayer(){
		return this.player;
	}
	
}