package escape.event;

import escape.gameworld.Player;

public class RemovePlayerEvent implements Event {
	
	private Player player;
	
	public RemovePlayerEvent(Player player){
		this.player = player;
	}
	
	public Player getPlayer(){
		return this.player;
	}

}
