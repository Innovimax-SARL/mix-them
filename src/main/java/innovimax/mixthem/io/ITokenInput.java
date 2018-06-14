package innovimax.mixthem.io;

import java.io.IOException;

/**
* This interface provides for reading tokens from an input.
* @author Innovimax
* @version 1.0
*/
public interface ITokenInput {
	/**
	* Returns true if there is more tokens.
	* @return Returns true if there is more tokens
	* @throws IOException - If an I/O error occurs
	*/
	boolean hasMoreTokens() throws IOException;
	/**
 	* Reads next token.
 	* @return The input token or null if no more tokens
 	* @throws IOException - If an I/O error occurs
 	*/
	IToken nextToken() throws IOException;
	/**
	* Closes this input and releases any system resources associated with it.
	* @throws IOException - If an I/O error occurs
	*/
	void close() throws IOException;
}
