package innovimax.mixthem.io;

import java.io.BufferedWriter;
import java.io.IOException;
import java.io.OutputStream;
import java.io.OutputStreamWriter;

/**
* <p>Writes characters into an output stream.</p>
* <p>This is the default implementation of IOutputChar.</p>
* @see IOutputLine
* @author Innovimax
* @version 1.0
*/
public class DefaultCharWriter implements IOutputChar {

	private final BufferedWriter writer;

	/**
 	* Creates a character writer.
 	* @param output The output stream for characters to be written.
 	* @throws IOException - If an I/O error occurs
 	*/
	public DefaultCharWriter(final OutputStream output) throws IOException {
		this.writer = new BufferedWriter(new OutputStreamWriter(output));
	}

	@Override
	public void writeCharacter(final int c) throws IOException {		
		this.writer.write(c);
	}

	@Override
	public void writeCharacters(final char[] buffer, final int len) throws IOException {      
		this.writer.write(buffer, 0, len);
		this.writer.flush(); // WHY ?	
	}

	@Override
	public void close() throws IOException {
		this.writer.close();
	}

}
