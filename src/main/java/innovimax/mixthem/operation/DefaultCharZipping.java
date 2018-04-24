package innovimax.mixthem.operation;

import innovimax.mixthem.MixException;
import innovimax.mixthem.arguments.RuleParam;
import innovimax.mixthem.arguments.ParamValue;

import java.util.Arrays;
import java.util.Map;

/**
* <p>Zips two characters.</p>
* @see CharOperation
* @author Innovimax
* @version 1.0
*/
public class DefaultCharZipping extends AbstractCharOperation {
	
	private final String sep;

	/**
	* Constructor
 	* @param params The list of parameters (maybe empty)
	* @see innovimax.mixthem.arguments.RuleParam
	* @see innovimax.mixthem.arguments.ParamValue
	*/
	public DefaultCharZipping(Map<RuleParam, ParamValue> params) {
		super(params);		
		this.sep = params.getOrDefault(RuleParam.ZIP_SEP, ZipOperation.DEFAULT_ZIP_SEPARATOR.getValue()).asString();
	}
	
	@Override
	public void process(int c1, int c2, CharResult result) throws MixException {
		result.reset();
		int len = (c1 != -1 ? 1 : 0) + sep.length() + (c2 != -1 ? 1 : 0);
        	int[] array = new int[len];
		if (c1 != -1) { 
			array[0] = c1; 
		}		
        	for (int i = 0; i < sep.length(); i++) {			
			array[i + 1] = (int) sep.charAt(i);
		}		
		if (c2 != -1) { 
			array[array.length - 1] = c2; 
		}        	
		result.setResult(Arrays.stream(array));
	}

}
