package innovimax.mixthem.operation;

import innovimax.mixthem.MixException;
import innovimax.mixthem.arguments.RuleParam;
import innovimax.mixthem.arguments.ParamValue;

import java.util.Arrays;
import java.util.Map;

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
	public DefaultCharAlternation(final Map<RuleParam, ParamValue> params) {
		super(params);
		this.odd = true;
	}
	
	@Override
	public void process(final int c1, final int c2, final CharResult result) throws MixException {
		result.reset();
		final int[] array = new int[1];
		if (c1 == -1) {
			array[0] = c2;
		} else if (c2 == -1) {
			array[0] = c1;
		} else {					
			array[0] = this.odd ? c1 : c2;
			this.odd = !this.odd;			
		}
		result.setResult(Arrays.stream(array));
	}

}
