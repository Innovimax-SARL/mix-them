package innovimax.mixthem.io;

import java.io.IOException;

/**
* Will be deprecated in future version !!!
* This interface provides for reading lines from an character-input.
* @author Innovimax
* @version 1.0
*/
public interface ILineInput extends ITokenInput {
	/**
	* Returns true if there is more lines.
	* @return Returns true if there is more lines
	* @throws IOException - If an I/O error occurs
	*/	
	boolean hasLine() throws IOException;
	/**
 	* Reads a line, or returns null if no more lines.
 	* @return The line, or null if no more lines
 	* @throws IOException - If an I/O error occurs
 	*/	
	String nextLine() throws IOException;
}
