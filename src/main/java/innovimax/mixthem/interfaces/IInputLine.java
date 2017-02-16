package innovimax.mixthem.interfaces;

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
 	* Reads a line of characters
 	* @return The line of characters read, or null if there is no more lines.
 	* @throws IOException - If an I/O error occurs
 	*/	
	String nextLine() throws IOException;
	/**
	* Closes this input and releases any system resources associated with it.
	* @throws IOException - If an I/O error occurs
	*/
  void close() throws IOException;	

	/**
	* Preloads this input by reading all the lines of characters in memory.
	* @throws IOException - If an I/O error occurs
	*/
	void preload() throws IOException;		
	/**
	* Returns the number of lines preloaded.
	* @return The number of lines preloaded, or -1 if no preloading nas been done
	*/	
	int size();
	/**
	* Returns true if there is a line with the given index.
	* @return Returns true if this line exists
	*/	
	boolean hasLine(int index);
	/**
 	* Reads the line with the given index
 	* @return The line with the given index, or null if there is not exists.
 	*/	
	String getLine(int index);
}
