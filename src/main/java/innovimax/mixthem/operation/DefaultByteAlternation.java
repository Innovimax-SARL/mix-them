package innovimax.mixthem.operation;

import innovimax.mixthem.MixException;
import innovimax.mixthem.arguments.RuleParam;
import innovimax.mixthem.arguments.ParamValue;

import java.util.Arrays;
import java.util.Map;
import java.util.Random;

/**
* <p>Alternate two or more bytes.</p>
* <p>When a byte is missing then next available byte is selected.</p>
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
	public void process(final byte[] byteRange, final ByteResult result) throws MixException {
		final int[] array = new int[1];
		int channel = this.mode == AltMode.NORMAL ? this.channel : this.random.nextInt(byteRange.length);
		byte b = byteRange[channel];
		//System.out.println("RANGE="+Arrays.toString(byteRange)+" CHANNEL="+channel+" BYTE="+b);
		if (b == -1) {					
			channel = nextChannel(byteRange, channel);
			b = byteRange[channel];
			//System.out.println("NEW_CHANNEL="+channel+" BYTE="+b);
		}
		array[0] = b;
		if (this.mode == AltMode.NORMAL) {
			this.channel = channel + 1;
			if (this.channel == byteRange.length) {
				this.channel = 0;
			}		
		}
		result.setResult(Arrays.stream(array));
	}
	
	private int nextChannel(final byte[] byteRange, final int curChannel) {
		int channel = curChannel+1;
		while (channel != curChannel) {
			if (channel < byteRange.length) {
				final byte b = byteRange[channel];
				if (b != -1) {
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
