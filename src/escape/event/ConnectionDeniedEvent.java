package escape.event;

/**
 * An event sent by the Server to the Client the moment the Server accepts the connection
 * 
 * @author morenojuli
 *
 */
public class ConnectionDeniedEvent implements Event {
	
	private int id;
	
	
	/**
	 * Constructor for ConnectionDeniedEvent
	 * @param id
	 */
	public ConnectionDeniedEvent(int id){
		this.id = id;
	}
	
	/**
	 * Method returning the id for the Client whose connection is denied
	 * @return id
	 */
	public int getId(){
		return this.id;
	}

}