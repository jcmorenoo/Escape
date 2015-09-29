package escape.event;

/**
 * An event sent by the Server to the Client the moment the Server accepts the connection
 * 
 * @author morenojuli
 *
 */
public class ConnectionAcceptedEvent implements Event {
	
	private int id;
	
	/**
	 * Constructor for ConnectionAcceptedEvent
	 * @param id
	 */
	public ConnectionAcceptedEvent(int id){
		this.id = id;
	}
	
	/**
	 * returns the int id which the server picked for the Client
	 * @return int ID
	 */
	public int getId(){
		return this.id;
	}

}