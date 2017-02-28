package innovimax.mixthem.operation;

import innovimax.mixthem.MixException;

/**
* This interface provides for zipping two lines
* @author Innovimax
* @version 1.0
*/
public interface IZipLine {	
	/**
 	* Returns the result of zipping two lines.
	* @param line1 The first line to zip
 	* @param line2 The second line to zip	
 	* @return The result of zipping two lines
 	* @throws MixException - If an mixing error occurs
 	*/
	String zip(String line1, String line2) throws MixException;	
}
