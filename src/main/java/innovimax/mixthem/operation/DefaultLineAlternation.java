package innovimax.mixthem.operation;

import innovimax.mixthem.MixException;
import innovimax.mixthem.arguments.ParamValue;
import innovimax.mixthem.arguments.RuleParam;

import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.Set;

/**
* <p>Alternates two or more lines.</p>
* <p>When a line is missing then next available line is selected.</p>
* @see ILineOperation
* @author Innovimax
* @version 1.0
*/
public class DefaultLineAlternation extends AbstractLineOperation {

	private final AltMode mode;
	private int channel;
	private final Random random;

	/**
	* @param mode The alternate mode to process
	* @param selection The file index selection (maybe empty)
 	* @param params The list of parameters (maybe empty)	
	* @see innovimax.mixthem.operation.RuleParam
	* @see innovimax.mixthem.operation.ParamValue
	*/
	public DefaultLineAlternation(final AltMode mode, final Set<Integer> selection, final Map<RuleParam, ParamValue> params) {
		super(selection, params);
		this.mode = mode;
		this.channel = 0;
		this.random = new Random(params.getOrDefault(RuleParam.RANDOM_SEED, AltOperation.DEFAULT_RANDOM_SEED.getValue()).asInt());
	}	

	@Override
	public void process(final List<String> lineRange, final LineResult result) throws MixException {
		int channel = this.mode == AltMode.NORMAL ? this.channel : this.random.nextInt(lineRange.size());
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
		result.setResult(line);
	}
	
	@Override
	public boolean mixable(final List<String> lineRange) {	
		return true;	
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
