package innovimax.mixthem.io;

import java.io.BufferedWriter;
import java.io.IOException;
import java.io.OutputStream;
import java.io.OutputStreamWriter;

/**
* <p>Writes characters into an output stream.</p>
* @see ITokenOutput
* @author Innovimax
* @version 1.0
*/
public class DefaultCharWriter implements ITokenOutput {

	private final BufferedWriter writer;
	private final boolean buffering;

	/**
 	* Creates a character writer.
 	* @param output The output stream for characters to be written.
 	* @param buffering The buffering indicator
     */
	public DefaultCharWriter(final OutputStream output, final boolean buffering) {
		super();
		this.writer = new BufferedWriter(new OutputStreamWriter(output));
		this.buffering = buffering;
	}

	/**
 	* Creates a character writer.
 	* @param output The output stream for characters to be written.
	 */
	public DefaultCharWriter(final OutputStream output) {
		this(output, false);
	}

	@Override
	public void writeToken(final IToken token) throws IOException {
		if (this.buffering) {
			this.writer.write(token.asCharacterArray(), 0, token.asCharacterArray().length);
		} else {
			this.writer.write(token.asCharacter());
		}
	}

	@Override
	public void close() throws IOException {
		this.writer.close();
	}

}
