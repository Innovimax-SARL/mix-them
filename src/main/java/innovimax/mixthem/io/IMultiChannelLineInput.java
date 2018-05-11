package innovimax.mixthem.io;

import java.io.IOException;

/**
* This interface provides for reading lines from a multi-channel line-input.
* @author Innovimax
* @version 1.0
*/
public interface IMultiChannelLineInput {
    /**
	* Returns true if there is more lines in one of the channels.
	* @return Returns true if there is more lines in one of the channels
	* @throws IOException - If an I/O error occurs
	*/
	boolean hasLine() throws IOException;
	/**
 	* Reads line range (one by channel, null if no more line in channel)
 	* @return The array of lines (one line per channel or null if no more lines in the channel)
 	* @throws IOException - If an I/O error occurs
 	*/
	String[] nextLineRange() throws IOException;
	/**
	* Closes this input channels and releases any system resources associated with them.
	* @throws IOException - If an I/O error occurs
	*/
	void close() throws IOException;	
}