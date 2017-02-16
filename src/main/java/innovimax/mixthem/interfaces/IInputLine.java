package innovimax.mixthem.interfaces;

import java.io.IOException;
import java.io.UncheckedIOException;
import java.util.stream.Stream;

/**
* This interface provides for reading lines from an character-input.
* @author Innovimax
* @version 1.0
*/
public interface IInputLine {
	/**
	* Returns true if there is more lines.
	* @return Returns true if there is more lines
	* @throws IOException - If an I/O error occurs
	*/	
	public boolean hasLine() throws IOException;
	/**
 	* Reads a line of characters
 	* @return The line of characters read, or null if there is no more lines.
 	* @throws IOException - If an I/O error occurs
 	*/	
	public String nextLine() throws IOException;
	/**
	* Closes this input and releases any system resources associated with it.
	* @throws IOException - If an I/O error occurs
	*/
	public void close() throws IOException;
	/**
	* Preloads this input by reading all the lines of characters in memory.
	* @throws IOException - If an I/O error occurs
	*/
	public void preload() throws IOException;		
}
