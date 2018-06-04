package innovimax.mixthem.io;

import innovimax.mixthem.arguments.TokenType;

import java.io.IOException;
import java.io.OutputStream;


public class TokenSerializer implements ISerialize {

	private final ISerialize writer;
	
	/**
	* Constructor
	* @param output The output stream for tokens to be written.	
	* @param tokenType The output tokenization type
	* @see innovimax.mixthem.io.InputResource
	*/
	public TokenSerializer(final OutputStream output, final TokenType tokenType) {		
		switch(tokenType) {
			case BYTE: this.writer = new DefaultByteWriter(output);
			case CHAR: this.writer = new DefaultCharWriter(output);
			case LINE: this.writer = new DefaultLineWriter(output);
			case FILEBYTE: /*this.writer = new DefaultFileByteWriter(output)*/ throw new RuntimeException("TODO");
			case FILECHAR: /*this.writer = new DefaultByteCharWriter(output)*/ throw new RuntimeException("TODO");
			default: throw new UnsupportedOperationException("Token not expected: " + tokenType.getName());
		}
	}

	@Override
	public void writeToken(IToken token) throws IOException {		
		throw new RuntimeException("TODO");
	}
	
	@Override
	public void close() throws IOException {
		this.writer.close();
	}

}