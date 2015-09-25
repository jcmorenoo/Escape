package escape.event;

/**
 * An event sent by the Server to the Client the moment the Server accepts the connection
 * 
 * @author morenojuli
 *
 */
public class ConnectionDeniedEvent implements Event {
	
	private int id;
	
	public ConnectionDeniedEvent(int id){
		this.id = id;
	}
	
	public int getId(){
		return this.id;
	}

}