package innovimax.mixthem.io;

import java.io.IOException;

/**
* This interface provides for reading tokens from an input.
* @author Innovimax
* @version 1.0
*/
public interface IToken {
	/**
	* Closes this input and releases any system resources associated with it.
	* @throws IOException - If an I/O error occurs
	*/
	void close() throws IOException;
}
