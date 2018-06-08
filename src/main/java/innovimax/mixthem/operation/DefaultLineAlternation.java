package innovimax.mixthem.operation;

import innovimax.mixthem.MixException;
import innovimax.mixthem.arguments.ParamValue;
import innovimax.mixthem.arguments.RuleParam;
import innovimax.mixthem.arguments.TokenType;
import innovimax.mixthem.io.ITokenRange;
import innovimax.mixthem.io.Token;

//import java.util.List;
import java.util.Map;
//import java.util.Random;
import java.util.Set;

/**
* <p>Alternates two or more lines.</p>
* <p>When a line is missing then next available line is selected.</p>
* @see ILineOperation
* @author Innovimax
* @version 1.0
*/
public class DefaultLineAlternation extends AbstractTokenAlternation /*AbstractLineOperation*/ {

	/*private final AltMode mode;
	private int channel;
	private final Random random;*/

	/**
	* @param mode The alternate mode to process
	* @param selection The file index selection (maybe empty)
 	* @param params The list of parameters (maybe empty)	
	* @see innovimax.mixthem.operation.RuleParam
	* @see innovimax.mixthem.operation.ParamValue
	*/
	public DefaultLineAlternation(final AltMode mode, final Set<Integer> selection, final TokenType tokenType, final Map<RuleParam, ParamValue> params) {
		super(mode, selection, tokenType, params);
		/*super(selection, tokenType, params);
		this.mode = mode;
		this.channel = 0;
		this.random = new Random(params.getOrDefault(RuleParam.RANDOM_SEED, AltOperation.DEFAULT_RANDOM_SEED.getValue()).asInt());*/
	}	

	@Override
	/*public void process(final List<String> lineRange, final LineResult result) throws MixException {*/
	public void process(final ITokenRange tokenRange, final ITokenResult result) throws MixException {
		/*int channel = this.mode == AltMode.NORMAL ? this.channel : this.random.nextInt(lineRange.size());
		String line = lineRange.get(channel);
		//System.out.println("RANGE="+lineRange.toString());
		//System.out.println("CHANNEL="+channel+" LINE="+line);
		if (line == null) {
			channel = nextChannel(lineRange, channel);
			line = lineRange.get(channel);
			//System.out.println("NEW_CHANNEL="+channel+" LINE="+line);
		}		
		if (this.mode == AltMode.NORMAL) {
			this.channel = channel + 1;
			if (this.channel == lineRange.size()) {
				this.channel = 0;
			}		
		}
		result.setResult(line);*/
		int channel = this.mode == AltMode.NORMAL ? this.channel : this.random.nextInt(tokenRange.size());
		String line = tokenRange.getToken(channel).asString();
		System.out.println("RANGE="+tokenRange.toString());
		System.out.println("CHANNEL="+channel+" LINE="+line);
		if (line == null) {					
			channel = nextChannel(tokenRange, channel);
			line = tokenRange.getToken(channel).asString();
			System.out.println("NEW_CHANNEL="+channel+" LINE="+line);
		}		
		if (this.mode == AltMode.NORMAL) {
			this.channel = channel + 1;
			if (this.channel == tokenRange.size()) {
				this.channel = 0;
			}		
		}
		result.setResult(Token.createLineToken(line));
	}
	
	@Override
	public boolean mixable(final ITokenRange tokenRange) {
		return true;	
	}

	/*private int nextChannel(final List<String> lineRange, final int curChannel) {
		int channel = curChannel+1;
		while (channel != curChannel) {
			if (channel < lineRange.size()) {
				final String line = lineRange.get(channel);
				if (line != null) {
					break;
				}
				channel++;
			} else {
				channel = 0;
			}
		}
		return channel;
	}*/
	
}
