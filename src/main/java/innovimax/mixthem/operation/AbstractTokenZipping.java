package innovimax.mixthem.operation;

import innovimax.mixthem.arguments.ParamValue;
import innovimax.mixthem.arguments.RuleParam;
import innovimax.mixthem.arguments.TokenType;

import java.util.Map;
import java.util.Set;

/**
* <p>Abstract class for token zipping.</p>
* @author Innovimax
* @version 1.0
*/
abstract class AbstractTokenZipping extends AbstractTokenOperation {
	
	final String sep;
	
	/**
	* Constructor
	* @param selection The file index selection (maybe empty)
 	* @param params The list of parameters (maybe empty)
	* @see innovimax.mixthem.arguments.RuleParam
	* @see innovimax.mixthem.arguments.ParamValue
	*/
    AbstractTokenZipping(final Set<Integer> selection, final TokenType tokenType, final Map<RuleParam, ParamValue> params) {
		super(selection, tokenType, params);
		this.sep = params.getOrDefault(RuleParam.ZIP_SEP, ZipOperation.DEFAULT_ZIP_SEPARATOR.getValue()).asString();
	}

}
