package innovimax.mixthem.io;

import innovimax.mixthem.interfaces.IInputChar;

import java.io.File;
import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.io.IOException;

/*
    Created by innovimax
    Default implementation of IInputChar
*/
public class DefaultCharReader implements IInputChar {

	private BufferedReader reader = null;
	private boolean ended = false;

	public DefaultCharReader(File input) throws IOException {		
		this.reader = new BufferedReader(new InputStreamReader(new FileInputStream(input)));
	}

	@Override
	public boolean hasCharacter() throws IOException {
		return (reader.ready() && !this.ended);
	}

	@Override
	public int nextCharacter() throws IOException {
		int c = this.reader.read();
		if (c == -1) {
			this.ended = true;
		}
		return c;
	}

	@Override
	public int nextCharacters(char[] buffer) throws IOException {
		int len = this.reader.read(buffer, 0, buffer.length);
		if (len == -1) {
			this.ended = true;
		}
		return len;
	}

	@Override
	public void close() throws IOException {
		this.reader.close();
	}

}
