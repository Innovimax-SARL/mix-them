package innovimax.mixthem.operation;

import innovimax.mixthem.MixException;
import innovimax.mixthem.arguments.RuleParam;
import innovimax.mixthem.arguments.ParamValue;
import innovimax.mixthem.io.DefaultByteWriter;
import innovimax.mixthem.io.IByteOutput;
import innovimax.mixthem.io.IMultiChannelByteInput;
import innovimax.mixthem.io.InputResource;
import innovimax.mixthem.io.MultiChannelByteReader;

import java.io.IOException;
import java.io.OutputStream;
import java.util.List;
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
    public void processFiles(final List<InputResource> inputs, final OutputStream output) throws MixException, IOException {
	    final IMultiChannelByteInput reader = new MultiChannelByteReader(inputs);	    
	    final IByteOutput writer = new DefaultByteWriter(output);
	    final ByteResult result = new ByteResult();
	    while (reader.hasByte()) {
		    result.reset();
		    final byte[] byteRange = reader.nextByteRange();		    
		    process(byteRange, result);
		    if (result.hasResult()) {
			    result.getResult().forEach(i -> {
				    try {
					    writer.writeByte((byte) i);
				    } catch (IOException e) {
					    throw new RuntimeException(e);
				    }
			    });
		    }		    
	    }
	    reader.close();	    
	    writer.close();
    }

}
