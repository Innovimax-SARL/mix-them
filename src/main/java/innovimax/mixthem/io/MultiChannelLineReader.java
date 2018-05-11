package innovimax.mixthem.io;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class MultiChannelLineReader implements IMultiChannelLineInput {
	
	private final List<ILineInput> readers = new ArrayList<ILineInput>();
	
	public MultiChannelLineReader(final List<InputResource> inputs) {
		inputs.stream().forEach(input -> {
			try {
				this.readers.add(new DefaultLineReader(input));
			} catch (IOException e) {
				throw new RuntimeException(e);
			}
		});
	}
	
	@Override
	public boolean hasLine() throws IOException {
		final Iterator<ILineInput> iterator = this.readers.iterator();
		while (iterator.hasNext()) {
			final ILineInput reader = iterator.next();
			if (reader.hasLine()) {
				return true;
			}
		}
		return false;
	}
	
	@Override
	public List<String> nextLineRange() throws IOException {
		final List<String> lines = new ArrayList<String>();		
		final Iterator<ILineInput> iterator = this.readers.iterator();
		while (iterator.hasNext()) {
			final ILineInput reader = iterator.next();
			final String line = reader.nextLine();
			lines.add(line);
		}
		return lines;
	}

	@Override
	public void close() throws IOException {
		final Iterator<ILineInput> iterator = this.readers.iterator();
		while (iterator.hasNext()) {
			final ILineInput reader = iterator.next();
			reader.close();
		}		
	}
	
}
