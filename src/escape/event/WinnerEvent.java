package escape.event;

import escape.gameworld.Player;


/**
 * Class representing an Event when a player wins the game. This
 *  is sent by the Client to the Server. 
 * @author morenojuli
 *
 */
public class WinnerEvent implements Event {
	private Player player;
	
	/**
	 * Constructor for WinnerEvent
	 * @param Player 
	 */
	public WinnerEvent(Player p){
		this.player = p;
	}
	
	/**
	 * Returns Player contained in the WinnerEvent
	 * @return Player
	 */
	public Player getPlayer(){
		return this.player;
	}
}
