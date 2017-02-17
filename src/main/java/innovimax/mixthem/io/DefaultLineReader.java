package innovimax.mixthem;

import innovimax.mixthem.interfaces.IInputLine;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
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

	private final BufferedReader reader;
	private final boolean first;
	private final Random random;
	private boolean jump;

	/**
 	* Creates a line reader.
 	* @param file The input file to be read
 	* @param first True is this reader is the first one
 	* @throws IOException - If an I/O error occurs
 	*/
	public DefaultLineReader(File input, boolean first) throws IOException {
		this.reader = new BufferedReader(new FileReader(input));
		this.first = first;
		this.random = new Random(DEFAULT_RANDOM_SEED);
		this.jump = !first;
	}

	@Override
	public boolean hasLine() throws IOException {
		// should return True only if there is a line to write regarding the type
		return this.reader.ready();
	}

	@Override
	public String nextLine(ReadType type, boolean force) throws IOException {
		String line = null;
		if (hasLine()) {
			switch (type) {
				case _SIMPLE:
					if (!this.jump || force) {
						line = this.reader.readLine();						
					} else {
						this.reader.readLine();
					}					
					this.jump = !this.jump;
					break;
				case _RANDOM:					
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
