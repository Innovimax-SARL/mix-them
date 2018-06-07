package innovimax.mixthem.io;

/**
* This interface provides for token range representation.
* @author Innovimax
* @version 1.0
*/
public interface ITokenRange {
    	/**
	* Indicates the count of tokens.
	* @return Returns the token count
	*/
	int countTokens();
	/**
 	* Get a token at the given position.
 	* @param index The position of the token
 	* @return The token or <code>null</code> if none
 	*/
	IToken getToken(int index);
	/**
 	* Set a token at the given position.
 	* @param index The position of the token
 	* @param token The token
 	*/
	void setToken(int index, IToken token);
}
