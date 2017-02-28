package innovimax.mixthem.io;

import java.io.IOException;

/**
* This interface provides for writing lines in an output stream.
* @author Innovimax
* @version 1.0
*/
public interface IOutputLine {
	/**
 	* Writes a line of characters.
 	* @param line The line of characters to be written
 	* @throws IOException - If an I/O error occurs
 	*/		
	void writeLine(String line) throws IOException;
	/**
	* Closes this output and releases any system resources associated with it.
	* @throws IOException - If an I/O error occurs
	*/
	void close() throws IOException;
}
