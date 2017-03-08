
package innovimax.mixthem.operation;

import innovimax.mixthem.MixException;

interface ICharOperation extends IOperation {
	/**
	* Returns the result of the operation as {@link OperationResult}.
	* @param c1 The first character to zip (maybe -1)
	* @param c2 The second character to zip (maybe -1)
	* @return The result of the operation as {@link OperationResult}
	* @throws MixException - If an mixing error occurs
	* @throws ProcessException - If process must be ended
	*/	
	OperationResult process(int c1, int c2) throws MixException;
}
