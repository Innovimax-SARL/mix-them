package innovimax.mixthem.interfaces;

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
	public void writeLine(String line) throws IOException;
	/**
	* Closes this output and releases any system resources associated with it.
	* @throws IOException - If an I/O error occurs
	*/
	public void close() throws IOException;
}
