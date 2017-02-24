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
 	* Retrieves the joining type regarding the additional parameters.
 	* @param params The list of additional parameters (from command line)
 	* @return The joining type regarding the additional parameters
 	*/
	JoinType getType(List<String> params);
	/**
 	* Returns the list of column parameters as integer.
 	* @param params The list of additional parameters (from command line)
 	* @return The list of column parameters as integer
 	*/
	List<Integer> getColumns(List<String> params) throws MixException;
	/**
 	* Returns the result of joining two lines regarding the type of join.
	* @param line1 The first line to join
 	* @param line2 The second line to join
	* @param type The type of join required
	* @param columns The list of column indexes depends on type (maybe empty)
 	* @return The result of joining two lines regarding the type of join
 	* @throws MixException - If an mixing error occurs
 	*/
	String join(String line1, String line2, JoinType type, List<Integer> columns) throws MixException;	
}
