package escape.event;

public class UserSetupEvent implements Event {
	
	private String name;
	private int id;
	
	public UserSetupEvent(String name, int id){
		this.name = name;
		this.id = id;
	}

	public int getId(){
		return this.id;
	}
	
	public String getName(){
		return this.name;
	}
	
}
