package innovimax.mixthem.operation;

import innovimax.mixthem.MixException;
import innovimax.mixthem.arguments.RuleParam;
import innovimax.mixthem.arguments.ParamValue;

import java.util.Arrays;
import java.util.Map;
import java.util.Random;

/**
* <p>Alternate two or more characters.</p>
* <p>When a character is missing then next available character is selected.</p>
* @see ICharOperation
* @author Innovimax
* @version 1.0
*/
public class DefaultCharAlternation extends AbstractCharOperation {
	
	private final AltMode mode;
	private int channel;
	private final Random random;
	
	/**
	* Constructor
	* @param mode The alternate mode to process
 	* @param params The list of parameters (maybe empty)
	* @see innovimax.mixthem.arguments.RuleParam
	* @see innovimax.mixthem.arguments.ParamValue
	*/
	public DefaultCharAlternation(final AltMode mode, final Map<RuleParam, ParamValue> params) {
		super(params);
		this.mode = mode;
		this.channel = 0;
		this.random = new Random(params.getOrDefault(RuleParam.RANDOM_SEED, AltOperation.DEFAULT_RANDOM_SEED.getValue()).asInt());
	}
	
	@Override
	public void process(final int[] charRange, final CharResult result) throws MixException {
		final int[] array = new int[1];
		int channel = this.mode == AltMode.NORMAL ? this.channel : this.random.nextInt(charRange.length);
		int c = charRange[channel];
		//System.out.println("RANGE="+Arrays.toString(charRange)+" CHANNEL="+channel+" CHAR="+c);
		if (c == -1) {					
			channel = nextChannel(charRange, channel);
			c = charRange[channel];
			//System.out.println("NEW_CHANNEL="+channel+" CHAR="+c);
		}
		array[0] = c;
		if (this.mode == AltMode.NORMAL) {
			this.channel = channel + 1;
			if (this.channel == charRange.length) {
				this.channel = 0;
			}		
		}
		result.setResult(Arrays.stream(array));
	}
	
	private int nextChannel(final int[] charRange, final int curChannel) {
		int channel = curChannel+1;
		while (channel != curChannel) {
			if (channel < charRange.length) {
				final int c = charRange[channel];
				if (c != -1) {
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
