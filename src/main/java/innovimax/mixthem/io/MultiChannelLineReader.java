package innovimax.mixthem.io;

import innovimax.mixthem.utils.StreamUtils;

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
	public boolean hasLine() {
		return this.readers.stream()
			.anyMatch(StreamUtils.test(reader -> reader.hasLine()));
	}
	
	@Override
	public List<String> nextLineRange(List<Boolean> readingRange) {
		return IntStream.range(0, readers.size())
			.mapToObj(StreamUtils.applyToInt(index -> readingRange.get(index).booleanValue() ? 
								readers.get(index).nextLine() : null))
			.collect(Collectors.toList());
	}

	@Override
	public void close() {
		this.readers
			.forEach(StreamUtils.consume(reader -> reader.close()));
	}
	
}
