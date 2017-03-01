package innovimax.mixthem.operation;

import innovimax.mixthem.arguments.ParamValue;
import innovimax.mixthem.arguments.RuleParam;

import innovimax.mixthem.MixException;

import java.util.Map;

/**
* This interface provides for joining two lines
* @author Innovimax
* @version 1.0
*/
public interface IJoinLine {	
	/**
 	* Returns the result of joining two lines regarding the parameters.
	* @param line1 The first line to join
 	* @param line2 The second line to join
	* @param params The list of parameters (maybe empty)
 	* @return The result of joining two lines regarding the parameters
 	* @throws MixException - If an mixing error occurs
	* @see innovimax.mixthem.operation.RuleParam
	* @see innovimax.mixthem.operation.ParamValue
 	*/
	String join(String line1, String line2, Map<RuleParam, ParamValue> params) throws MixException;	
}
