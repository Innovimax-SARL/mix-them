package innovimax.mixthem.interfaces;

import java.io.IOException;

/*
    Created by innovimax
    Managing a stream of characters
*/
public interface IInputChar {
	/**
	* Returns true if there is more elements.
	* @return Returns true if there is more elements
	* @throws IOException - If an I/O error occurs
	*/
	public boolean hasCharacter() throws IOException;
	/**
 	* Reads a single character
 	* @return The character read as an int, or -1 if there is no more characters.
 	* @throws IOException - If an I/O error occurs
 	*/
	public int nextCharacter() throws IOException;
	/**
 	* Reads characters into a portion of an array.
 	* @param buffer Destination buffer
 	* @param len - Maximum number of characters to read
 	* @return The number of characters read, or -1 if there is no more characters
 	* @throws IOException - If an I/O error occurs
 	*/
	public int nextCharacters(char[] buffer, int len) throws IOException;
	/**
	* Closes this input and releases any system resources associated with it.
	*/
	public void close() throws IOException;	
}
