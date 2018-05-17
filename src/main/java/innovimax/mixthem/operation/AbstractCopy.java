package innovimax.mixthem.operation;

import innovimax.mixthem.arguments.RuleParam;
import innovimax.mixthem.arguments.ParamValue;

import java.util.Map;

/**
* <p>Abstract class for all copy operation.</p>
* @see ICopy
* @author Innovimax
* @version 1.0
*/
public abstract class AbstractCopy extends AbstractOperation implements ICopy {

	/**
	* Constructor
 	* @param params The list of parameters (maybe empty)
	* @see innovimax.mixthem.arguments.RuleParam
	* @see innovimax.mixthem.arguments.ParamValue
	*/
	public AbstractCopy(final Map<RuleParam, ParamValue> params) {
		super(params);
	}

}
