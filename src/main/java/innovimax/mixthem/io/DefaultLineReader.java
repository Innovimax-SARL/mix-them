package innovimax.mixthem.io;

import java.io.BufferedReader;
import java.io.IOException;

/**
* <p>Reads lines from a character-input file.</p>
* <p>This is the default implementation of ILineInput.</p>
* @see ILineInput
* @author Innovimax
* @version 1.0
*/
public class DefaultLineReader implements ILineInput {

	private final BufferedReader reader;

	/**
 	* Creates a line reader from an input resource.
 	* @param input The input resource to be read 	
 	* @throws IOException - If an I/O error occurs
 	*/
	public DefaultLineReader(final InputResource input) throws IOException {
		this.reader = input.newBufferedReader();
	}
	
	// Will be deprecated in future version !!!
	@Override
	public boolean hasLine() throws IOException {
		return this.reader.ready();
	}

	// Will be deprecated in future version !!!
	@Override
	public String nextLine() throws IOException {
		String line = null;
		if (hasLine()) {
			line = this.reader.readLine();
		}
		return line;
	}

	@Override
	public boolean hasMoreTokens() throws IOException {
		return this.reader.ready();
	}
	
	@Override
	public IToken nextToken() throws IOException {
		return Token.createLineToken(hasMoreTokens() ? this.reader.readLine() : null);		
	}

	@Override
	public void close() throws IOException {
		this.reader.close();
	}

}
