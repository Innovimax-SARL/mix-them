package innovimax.mixthem.io;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import java.util.stream.IntStream;

public class MultiChannelCharReader implements IMultiChannelCharInput {
	
	private final List<ICharInput> readers = new ArrayList<ICharInput>();
	
	/**
	* Constructor
	* @param inputs The list of inputs as InputResource
	* @param selection The input index selection (maybe empty)
	* @see innovimax.mixthem.io.InputResource
	*/
	public MultiChannelCharReader(final List<InputResource> inputs, final Set<Integer> selection) {		
		IntStream.rangeClosed(1, inputs.size())
			.filter(index -> selection.isEmpty() || selection.contains(Integer.valueOf(index)))
			.mapToObj(index -> inputs.get(index-1))
			.forEach(input -> {
				try {
					this.readers.add(new DefaultCharReader(input));
				} catch (IOException e) {
					throw new RuntimeException(e);
				}
			});
	}
	
	@Override
	public boolean hasCharacter() throws IOException {
		return this.readers.stream()
			.anyMatch(reader -> {
				try {
					return reader.hasCharacter();
				} catch (IOException e) {
					throw new RuntimeException(e);
				}		
			});
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
		this.readers.forEach(reader -> {
			try {
				reader.close());
			} catch (IOException e) {
				throw new RuntimeException(e);
			}
		});
	}
	
}
