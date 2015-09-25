package escape.server;
import java.io.IOException;

import escape.event.ChangeDirectionEvent;
import escape.event.ChangeRoomEvent;
import escape.event.DropItemEvent;
import escape.event.EnterRoomEvent;
import escape.event.Event;
import escape.event.InspectItemEvent;
import escape.event.PickUpItemEvent;
import escape.event.TestEvent;



/**
 * A Thread which will keep accepting events from client and updating the game and will keep sending Events to the Clients when needed.
 * @author morenojuli
 *
 */

public class UpdateThread extends Thread {
	
	private Server server;
//	private Game game; field containing the game
	
	public UpdateThread(Server server){
		this.server = server;
		setDaemon(true);// daemon meaning low prio, and stops when no user thread running... idk what it means really
		
		
	}
	
	public void run(){
		Update update = null;
		Event event = null;
		
		while(true){
				// handles all updates?? updates to game and then send game updates to players?
				try {
					update = server.getUpdates().take();
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				
				event = update.getEvent();
				
				if(event instanceof TestEvent){
					TestEvent testEvent = (TestEvent) event;
					System.out.println(testEvent.message);
				}
				
				else if(event instanceof ChangeRoomEvent){
					ChangeRoomEvent e = (ChangeRoomEvent) event;
				}
				else if(event instanceof ChangeDirectionEvent){
					ChangeDirectionEvent e = (ChangeDirectionEvent) event;
				}
				else if(event instanceof DropItemEvent){
					DropItemEvent e = (DropItemEvent) event;
				}
				else if(event instanceof EnterRoomEvent){
					EnterRoomEvent e = (EnterRoomEvent) event;
				}
				else if(event instanceof InspectItemEvent){
					InspectItemEvent e = (InspectItemEvent) event;
				}
				else if(event instanceof PickUpItemEvent){
					PickUpItemEvent e = (PickUpItemEvent) event;
				}
				
				
				
		}
	}
	
	//send an event to a player
	public void sendClient(Event event, Connection player){
		try {
			player.getOutput().reset();
			player.getOutput().writeObject(event);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}