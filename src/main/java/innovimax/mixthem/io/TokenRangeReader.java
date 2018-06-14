package innovimax.mixthem.io;

import innovimax.mixthem.arguments.TokenType;
import innovimax.mixthem.utils.StreamUtils;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

/**
* This is the implementation of ITokenRangeInput interface.
* @see ITokenRangeInput
* @author Innovimax
* @version 1.0
*/
public class TokenRangeReader implements ITokenRangeInput {

	private final List<ITokenInput> readers;
	private TokenRange lastTokenRange;
	
	/**
	* Constructor
	* @param inputs The list of inputs as InputResource
	* @param selection The input index selection (maybe empty)
	* @param tokenType The input tokenization type
	* @see innovimax.mixthem.io.InputResource
	*/
	public TokenRangeReader(final List<InputResource> inputs, final Set<Integer> selection, final TokenType tokenType) {
		this.readers = IntStream.rangeClosed(1, inputs.size())
			.filter(index -> selection.isEmpty() || selection.contains(Integer.valueOf(index)))
			.mapToObj(index -> inputs.get(index-1))
			.map(StreamUtils.apply(input -> {
				switch(tokenType) {
					case BYTE: return new DefaultByteReader(input);
					case CHAR: return new DefaultCharReader(input);
					case LINE: return new DefaultLineReader(input);
					case FILEBYTE: /*return new DefaultFileByteReader(input)*/ throw new RuntimeException("TODO");
					case FILECHAR: /*return new DefaultFileCharReader(input)*/ throw new RuntimeException("TODO");
					default: throw new UnsupportedOperationException("Token not expected: " + tokenType.getName());
				}
			}))
			.collect(Collectors.toList());
		this.lastTokenRange = null;
	}

	@Override
	public boolean hasMoreTokens() {		
		return this.readers.stream()
			.anyMatch(StreamUtils.test(reader -> reader.hasMoreTokens()));
	}

	@Override
	public TokenRange nextTokenRange(ITokenStatusRange tokenStatusRange) {
		final List<IToken> tokenRange = IntStream.range(0, readers.size())
			.mapToObj(StreamUtils.applyToInt(channel -> 
				tokenStatusRange.readingToken(channel) ? readers.get(channel).nextToken() : this.lastTokenRange.getToken(channel)))
			.collect(Collectors.toList());
		this.lastTokenRange = new TokenRange(tokenRange);
		return this.lastTokenRange;
	}
	
	@Override
	public void close() {
		this.readers
			.forEach(StreamUtils.consume(reader -> reader.close()));
	}

}
