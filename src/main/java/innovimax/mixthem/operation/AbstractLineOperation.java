package innovimax.mixthem.operation;

import innovimax.mixthem.MixException;
import innovimax.mixthem.arguments.RuleParam;
import innovimax.mixthem.arguments.ParamValue;
import innovimax.mixthem.io.DefaultLineReader;
import innovimax.mixthem.io.DefaultLineWriter;
import innovimax.mixthem.io.IInputLine;
import innovimax.mixthem.io.IOutputLine;

import java.io.File;
import java.io.InputStream;
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
		processFiles(new DefaultLineReader(file1), new DefaultLineReader(file2), new DefaultLineWriter(out));
    	}
	
	@Override
	public void processFiles(InputStream input1, InputStream input2, OutputStream out) throws MixException, IOException {
		processFiles(new DefaultLineReader(input1), new DefaultLineReader(input2), new DefaultLineWriter(out));
	}
	
	private void processFiles(IInputLine reader1, IInputLine reader2, IOutputLine writer) throws MixException, IOException {
		LineResult result = new LineResult();
		while (reader1.hasLine() || reader2.hasLine()) {
			final String line1 = result.readingFirstFile() ? reader1.nextLine() : result.getFirstLine();
			final String line2 = result.readingSecondFile() ? reader2.nextLine() : result.getSecondLine();
			process(line1, line2, result);
			if (result.hasResult()) {
				writer.writeLine(result.getResult());
			}
        	}
        	reader1.close();
        	reader2.close();
        	writer.close();				
    	}

}
