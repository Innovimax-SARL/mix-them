package innovimax.mixthem.operation;

import innovimax.mixthem.MixException;
import innovimax.mixthem.arguments.RuleParam;
import innovimax.mixthem.arguments.ParamValue;
import innovimax.mixthem.io.DefaultCharWriter;
import innovimax.mixthem.io.ICharOutput;
import innovimax.mixthem.io.IMultiChannelCharInput;
import innovimax.mixthem.io.InputResource;
import innovimax.mixthem.io.MultiChannelCharReader;

import java.io.IOException;
import java.io.OutputStream;
import java.util.List;
import java.util.Map;

/**
* <p>Abstract class for all character operation.</p>
* @see ICharOperation
* @author Innovimax
* @version 1.0
*/
public abstract class AbstractCharOperation extends AbstractOperation implements ICharOperation {

	/**
	* Constructor
 	* @param params The list of parameters (maybe empty)
	* @see innovimax.mixthem.arguments.RuleParam
	* @see innovimax.mixthem.arguments.ParamValue
	*/
	public AbstractCharOperation(final Map<RuleParam, ParamValue> params) {
		super(params);
	}

	@Override
    	public void processFiles(final List<InputResource> inputs, final OutputStream output) throws MixException, IOException {				
		final IMultiChannelCharInput reader = new MultiChannelCharReader(inputs);
		final ICharOutput writer = new DefaultCharWriter(output);
		final CharResult result = new CharResult();
		while (reader.hasCharacter()) {
			result.reset();
			final int[] charRange = reader.nextCharacterRange();
			process(charRange, result);
			if (result.hasResult()) {
				result.getResult().forEach(i -> {
					try {
						writer.writeCharacter((char) i);
					} catch (IOException e) {
						throw new RuntimeException(e);
					}
				});
			}			
		}
		reader.close();
		writer.close();
    	}

}
