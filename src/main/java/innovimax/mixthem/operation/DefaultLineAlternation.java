package innovimax.mixthem.operation;

import innovimax.mixthem.MixException;
import innovimax.mixthem.arguments.ParamValue;
import innovimax.mixthem.arguments.RuleParam;

import java.util.List;
import java.util.Map;
import java.util.Random;

/**
* <p>Alternates two lines.</p>
* @see ILineOperation
* @author Innovimax
* @version 1.0
*/
public class DefaultLineAlternation extends AbstractLineOperation {

	private final AltMode mode;
	private boolean odd;
	private int channel;
	private final Random random;

	/**
	* @param mode The alternate mode to process
 	* @param params The list of parameters (maybe empty)	
	* @see innovimax.mixthem.operation.RuleParam
	* @see innovimax.mixthem.operation.ParamValue
	*/
	public DefaultLineAlternation(final AltMode mode, final Map<RuleParam, ParamValue> params) {
		super(params);
		this.mode = mode;
		this.odd = true;
		this.channel = 0;
		this.random = new Random(params.getOrDefault(RuleParam.RANDOM_SEED, AltOperation.DEFAULT_RANDOM_SEED.getValue()).asInt());
	}	

	@Override
	public void process(final String line1, final String line2, final LineResult result) throws MixException {		
		result.reset();
		if (line1 == null) {
			result.setResult(line2);
		} else if (line2 == null) {
			result.setResult(line1);
		} else {
			String line = null;
			switch (this.mode) {
				case RANDOM:
					line = this.random.nextBoolean() ? line1 : line2;
					break;
				case NORMAL:
				default:
					line = this.odd ? line1 : line2;
					this.odd = !this.odd;
			}
			result.setResult(line);
		}
	}
	
	@Override
	public void process(final List<String> lineRange, final LineResult result) throws MixException {
		result.reset();
		int channel = this.mode == AltMode.NORMAL ? this.channel : this.random.nextInt(lineRange.size());
		String line = lineRange.get(channel);
		System.out.println("RANGE="+lineRange.toString());
		System.out.println("CHANNEL="+channel+" LINE="+line);
		if (line == null) {
			channel = nextChannel(lineRange, channel);
			line = lineRange.get(channel);
			System.out.println("NEW_CHANNEL="+channel+" LINE="+line);
		}		
		if (this.mode == AltMode.NORMAL) {
			this.channel = channel + 1;
			if (this.channel == lineRange.size()) {
				this.channel = 0;
			}		
		}
		result.setResult(line);
	}

	private int nextChannel(final List<String> lineRange, final int curChannel) {
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
	}
	
}
