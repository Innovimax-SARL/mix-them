package innovimax.mixthem.interfaces;

import innovimax.mixthem.io.ReadType;

import java.io.IOException;

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
	boolean hasLine() throws IOException;
	/**
 	* Reads an eligible line regarding the type, or returns null if not eligible or no more lines.
 	* @param type The type of reading expected
 	* @param force True if reading is required whatever the type said
 	* @return The eligible line, or null if not eligible or no more lines
 	* @throws IOException - If an I/O error occurs
 	*/	
	String nextLine(ReadType type, boolean force) throws IOException;
	/**
	* Closes this input and releases any system resources associated with it.
	* @throws IOException - If an I/O error occurs
	*/
	void close() throws IOException;
}
