package innovimax.mixthem.io;

import java.util.List;

/**
* This interface provides for reading lines from a multi-channel line-input.
* @author Innovimax
* @version 1.0
*/
public interface IMultiChannelLineInput {
    /**
	* Returns true if there is more lines in one of the channels.
	* @return Returns true if there is more lines in one of the channel
	*/
	boolean hasLine();
	/**
 	* Reads line range depends on indicators (one by channel, null if no more line in channel)
	* @param readingRange indicates wich channel has to be effectivly read
 	* @return The array of lines (one line per channel or null if no more lines in the channel) 	
 	*/
	List<String> nextLineRange(List<Boolean> readingRange);	
	/**
	* Closes this input channels and releases any system resources associated with them.
	*/
	void close();
}
