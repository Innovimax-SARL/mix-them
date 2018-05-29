package innovimax.mixthem.io;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
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
			.map(input -> {
				try {
					return new DefaultByteReader(input);
				} catch (IOException e) {
					throw new RuntimeException(e);
				}})
			.collect(Collectors.toList());
	}
	
	@Override
	public boolean hasByte() throws IOException {
		return this.readers.stream()
			.anyMatch(reader -> {
				try {
					return reader.hasByte();
				} catch (IOException e) {
					throw new RuntimeException(e);
				}});
	}
	
	@Override
	public byte[] nextByteRange() throws IOException {
		return readers.stream()
			.map(reader -> {
				try {
					return Byte.valueOf(reader.nextByte());
				} catch (IOException e) {
					throw new RuntimeException(e);
				}	
			})			
			.collect(ByteArrayOutputStream::new, 
				 (baos, b) -> baos.write(b.byteValue()), 
				 (baos1, baos2) -> baos1.write(baos2.toByteArray(), 0, baos2.size())
			)
			.toByteArray();
	}
	
	@Override
	public void close() throws IOException {
		this.readers
			.forEach(reader -> {
				try {
					reader.close();
				} catch (IOException e) {
					throw new RuntimeException(e);
				}});		
	}

}
