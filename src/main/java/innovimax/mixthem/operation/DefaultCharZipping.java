package innovimax.mixthem.operation;

import innovimax.mixthem.MixException;
import innovimax.mixthem.arguments.RuleParam;
import innovimax.mixthem.arguments.ParamValue;

import java.util.Arrays;
import java.util.Map;
import java.util.Set;

/**
* <p>Zips two or more characters.</p>
* <p>Zipping stops when a character is missing.</p>
* @see CharOperation
* @author Innovimax
* @version 1.0
*/
public class DefaultCharZipping extends AbstractCharOperation {
	
	private final String sep;

	/**
	* Constructor
	* @param selection The file index selection (maybe empty)
 	* @param params The list of parameters (maybe empty)
	* @see innovimax.mixthem.arguments.RuleParam
	* @see innovimax.mixthem.arguments.ParamValue
	*/
	public DefaultCharZipping(final Set<Integer> selection, Map<RuleParam, ParamValue> params) {
		super(selection, params);		
		this.sep = params.getOrDefault(RuleParam.ZIP_SEP, ZipOperation.DEFAULT_ZIP_SEPARATOR.getValue()).asString();
	}
	
	@Override
	public void process(final int[] charRange, final CharResult result) throws MixException {
		//System.out.println("RANGE="+Arrays.toString(charRange));
		boolean zipable = true;		
		for (int i=0; i < charRange.length; i++) {			
			if (charRange[i] == -1) {
				zipable = false;
				break;
			}
		}
		if (zipable) {			
			final int len = charRange.length + (charRange.length - 1) * sep.length();
			final int[] array = new int[len];
			int index = 0;
			for (int i=0; i < charRange.length; i++) {
				array[index++] = charRange[i];				
				if (index < array.length) {
        				for (int j = 0; j < sep.length(); j++) {
						array[index++] = (int) sep.charAt(j);
					}				
				}
			}
			result.setResult(Arrays.stream(array));
		}
	}
	
}
