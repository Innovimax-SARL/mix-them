package innovimax.mixthem.exceptions;

/**
* Signals any exception that occur during mixing.
* @author Innovimax
* @version 1.0
*/
public class MixException extends Exception {

	public MixException() { 
		super();
	}
	
	public MixException(String message) { 
		super(message); 
	}
	
	public MixException(Throwable cause) { 
		super(cause); 
	}

	public MixException(String message, Throwable cause) {
		super(message, cause);
	}

}
