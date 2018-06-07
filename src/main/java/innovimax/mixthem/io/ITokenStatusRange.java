package innovimax.mixthem.io;

/**
* <p>This interface provides for channel reading status range representation.</p>
* <p>Reading status indicates if next token is required for reading in each channel.</p>
* @author Innovimax
* @version 1.0
*/
public interface ITokenStatusRange {
	/**
	* Indicates the count of channels in the range.
	* @return Returns the range size
	*/
	int size();
	/**
 	* Add a reading status in the range. 	
 	* @param reading The reading status
 	*/
	void addTokenStatus(boolean reading);
	/**
 	* Set a reading status at the given position.
 	* @param channel The channel of the reading status
 	* @param reading The reading status
 	*/
	void setTokenStatus(int channel, boolean reading);
	/**
 	* Indicates if next token need to be read for the given channel.
 	* @param channel The channel of the reading status
 	* @return Returns true if next token must be read
 	*/
	boolean readingToken(int channel);
}
