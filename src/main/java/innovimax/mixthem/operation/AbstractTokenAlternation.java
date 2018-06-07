package innovimax.mixthem.operation;

import innovimax.mixthem.arguments.ParamValue;
import innovimax.mixthem.arguments.RuleParam;
import innovimax.mixthem.arguments.TokenType;
import innovimax.mixthem.io.ITokenRange;

import java.util.Map;
import java.util.Random;
import java.util.Set;

/**
* <p>Abstract class for token alternation.</p>
* @author Innovimax
* @version 1.0
*/
abstract class AbstractTokenAlternation extends AbstractTokenOperation {
	
	protected final AltMode mode;
	protected int channel;
	protected final Random random;
	
	/**
	* Constructor
	* @param mode The alternate mode to process
	* @param selection The file index selection (maybe empty)
 	* @param params The list of parameters (maybe empty)
	* @see innovimax.mixthem.arguments.RuleParam
	* @see innovimax.mixthem.arguments.ParamValue
	*/
	public AbstractTokenAlternation(final AltMode mode, final Set<Integer> selection, final TokenType tokenType, final Map<RuleParam, ParamValue> params) {
		super(selection, tokenType, params);
		this.mode = mode;
		this.channel = 0;
		this.random = new Random(params.getOrDefault(RuleParam.RANDOM_SEED, AltOperation.DEFAULT_RANDOM_SEED.getValue()).asInt());
	}
	
	protected int nextChannel(ITokenRange tokenRange, final int curChannel) {
		int channel = curChannel+1;
		while (channel != curChannel) {
			if (channel < tokenRange.size()) {				
				if (!tokenRange.getToken(channel).isEmpty()) {
					break;				
				}
				channel++;
			} else {
				channel = 0;
			}
		}
		return channel;
	}

}
