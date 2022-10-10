package innovimax.mixthem.io;

import java.io.BufferedReader;
import java.io.IOException;

/**
* <p>Reads lines from a character-input file.</p>
** @see ITokenInput
* @author Innovimax
* @version 1.0
*/
public class DefaultLineReader implements ITokenInput {

	private final BufferedReader reader;

	/**
 	* Creates a line reader from an input resource.
 	* @param input The input resource to be read 	
 	* @throws IOException - If an I/O error occurs
 	*/
	public DefaultLineReader(final InputResource input) throws IOException {
		super();
		this.reader = input.newBufferedReader();
	}

	@Override
	public boolean hasMoreTokens() throws IOException {
		return this.reader.ready();
	}
	
	@Override
	public IToken nextToken() throws IOException {
		return Token.createLineToken(this.hasMoreTokens() ? this.reader.readLine() : null);
	}

	@Override
	public void close() throws IOException {
		this.reader.close();
	}

}
