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

/**
* <p>Abstract class for all line operation.</p>
* @see ILineOperation
* @author Innovimax
* @version 1.0
*/
public abstract class AbstractLineOperation extends AbstractOperation implements ILineOperation {
	
	/**
	* Constructor
 	* @param params The list of parameters (maybe empty)
	* @see innovimax.mixthem.arguments.RuleParam
	* @see innovimax.mixthem.arguments.ParamValue
	*/
	public AbstractLineOperation(final Map<RuleParam, ParamValue> params) {
		super(params);
	}

	@Override
	public void processFiles(final List<InputResource> inputs, final OutputStream output) throws MixException, IOException {
		final IMultiChannelLineInput reader = new MultiChannelLineReader(inputs);
		final ILineOutput writer = new DefaultLineWriter(output);
		final LineResult result = new LineResult(inputs.size());		
		while (reader.hasLine()) {
			// read next range lines depends on last result indicators
			final List<String> lineRange = reader.nextLineRange(result.getLineReadingRange());
			// set range preserved lines from last result
			for (int i=0; i < lineRange.size(); i++) {
				if (!result.getLineReadingRange().get(i).booleanValue()) {
					lineRange.set(i, result.getRangeLine(i));
				}
			}			
			if (mixable(lineRange)) {
				// process mixing
				process(lineRange, result);
				// write mixing result if has one
				if (result.hasResult()) {
					writer.writeLine(result.getResult());
				}			
			}
			result.reset();
		}
		reader.close();
		writer.close();
    	}
	
	@Override
	public boolean mixable(final List<String> lineRange) {		
		for (int i=0; i < lineRange.size(); i++) {			
			if (lineRange.get(i) == null) {
				return false;				
			}
		}
		return true;	
	}

}
