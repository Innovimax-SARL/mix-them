package innovimax.mixthem;

/**
* Signals any exception that occur during mixing.
* @author Innovimax
* @version 1.0
*/
class MixException extends Exception {

	public MixException(final String message, final Throwable cause) {
		super(message, cause);
	}

}
