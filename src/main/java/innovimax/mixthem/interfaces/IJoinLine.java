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
 	* Joins two lines on the default first field.
 	* @param line1 The first line to be joined
 	* @param line2 The second line to be joined
 	* @return The result of joining lines, or null if no join possible
 	* @throws MixException - If an joining error occurs
 	*/	
	String join(String line1, String line2) throws MixException;
	/**
 	* Joins two lines on a common field
 	* @param line1 The first line to be joined
 	* @param line2 The second line to be joined
	* @param index The index of the common field
 	* @return The result of joining lines, or null if no join possible
 	* @throws MixException - If an joining error occurs
 	*/	
	String join(String line1, String line2, int index) throws MixException;
	/**
 	* Joins two lines on a common field that has a different index.
 	* @param line1 The first line to be joined
 	* @param line2 The second line to be joined
	* @param index1 The index of the common field in the first line
	* @param index2 The index of the common field in the second line
 	* @return The result of joining lines, or null if no join possible
 	* @throws MixException - If an joining error occurs
 	*/	
	String join(String line1, String line2, int index1, int index2) throws MixException;
	/**
 	* Joins two lines on a common field regarding the type.
 	* @param line1 The first line to be joined
 	* @param line2 The second line to be joined
	* @param type The type of join asked for mixing
	* @param params The additional parameter values (maybe empty)
 	* @return The result of joining lines, or null if no join possible
 	* @throws MixException - If an joining error occurs
 	*/	
	String join(String line1, String line2, JoinType type, List<String> params) throws MixException;	
}
