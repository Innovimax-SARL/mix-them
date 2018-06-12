package innovimax.mixthem.io;

import java.io.BufferedWriter;
import java.io.IOException;
import java.io.OutputStream;
import java.io.OutputStreamWriter;

/**
* <p>Writes characters into an output stream.</p>
* <p>This is the default implementation of ILineOutput.</p>
* @see ILineOutput
* @author Innovimax
* @version 1.0
*/
public class DefaultLineWriter implements ITokenOutput {

	private final BufferedWriter writer;

	/**
 	* Creates a line writer.
 	* @param output The output stream for lines to be written.
 	* @throws IOException - If an I/O error occurs
 	*/
	public DefaultLineWriter(final OutputStream output) throws IOException {
		this.writer = new BufferedWriter(new OutputStreamWriter(output));
	}

	@Override
	public void writeToken(IToken token) throws IOException {
		this.writer.write(token.asString());
		this.writer.newLine();
	}

	@Override
	public void close() throws IOException {
		this.writer.close();
	}

}
