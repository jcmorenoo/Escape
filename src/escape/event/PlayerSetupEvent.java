package escape.event;

import escape.gameworld.Player;
import escape.gameworld.Room;

public class PlayerSetupEvent implements Event {
	
	private Player player;
	private Room room;
	
	public PlayerSetupEvent(Player p, Room room){
		this.player = p;
	}
	
	public Player getPlayer(){
		return this.player;
	}
	
	public Room getRoom(){
		return this.room;
	}
	
}
