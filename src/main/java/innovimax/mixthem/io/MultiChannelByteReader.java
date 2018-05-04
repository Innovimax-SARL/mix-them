package innovimax.mixthem.io;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class MultiChannelByteReader implements IMultiChannelInputByte {
	
	private final List<IInputByte> readers = new ArrayList<IInputByte>();
	
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
		Iterator<IInputByte> iterator = this.readers.iterator();
		while (iterator.hasNext()) {
			IInputByte reader = iterator.next();
			if (reader.hasByte()) {
				return true;
			}
		}
		return false;
	}
	
	@Override
	public byte[] nextBytes() throws IOException {
		byte[] bytes = new byte[this.readers.size()];
		int channel = 0;
		Iterator<IInputByte> iterator = this.readers.iterator();
		while (iterator.hasNext()) {
			IInputByte reader = iterator.next();
			byte b = reader.nextByte();
			bytes[channel++] = b;
		}
		return bytes;
	}
	
	@Override
	public void close() throws IOException {
		Iterator<IInputByte> iterator = this.readers.iterator();
		while (iterator.hasNext()) {
			IInputByte reader = iterator.next();
			reader.close();
		}		
	}

}
