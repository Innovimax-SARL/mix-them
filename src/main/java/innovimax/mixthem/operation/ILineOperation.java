package innovimax.mixthem.operation;
import innovimax.mixthem.MixException;

interface ILineOperation extends IOperation {
	/**
 	* Processes operation and set new result in the LineResult parameter.
	* @param line1 The first line to join (maybe null)
 	* @param line2 The second line to join (maybe null)
	* @param result The previous operation result
 	* @return The result result of the operation (maybe null)
 	* @throws MixException - If an mixing error occurs
	* @see innovimax.mixthem.operation.LineResult
 	*/
	void process(String line1, String line2, LineResult result) throws MixException;
}
