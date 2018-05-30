package innovimax.mixthem.operation;

import innovimax.mixthem.MixException;
import innovimax.mixthem.arguments.RuleParam;
import innovimax.mixthem.arguments.ParamValue;
import innovimax.mixthem.io.DefaultLineWriter;
import innovimax.mixthem.io.ILineOutput;
import innovimax.mixthem.io.IMultiChannelLineInput;
import innovimax.mixthem.io.InputResource;
import innovimax.mixthem.io.MultiChannelLineReader;

import java.io.IOException;
import java.io.OutputStream;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.IntStream;

/**
* <p>Abstract class for all line operation.</p>
* @see ILineOperation
* @author Innovimax
* @version 1.0
*/
public abstract class AbstractLineOperation extends AbstractOperation implements ILineOperation {
	
	/**
	* Constructor
	* @param selection The file index selection (maybe empty)
 	* @param params The list of parameters (maybe empty)
	* @see innovimax.mixthem.arguments.RuleParam
	* @see innovimax.mixthem.arguments.ParamValue
	*/
	public AbstractLineOperation(final Set<Integer> selection, final Map<RuleParam, ParamValue> params) {
		super(selection, params);
	}

	@Override
	public void processFiles(final List<InputResource> inputs, final OutputStream output) throws MixException, IOException {
		final IMultiChannelLineInput reader = new MultiChannelLineReader(inputs, this.selection);
		final ILineOutput writer = new DefaultLineWriter(output);
		final LineResult result = new LineResult(inputs.size());		
		while (reader.hasLine()) {
			// read next range lines depends on last result indicators
			final List<String> lineRange = reader.nextLineRange(result.getLineReadingRange());
			// set range preserved lines from last result
			IntStream.range(0, lineRange.size())
				.filter(index -> !result.getLineReadingRange().get(index).booleanValue())
				.forEach(index -> lineRange.set(index, result.getRangeLine(index)));
			result.reset();
			if (mixable(lineRange)) {
				// process mixing
				process(lineRange, result);
				// write mixing result if has one
				if (result.hasResult()) {
					writer.writeLine(result.getResult());
				}			
			}			
		}
		reader.close();
		writer.close();
    	}
	
	@Override
	public boolean mixable(final List<String> lineRange) {
		return IntStream.range(0, lineRange.size())
			.allMatch(index -> lineRange.get(index) != null);
	}

}
