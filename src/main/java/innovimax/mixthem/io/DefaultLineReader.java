package innovimax.mixthem.io;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;

/**
* <p>Reads lines from a character-input file.</p>
* <p>This is the default implementation of IInputLine.</p>
* @see IInputLine
* @author Innovimax
* @version 1.0
*/
public class DefaultLineReader implements IInputLine {

	private final Path path;
	private final BufferedReader reader;

	/**
 	* Creates a line reader.
 	* @param input The input file to be read 	
 	* @throws IOException - If an I/O error occurs
 	*/
	public DefaultLineReader(File input) throws IOException {
		this.path = input.toPath();
		this.reader = Files.newBufferedReader(path, StandardCharsets.UTF_8);		
	}
	
	@Override
	public boolean hasLine() throws IOException {
		return this.reader.ready();
	}

	@Override
	public String nextLine() throws IOException {
		String line = null;
		if (hasLine()) {
			line = this.reader.readLine();
		}
		return line;
	}

	@Override
	public void close() throws IOException {
		this.reader.close();
	}

}
