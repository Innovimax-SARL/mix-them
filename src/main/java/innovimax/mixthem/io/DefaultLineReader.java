package innovimax.mixthem;

import innovimax.mixthem.interfaces.IInputLine;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.UncheckedIOException;
import java.util.List;
import java.util.stream.Collectors;

/**
* <p>Reads lines from a character-input file.</p>
* <p>This is the default implementation of IInputLine.</p>
* @see IInputLine
* @author Innovimax
* @version 1.0
*/
public class DefaultLineReader implements IInputLine {

	private final BufferedReader reader;
	private List<String> lines;	

	/**
 	* Creates a line reader.
 	* @param file The input file to be read
 	* @throws IOException - If an I/O error occurs
 	*/
	public DefaultLineReader(File input) throws IOException {
		this.reader = new BufferedReader(new FileReader(input));
	}

	@Override
	public boolean hasLine() throws IOException {
		return this.reader.ready();
	}

	@Override
	public String nextLine() throws IOException {
		return this.reader.readLine();
	}

	@Override
	public void close() throws IOException {
		this.reader.close();
	}

	@Override
	public void preload() throws IOException {		
		this.lines = this.reader.lines().collect(Collectors.toList());
	}	

}
