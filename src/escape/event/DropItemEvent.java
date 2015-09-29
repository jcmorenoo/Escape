package escape.event;

import escape.gameworld.Item;
import escape.gameworld.Player;

/**
 * An Event sent by the Client to the Server when the Client drops an item. 
 * @author morenojuli
 *
 */
public class DropItemEvent implements Event {

	private Player player;
	private Item item;
	
	/**
	 * Constructor for DropItemEvent
	 * @param player
	 * @param item
	 */
	public DropItemEvent(Player player, Item item){
		this.player = player;
		this.item = item;
	}
	
	/**
	 * Method returning the Player that wants to drop an item
	 * @return player
	 */
	public Player getPlayer(){
		return this.player;
	}
	
	/**
	 * Method returning the item that the player wants to drop
	 * @return item
	 */
	public Item getItem(){
		return this.item;
	}
	
}