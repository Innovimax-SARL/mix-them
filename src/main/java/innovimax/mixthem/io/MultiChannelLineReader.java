package innovimax.mixthem.io;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import java.util.stream.IntStream;

public class MultiChannelLineReader implements IMultiChannelLineInput {
	
	private final List<ILineInput> readers = new ArrayList<ILineInput>();
	
	/**
	* Constructor
	* @param inputs The list of inputs as InputResource
	* @param selection The input index selection (maybe empty)
	* @see innovimax.mixthem.io.InputResource
	*/
	public MultiChannelLineReader(final List<InputResource> inputs, final Set<Integer> selection) {
		IntStream.rangeClosed(1, inputs.size())
			.filter(index -> selection.isEmpty() || selection.contains(Integer.valueOf(index)))
			.mapToObj(index -> inputs.get(index-1))
			.forEachOrdered(input -> {
				try {
					this.readers.add(new DefaultLineReader(input));
				} catch (IOException e) {
					throw new RuntimeException(e);
				}
			});
	}
	
	@Override
	public boolean hasLine() throws IOException {
		return this.readers.stream()
			.anyMatch(reader -> {
				try {
					return reader.hasLine();
				} catch (IOException e) {
					throw new RuntimeException(e);
				}		
			});
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
	public List<String> nextLineRange(List<Boolean> readingRange) throws IOException {
		final List<String> lines = new ArrayList<String>();
		int index = 0;
		final Iterator<ILineInput> iterator = this.readers.iterator();
		while (iterator.hasNext()) {
			final ILineInput reader = iterator.next();
			if (readingRange.get(index).booleanValue()) {				
				lines.add(reader.nextLine());
			} else {
				lines.add(null);
			}
			index++;
		}
		return lines;		
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
