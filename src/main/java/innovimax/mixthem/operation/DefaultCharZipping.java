package innovimax.mixthem.operation;

import innovimax.mixthem.MixException;
import innovimax.mixthem.arguments.RuleParam;
import innovimax.mixthem.arguments.ParamValue;

/**
* <p>Zips two characters.</p>
* @see CharOperation
* @author Innovimax
* @version 1.0
*/
public class DefaultCharZipping extends AbstractOperation {
	
	private final static String DEFAULT_ZIP_SEPARATOR = "";


	/**
	* Constructor
 	* @param params The list of parameters (maybe empty)
	* @see innovimax.mixthem.arguments.RuleParam
	* @see innovimax.mixthem.arguments.ParamValue
	*/
	public DefaultCharZipping(Map<RuleParam, ParamValue> params) {
		super(params);
	}
	
	/**
 	* Returns the result of zipping two lines.
	* @param c1 The first character to zip
 	* @param c2 The second character to zip	
 	* @return The result of zipping two characters
 	* @throws MixException - If an mixing error occurs
 	*/
	@Override
	public int[] process(int c1, int c2) throws MixException {
		int[] zip = null;
		String sep = DEFAULT_ZIP_SEPARATOR;
		if (this.params.containsKey(RuleParam._ZIP_SEP)) {
			sep = this.params.get(RuleParam._ZIP_SEP).asString();
		}		
		if (c1 != -1 && c2 != -1) {
        zip = new int[2 + sep.length()];
        int index = 0;
        zip[index++] = c1;
        for (int n = 0; n < sep.length(); n++) {
          int cn = sep.codePointAt(n);
          zip[index++] = cn;
        }
        zip[index] = c2;
			}
		}
		return zip;
	}

}
