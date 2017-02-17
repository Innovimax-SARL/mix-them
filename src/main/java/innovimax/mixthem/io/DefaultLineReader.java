package innovimax.mixthem.io;

import innovimax.mixthem.interfaces.IInputLine;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Random;

/**
* <p>Reads lines from a character-input file.</p>
* <p>This is the default implementation of IInputLine.</p>
* @see IInputLine
* @author Innovimax
* @version 1.0
*/
public class DefaultLineReader implements IInputLine {

	private final static int DEFAULT_RANDOM_SEED = 1789;

	private final Path path;
	private final BufferedReader reader;
	private final boolean first;
	private final Random random;
	private boolean jump;

	/**
 	* Creates a line reader.
 	* @param input The input file to be read
 	* @param first True is this reader is the first one
 	* @throws IOException - If an I/O error occurs
 	*/
	public DefaultLineReader(File input, boolean first) throws IOException {
		this.path = input.toPath();
		this.reader = Files.newBufferedReader(path, StandardCharsets.UTF_8);
		this.first = first;
		this.random = new Random(DEFAULT_RANDOM_SEED);
		this.jump = !first;
	}

	@Override
	public boolean hasLine() throws IOException {
		return this.reader.ready();
	}

	@Override
	public String nextLine(ReadType type, boolean force) throws IOException {
		String line = null;
		if (hasLine()) {
			switch (type) {
				case _ALT_SIMPLE:
					if (!this.jump || force) {
						line = this.reader.readLine();						
					} else {
						this.reader.readLine();
					}					
					this.jump = !this.jump;
					break;
				case _ALT_RANDOM:					
					if (random.nextBoolean() == this.first || force) {
						line = this.reader.readLine();
					} else {
						this.reader.readLine();
					}					
					break;
			}
		}
		return line;
	}

	@Override
	public void close() throws IOException {
		this.reader.close();
	}

}
