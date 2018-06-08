package innovimax.mixthem.operation;

import innovimax.mixthem.MixException;
import innovimax.mixthem.arguments.ParamValue;
import innovimax.mixthem.arguments.RuleParam;
import innovimax.mixthem.arguments.TokenType;
import innovimax.mixthem.io.ITokenRange;
import innovimax.mixthem.io.Token;

import java.util.Map;
import java.util.Set;

/**
* <p>Alternate two or more bytes.</p>
* <p>When a byte is missing then next available byte is selected.</p>
* @author Innovimax
* @version 1.0
*/
public class DefaultByteAlternation extends AbstractTokenAlternation {
	
	/**
	* Constructor
	* @param mode The alternate mode to process
	* @param selection The file index selection (maybe empty)
 	* @param params The list of parameters (maybe empty)
	* @see innovimax.mixthem.arguments.RuleParam
	* @see innovimax.mixthem.arguments.ParamValue
	*/
	public DefaultByteAlternation(final AltMode mode, final Set<Integer> selection, final TokenType tokenType, final Map<RuleParam, ParamValue> params) {
		super(mode, selection, tokenType, params);		
	}

	@Override
	public void process(ITokenRange tokenRange, ITokenResult result) throws MixException {		
		int channel = this.mode == AltMode.NORMAL ? this.channel : this.random.nextInt(tokenRange.size());
		byte b = tokenRange.getToken(channel).asByte();
		//System.out.println("RANGE="+tokenRange.toString()+" CHANNEL="+channel+" BYTE="+b);
		if (b == -1) {					
			channel = nextChannel(tokenRange, channel);
			b = tokenRange.getToken(channel).asByte();
			//System.out.println("NEW_CHANNEL="+channel+" BYTE="+b);
		}		
		if (this.mode == AltMode.NORMAL) {
			this.channel = channel + 1;
			if (this.channel == tokenRange.size()) {
				this.channel = 0;
			}		
		}
		result.setResult(Token.createByteToken(b));
	}

}
