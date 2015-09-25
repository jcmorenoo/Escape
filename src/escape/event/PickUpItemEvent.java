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
	
	public PickUpItemEvent(Player player, Item item){
		this.player = player;
		this.item = item;
	}
	
	public Item getItem(){
		return this.item;
	}
	
	public Player getPlayer(){
		return this.player;
	}

}