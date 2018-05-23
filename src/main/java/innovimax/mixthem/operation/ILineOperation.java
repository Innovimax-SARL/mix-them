package innovimax.mixthem.operation;

import innovimax.mixthem.MixException;

import java.util.List;

interface ILineOperation extends IOperation {
	/**
 	* Processes operation and set new result in the LineResult parameter.
	* @param lineRange The range of lines to mix	
	* @param result The previous operation result
 	* @return The result of the operation (maybe null)
 	* @throws MixException - If an mixing error occurs
	* @see innovimax.mixthem.operation.LineResult	
	*/	
	void process(List<String> lineRange, LineResult result) throws MixException;
	/**
 	* Is mixing operation is  possible on line range?
	* @param lineRange The range of lines to mix		
 	* @return True if mixing operation is possible 	
	*/	
	boolean mixable(List<String> lineRange);
}
