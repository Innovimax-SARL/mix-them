package innovimax.mixthem.operation;

import innovimax.mixthem.MixException;
import innovimax.mixthem.arguments.RuleParam;
import innovimax.mixthem.arguments.ParamValue;
import innovimax.mixthem.io.InputResource;
import innovimax.mixthem.io.Token;

import java.io.IOException;
import java.io.OutputStream;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
* <p>Abstract class for all token operation.</p>
* @see ITokenOperation
* @author Innovimax
* @version 1.0
*/
public abstract class AbstractTokenOperation extends AbstractOperation implements ITokenOperation {
	
	/**
	* Constructor
	* @param selection The file index selection (maybe empty)
 	* @param params The list of parameters (maybe empty)
	* @see innovimax.mixthem.arguments.RuleParam
	* @see innovimax.mixthem.arguments.ParamValue
	*/
	public AbstractTokenOperation(final Set<Integer> selection, final Map<RuleParam, ParamValue> params) {
		super(selection, params);
	}

	@Override
	public void processFiles(final List<InputResource> inputs, final OutputStream output) throws MixException, IOException {
		//TODO
    	}
	
	@Override
	public boolean mixable(final List<Token> tokenRange) {
		//TODO
		return false;
	}

}
