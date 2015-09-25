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
	
	public DropItemEvent(Player player, Item item){
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