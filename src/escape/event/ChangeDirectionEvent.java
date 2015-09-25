package escape.event;

import escape.gameworld.Player;

/**
 * Represents a player changing direction. This implements Event. This is sent by the Client to the Server.
 * 
 * @author morenojuli
 *
 */
public class ChangeDirectionEvent implements Event {
 
	private String direction;
	
	private Player player;
	
	public ChangeDirectionEvent(Player player, String direction){
		this.player = player;
		this.direction=direction;
	}
	
	public String getDirection(){
		return this.direction;
	}
	
	public Player getPlayer(){
		return this.player;
	}
}