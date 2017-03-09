
package innovimax.mixthem.operation;

import innovimax.mixthem.MixException;

import java.util.stream.IntStream;

interface ICharOperation extends IOperation {
	/**
	* Returns the result of the operation as an int stream.
	* @param c1 The first character to zip (maybe -1)
	* @param c2 The second character to zip (maybe -1)
	* @return The result of the operation as an int stream
	* @throws MixException - If an mixing error occurs	
	*/	
	IntStream process(int c1, int c2) throws MixException;
}
