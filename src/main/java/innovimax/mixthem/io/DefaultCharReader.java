package innovimax.mixthem.io;

import innovimax.mixthem.interfaces.IInputChar;

import java.io.File;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

/**
* <p>Reads characters from a character-input file.</p>
* <p>This is the default implementation of IInputChar.</p>
* @see IInputChar
* @author Innovimax
* @version 1.0
*/
public class DefaultCharReader implements IInputChar {

	private final BufferedReader reader;	

	/**
 	* Creates a character reader.
 	* @param file The input file to be read
 	* @throws IOException - If an I/O error occurs
 	*/
	public DefaultCharReader(File input, boolean first) throws IOException {		
		this.reader = new BufferedReader(new FileReader(input));
		this.first = first;
		this.jump = !first;		
	}

	@Override
	public boolean hasCharacter() throws IOException {
		return this.reader.ready();
	}

	@Override
	public int nextCharacter(ReadType type, boolean force) throws IOException {		
		int c = -1;
		if (hasCharacter()) {
			switch (type) {
				case _ALT_SIMPLE:
					if (!this.jump || force) {
						c = this.reader.read();
					} else {
						this.reader.read();
					}					
					this.jump = !this.jump;
					break;				
			}
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
