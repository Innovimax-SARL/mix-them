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
	public int[] process(int c1, int c2) throws MixException {
		String sep = DEFAULT_ZIP_SEPARATOR;
		if (this.params.containsKey(RuleParam._ZIP_SEP)) {
			sep = this.params.get(RuleParam._ZIP_SEP).asString();
		}
		int len = (c1 != -1 ? 1 : 0) + sep.length() + (c2 != -1 ? 1 : 0);
        	int[] zip = new int[len];
		if (c1 != -1) { 
			zip[0] = c1; 
		}
        	for (int i = 0; i < sep.length(); i++) {			
			zip[i + 1] = (int) sep.charAt(i);
		}		
		if (c2 != -1) { 
			zip[zip.length - 1] = c2; 
		}        	
		return zip;
	}

}
