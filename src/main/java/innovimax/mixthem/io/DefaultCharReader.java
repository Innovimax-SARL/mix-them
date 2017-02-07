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

	public DefaultCharReader(File input) throws IOException {		
		this.reader = new BufferedReader(new InputStreamReader(new FileInputStream(input)));
	}

	@Override
	public boolean hasCharacter() throws IOException {
		return reader.ready();
	}

	@Override
	public int nextCharacter() throws IOException {
		return this.reader.read();		
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
