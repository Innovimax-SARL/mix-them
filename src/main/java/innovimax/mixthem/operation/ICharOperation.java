
package innovimax.mixthem.operation;

import innovimax.mixthem.MixException;

interface ICharOperation extends IOperation {
	/**
 	* Processes operation and set new result in the CharResult parameter.
	* @param c1 The first character to mix as an int (maybe -1)
	* @param c2 The second character to mix as an int (maybe -1)
	* @param result The previous operation result
 	* @return The result of the operation (maybe null)
 	* @throws MixException - If an mixing error occurs
	* @see innovimax.mixthem.operation.CharResult	
	*/	
	void process(int c1, int c2, CharResult result) throws MixException;
	/**
 	* Processes operation and set new result in the CharResult parameter.
	* @param characterRange The range of characters as int to mix	
	* @param result The previous operation result
 	* @return The result of the operation (maybe null)
 	* @throws MixException - If an mixing error occurs
	* @see innovimax.mixthem.operation.CharResult	
	*/	
	void process(int[] characterRange, CharResult result) throws MixException;
}
