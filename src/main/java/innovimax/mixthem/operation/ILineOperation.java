package innovimax.mixthem.operation;
import innovimax.mixthem.MixException;

interface ILineOperation extends IOperation {
	/**
 	* Processes operation and set new result in the LineResult parameter.
	* @param result The previous operation result (with new read lines)
 	* @return The result result of the operation (maybe null)
 	* @throws MixException - If an mixing error occurs
	* @see innovimax.mixthem.operation.LineResult
 	*/
	void process(LineResult result) throws MixException;
}
