package innovimax.mixthem.io;

import java.util.List;

/**
* <p>This interface provides for token range representation.</p>
* <p>A range represents the list of tokens read for each input channel.</p>
* @author Innovimax
* @version 1.0
*/
public interface ITokenRange {
    	/**
	* Indicates the count of channels in the range.
	* @return Returns the range size
	*/
	int size();
	/**
 	* Get the list of tokens. 	
 	* @return The list of tokens
 	*/
	List<IToken> asList();
	/**
 	* Get a token at the given position.
 	* @param channel The channel of the token
 	* @return The token or {@code null} if none
 	*/
	IToken getToken(int channel);
	/**
 	* Set a token at the given position.
 	* @param channel The channel of the token
 	* @param token The token
 	*/
	void setToken(int channel, IToken token);
	/**
 	* Add a token in the range. 	
 	* @param token The token
 	*/
	void addToken(IToken token);
}
