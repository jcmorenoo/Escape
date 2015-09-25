package escape.server;
import escape.event.Event;


/**
 * An Update which contains the Event sent by the Client to the Server.
 * We might not need this one though..
 * @author morenojuli
 *
 */

public class Update {
	private final Event event;
	
	public Update(Event event){
		this.event = event;
		
		
	}
	
	public Event getEvent(){
		return this.event;
	}
}