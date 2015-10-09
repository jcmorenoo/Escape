package escape.event;

import escape.gameworld.Player;

/**
 * An Event which is sent by the Server to the Client when the Game is over.
 * The Player in this Event is the winner of the Game.
 * @author morenojuli
 *
 */
public class GameOverEvent implements Event {
	
	private Player player;
	
	/**
	 * Constructor for GameOverEvent
	 * @param player
	 */
	public GameOverEvent(Player player){
		this.player = player;
	}
	
	/**
	 * Method returning the player who won the game.
	 * @return player
	 */
	public Player getPlayer(){
		return this.player;
	}
}