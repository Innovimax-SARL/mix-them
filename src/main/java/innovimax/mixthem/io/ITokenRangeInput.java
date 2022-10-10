package innovimax.mixthem.io;

/**
* This interface provides for reading tokens from a multichannel input.
* @author Innovimax
* @version 1.0
*/
public interface ITokenRangeInput {
    /**
	* Returns true if there is more tokens in one of the channels.
	* @return Returns true if there is more tokens in one of the channels
	*/
	boolean hasMoreTokens();
	/**
 	* Reads next token range (one token by channel, <code>null</code> if no more token in channel)
 	* @param tokenStatusRange indicates which channel has to be effectively read
 	* @return The list of tokens (may contain <code>null</code> values)
 	*/
	TokenRange nextTokenRange(ITokenStatusRange tokenStatusRange);
	/**
	* Closes this input channels and releases any system resources associated with them.
	*/
	void close();
}
