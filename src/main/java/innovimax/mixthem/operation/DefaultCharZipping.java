package innovimax.mixthem.operation;

import innovimax.mixthem.MixException;
import innovimax.mixthem.arguments.RuleParam;
import innovimax.mixthem.arguments.ParamValue;

import java.util.Arrays;
import java.util.Map;
import java.util.stream.IntStream;

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
		this.sep = params.containsKey(RuleParam.ZIP_SEP) ? params.get(RuleParam.ZIP_SEP).asString() : ZipOperation.DEFAULT_ZIP_SEPARATOR.toString();		
	}
	
	@Override
	public IntStream process(int c1, int c2) throws MixException {
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
		return Arrays.stream(zip);
	}

}
