package innovimax.mixthem.operation;
import innovimax.mixthem.MixException;

interface ILineOperation extends IOperation {
	/**
 	* Returns the result of the operation (maybe null).
	* @param line1 The first line to join (maybe null)
 	* @param line2 The second line to join (maybe null)
 	* @return The result result of the operation (maybe null)
 	* @throws MixException - If an mixing error occurs
	* @throws ProcessException - If process must be ended
 	*/
	String process(String line1, String line2) throws MixException;
}
