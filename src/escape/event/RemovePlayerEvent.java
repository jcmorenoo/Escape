package escape.event;

import escape.gameworld.Player;

/**
 * Class which is responsible for removing players in the game. This is sent by the Server 
 * to the Clients to remove the Player who left the game. This is needed since Clients have a game
 * of their own.
 * @author julian
 *
 */
public class RemovePlayerEvent implements Event {
	
	private Player player;
	
	/**
	 * Constructor for RemovePlayerEvent
	 * @param player
	 */
	public RemovePlayerEvent(Player player){
		this.player = player;
	}
	
	/**
	 * Returns the Player to be removed from the game.
	 * @return
	 */
	public Player getPlayer(){
		return this.player;
	}

}
