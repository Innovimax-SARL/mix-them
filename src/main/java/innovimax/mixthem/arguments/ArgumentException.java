package innovimax.mixthem.arguments;

/**
* Signals any exception that occur during arguments checking.
* @author Innovimax
* @version 1.0
*/
public class ArgumentException extends Exception {

	public ArgumentException() { 
		super();
	}
	
	public ArgumentException(String message) { 
		super(message); 
	}
	
	public ArgumentException(Throwable cause) { 
		super(cause); 
	}

	public ArgumentException(String message, Throwable cause) {
		super(message, cause);
	}

}
