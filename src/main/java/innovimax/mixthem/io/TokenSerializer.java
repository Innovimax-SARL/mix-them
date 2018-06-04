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
	* @throws IOException - If an I/O error occurs
	*/
	public TokenSerializer(final OutputStream output, final TokenType tokenType) throws IOException {		
		switch(tokenType) {
			case BYTE: this.writer = new DefaultByteWriter(output); break;
			case CHAR: this.writer = new DefaultCharWriter(output); break;
			case LINE: this.writer = new DefaultLineWriter(output); break;
			case FILEBYTE: /*this.writer = new DefaultFileByteWriter(output)*/ throw new RuntimeException("TODO");
			case FILECHAR: /*this.writer = new DefaultByteCharWriter(output)*/ throw new RuntimeException("TODO");
			default: throw new UnsupportedOperationException("Token not expected: " + tokenType.getName());
		}
	}

	@Override
	public void writeToken(Token token) throws IOException {		
		this.writer.writeToken(token);
	}
	
	@Override
	public void close() throws IOException {
		this.writer.close();
	}

}