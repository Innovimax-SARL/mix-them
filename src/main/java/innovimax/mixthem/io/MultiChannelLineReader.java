package innovimax.mixthem.io;

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
			.map(input -> {
				try {
					return new DefaultLineReader(input);
				} catch (IOException e) {
					throw new RuntimeException(e);
				}})
			.collect(Collectors.toList());
	}
	
	@Override
	public boolean hasLine() throws IOException {
		return this.readers.stream()
			.anyMatch(reader -> {
				try {
					return reader.hasLine();
				} catch (IOException e) {
					throw new RuntimeException(e);
				}});
	}
	
	@Override
	public List<String> nextLineRange(List<Boolean> readingRange) throws IOException {		
		return IntStream.range(0, readers.size())
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
			.forEach(reader -> {
				try {
					reader.close();
				} catch (IOException e) {
					throw new RuntimeException(e);
				}});		
	}
	
}
