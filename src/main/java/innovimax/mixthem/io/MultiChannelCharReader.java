package innovimax.mixthem.io;

import innovimax.mixthem.utils.StreamUtils;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class MultiChannelCharReader implements IMultiChannelCharInput {
	
	private final List<ICharInput> readers;
	
	/**
	* Constructor
	* @param inputs The list of inputs as InputResource
	* @param selection The input index selection (maybe empty)
	* @see innovimax.mixthem.io.InputResource
	*/
	public MultiChannelCharReader(final List<InputResource> inputs, final Set<Integer> selection) {		
		this.readers = IntStream.rangeClosed(1, inputs.size())
			.filter(index -> selection.isEmpty() || selection.contains(Integer.valueOf(index)))
			.mapToObj(index -> inputs.get(index-1))
			.map(StreamUtils.apply(input -> new DefaultCharReader(input)))
			.collect(Collectors.toList());
	}
	
	@Override
	public boolean hasCharacter() {
		return this.readers.stream()					
			.anyMatch(StreamUtils.test(reader -> reader.hasCharacter()));
	}
	
	@Override
	public int[] nextCharacterRange() {
		return readers.stream()			
			.mapToInt(StreamUtils.applyAsInt(reader -> reader.nextCharacter()))
			.toArray();
	}

	@Override
	public void close() {
		this.readers			
			.forEach(StreamUtils.consume(reader -> reader.close()));
	}
	
}
