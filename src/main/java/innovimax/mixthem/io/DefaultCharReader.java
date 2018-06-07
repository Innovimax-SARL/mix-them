package innovimax.mixthem.io;

import java.io.BufferedReader;
import java.io.IOException;

/**
* <p>Reads characters from a character-input file.</p>
* <p>This is the default implementation of ICharInput.</p>
* @see ICharInput
* @author Innovimax
* @version 1.0
*/
public class DefaultCharReader implements ICharInput {

	private final BufferedReader reader;

	/**
 	* Creates a character reader from an input resource.
 	* @param input The input resource to be read
 	* @throws IOException - If an I/O error occurs
 	*/
	public DefaultCharReader(final InputResource input) throws IOException {		
		this.reader = input.newBufferedReader();
	}

	// Will be deprecated in future version !!!
	@Override
	public boolean hasCharacter() throws IOException {
		return this.reader.ready();
	}

	// Will be deprecated in future version !!!
	@Override
	public int nextCharacter() throws IOException {		
		int c = -1;
		if (hasCharacter()) {
			c = this.reader.read();
		}
		return c;
	}

	// Will be deprecated in future version !!!
	@Override
	public int nextCharacters(final char[] buffer, final int len) throws IOException {
		return this.reader.read(buffer, 0, len);		
	}

	@Override
	public boolean hasMoreTokens() throws IOException {
		return this.reader.ready();
	}
	
	@Override
	public IToken nextToken() throws IOException {
		return Token.createCharToken(hasMoreTokens() ? this.reader.read() : -1);		
	}

	@Override
	public void close() throws IOException {
		this.reader.close();
	}

}
