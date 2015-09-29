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
	
	/**
	 * Constructor of ChangeDirectionEvent.
	 * @param player
	 * @param direction
	 */
	public ChangeDirectionEvent(Player player, String direction){
		this.player = player;
		this.direction=direction;
	}
	
	/**
	 * Method returning direction that the player wants to turn to.
	 * @return direction
	 */
	public String getDirection(){
		return this.direction;
	}
	
	/**
	 * Method returning the player created the event
	 * @return player
	 */
	public Player getPlayer(){
		return this.player;
	}
}