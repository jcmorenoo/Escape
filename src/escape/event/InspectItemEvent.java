package escape.event;

import escape.gameworld.Item;
import escape.gameworld.Player;

/**
 * Event which is sent by the Client to the Server when Client tries to inspect an item.
 * We probably do not need this one.
 * 
 * @author morenojuli
 *
 */

public class InspectItemEvent implements Event {
	private Item item;
	private Player player;
	
	/**
	 * Constructor for InspectItemEvent
	 * @param player
	 * @param item
	 */
	public InspectItemEvent(Player player, Item item){
		this.player = player;
		this.item = item;
	}
	
	/**
	 * Method which returns the Player who wants to inspect an item
	 * @return
	 */
	public Player getPlayer(){
		return this.player;
	}
	
	/**
	 * Method which returns the item that the player wants to inspect..
	 * @return
	 */
	public Item getItem(){
		return this.item;
	}
}