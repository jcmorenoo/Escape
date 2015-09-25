package escape.event;

import escape.gameworld.Player;

/**
 * An Event which is sent by the Server to the Client when the Game is over.
 * The Player in this Event is the winner of the Game.
 * @author morenojuli
 *
 */
public class GameOverEvent {
	
	private Player player;
	
	public GameOverEvent(Player player){
		this.player = player;
	}
	
	public Player getPlayer(){
		return this.player;
	}
}