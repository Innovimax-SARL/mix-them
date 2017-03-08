package innovimax.mixthem.operation;

import static innovimax.mixthem.MixConstants;
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

	/**
 	* Returns the alternation result as {@link OperationResult}
	* @param line1 The first line to alternate
 	* @param line2 The second line to alternate
 	* @return The alternation result as {@link OperationResult}
 	* @throws MixException - If an mixing error occurs
 	*/
	@Override
	public OperationResult process(String line1, String line2) throws MixException {
		String line;
		if (line1 == null) {
			line = line2;
		} else if (line2 == null) {
			line = line1;
		} else {
			switch (this.mode) {
				case _RANDOM:
					line = this.random.nextBoolean() ? line1 : line2;
					break;
				case _NORMAL:
				default:
					line = this.odd ? line1 : line2;
					this.odd = !this.odd;
			}			
		}
		return OperationResult(line.toCharArray());		
	}

}
