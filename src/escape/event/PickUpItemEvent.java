package escape.event;

import escape.gameworld.Item;
import escape.gameworld.Player;

/**
 * An event sent by the Client to the Server when Player picks up an Item.
 * 
 * @author morenojuli
 *
 */

public class PickUpItemEvent implements Event {
	
	private Item item;
	private Player player;
	
	/**
	 * Constructor for PickUpItemEvent
	 * @param player
	 * @param item
	 */
	public PickUpItemEvent(Player player, Item item){
		this.player = player;
		this.item = item;
	}
	
	/**
	 * Method which returns the Item that the player wants to pick up
	 * @return
	 */
	public Item getItem(){
		return this.item;
	}
	
	/**
	 * Method which returns the player who want to pick up item
	 * @return
	 */
	public Player getPlayer(){
		return this.player;
	}

}