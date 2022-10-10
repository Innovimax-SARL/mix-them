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
	 */
	public DefaultByteWriter(final OutputStream output, final boolean buffering) {
		super();
		this.writer = new BufferedOutputStream(output);
		this.buffering = buffering;
	}

	/**
 	* Creates a byte writer.
 	* @param output The output stream for bytes to be written.
	 */
	public DefaultByteWriter(final OutputStream output) {
		this(output, false);
	}

	@Override
	public void writeToken(final IToken token) throws IOException {
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
