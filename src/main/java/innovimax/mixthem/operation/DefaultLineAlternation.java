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

	private final static int DEFAULT_RANDOM_SEED = 1789;
	
	private final AltMode mode;
	private boolean first;
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
		this.first = true;
		this.random = new Random(DEFAULT_RANDOM_SEED);
		if (this.params.containsKey(RuleParam._RANDOM_SEED)) {
			this.random.setSeed(this.params.get(RuleParam._RANDOM_SEED).asInt());
		}
	}	

	/**
 	* Returns the alternated line or null if no result.
	* @param line1 The first line to alternate
 	* @param line2 The second line to alternate
 	* @return The alternated line or null if no result
 	* @throws MixException - If an mixing error occurs
 	*/
	@Override
	public String process(String line1, String line2) throws MixException {		
		if (line1 == null) {
			return line2;
		} else if (line2 == null) {
			return line1;
		} else {
			String line = null;
			switch (this.mode) {
				case _RANDOM:
					line = this.random.nextBoolean() ? line1 : line2;
					break;
				case _NORMAL:
				default:
					line = this.first ? line1 : line2;
					this.first = !this.first;
			}
			return line;
		}
	}

}
