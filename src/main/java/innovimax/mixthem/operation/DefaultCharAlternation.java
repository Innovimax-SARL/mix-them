package innovimax.mixthem.operation;

import innovimax.mixthem.MixException;
import innovimax.mixthem.arguments.ParamValue;
import innovimax.mixthem.arguments.RuleParam;
import innovimax.mixthem.arguments.TokenType;
import innovimax.mixthem.io.IToken;
import innovimax.mixthem.io.Token;

import java.util.List;
import java.util.Map;
import java.util.Set;

/**
* <p>Alternate two or more characters.</p>
* <p>When a character is missing then next available character is selected.</p>
* @author Innovimax
* @version 1.0
*/
public class DefaultCharAlternation extends AbstractTokenAlternation {

	/**
	* Constructor
	* @param mode The alternate mode to process
	* @param selection The file index selection (maybe empty)
 	* @param params The list of parameters (maybe empty)
	* @see innovimax.mixthem.arguments.RuleParam
	* @see innovimax.mixthem.arguments.ParamValue
	*/
	public DefaultCharAlternation(final AltMode mode, final Set<Integer> selection, final TokenType tokenType, final Map<RuleParam, ParamValue> params) {
		super(mode, selection, tokenType, params);		
	}
	
	@Override
	public void process(final List<IToken> tokenRange, final TokenResult result) throws MixException {
		int channel = this.mode == AltMode.NORMAL ? this.channel : this.random.nextInt(tokenRange.size());
		int c = tokenRange.get(channel).asCharacter();
		//System.out.println("RANGE="+tokenRange.toString()+" CHANNEL="+channel+" CHAR="+c);
		if (c == -1) {					
			channel = nextChannel(tokenRange, channel);
			c = tokenRange.get(channel).asCharacter();
			//System.out.println("NEW_CHANNEL="+channel+" CHAR="+c);
		}		
		if (this.mode == AltMode.NORMAL) {
			this.channel = channel + 1;
			if (this.channel == tokenRange.size()) {
				this.channel = 0;
			}		
		}
		result.setResult(Token.createCharToken(c));
	}

}
