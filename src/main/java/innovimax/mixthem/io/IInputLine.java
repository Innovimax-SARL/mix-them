package innovimax.mixthem.io;

import java.io.IOException;

/**
* This interface provides for reading lines from an character-input.
* @author Innovimax
* @version 1.0
*/
public interface IInputLine {
	/**
	* Sets seed value for random reading only.
	* @param The seed value	
	*/	
	void setSeed(int seed);
	/**
	* Returns true if there is more lines.
	* @return Returns true if there is more lines
	* @throws IOException - If an I/O error occurs
	*/	
	boolean hasLine() throws IOException;
	/**
 	* Reads an eligible line regarding the type, or returns null if not eligible or no more lines.
 	* @param type The type of reading expected
 	* @return The eligible line, or null if not eligible or no more lines
 	* @throws IOException - If an I/O error occurs
 	*/	
	String nextLine(ReadType type) throws IOException;
	/**
	* Closes this input and releases any system resources associated with it.
	* @throws IOException - If an I/O error occurs
	*/
	void close() throws IOException;
}
