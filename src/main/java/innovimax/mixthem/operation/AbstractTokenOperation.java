package innovimax.mixthem.operation;

import innovimax.mixthem.MixException;
import innovimax.mixthem.arguments.ParamValue;
import innovimax.mixthem.arguments.RuleParam;
import innovimax.mixthem.arguments.TokenType;
import innovimax.mixthem.io.InputResource;
import innovimax.mixthem.io.ITokenOutput;
import innovimax.mixthem.io.ITokenRange;
import innovimax.mixthem.io.ITokenRangeInput;
import innovimax.mixthem.io.TokenRangeReader;
import innovimax.mixthem.io.TokenSerializer;
import innovimax.mixthem.utils.StreamUtils;

import java.io.IOException;
import java.io.OutputStream;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.IntStream;

/**
* <p>Abstract class for all token operation.</p>
* @see ITokenOperation
* @author Innovimax
* @version 1.0
*/
abstract class AbstractTokenOperation extends AbstractOperation implements ITokenOperation {
	
	/**
	* Constructor
	* @param selection The file index selection (maybe empty)
	* @param tokenType The input tokenization type
 	* @param params The list of parameters (maybe empty)
	* @see innovimax.mixthem.arguments.RuleParam
	* @see innovimax.mixthem.arguments.ParamValue
	*/
	public AbstractTokenOperation(final Set<Integer> selection, final TokenType tokenType, final Map<RuleParam, ParamValue> params) {
		super(selection, tokenType, params);
	}

	@Override
	public void processFiles(final List<InputResource> inputs, final OutputStream output) throws MixException, IOException {
		final ITokenRangeInput reader = new TokenRangeReader(inputs, this.selection, this.tokenType);
		final ITokenOutput writer = new TokenSerializer(output, this.tokenType);
		final ITokenResult result = new TokenResult(inputs.size());		
		while (reader.hasMoreTokens()) {
			// read next token range (depends on last result indicators)
			final ITokenRange tokenRange = reader.nextTokenRange(result.getTokenStatusRange());
			result.reset();
			if (mixable(tokenRange)) {
				// process mixing
				process(tokenRange, result);
				// write mixing result if has one
				if (result.hasResult()) {
					result.getResult()
						.forEach(StreamUtils.consume(token -> writer.writeToken(token)));
				}			
			}			
		}
		reader.close();
		writer.close();
    	}
	
	@Override
	public boolean mixable(final ITokenRange tokenRange) {
		return IntStream.range(0, tokenRange.size())
			.allMatch(index -> tokenRange.getToken(index) != null);
	}

}
