
package innovimax.mixthem.operation;

import innovimax.mixthem.MixException;

interface IByteOperation extends IOperation {
	/**
 	* Processes operation and set new result in the ByteResult parameter.
	* @param bytes The array of bytes to mix	
	* @param result The previous operation result
 	* @return The result of the operation (maybe null)
 	* @throws MixException - If an mixing error occurs
	* @see innovimax.mixthem.operation.ByteResult	
	*/	
	void process(byte[] bytes, ByteResult result) throws MixException;
}
