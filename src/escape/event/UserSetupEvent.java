package escape.event;

/**
 * Class which is sent by the Client to the Server to setup the Player name.
 * When received by the Server, server will create a new Player with the name 
 * provided by the Client
 * @author julian
 *
 */
public class UserSetupEvent implements Event {
	
	private String name;
	private int id;
	
	/**
	 * Constructor for UserSetupEvent
	 * @param name
	 * @param id
	 */
	public UserSetupEvent(String name, int id){
		this.name = name;
		this.id = id;
	}

	/**
	 * Method which returns id of the client
	 * @return id
	 */
	public int getId(){
		return this.id;
	}
	
	/**
	 * Method which returns name set by the player
	 * @return name
	 */
	public String getName(){
		return this.name;
	}
	
}
