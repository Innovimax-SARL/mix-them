
package innovimax.mixthem.operation;

import innovimax.mixthem.MixException;

interface IByteOperation extends IOperation {
	/**
 	* Processes operation and set new result in the ByteResult parameter.
	* @param b1 The first byte to mix (maybe -1)
	* @param b2 The second byte to mix (maybe -1)
	* @param result The previous operation result
 	* @return The result of the operation (maybe null)
 	* @throws MixException - If an mixing error occurs
	* @see innovimax.mixthem.operation.ByteResult	
	*/	
	void process(byte b1, byte b2, ByteResult result) throws MixException;
}
