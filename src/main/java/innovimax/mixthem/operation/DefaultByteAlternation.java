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
	private boolean odd;
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
		this.odd = true;
		this.channel = 0;
		this.random = new Random(params.getOrDefault(RuleParam.RANDOM_SEED, AltOperation.DEFAULT_RANDOM_SEED.getValue()).asInt());
	}
	
	@Override
	public void process(final byte b1, final byte b2, final ByteResult result) throws MixException {
		result.reset();
		final int[] array = new int[1];
		if (b1 == -1) {
			array[0] = b2;
		} else if (b2 == -1) {
			array[0] = b1;
		} else {
			switch (this.mode) {
				case RANDOM:					
					array[0] = this.random.nextBoolean() ? b1 : b2;
					break;
				case NORMAL:
				default:
					array[0] = this.odd ? b1 : b2;
					this.odd = !this.odd;	
			}			
		}
		result.setResult(Arrays.stream(array));
	}
	
	@Override
	public void process(byte[] bytes, ByteResult result) throws MixException {
		result.reset();
		final int[] array = new int[1];
		switch (this.mode) {
			case RANDOM:
				//TODO: manage more than 2 channels...
				boolean first = this.random.nextBoolean();
				byte b = first ? bytes[0] : bytes[1];
				if (b == -1) {
					array[0] = first ? bytes[1] : bytes[0];
				} else {
					array[0] = b;
				}
				break;
			case NORMAL:
			default:				
				byte bn = bytes[this.channel];
				System.out.println("BYTES="+Arrays.toString(bytes)+" CHANNEL="+channel+" BYTE="+bn);
				if (bn == -1) {
					this.channel = nextChannel(bytes, this.channel);
					bn = bytes[this.channel];
					System.out.println("NEW_CHANNEL="+channel+" BYTE="+bn);
				}
				array[0] = bn;				
				this.channel++;	
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
			} else {
				c = 0;
			}
		}
		return c;
	}

}
