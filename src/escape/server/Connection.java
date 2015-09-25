package escape.server;
import java.io.ObjectOutputStream;
import java.net.Socket;

/**
 * Represents a connection between Server to the Client.
 * @author morenojuli
 *
 */

public class Connection {
	
	private Socket socket;
	private ObjectOutputStream output;
	
	public Connection(Socket socket, ObjectOutputStream output){
		this.socket = socket;
		this.output = output;
	}
	
	public Socket getSocket(){
		return this.socket;
	}
	
	public ObjectOutputStream getOutput(){
		return this.output;
	}

}