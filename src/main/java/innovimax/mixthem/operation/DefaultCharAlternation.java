package innovimax.mixthem.operation;

import innovimax.mixthem.MixException;
import innovimax.mixthem.arguments.RuleParam;
import innovimax.mixthem.arguments.ParamValue;

import java.util.Arrays;
import java.util.Map;
import java.util.stream.IntStream;

/**
* <p>Alternate two characters.</p>
* @see ICharOperation
* @author Innovimax
* @version 1.0
*/
public class DefaultCharAlternation extends AbstractCharOperation {
	
	private boolean odd;
	
	/**
	* Constructor
 	* @param params The list of parameters (maybe empty)
	* @see innovimax.mixthem.arguments.RuleParam
	* @see innovimax.mixthem.arguments.ParamValue
	*/
	public DefaultCharAlternation(Map<RuleParam, ParamValue> params) {
		super(params);
		this.odd = true;
	}
	
	@Override
	public IntStream process(int c1, int c2) throws MixException {		
		int[] result = new int[1];
		if (c1 == -1) {
			result[0] = c2;
		} else if (c2 == -1) {
			result[0] = c1;
		} else {					
			result[0] = this.odd ? c1 : c2;
			this.odd = !this.odd;			
		}
		return Arrays.stream(result);
	}

}
