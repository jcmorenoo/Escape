package escape.event;

import escape.gameworld.Player;

public class WinnerEvent implements Event {
	private Player player;
	
	public WinnerEvent(Player p){
		this.player = p;
	}
	;
	public Player getPlayer(){
		return this.player;
	}
}
