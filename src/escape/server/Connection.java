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
	
	/**
	 * Constructor for Connection
	 * @param socket - socket to the Client
	 * @param output - ObjectOutputStream to the client
	 */
	public Connection(Socket socket, ObjectOutputStream output){
		this.socket = socket;
		this.output = output;
	}
	
	/**
	 * Method which returns the socket
	 * @return Socket
	 */
	public Socket getSocket(){
		return this.socket;
	}
	
	/**
	 * Method which returns the ObjectOutputStream to the Client
	 * @return ObjectOutputStream output
	 */
	public ObjectOutputStream getOutput(){
		return this.output;
	}

}