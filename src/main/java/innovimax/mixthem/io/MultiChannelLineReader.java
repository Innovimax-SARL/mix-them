package innovimax.mixthem.io;

import innovimax.mixthem.utils.StreamUtils;

import java.io.IOException;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class MultiChannelLineReader implements IMultiChannelLineInput {
	
	private final List<ILineInput> readers;
	
	/**
	* Constructor
	* @param inputs The list of inputs as InputResource
	* @param selection The input index selection (maybe empty)
	* @see innovimax.mixthem.io.InputResource
	*/
	public MultiChannelLineReader(final List<InputResource> inputs, final Set<Integer> selection) {
		this.readers = IntStream.rangeClosed(1, inputs.size())
			.filter(index -> selection.isEmpty() || selection.contains(Integer.valueOf(index)))
			.mapToObj(index -> inputs.get(index-1))
			.map(StreamUtils.apply(input -> new DefaultLineReader(input)))
			.collect(Collectors.toList());
	}
	
	@Override
	public boolean hasLine() throws IOException {
		return this.readers.stream()
			.anyMatch(StreamUtils.test(reader -> reader.hasLine()));
	}
	
	@Override
	public List<String> nextLineRange(List<Boolean> readingRange) throws IOException {		
		return IntStream.range(0, readers.size())
			//TODO: new functional interface IntFunctionException
			.mapToObj(index -> {
				try {
					return readingRange.get(index).booleanValue() ? readers.get(index).nextLine() : null;
				} catch (IOException e) {
					throw new RuntimeException(e);
				}})
			.collect(Collectors.toList());
	}

	@Override
	public void close() throws IOException {
		this.readers
			.forEach(StreamUtils.consume(reader -> reader.close()));
	}
	
}
