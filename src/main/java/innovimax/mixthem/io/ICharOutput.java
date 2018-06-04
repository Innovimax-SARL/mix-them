package innovimax.mixthem.io;

import java.io.IOException;

/**
* Will be deprecated in future version !!!
* This interface provides for writing characters in an output stream.
* @author Innovimax
* @version 1.0
*/
public interface ICharOutput extends ISerialize {
	/**
 	* Writes a single character.
 	* @param c The character to be written
 	* @throws IOException - If an I/O error occurs
 	*/	
	void writeCharacter(char c) throws IOException;
	/**
 	* Writes a portion of an array of characters.
 	* @param buffer Buffer of characters
 	* @param len Number of characters to write
 	* @throws IOException - If an I/O error occurs
 	*/	
	void writeCharacters(char[] buffer, int len) throws IOException;			
}
