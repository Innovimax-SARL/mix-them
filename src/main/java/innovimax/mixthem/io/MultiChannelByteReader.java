package innovimax.mixthem.io;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import java.util.stream.IntStream;

public class MultiChannelByteReader implements IMultiChannelByteInput {
	
	private final List<IByteInput> readers = new ArrayList<IByteInput>();
	
	/**
	* Constructor
	* @param inputs The list of inputs as InputResource
	* @param selection The input index selection (maybe empty)
	* @see innovimax.mixthem.io.InputResource
	*/
	public MultiChannelByteReader(final List<InputResource> inputs, final Set<Integer> selection) {	
		IntStream.rangeClosed(1, inputs.size())
			.filter(index -> selection.isEmpty() || selection.contains(Integer.valueOf(index)))
			.mapToObj(index -> inputs.get(index-1))
			.forEachOrdered(input -> {
				try {
					this.readers.add(new DefaultByteReader(input));
				} catch (IOException e) {
					throw new RuntimeException(e);
				}
			});
	}
	
	@Override
	public boolean hasByte() throws IOException {
		return this.readers.stream()
			.anyMatch(reader -> {
				try {
					return reader.hasByte();
				} catch (IOException e) {
					throw new RuntimeException(e);
				}		
			});
	}
	
	@Override
	public byte[] nextByteRange() throws IOException {
		final byte[] bytes = new byte[this.readers.size()];
		int channel = 0;
		final Iterator<IByteInput> iterator = this.readers.iterator();
		while (iterator.hasNext()) {
			final IByteInput reader = iterator.next();
			final byte b = reader.nextByte();
			bytes[channel++] = b;
		}
		return bytes;
	}
	
	@Override
	public void close() throws IOException {
		this.readers.forEach(reader -> {
			try {
				reader.close();
			} catch (IOException e) {
				throw new RuntimeException(e);
			}
		});		
	}

}
