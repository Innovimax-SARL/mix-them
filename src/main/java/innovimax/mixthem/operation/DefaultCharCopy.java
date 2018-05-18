package innovimax.mixthem.operation;

import innovimax.mixthem.arguments.Rule;
import innovimax.mixthem.arguments.RuleParam;
import innovimax.mixthem.arguments.ParamValue;
import innovimax.mixthem.io.DefaultCharReader;
import innovimax.mixthem.io.DefaultCharWriter;
import innovimax.mixthem.io.ICharInput;
import innovimax.mixthem.io.ICharOutput;
import innovimax.mixthem.io.InputResource;

import java.io.IOException;
import java.io.OutputStream;
import java.util.Map;

/**
* <p>Copy all file characters.</p>
* @author Innovimax
* @version 1.0
*/
public class DefaultCharCopy extends AbstractCopyOperation {

	private final static int CHAR_BUFFER_SIZE = 1024;	
	
	/**
	* Constructor	
 	* @param params The list of parameters (maybe empty)
	* @see innovimax.mixthem.arguments.RuleParam
	* @see innovimax.mixthem.arguments.ParamValue
	*/
	public DefaultCharCopy(final Rule rule, final Map<RuleParam, ParamValue> params) {
		super(rule, params);			
	}
	
	@Override
	public void process(InputResource input, OutputStream out) throws IOException {
		char[] buffer = new char[CHAR_BUFFER_SIZE];
		ICharInput reader = new DefaultCharReader(input);
		ICharOutput writer = new DefaultCharWriter(out);
		while (reader.hasCharacter()) {
			final int len = reader.nextCharacters(buffer, CHAR_BUFFER_SIZE);
			writer.writeCharacters(buffer, len);
		}
		reader.close();
		writer.close();
	}    

}
