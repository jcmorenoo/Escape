package escape.gameworld;

@SuppressWarnings("serial")
public class EscapeException extends Exception {
	
	private String message = "";

	public EscapeException(String s) {
		message = s;
	}

	@Override
	public String toString() {
		return this.message;
	}
}
