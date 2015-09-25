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
	
	public EnterRoomEvent(Player player, Room room){
		this.player = player;
		this.room = room;
	}
	
	public Player getPlayer(){
		return this.player;
	}
	
	public Room getRoom(){
		return this.room;
	}
	
}