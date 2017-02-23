package innovimax.mixthem.interfaces;

import innovimax.mixthem.exceptions.MixException;
import innovimax.mixthem.join.JoinType;

import java.util.List;

/**
* This interface provides for joining two lines
* @author Innovimax
* @version 1.0
*/
public interface IJoinLine {	
	/**
 	* Returns the result of joining two lines depends on type and additional parameters.
	* @param line1 The first line to join
 	* @param line2 The second line to join
	* @param type The type of join required
	* @param params The list of additional parameters (from command line)
 	* @return The result of joining two lines depends on type and additional parameters
 	* @throws MixException - If an mixing error occurs
 	*/
	String join(String line1, String line2, JoinType type, List<String> params) throws MixException;	
	/**
 	* Retrieves the joining type regarding the additional parameters.
 	* @param params The list of additional parameters (from command line)
 	* @return The joining type regarding the additional parameters
 	*/
	JoinType getType(List<String> params);
}
