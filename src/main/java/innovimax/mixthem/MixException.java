package innovimax.mixthem;

/*
    Created by innovimax-jim
    Used for all mixing files exception
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
