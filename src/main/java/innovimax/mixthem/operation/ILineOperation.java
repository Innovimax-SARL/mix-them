package innovimax.mixthem.operation;

import innovimax.mixthem.MixException;

import java.util.List;

interface ILineOperation extends IOperation {
	/**
 	* Processes operation and set new result in the LineResult parameter.
	* @param line1 The first line to mix (maybe null)
 	* @param line2 The second line to mix (maybe null)
	* @param result The previous operation result
 	* @return The result of the operation (maybe null)
 	* @throws MixException - If an mixing error occurs
	* @see innovimax.mixthem.operation.LineResult
 	*/
	void process(String line1, String line2, LineResult result) throws MixException;
	/**
 	* Processes operation and set new result in the LineResult parameter.
	* @param lineRange The range of lines to mix	
	* @param result The previous operation result
 	* @return The result of the operation (maybe null)
 	* @throws MixException - If an mixing error occurs
	* @see innovimax.mixthem.operation.LineResult	
	*/	
	void process(List<String> lineRange, LineResult result) throws MixException;
}
