package innovimax.mixthem.operation;

import innovimax.mixthem.arguments.ParamValue;
import innovimax.mixthem.arguments.RuleParam;
import innovimax.mixthem.arguments.TokenType;
import innovimax.mixthem.io.DefaultCharReader;
import innovimax.mixthem.io.DefaultCharWriter;
import innovimax.mixthem.io.InputResource;
import innovimax.mixthem.io.ITokenInput;
import innovimax.mixthem.io.ITokenOutput;

import java.io.IOException;
import java.io.OutputStream;
import java.util.Map;
import java.util.Set;

/**
* <p>Copy all file characters.</p>
* @author Innovimax
* @version 1.0
*/
public class DefaultCharCopy extends AbstractCopyOperation {

	/**
	* Constructor
	* @param selection The file index selection (maybe empty)
 	* @param params The list of parameters (maybe empty)
	* @see innovimax.mixthem.arguments.RuleParam
	* @see innovimax.mixthem.arguments.ParamValue
	*/
	public DefaultCharCopy(final Set<Integer> selection, final TokenType tokenType, final Map<RuleParam, ParamValue> params) {
		super(selection, tokenType, params);			
	}
	
	@Override
	public void process(final InputResource input, final OutputStream output) throws IOException {
		final ITokenInput reader = new DefaultCharReader(input, true, BUFFER_SIZE);
		final ITokenOutput writer = new DefaultCharWriter(output, true);
		while (reader.hasMoreTokens()) {
			writer.writeToken(reader.nextToken());
		}
		reader.close();
		writer.close();
	}    

}
