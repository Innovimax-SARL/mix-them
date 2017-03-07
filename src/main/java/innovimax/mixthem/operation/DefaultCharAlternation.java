package innovimax.mixthem.operation;

import innovimax.mixthem.MixException;
import innovimax.mixthem.arguments.RuleParam;
import innovimax.mixthem.arguments.ParamValue;

import java.util.Map;

/**
* <p>Alternate two characters.</p>
* @see ICharOperation
* @author Innovimax
* @version 1.0
*/
public class DefaultCharAlternation extends AbstractCharOperation {
	
	private boolean first;
	
	/**
	* Constructor
 	* @param params The list of parameters (maybe empty)
	* @see innovimax.mixthem.arguments.RuleParam
	* @see innovimax.mixthem.arguments.ParamValue
	*/
	public DefaultCharAlternation(Map<RuleParam, ParamValue> params) {
		super(params);
		this.first = true;
	}
	
	/**
 	* Returns the alternated char.
	* @param c1 The first character to alternate
 	* @param c2 The second character to alternate	
 	* @return The alternated char
 	* @throws MixException - If an mixing error occurs
 	*/
	@Override
	public int[] process(int c1, int c2) throws MixException, ProcessException {		
		int[] result = new int[1];
		result[0] = this.first ? c1 : c2;
		this.first = !this.first;
		return result;
	}

}
