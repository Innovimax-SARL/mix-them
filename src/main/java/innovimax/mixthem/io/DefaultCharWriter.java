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
 	* @throws IOException - If an I/O error occurs
 	*/
	public DefaultCharWriter(final OutputStream output, final boolean buffering) throws IOException {
		this.writer = new BufferedWriter(new OutputStreamWriter(output));
		this.buffering = buffering;
	}

	/**
 	* Creates a character writer.
 	* @param output The output stream for characters to be written.
 	* @throws IOException - If an I/O error occurs
 	*/
	public DefaultCharWriter(final OutputStream output) throws IOException {
		this(output, false);
	}

	@Override
	public void writeToken(IToken token) throws IOException {
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
