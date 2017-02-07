package innovimax.mixthem.interfaces;

import java.io.IOException;

/*
    Created by innovimax
    Managing a stream of characters
*/
public interface IOutputChar {
	/**
 	* Writes a single character.
 	* @param c The character as an int to be written
 	* @throws IOException - If an I/O error occurs
 	*/	
	public void writeCharacter(int c) throws IOException;
	/**
 	* Writes a portion of an array of characters.
 	* @param buffer Buffer of characters
 	* @param len Number of characters to write
 	* @throws IOException - If an I/O error occurs
 	*/	
	public void writeCharacters(char[] buffer, int len) throws IOException;	
	/**
	* Closes this output and releases any system resources associated with it.
	*/
	public void close() throws IOException;		
}
