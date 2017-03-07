package innovimax.mixthem.operation;

import innovimax.mixthem.MixException;
import innovimax.mixthem.arguments.ParamValue;
import innovimax.mixthem.arguments.RuleParam;

import java.util.Map;

/**
* <p>Alternates two lines.</p>
* @see ILineOperation
* @author Innovimax
* @version 1.0
*/
public class DefaultLineAlternation extends AbstractLineOperation {
	
	/**
 	* @param params The list of parameters (maybe empty)
	* @see innovimax.mixthem.operation.RuleParam
	* @see innovimax.mixthem.operation.ParamValue
	*/
	public DefaultLineAlternation(Map<RuleParam, ParamValue> params) {
		super(params);
	}	

	/**
 	* Returns the alternated line.
	* @param line1 The first line to alternate
 	* @param line2 The second line to alternate
 	* @return The alternated line
 	* @throws MixException - If an mixing error occurs
 	*/
	@Override
	public String process(String line1, String line2) throws MixException {
    //TODO
		System.out.println(line1);
    System.out.println(line2);
		return null;
	}

}
