package innovimax.mixthem.io;

import java.io.IOException;

/**
* Will be deprecated in future version !!!
* This interface provides for reading characters from an character-input.
* @author Innovimax
* @version 1.0
*/
public interface ICharInput extends ITokenInput {
	/**
	* Returns true if there is more characters.
	* @return Returns true if there is more characters
	* @throws IOException - If an I/O error occurs
	*/
	boolean hasCharacter() throws IOException;
	/**
 	* Reads a character.
 	* @return The character as an int or -1 if no more characters
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
}
