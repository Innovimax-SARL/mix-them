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
	
	private final static String DEFAULT_ZIP_SEPARATOR = "";

	/**
	* Constructor
 	* @param params The list of parameters (maybe empty)
	* @see innovimax.mixthem.arguments.RuleParam
	* @see innovimax.mixthem.arguments.ParamValue
	*/
	public DefaultCharAlternation(Map<RuleParam, ParamValue> params) {
		super(params);
	}
	
	/**
 	* Returns the alternated char.
	* @param c1 The first character to alternate
 	* @param c2 The second character to alternate	
 	* @return The alternated char
 	* @throws MixException - If an mixing error occurs
 	*/
	@Override
	public int[] process(int c1, int c2) throws MixException {
    //TODO
    System.out.println(c1);
    System.out.println(c2);
    int[] result = new int[0];
    return result;
	}

}
