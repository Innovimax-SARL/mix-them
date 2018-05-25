package innovimax.mixthem.operation;

import innovimax.mixthem.arguments.RuleParam;
import innovimax.mixthem.arguments.ParamValue;

import java.util.Map;
import java.util.Set;

/**
* <p>Abstract class for all operation.</p>
* @author Innovimax
* @version 1.0
*/
public abstract class AbstractOperation {
	
	protected final Set<Integer> selection;
	protected final Map<RuleParam, ParamValue> params;
	
	/**
	* Constructor
	* @param selection The file index selection (maybe empty)
 	* @param params The list of parameters (maybe empty)
	* @see innovimax.mixthem.arguments.RuleParam
	* @see innovimax.mixthem.arguments.ParamValue
	*/
	public AbstractOperation(final Set<Integer> selection, final Map<RuleParam, ParamValue> params) {
		this.selection = selection;
		this.params = params;
	}

}
