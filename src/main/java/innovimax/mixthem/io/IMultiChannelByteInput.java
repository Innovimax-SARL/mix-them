package innovimax.mixthem.io;

/**
* This interface provides for reading bytes from a multi-channel byte-input.
* @author Innovimax
* @version 1.0
*/
public interface IMultiChannelByteInput {
    /**
	* Returns true if there is more bytes in one of the channels.
	* @return Returns true if there is more bytes in one of the channels
	*/
	boolean hasByte();
	/**
 	* Reads byte range (one by channel, -1 if no more byte in channel)
 	* @return The array of bytes (one byte per channel or -1 if no more bytes in the channel)
 	*/
	byte[] nextByteRange();
	/**
	* Closes this input channels and releases any system resources associated with them.
	*/
	void close();
}
