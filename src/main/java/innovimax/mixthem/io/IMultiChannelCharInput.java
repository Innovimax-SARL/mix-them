package innovimax.mixthem.io;

import java.io.IOException;

/**
* This interface provides for reading bytes from a multi-channel char-input.
* @author Innovimax
* @version 1.0
*/
public interface IMultiChannelCharInput {
    /**
	* Returns true if there is more characters in one of the channels.
	* @return Returns true if there is more characters in one of the channels
	* @throws IOException - If an I/O error occurs
	*/
	boolean hasCharacter() throws IOException;
	/**
 	* Reads character range (one by channel, -1 if no more character in channel)
 	* @return The array of characters as int (one character per channel or -1 if no more characters in the channel)
 	* @throws IOException - If an I/O error occurs
 	*/
	int[] nextCharacterRange() throws IOException;
	/**
	* Closes this input channels and releases any system resources associated with them.
	* @throws IOException - If an I/O error occurs
	*/
	void close() throws IOException;	
}
