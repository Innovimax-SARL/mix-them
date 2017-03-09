
package innovimax.mixthem.operation;

import innovimax.mixthem.MixException;

import java.util.stream.Stream;

interface ICharOperation extends IOperation {
	/**
	* Returns the result of the operation as a stream.
	* @param c1 The first character to zip (maybe -1)
	* @param c2 The second character to zip (maybe -1)
	* @return The result of the operation as a stream
	* @throws MixException - If an mixing error occurs	
	*/	
	Stream process(int c1, int c2) throws MixException;
}
