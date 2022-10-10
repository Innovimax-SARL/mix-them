package innovimax.mixthem.io;

import innovimax.mixthem.arguments.TokenType;

import java.io.IOException;
import java.io.OutputStream;

/**
* This is the implementation of ITokenOutput interface.
* @see ITokenOutput
* @author Innovimax
* @version 1.0
*/
public class TokenSerializer implements ITokenOutput {

	private final ITokenOutput writer;
	
	/**
	* Constructor
	* @param output The output stream for tokens to be written.	
	* @param tokenType The output tokenization type
	 */
	public TokenSerializer(final OutputStream output, final TokenType tokenType) {
		super();
		switch (tokenType) {
			case BYTE:
				this.writer = new DefaultByteWriter(output);
				break;
			case CHAR:
				this.writer = new DefaultCharWriter(output);
				break;
			case LINE:
				this.writer = new DefaultLineWriter(output);
				break;
			case FILEBYTE: /*this.writer = new DefaultFileByteWriter(output)*/
				throw new RuntimeException("TODO");
			case FILECHAR: /*this.writer = new DefaultByteCharWriter(output)*/
				throw new RuntimeException("TODO");
			default:
				throw new UnsupportedOperationException("Token not expected: " + tokenType.getName());
		}
	}

	@Override
	public void writeToken(final IToken token) throws IOException {
		this.writer.writeToken(token);
	}
	
	@Override
	public void close() throws IOException {
		this.writer.close();
	}

}