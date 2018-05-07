package innovimax.mixthem.io;

import java.io.IOException;

/**
* This interface provides for reading bytes from a multi-channel byte-input.
* @author Innovimax
* @version 1.0
*/
public interface IMultiChannelByteInput {
    /**
	* Returns true if there is more bytes in one of the channels.
	* @return Returns true if there is more bytes in one of the channels
	* @throws IOException - If an I/O error occurs
	*/
	boolean hasByte() throws IOException;
	/**
 	* Reads a byte from each channel.
 	* @return The array of bytes (one byte per channel or -1 if no more bytes in the channel)
 	* @throws IOException - If an I/O error occurs
 	*/
	byte[] nextBytes() throws IOException;
	/**
	* Closes this input channels and releases any system resources associated with them.
	* @throws IOException - If an I/O error occurs
	*/
	void close() throws IOException;	
}
