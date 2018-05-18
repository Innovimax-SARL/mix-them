package innovimax.mixthem.operation;

import innovimax.mixthem.arguments.RuleParam;
import innovimax.mixthem.arguments.ParamValue;
import innovimax.mixthem.io.DefaultByteReader;
import innovimax.mixthem.io.DefaultByteWriter;
import innovimax.mixthem.io.IByteInput;
import innovimax.mixthem.io.IByteOutput;
import innovimax.mixthem.io.InputResource;

import java.io.IOException;
import java.io.OutputStream;
import java.util.Map;

/**
* <p>Copy all file bytes.</p>
* @author Innovimax
* @version 1.0
*/
public class DefaultByteCopy extends AbstractCopyOperation {

	/**
	* Constructor
	* @param mode The copy mode to process
	* @param params The list of parameters (maybe empty)
	* @see innovimax.mixthem.arguments.RuleParam
	* @see innovimax.mixthem.arguments.ParamValue
	*/
	public DefaultByteCopy(final CopyMode mode, final Map<RuleParam, ParamValue> params) {
		super(mode, params);		
	}
	
	@Override
	public void process(InputResource input, OutputStream out) throws IOException {
		byte[] buffer = new byte[BUFFER_SIZE];
		IByteInput reader = new DefaultByteReader(input);
		IByteOutput writer = new DefaultByteWriter(out);
		while (reader.hasByte()) {
			final int len = reader.nextBytes(buffer, BUFFER_SIZE);
			writer.writeBytes(buffer, len);
		}
		reader.close();
		writer.close();
	}    

}
