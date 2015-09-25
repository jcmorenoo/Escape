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
	
	public InspectItemEvent(Player player, Item item){
		this.player = player;
		this.item = item;
	}
	
	public Player getPlayer(){
		return this.player;
	}
	
	public Item getItem(){
		return this.item;
	}
}