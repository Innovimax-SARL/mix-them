package innovimax.mixthem.io;

import java.io.IOException;

/**
* This interface provides for reading characters from an character-input.
* @author Innovimax
* @version 1.0
*/
public interface IInputChar {
	/**
	* Returns true if there is more characters.
	* @return Returns true if there is more characters
	* @throws IOException - If an I/O error occurs
	*/
	boolean hasCharacter() throws IOException;
	/**
 	* Reads a character, or returns -1 if no more characters.
 	* @param type The type of reading expected
 	* @return The character as an int, or -1 if more characters
 	* @throws IOException - If an I/O error occurs
 	*/
	int nextCharacter() throws IOException;
	/**
 	* Reads characters into a portion of an array.
 	* @param buffer Destination buffer
 	* @param len - Maximum number of characters to read
 	* @return The number of characters read, or -1 if there is no more characters
 	* @throws IOException - If an I/O error occurs
 	*/
	int nextCharacters(char[] buffer, int len) throws IOException;
	/**
	* Closes this input and releases any system resources associated with it.
	* @throws IOException - If an I/O error occurs
	*/
	void close() throws IOException;	
}
