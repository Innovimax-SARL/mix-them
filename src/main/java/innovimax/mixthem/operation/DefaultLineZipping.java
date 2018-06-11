package innovimax.mixthem.operation;

import innovimax.mixthem.MixException;
import innovimax.mixthem.arguments.ParamValue;
import innovimax.mixthem.arguments.RuleParam;
import innovimax.mixthem.arguments.TokenType;
import innovimax.mixthem.io.ITokenRange;
import innovimax.mixthem.io.Token;

import java.util.Map;
import java.util.Set;
import java.util.stream.IntStream;
import java.util.stream.Stream;

/**
* <p>Zips two or more lines.</p>
* <p>Zipping stops when a line is missing.</p>
* @author Innovimax
* @version 1.0
*/
public class DefaultLineZipping extends AbstractTokenZipping {
	
	/**
	* Constructor
	* @param selection The file index selection (maybe empty)
 	* @param params The list of parameters (maybe empty)
	* @see innovimax.mixthem.arguments.RuleParam
	* @see innovimax.mixthem.arguments.ParamValue
	*/
	public DefaultLineZipping(final Set<Integer> selection, final TokenType tokenType, final Map<RuleParam, ParamValue> params) {
		super(selection, tokenType, params);		
	}
	
	@Override	
	public void process(final ITokenRange tokenRange, final ITokenResult result) throws MixException {
		//System.out.println("RANGE="+tokenRange.toString())
		final String zip = IntStream.range(0, tokenRange.size())
				.mapToObj(channel -> channel > 0 ? 
					Stream.of(this.sep, tokenRange.getToken(channel).asString()) :
					Stream.of(tokenRange.getToken(channel).asString()))
				.flatMap(stream -> stream)
				.collect(StringBuilder::new, StringBuilder::append, StringBuilder::append)
            			.toString();
		result.setResult(Token.createLineToken(zip));
	}


}
