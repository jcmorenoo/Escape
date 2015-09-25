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
	
	public ChangeRoomEvent(Player player, Room room){
		this.player = player;
		this.room = room;
	}
	
	public Room getRoom(){
		return this.room;
	}
	
	public Player getPlayer(){
		return this.player;
	}
	
}