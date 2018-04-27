package innovimax.mixthem.operation;

import innovimax.mixthem.arguments.RuleParam;
import innovimax.mixthem.arguments.ParamValue;

import java.util.Map;

/**
* <p>Abstract class for all operation.</p>
* @see ILineOperation
* @author Innovimax
* @version 1.0
*/
public abstract class AbstractOperation {
	
	protected final Map<RuleParam, ParamValue> params;
	
	/**
	* Constructor
 	* @param params The list of parameters (maybe empty)
	* @see innovimax.mixthem.arguments.RuleParam
	* @see innovimax.mixthem.arguments.ParamValue
	*/
	public AbstractOperation(final Map<RuleParam, ParamValue> params) {
		this.params = params;
	}

}
