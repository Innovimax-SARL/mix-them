package innovimax.mixthem.io;

import innovimax.mixthem.interfaces.IOutputChar;

import java.io.BufferedWriter;
import java.io.IOException;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.Writer;

/*
    Created by innovimax
    Default implementation of IOutputChar
*/
public class DefaultCharWriter implements IOutputChar {

	private Writer writer = null;

	public DefaultCharWriter(OutputStream output) throws IOException {
		this.writer = new BufferedWriter(new OutputStreamWriter(output));
	}

	@Override
	public void writeCharacter(int c) throws IOException {		
		this.writer.write(c);
	}

	@Override
	public void writeCharacters(char[] buffer, int len) throws IOException {      
		this.writer.write(buffer, 0, len);
		this.writer.flush();
	}

}
