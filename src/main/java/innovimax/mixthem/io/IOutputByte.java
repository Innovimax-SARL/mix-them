package innovimax.mixthem.io;

import java.io.IOException;

/**
* This interface provides for writing bytes in an output stream.
* @author Innovimax
* @version 1.0
*/
public interface IOutputByte {
	/**
 	* Writes a single byte.
 	* @param b The byte to be written
 	* @throws IOException - If an I/O error occurs
 	*/	
	void writeByte(byte b) throws IOException;
	/**
 	* Writes a portion of an array of bytes.
 	* @param buffer Buffer of bytes
 	* @param len Number of bytes to write
 	* @throws IOException - If an I/O error occurs
 	*/	
	void writeBytes(byte[] buffer, int len) throws IOException;	
	/**
	* Closes this output and releases any system resources associated with it.
 	* @throws IOException - If an I/O error occurs	
	*/
	void close() throws IOException;		
}
