package innovimax.mixthem.io;

import java.io.IOException;
import java.util.List;

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
	List<String> nextLineRange() throws IOException;
	/**
 	* Reads line range depends on indicators (one by channel, null if no more line in channel)
	* @param readingRange indicates wich channel has to be effectivly read
 	* @return The array of lines (one line per channel or null if no more lines in the channel)
 	* @throws IOException - If an I/O error occurs
 	*/
	List<String> nextLineRange(List<Boolean> readingRange) throws IOException;	
	/**
	* Closes this input channels and releases any system resources associated with them.
	* @throws IOException - If an I/O error occurs
	*/
	void close() throws IOException;	
}
