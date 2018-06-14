package innovimax.mixthem.io;

import java.io.IOException;

/**
* This interface provides for writing tokens in an output.
* @author Innovimax
* @version 1.0
*/
public interface ITokenOutput {
	/**
 	* Writes a token.
 	* @param token The token to be written
 	* @throws IOException - If an I/O error occurs
 	*/	
	void writeToken(IToken token) throws IOException;
	/**
	* Closes this output and releases any system resources associated with it.
 	* @throws IOException - If an I/O error occurs	
	*/
	void close() throws IOException;		
}
