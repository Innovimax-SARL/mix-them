package innovimax.mixthem.operation;

import innovimax.mixthem.MixException;
import innovimax.mixthem.arguments.RuleParam;
import innovimax.mixthem.arguments.ParamValue;
//import innovimax.mixthem.io.DefaultLineReader;
import innovimax.mixthem.io.DefaultLineWriter;
//import innovimax.mixthem.io.ILineInput;
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
		/*final ILineInput reader1 = new DefaultLineReader(inputs.get(0));
		final ILineInput reader2 = new DefaultLineReader(inputs.get(1));
		final ILineOutput writer = new DefaultLineWriter(output);
		final LineResult result = new LineResult();
		while (reader1.hasLine() || reader2.hasLine()) {
			final String line1 = result.readingFirstFile() ? reader1.nextLine() : result.getFirstLine();
			final String line2 = result.readingSecondFile() ? reader2.nextLine() : result.getSecondLine();
			process(line1, line2, result);
			if (result.hasResult()) {
				writer.writeLine(result.getResult());
			}
        	}
        	reader1.close();
        	reader2.close();
        	writer.close();	*/
		final IMultiChannelLineInput reader = new MultiChannelLineReader(inputs);
		final ILineOutput writer = new DefaultLineWriter(output);
		final LineResult result = new LineResult(inputs.size());
		while (reader.hasLine()) {
			final List<String> lineRange = reader.nextLineRange(result.getLineReadingRange());
			for (int i=0; i < lineRange.size(); i++) {
				if (!result.getLineReadingRange().get(i).booleanValue()) {
					lineRange.set(i, result.getRangeLine(i));
				}
			}
			process(lineRange, result);
			if (result.hasResult()) {
				writer.writeLine(result.getResult());
			}
		}
		reader.close();
		writer.close();
    	}

}
