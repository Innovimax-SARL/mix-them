package innovimax.mixthem;

import java.io.IOException;

/*
    Created by innovimax
    Managing a stream of characters
*/
public interface ICharStream {
	public boolean hasCharacter() throws IOException;
	public int nextCharacter() throws IOException;
	public int nextCharacters(byte[] buffer) throws IOException;
	public void printCharacter(int c) throws IOException;
	public void printCharacters(byte[] buffer, int len) throws IOException;
	public void close() throws IOException;	
}
