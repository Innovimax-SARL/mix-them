package innovimax.mixthem.operation;

import innovimax.mixthem.MixException;
import innovimax.mixthem.arguments.RuleParam;
import innovimax.mixthem.arguments.ParamValue;

import java.util.Arrays;
import java.util.Map;
import java.util.Random;

/**
* <p>Alternate two or more bytes.</p>
* @see IByteOperation
* @author Innovimax
* @version 1.0
*/
public class DefaultByteAlternation extends AbstractByteOperation {
	
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
	public DefaultByteAlternation(final AltMode mode, final Map<RuleParam, ParamValue> params) {
		super(params);
		this.mode = mode;
		this.channel = 0;
		this.random = new Random(params.getOrDefault(RuleParam.RANDOM_SEED, AltOperation.DEFAULT_RANDOM_SEED.getValue()).asInt());
	}
	
	@Override
	public void process(byte[] bytes, ByteResult result) throws MixException {
		result.reset();
		final int[] array = new int[1];
		int channel = this.mode == AltMode.NORMAL ? this.channel : this.random.nextInt(bytes.length);
		byte b = bytes[channel];
		System.out.println("BYTES+"+Arrays.toString(bytes)+" CHANNEL="+channel+" BYTE="+b);
		if (b == -1) {					
			channel = nextChannel(bytes, channel);
			b = bytes[channel];
			System.out.println("NEW_CHANNEL="+channel+" BYTE="+b);
		}
		array[0] = b;
		if (this.mode == AltMode.NORMAL) {
			this.channel = channel + 1;
			if (this.channel == bytes.length) {
				this.channel = 0;
			}		
		}
		result.setResult(Arrays.stream(array));
	}
	
	private int nextChannel(final byte[] bytes, final int channel) {
		int c = channel+1;
		while (c != channel) {
			if (c < bytes.length) {
				final byte b = bytes[c];
				if (b != -1) {
					break;
				}
				c++;
			} else {
				c = 0;
			}
		}
		return c;
	}

}
