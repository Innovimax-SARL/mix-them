package innovimax.mixthem.io;

import java.io.BufferedOutputStream;
import java.io.IOException;
import java.io.OutputStream;

/**
* <p>Writes bytes into an output stream.</p>
* @see ITokenOutput
* @author Innovimax
* @version 1.0
*/
public class DefaultByteWriter implements ITokenOutput {

	private final BufferedOutputStream writer;
	private final boolean buffering;

	/**
 	* Creates a byte writer.
 	* @param output The output stream for bytes to be written.
 	* @param buffering The buffering indicator
 	* @throws IOException - If an I/O error occurs
 	*/
	public DefaultByteWriter(final OutputStream output, final boolean buffering) throws IOException {
		this.writer = new BufferedOutputStream(output);
		this.buffering = buffering;
	}

	/**
 	* Creates a byte writer.
 	* @param output The output stream for bytes to be written.
 	* @throws IOException - If an I/O error occurs
 	*/
	public DefaultByteWriter(final OutputStream output) throws IOException {
		this(output, false);
	}

	@Override
	public void writeToken(IToken token) throws IOException {
		if (this.buffering) {
			this.writer.write(token.asByteArray(), 0, token.asByteArray().length);
		} else {
			this.writer.write(token.asByte());
		}
	}

	@Override
	public void close() throws IOException {
		this.writer.close();
	}

}
