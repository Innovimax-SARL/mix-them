package innovimax.mixthem.operation;

import static innovimax.mixthem.MixConstants.*;
import innovimax.mixthem.MixException;
import innovimax.mixthem.arguments.RuleParam;
import innovimax.mixthem.arguments.ParamValue;

import java.util.Map;

/**
* <p>Zips two characters.</p>
* @see CharOperation
* @author Innovimax
* @version 1.0
*/
public class DefaultCharZipping extends AbstractCharOperation {

	/**
	* Constructor
 	* @param params The list of parameters (maybe empty)
	* @see innovimax.mixthem.arguments.RuleParam
	* @see innovimax.mixthem.arguments.ParamValue
	*/
	public DefaultCharZipping(Map<RuleParam, ParamValue> params) {
		super(params);
	}
	
	@Override
	public OperationResult process(int c1, int c2) throws MixException {
		if (c1 == -1 || c2 == -1) {
			return new OperationResult(ResultType.CAN_STOP);
		}
		String sep = DEFAULT_ZIP_SEPARATOR;
		if (this.params.containsKey(RuleParam._ZIP_SEP)) {
			sep = this.params.get(RuleParam._ZIP_SEP).asString();
		}		
        	char[] data = new char[2 + sep.length()];
        	int index = 0;
        	data[index++] = (char) c1;
        	for (int n = 0; n < sep.length(); n++) {			
			data[index++] = sep.charAt(n);
		}
        	data[index] = (char) c2;
		return new OperationResult(data);
	}

}
