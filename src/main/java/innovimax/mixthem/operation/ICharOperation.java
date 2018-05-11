
package innovimax.mixthem.operation;

import innovimax.mixthem.MixException;

interface ICharOperation extends IOperation {
	/**
 	* Processes operation and set new result in the CharResult parameter.
	* @param characterRange The range of characters as int to mix	
	* @param result The previous operation result
 	* @return The result of the operation (maybe null)
 	* @throws MixException - If an mixing error occurs
	* @see innovimax.mixthem.operation.CharResult	
	*/	
	void process(int[] charRange, CharResult result) throws MixException;
}
