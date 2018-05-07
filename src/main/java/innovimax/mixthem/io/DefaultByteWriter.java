package innovimax.mixthem.io;

import java.io.BufferedOutputStream;
import java.io.IOException;
import java.io.OutputStream;

/**
* <p>Writes bytes into an output stream.</p>
* <p>This is the default implementation of IByteOutput.</p>
* @see IByteOutput
* @author Innovimax
* @version 1.0
*/
public class DefaultByteWriter implements IByteOutput {

	private final BufferedOutputStream writer;

	/**
 	* Creates a byte writer.
 	* @param output The output stream for bytes to be written.
 	* @throws IOException - If an I/O error occurs
 	*/
	public DefaultByteWriter(final OutputStream output) throws IOException {
		this.writer = new BufferedOutputStream(output);
	}

	@Override
	public void writeByte(final byte b) throws IOException {		
		this.writer.write(b);
	}

	@Override
	public void writeBytes(final byte[] buffer, final int len) throws IOException {      
		this.writer.write(buffer, 0, len);
		this.writer.flush(); // WHY ?	
	}

	@Override
	public void close() throws IOException {
		this.writer.close();
	}

}
