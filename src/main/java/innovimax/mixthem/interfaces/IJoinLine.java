package innovimax.mixthem.interfaces;

import innovimax.mixthem.exceptions.MixException;

/**
* This interface provides for joining two lines
* @author Innovimax
* @version 1.0
*/
public interface IJoinLine {
	/**
 	* Joins two lines on a common field.
 	* @param line1 The first line to be joined
 	* @param line2 The second line to be joined
 	* @return The result of joining lines, or null if no join possible
 	* @throws MixException - If an joining error occurs
 	*/	
	String join(String line1, String line2) throws MixException;
}
