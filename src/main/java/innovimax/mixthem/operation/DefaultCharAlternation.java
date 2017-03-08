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
	public OperationResult process(int c1, int c2) throws MixException {		
		char[] data = new char[1];
		if (c1 == -1) {
			data[0] = (char) c2;
		} else if (c2 == -1) {
			data[0] = (char) c1;
		} else {					
			data[0] = this.odd ? (char) c1 : (char) c2;
			this.odd = !this.odd;			
		}
		return new OperationResult(data);
	}

}
