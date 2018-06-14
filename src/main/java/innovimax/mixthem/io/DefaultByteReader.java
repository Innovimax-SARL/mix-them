package innovimax.mixthem.io;

import java.io.BufferedInputStream;
import java.io.IOException;

/**
* <p>Reads bytes from a byte-input file.</p>
* @see ITokenInput
* @author Innovimax
* @version 1.0
*/
public class DefaultByteReader implements ITokenInput {

	private final BufferedInputStream reader;
	private final boolean buffering;
	private final int bufferSize;

	/**
 	* Creates a character reader from an input resource.
 	* @param input The input resource to be read
 	* @param buffering The buffering indicator
 	* @param bufferSize The input buffer size
 	* @throws IOException - If an I/O error occurs
 	*/
	public DefaultByteReader(final InputResource input, final boolean buffering, final int bufferSize) throws IOException {		
		this.reader = input.newBufferedInputStream();
		this.buffering = buffering;
		this.bufferSize = bufferSize;
	}

	/**
 	* Creates a character reader from an input resource.
 	* @param input The input resource to be read
 	* @throws IOException - If an I/O error occurs
 	*/
	public DefaultByteReader(final InputResource input) throws IOException {		
		this(input, false, 1);
	}

	@Override
	public boolean hasMoreTokens() throws IOException {
		return this.reader.available() > 0;
	}
	
	@Override
	public IToken nextToken() throws IOException {
		if (buffering) {
			if (hasMoreTokens()) {
				final byte[] buffer = new byte[this.bufferSize];
				final int len = this.reader.read(buffer, 0, this.bufferSize);
				return Token.createByteArrayToken(buffer, len);
			}	
			return Token.createByteArrayToken(null, 0);
		}
		return Token.createByteToken(hasMoreTokens() ? (byte) this.reader.read() : (byte) -1);
	}

	@Override
	public void close() throws IOException {
		this.reader.close();
	}

}
