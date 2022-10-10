package innovimax.mixthem.operation;

import innovimax.mixthem.arguments.ParamValue;
import innovimax.mixthem.arguments.RuleParam;
import innovimax.mixthem.arguments.TokenType;
import innovimax.mixthem.io.ITokenRange;
import innovimax.mixthem.io.Token;

import java.util.Map;
import java.util.Set;

/**
* <p>Alternates two or more lines.</p>
* <p>When a line is missing then next available line is selected.</p>
* @see ITokenOperation
* @author Innovimax
* @version 1.0
*/
public class DefaultLineAlternation extends AbstractTokenAlternation  {

	/**
	* @param mode The alternate mode to process
	* @param selection The file index selection (maybe empty)
 	* @param params The list of parameters (maybe empty)	
	* @see innovimax.mixthem.arguments.RuleParam
	* @see innovimax.mixthem.arguments.ParamValue
	*/
	public DefaultLineAlternation(final AltMode mode, final Set<Integer> selection, final TokenType tokenType, final Map<RuleParam, ParamValue> params) {
		super(mode, selection, tokenType, params);
	}	

	@Override
	public void process(final ITokenRange tokenRange, final ITokenResult result) {
		int channel = this.mode == AltMode.NORMAL ? this.channel : this.random.nextInt(tokenRange.size());
		String line = tokenRange.getToken(channel).asString();
		//System.out.println("RANGE="+tokenRange.toString());
		//System.out.println("CHANNEL="+channel+" LINE="+line);
		if (line == null) {					
			channel = nextChannel(tokenRange, channel);
			line = tokenRange.getToken(channel).asString();
			//System.out.println("NEW_CHANNEL="+channel+" LINE="+line);
		}		
		if (this.mode == AltMode.NORMAL) {
			this.channel = channel + 1;
			if (this.channel == tokenRange.size()) {
				this.channel = 0;
			}		
		}
		result.setResult(Token.createLineToken(line));
	}

}
