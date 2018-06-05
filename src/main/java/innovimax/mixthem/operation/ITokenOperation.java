
package innovimax.mixthem.operation;

import innovimax.mixthem.MixException;
import innovimax.mixthem.io.Token;

import java.util.List;

interface ITokenOperation extends IOperation {
	/**
 	* Processes operation and set new result in the TokenResult parameter.
	* @param tokenRange The range of tokens to mix	
	* @param result The previous operation result
 	* @return The result of the operation (maybe null)
 	* @throws MixException - If an mixing error occurs
	* @see innovimax.mixthem.operation.TokenResult	
	*/	
	void process(List<Token> tokenRange, TokenResult result) throws MixException;
}
