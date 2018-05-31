package innovimax.mixthem.io;

import innovimax.mixthem.arguments.Token;
import innovimax.mixthem.utils.StreamUtils;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

/**
* This is the implementation of ITokenRange interface.
* @author Innovimax
* @version 1.0
*/
public class TokenRangeReader implements ITokenRange {

	private final Token token;
	private final List<ITokenInput> readers;
	
	/**
	* Constructor
	* @param inputs The list of inputs as InputResource
	* @param selection The input index selection (maybe empty)
	* @param token The input tokenization mode
	* @see innovimax.mixthem.io.InputResource
	*/
	public TokenRangeReader(final List<InputResource> inputs, final Set<Integer> selection, final Token token) {
		this.token = token;
		this.readers = IntStream.rangeClosed(1, inputs.size())
			.filter(index -> selection.isEmpty() || selection.contains(Integer.valueOf(index)))
			.mapToObj(index -> inputs.get(index-1))
			.map(StreamUtils.apply(input -> {
				switch(this.token) {
					case BYTE: return new DefaultByteReader(input);
					case CHAR: return new DefaultCharReader(input);
					case LINE: return new DefaultLineReader(input);
					case FILEBYTE: /*return new DefaultFileByteReader(input)*/ throw new RuntimeException("TODO");
					case FILECHAR: /*return new DefaultFileCharReader(input)*/ throw new RuntimeException("TODO");
					default: throw new UnsupportedOperationException("Token not expected: " + token.getName());
				}
			}))
			.collect(Collectors.toList());
	}

	@Override
	public boolean hasMoreTokens() {
		//TODO: remove switch !!!
		//	see below...
		return this.readers.stream()
			.anyMatch(StreamUtils.test(reader -> {
				switch(this.token) {
					case BYTE: return ((IByteInput) reader).hasByte();
					case CHAR: return ((ICharInput) reader).hasCharacter();
					case LINE: return ((ILineInput) reader).hasLine();
					case FILEBYTE: /*((IFileInput) reader).hasFile()*/ throw new RuntimeException("TODO");
					case FILECHAR: /*((IFileInput) reader).hasFile()*/ throw new RuntimeException("TODO");
					default: throw new UnsupportedOperationException("Token not expected: " + token.getName());
				}
			}));
	}

	@Override
	public List<InputToken> nextTokenRange(List<Boolean> readingRange) {
		//TODO: remove switch !!!
		//	replace nextByte, nextChar, nextLine by nextToken
		//	add hasMoreTokens, nextToken in ITokenInput
		//	remove IByteInput, ICharInput, ILineInput
		//	DefaultByteReader, DefaultCharReader, DefaultLineReader directly implements ITokenInput
		switch(this.token) {
			case BYTE: return readers.stream()
					.map(StreamUtils.apply(reader -> ((IByteInput) reader).nextByte()))
					.map(b -> InputToken.createByteToken(b))
					.collect(Collectors.toList());
			case CHAR: return readers.stream()
					.map(StreamUtils.apply(reader -> ((ICharInput) reader).nextCharacter()))
					.map(c -> InputToken.createCharToken(c))
					.collect(Collectors.toList());
			case LINE: return IntStream.range(0, readers.size())
					.mapToObj(StreamUtils.applyToInt(index -> 
						readingRange.get(index).booleanValue() ? 
						((ILineInput) readers.get(index)).nextLine() : null))
					.map(line -> InputToken.createLineToken(line))
					.collect(Collectors.toList());
			case FILEBYTE: /*((IFileInput) reader).hasFile()*/ throw new RuntimeException("TODO");
			case FILECHAR: /*((IFileInput) reader).hasFile()*/ throw new RuntimeException("TODO");
			default: throw new UnsupportedOperationException("Token not expected: " + token.getName());
		}
	}
	
	@Override
	public void close() {
		this.readers
			.forEach(StreamUtils.consume(reader -> reader.close()));
	}

}
