package innovimax.mixthem.operation;

import innovimax.mixthem.utils.StreamUtils;

import innovimax.mixthem.arguments.RuleParam;
import innovimax.mixthem.arguments.ParamValue;
import innovimax.mixthem.arguments.TokenType;
import innovimax.mixthem.io.InputResource;

import java.io.OutputStream;
import java.util.Arrays;
import java.util.Map;
import java.util.List;
import java.util.Set;
import java.util.stream.IntStream;

/**
* Will be deprecated in future version !!!
* <p>Abstract class for all character operation.</p>
* @see ICopyOperation
* @author Innovimax
* @version 1.0
*/
public abstract class AbstractCopyOperation extends AbstractOperation implements ICopyOperation {
	
	final static int BUFFER_SIZE = 1024;

	/**
	* Constructor
	* @param selection The file index selection (maybe empty)
	* @param tokenType The input tokenization type
 	* @param params The list of parameters (maybe empty)
	* @see innovimax.mixthem.arguments.RuleParam
	* @see innovimax.mixthem.arguments.ParamValue
	*/
    AbstractCopyOperation(final Set<Integer> selection, final TokenType tokenType, final Map<RuleParam, ParamValue> params) {
		super(selection, tokenType, params);
	}

	@Override
	public void processFiles(final List<InputResource> inputs, final OutputStream output) {
		final IntStream indexes; 		
		if (this.selection.isEmpty()) {
			indexes = IntStream.range(1, inputs.size()+1);			
		} else {			
			indexes = Arrays.stream(this.selection.stream()
					.mapToInt(Number::intValue)
					.toArray());
		}				
                indexes.mapToObj(i -> inputs.get(i-1))
                	.forEach(StreamUtils.consume(input -> this.process(input, output)));
	}

}
