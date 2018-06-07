package innovimax.mixthem.operation;

import innovimax.mixthem.arguments.ParamValue;
import innovimax.mixthem.arguments.RuleParam;
import innovimax.mixthem.arguments.TokenType;

import java.util.Map;
import java.util.Set;

/**
* <p>Abstract class for all operation.</p>
* @author Innovimax
* @version 1.0
*/
abstract class AbstractOperation {
	
	protected final Set<Integer> selection;
	protected final TokenType tokenType;
	protected final Map<RuleParam, ParamValue> params;
	
	/**
	* Constructor
	* @param selection The file index selection (maybe empty)
	* @param tokenType The input tokenization type
 	* @param params The list of parameters (maybe empty)
	* @see innovimax.mixthem.arguments.RuleParam
	* @see innovimax.mixthem.arguments.ParamValue
	*/
	public AbstractOperation(final Set<Integer> selection, final TokenType tokenType, final Map<RuleParam, ParamValue> params) {
		this.selection = selection;
		this.tokenType = tokenType;
		this.params = params;
	}

}
