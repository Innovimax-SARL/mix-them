package innovimax.mixthem.operation;

import innovimax.mixthem.MixException;
import innovimax.mixthem.arguments.RuleParam;
import innovimax.mixthem.arguments.ParamValue;
import innovimax.mixthem.io.DefaultByteReader;
import innovimax.mixthem.io.DefaultByteWriter;
import innovimax.mixthem.io.IInputByte;
import innovimax.mixthem.io.IOutputByte;
import innovimax.mixthem.io.InputResource;

import java.io.IOException;
import java.io.OutputStream;
import java.util.Map;

/**
* <p>Abstract class for all byte operation.</p>
* @see IByteOperation
* @author Innovimax
* @version 1.0
*/
public abstract class AbstractByteOperation extends AbstractOperation implements IByteOperation {

	/**
	* Constructor
 	* @param params The list of parameters (maybe empty)
	* @see innovimax.mixthem.arguments.RuleParam
	* @see innovimax.mixthem.arguments.ParamValue
	*/
	public AbstractByteOperation(final Map<RuleParam, ParamValue> params) {
		super(params);
	}

    @Override
    public void processFiles(final InputResource input1, final InputResource input2, final OutputStream out) throws MixException, IOException {
		final IInputByte reader1 = new DefaultByteReader(input1);
		final IInputByte reader2 = new DefaultByteReader(input2);
		final IOutputByte writer = new DefaultByteWriter(out);
		final ByteResult result = new ByteResult();
		while (reader1.hasByte() || reader2.hasByte()) {
			final int b1 = reader1.nextByte();
			final int b2 = reader2.nextByte();
			process(b1, b2, result);
			if (result.hasResult()) {
				result.getResult().forEach(b -> {
					try {
						writer.writeByte(b);
					} catch (IOException e) {
						throw new RuntimeException(e);
					}
				});
			}
        }
        reader1.close();
        reader2.close();
        writer.close();
    }

}
