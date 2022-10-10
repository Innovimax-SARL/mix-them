
package innovimax.mixthem.operation;

import innovimax.mixthem.io.ITokenRange;

interface ITokenOperation extends IOperation {
	/**
 	* Processes operation and set new result in the ITokenResult parameter.
	* @param tokenRange The range of tokens to mix	
	* @param result The previous operation result
 	* @see innovimax.mixthem.operation.TokenResult
	*/	
	void process(ITokenRange tokenRange, ITokenResult result);
	/**
 	* Is mixing operation is  possible on token range?
	* @param tokenRange The range of tokens to mix		
 	* @return True if mixing operation is possible 	
	*/	
	boolean mixable(ITokenRange tokenRange);
}
