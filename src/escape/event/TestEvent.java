package escape.event;

/**
 * Used for testing purposes.
 * Contains message: "TEST EVENT"
 * @author morenojuli
 *
 */

public class TestEvent implements Event {
	
	
	public final String message;
	
	public TestEvent(){
		this.message = "TEST EVENT";
	}
	
}