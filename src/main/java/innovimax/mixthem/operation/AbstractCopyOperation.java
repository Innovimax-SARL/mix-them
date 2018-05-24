package innovimax.mixthem.operation;

import innovimax.mixthem.MixException;
import innovimax.mixthem.arguments.RuleParam;
import innovimax.mixthem.arguments.ParamValue;
import innovimax.mixthem.io.InputResource;

import java.io.IOException;
import java.io.OutputStream;
import java.util.Arrays;
import java.util.Map;
import java.util.List;
import java.util.stream.IntStream;

/**
* <p>Abstract class for all character operation.</p>
* @see ICopyOperation
* @author Innovimax
* @version 1.0
*/
public abstract class AbstractCopyOperation extends AbstractOperation implements ICopyOperation {
	
	protected final static int BUFFER_SIZE = 1024;	
	private final CopyMode copyMode;

	/**
	* Constructor
	* @param copyMode The copy mode to process
 	* @param params The list of parameters (maybe empty)
	* @see innovimax.mixthem.arguments.RuleParam
	* @see innovimax.mixthem.arguments.ParamValue
	*/
	public AbstractCopyOperation(final CopyMode copyMode, final Map<RuleParam, ParamValue> params) {
		super(params);
		this.copyMode = copyMode;
	}

	@Override
	public void processFiles(final List<InputResource> inputs, final OutputStream output) throws MixException, IOException {		
		switch(copyMode) {
			case UMPTEENTH:
				int index = params.get(RuleParam.FILE_INDEX).asInt() - 1;
				process(inputs.get(index), output);
				break;
			case SELECTION:
			default:
				final IntStream indexes; 
				if (params.containsKey(RuleParam.FILE_LIST)) {
					indexes = Arrays.stream(params.get(RuleParam.FILE_LIST).asIntArray());
				} else {
					indexes = IntStream.range(1, inputs.size()+1);
				}				
                    		indexes.mapToObj(i -> inputs.get(i-1)).forEach(input -> {
					try {
			 			process(input, output);
					} catch (IOException e) {
						throw new RuntimeException(e);
					}
				});				
		}
	}

}
