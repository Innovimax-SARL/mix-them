package innovimax.mixthem.io;

import innovimax.mixthem.utils.StreamUtils;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class MultiChannelByteReader implements IMultiChannelByteInput {
	
	private final List<IByteInput> readers;
	
	/**
	* Constructor
	* @param inputs The list of inputs as InputResource
	* @param selection The input index selection (maybe empty)
	* @see innovimax.mixthem.io.InputResource
	*/
	public MultiChannelByteReader(final List<InputResource> inputs, final Set<Integer> selection) {	
		this.readers = IntStream.rangeClosed(1, inputs.size())
			.filter(index -> selection.isEmpty() || selection.contains(Integer.valueOf(index)))
			.mapToObj(index -> inputs.get(index-1))
			.map(StreamUtils.apply(input -> new DefaultByteReader(input)))
			.collect(Collectors.toList());
	}
	
	@Override
	public boolean hasByte() {
		return this.readers.stream()
			.anyMatch(StreamUtils.test(reader -> reader.hasByte()));
	}
	
	@Override
	public byte[] nextByteRange() {
		return readers.stream()			
			.map(StreamUtils.apply(reader -> Byte.valueOf(reader.nextByte())))
			.collect(StreamUtils.byteCollector())
			.toByteArray();
	}
	
	@Override
	public void close() {
		this.readers
			.forEach(StreamUtils.consume(reader -> reader.close()));
	}

}
