package innovimax.mixthem.operation;

import innovimax.mixthem.MixException;
import innovimax.mixthem.arguments.ParamValue;
import innovimax.mixthem.arguments.RuleParam;

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
	private final Random random;

	/**
	* @param mode The alternate mode to process
 	* @param params The list of parameters (maybe empty)	
	* @see innovimax.mixthem.operation.RuleParam
	* @see innovimax.mixthem.operation.ParamValue
	*/
	public DefaultLineAlternation(AltMode mode, Map<RuleParam, ParamValue> params) {
		super(params);
		this.mode = mode;
		this.odd = true;
		this.random = new Random(AltOperation.DEFAULT_RANDOM_SEED.toInteger());
		if (this.params.containsKey(RuleParam.RANDOM_SEED)) {
			this.random.setSeed(this.params.get(RuleParam.RANDOM_SEED).asInt());
		}
	}	

	@Override
	public void process(String line1, String line2, LineResult result) throws MixException {		
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

}
