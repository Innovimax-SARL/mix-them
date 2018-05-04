package innovimax.mixthem.io;

import java.util.ArrayList;
import java.util.List;

public class MultiChannelByteReader {
	
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

}
