package innovimax.mixthem.io;

import java.io.IOException;

/**
* Will be deprecated in future version !!!
* This interface provides for writing lines in an output stream.
* @author Innovimax
* @version 1.0
*/
public interface ILineOutput extends ITokenOutput {
	/**
 	* Writes a line of characters.
 	* @param line The line of characters to be written
 	* @throws IOException - If an I/O error occurs
 	*/		
	void writeLine(String line) throws IOException;
}
