package innovimax.mixthem.io;

import java.io.File;
import java.io.BufferedReader;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;

/**
* <p>Reads characters from a character-input file.</p>
* <p>This is the default implementation of IInputChar.</p>
* @see IInputChar
* @author Innovimax
* @version 1.0
*/
public class DefaultCharReader implements IInputChar {

	private final Path path;
	private final BufferedReader reader;

	/**
 	* Creates a character reader.
 	* @param input The input file to be read
 	* @throws IOException - If an I/O error occurs
 	*/
	public DefaultCharReader(File input) throws IOException {		
		this.path = input.toPath();
		this.reader = Files.newBufferedReader(path, StandardCharsets.UTF_8);
	}

	@Override
	public boolean hasCharacter() throws IOException {
		return this.reader.ready();
	}

	@Override
	public int nextCharacter() throws IOException {		
		int c = -1;
		if (hasCharacter()) {
			c = this.reader.read();
		}
		return c;
	}

	@Override
	public int nextCharacters(char[] buffer, int len) throws IOException {
		return this.reader.read(buffer, 0, len);		
	}

	@Override
	public void close() throws IOException {
		this.reader.close();
	}

}
