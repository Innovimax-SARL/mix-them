package innovimax.mixthem.io;

import java.io.BufferedInputStream;
import java.io.IOException;

/**
* <p>Reads bytes from a byte-input file.</p>
* <p>This is the default implementation of IInputByte.</p>
* @see IInputByte
* @author Innovimax
* @version 1.0
*/
public class DefaultByteReader implements IInputByte {

	private final BufferedInputStream reader;

	/**
 	* Creates a character reader from an input resource.
 	* @param input The input resource to be read
 	* @throws IOException - If an I/O error occurs
 	*/
	public DefaultByteReader(final InputResource input) throws IOException {		
		this.reader = input.newBufferedInputStream();
	}

	@Override
	public boolean hasByte() throws IOException {
		return this.reader.available() > 0;
	}

	@Override
	public byte nextByte() throws IOException {		
		byte b = -1;
		if (hasByte()) {
			b = (byte) this.reader.read();
		}
		return b;
	}

	@Override
	public int nextBytes(final byte[] buffer, final int len) throws IOException {
		return this.reader.read(buffer, 0, len);		
	}

	@Override
	public void close() throws IOException {
		this.reader.close();
	}

}
