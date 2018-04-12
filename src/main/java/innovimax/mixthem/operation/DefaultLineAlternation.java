package innovimax.mixthem.operation;

import static innovimax.mixthem.MixConstants.*;
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
		this.random = new Random(DEFAULT_RANDOM_SEED);
		if (this.params.containsKey(RuleParam._RANDOM_SEED)) {
			this.random.setSeed(this.params.get(RuleParam._RANDOM_SEED).asInt());
		}
	}	

	@Override
	public void process(LineResult result) throws MixException {		
		result.resetTypes();
		if (result.getFirstLine() == null) {
			result.setResult(result.getLastLine());
		} else if (result.getLastLine() == null) {
			result.setResult(result.getFirstLine());
		} else {
			String line = null;
			switch (this.mode) {
				case _RANDOM:
					line = this.random.nextBoolean() ? result.getFirstLine() : result.getLastLine();
					break;
				case _NORMAL:
				default:
					line = this.odd ? result.getFirstLine() : result.getLastLine();
					this.odd = !this.odd;
			}
			result.setResult(line);
		}
		result.exploreBoth();
	}

}
