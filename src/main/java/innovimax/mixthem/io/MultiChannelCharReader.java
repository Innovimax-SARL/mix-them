package innovimax.mixthem.io;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class MultiChannelCharReader implements IMultiChannelCharInput {
	
	private final List<ICharInput> readers = new ArrayList<ICharInput>();
	
	public MultiChannelCharReader(final List<InputResource> inputs) {
		inputs.stream().forEach(input -> {
			try {
				this.readers.add(new DefaultCharReader(input));
			} catch (IOException e) {
				throw new RuntimeException(e);
			}
		});
	}
	
	@Override
	public boolean hasCharacter() throws IOException {
		final Iterator<ICharInput> iterator = this.readers.iterator();
		while (iterator.hasNext()) {
			final ICharInput reader = iterator.next();
			if (reader.hasCharacter()) {
				return true;
			}
		}
		return false;
	}
	
	@Override
	public int[] nextCharacterRange() throws IOException {
		final int[] chars = new int[this.readers.size()];
		int channel = 0;
		final Iterator<ICharInput> iterator = this.readers.iterator();
		while (iterator.hasNext()) {
			final ICharInput reader = iterator.next();
			final int c = reader.nextCharacter();
			chars[channel++] = c;
		}
		return chars;
	}

	@Override
	public void close() throws IOException {
		final Iterator<ICharInput> iterator = this.readers.iterator();
		while (iterator.hasNext()) {
			final ICharInput reader = iterator.next();
			reader.close();
		}		
	}
	
}
