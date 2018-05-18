package innovimax.mixthem.operation;

import innovimax.mixthem.MixException;
import innovimax.mixthem.arguments.Rule;
import innovimax.mixthem.arguments.RuleParam;
import innovimax.mixthem.arguments.ParamValue;
import innovimax.mixthem.io.InputResource;

import java.io.IOException;
import java.io.OutputStream;
import java.util.Map;
import java.util.List;

/**
* <p>Abstract class for all character operation.</p>
* @see ICopyOperation
* @author Innovimax
* @version 1.0
*/
public abstract class AbstractCopyOperation extends AbstractOperation implements ICopyOperation {
	
	final private Rule rule;

	/**
	* Constructor
 	* @param params The list of parameters (maybe empty)
	* @see innovimax.mixthem.arguments.RuleParam
	* @see innovimax.mixthem.arguments.ParamValue
	*/
	public AbstractCopyOperation(final Rule rule, final Map<RuleParam, ParamValue> params) {
		super(params);
		this.rule = rule;
	}

	@Override
	public void processFiles(final List<InputResource> inputs, final OutputStream output) throws MixException, IOException {		
		//TODO
		System.out.println("RULE="+rule);
		inputs.stream().forEach(input -> {
			try {
		 		process(input, output);
			} catch (IOException e) {
				throw new RuntimeException(e);
			}
		});
	}

}
