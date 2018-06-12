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
	public void writeToken(IToken token) throws IOException {
		//TODO: think about a bufferisation before writing
		this.writer.write(token.asByte());
	}

	@Override
	public void close() throws IOException {
		this.writer.close();
	}

	@Override
	public void writeBytes(final byte[] buffer, final int len) throws IOException {      
		this.writer.write(buffer, 0, len);
		this.writer.flush(); // WHY ?	
	}

}
