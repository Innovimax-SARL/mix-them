package innovimax.mixthem.io;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class MultiChannelByteReader implements IMultiChannelByteInput {
	
	private final List<IByteInput> readers = new ArrayList<IByteInput>();
	
	public MultiChannelByteReader(final List<InputResource> inputs) {
		inputs.stream().forEach(input -> {
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
