package innovimax.mixthem.operation;

import innovimax.mixthem.MixException;
import innovimax.mixthem.arguments.RuleParam;
import innovimax.mixthem.arguments.ParamValue;

import java.util.Arrays;
import java.util.Map;

/**
* <p>Alternate two bytes.</p>
* @see IByteOperation
* @author Innovimax
* @version 1.0
*/
public class DefaultByteAlternation extends AbstractByteOperation {
	
	private boolean odd;
	
	/**
	* Constructor
 	* @param params The list of parameters (maybe empty)
	* @see innovimax.mixthem.arguments.RuleParam
	* @see innovimax.mixthem.arguments.ParamValue
	*/
	public DefaultByteAlternation(final Map<RuleParam, ParamValue> params) {
		super(params);
		this.odd = true;
	}
	
	@Override
	public void process(final int b1, final int b2, final ByteResult result) throws MixException {
		result.reset();
		final int[] array = new int[1];
		if (b1 == -1) {
			array[0] = b2;
		} else if (b2 == -1) {
			array[0] = b1;
		} else {					
			array[0] = this.odd ? b1 : b2;
			this.odd = !this.odd;			
		}
		result.setResult(Arrays.stream(array));
	}

}
