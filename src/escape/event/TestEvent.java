package escape.event;

/**
 * Used for testing purposes.
 * Contains message: "TEST EVENT"
 * @author morenojuli
 *
 */

public class TestEvent implements Event {
	
	//public to make easier access to the message.
	public final String message;
	
	/**
	 * Constructor for TestEvent
	 * Sets message "TEST EVENT"
	 */
	public TestEvent(){
		this.message = "TEST EVENT";
	}
	
}