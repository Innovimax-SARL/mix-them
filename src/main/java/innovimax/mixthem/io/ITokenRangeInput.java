package innovimax.mixthem.io;

/**
* This interface provides for reading tokens from a multi-channel input.
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
 	* @param tokenStatusRange indicates wich channel has to be effectivly read
 	* @return The list of tokens (may contains <code>null</code> values)
 	*/
	TokenRange nextTokenRange(ITokenStatusRange tokenStatusRange);
	/**
	* Closes this input channels and releases any system resources associated with them.
	*/
	void close();
}
