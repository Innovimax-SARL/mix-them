package innovimax.mixthem.io;

import java.io.IOException;

/**
* This interface provides for reading bytes from an byte-input.
* @author Innovimax
* @version 1.0
*/
public interface IInputByte {
    /**
	* Returns true if there is more bytes.
	* @return Returns true if there is more bytes
	* @throws IOException - If an I/O error occurs
	*/
	boolean hasByte() throws IOException;
	/**
 	* Reads a byte, or returns -1 if no more bytes.
 	* @param type The type of reading expected
 	* @return The byte as an int, or -1 if more bytes
 	* @throws IOException - If an I/O error occurs
 	*/
	int nextByte() throws IOException;
	/**
 	* Reads bytes into a portion of an array.
 	* @param buffer Destination buffer
 	* @param len - Maximum number of characters to read
 	* @return The number of characters read, or -1 if there is no more characters
 	* @throws IOException - If an I/O error occurs
 	*/
	int nextBytes(byte[] buffer, int len) throws IOException;
	/**
	* Closes this input and releases any system resources associated with it.
	* @throws IOException - If an I/O error occurs
	*/
	void close() throws IOException;	
}
