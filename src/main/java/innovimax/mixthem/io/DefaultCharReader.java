package innovimax.mixthem.io;

import java.io.BufferedReader;
import java.io.IOException;

/**
* <p>Reads characters from a character-input file.</p>
* @see ITokenInput
* @author Innovimax
* @version 1.0
*/
public class DefaultCharReader implements ITokenInput {

	private final BufferedReader reader;
	private final boolean buffering;
	private final int bufferSize;

	/**
 	* Creates a character reader from an input resource.
 	* @param input The input resource to be read
 	* @param buffering The buffering indicator
 	* @param bufferSize The input buffer size
 	* @throws IOException - If an I/O error occurs
 	*/
	public DefaultCharReader(final InputResource input, final boolean buffering, final int bufferSize) throws IOException {		
		this.reader = input.newBufferedReader();
		this.buffering = buffering;
		this.bufferSize = bufferSize;
	}

	/**
 	* Creates a character reader from an input resource.
 	* @param input The input resource to be read
 	* @throws IOException - If an I/O error occurs
 	*/
	public DefaultCharReader(final InputResource input) throws IOException {		
		this(input, false, 1);
	}

	@Override
	public boolean hasMoreTokens() throws IOException {
		return this.reader.ready();
	}
	
	@Override
	public IToken nextToken() throws IOException {
		if (this.buffering) {
			if (hasMoreTokens()) {
				final char[] buffer = new char[this.bufferSize];
				final int len = this.reader.read(buffer, 0, this.bufferSize);
				return Token.createCharArrayToken(buffer, len);
			}	
			return Token.createCharArrayToken(null, 0);
		}
		return Token.createCharToken(hasMoreTokens() ? this.reader.read() : -1);
	}

	@Override
	public void close() throws IOException {
		this.reader.close();
	}

}
