package innovimax.mixthem.io;

/**
* This interface provides for reading bytes from a multi-channel char-input.
* @author Innovimax
* @version 1.0
*/
public interface IMultiChannelCharInput {
    /**
	* Returns true if there is more characters in one of the channels.
	* @return Returns true if there is more characters in one of the channels
	*/
	boolean hasCharacter();
	/**
 	* Reads character range (one by channel, -1 if no more character in channel)
 	* @return The array of characters as int (one character per channel or -1 if no more characters in the channel)
 	*/
	int[] nextCharacterRange();
	/**
	* Closes this input channels and releases any system resources associated with them.
	*/
	void close();
}
