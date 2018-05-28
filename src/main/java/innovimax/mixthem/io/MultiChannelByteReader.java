package innovimax.mixthem.io;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class MultiChannelByteReader implements IMultiChannelByteInput {
	
	private final List<IByteInput> readers = new ArrayList<IByteInput>();
	
	/**
	* Constructor
	* @param inputs The list of inputs as InputResource
	* @param selection The input index selection (maybe empty)
	* @see innovimax.mixthem.io.InputResource
	*/
	public MultiChannelByteReader(final List<InputResource> inputs, final Set<Integer> selection) {		
		IntStream.range(0, inputs.size())
			.filter(index -> selection.contains(Integer.valueOf(index)))
			.mapToObj(index -> inputs.get(index))
			.forEach(input -> {
				try {
					this.readers.add(new DefaultByteReader(input));
				} catch (IOException e) {
					throw new RuntimeException(e);
				}
			});
			
	}
	
	@Override
	public boolean hasByte() throws IOException {
		final Iterator<IByteInput> iterator = this.readers.iterator();
		while (iterator.hasNext()) {
			final IByteInput reader = iterator.next();
			if (reader.hasByte()) {
				return true;
			}
		}
		return false;
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
		final Iterator<IByteInput> iterator = this.readers.iterator();
		while (iterator.hasNext()) {
			final IByteInput reader = iterator.next();
			reader.close();
		}		
	}

}
