package innovimax.mixthem.operation;

import innovimax.mixthem.MixException;
import innovimax.mixthem.arguments.RuleParam;
import innovimax.mixthem.arguments.ParamValue;
import innovimax.mixthem.io.DefaultLineReader;
import innovimax.mixthem.io.DefaultLineWriter;
import innovimax.mixthem.io.IInputLine;
import innovimax.mixthem.io.IOutputLine;

import java.io.File;
import java.io.IOException;
import java.io.OutputStream;
import java.util.Map;

/**
* <p>Abstract class for all line operation.</p>
* @see ILineOperation
* @author Innovimax
* @version 1.0
*/
public abstract class AbstractLineOperation extends AbstractOperation implements ILineOperation {
	
	/**
	* Constructor
 	* @param params The list of parameters (maybe empty)
	* @see innovimax.mixthem.arguments.RuleParam
	* @see innovimax.mixthem.arguments.ParamValue
	*/
	public AbstractLineOperation(Map<RuleParam, ParamValue> params) {
		super(params);
	}

	@Override
	public void processFiles(File file1, File file2, OutputStream out) throws MixException, IOException {
		IInputLine reader1 = new DefaultLineReader(file1);
		IInputLine reader2 = new DefaultLineReader(file2);
		IOutputLine writer = new DefaultLineWriter(out);
		while (reader1.hasLine() || reader2.hasLine()) {
			final String line1 = reader1.nextLine();
			final String line2 = reader2.nextLine();
			String result = process(line1, line2);
			if (result != null) {
				writer.writeLine(result);
			}
        	}
        	reader1.close();
        	reader2.close();
        	writer.close();				
    	}

}
