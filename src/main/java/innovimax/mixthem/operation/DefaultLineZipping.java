package innovimax.mixthem.operation;

import innovimax.mixthem.MixException;
import innovimax.mixthem.arguments.RuleParam;
import innovimax.mixthem.arguments.ParamValue;

import java.util.Arrays;
import java.util.Collections;
import java.util.Iterator;
import java.util.Map;

/**
* <p>Zips two lines on a common field.</p>
* @see ILineOperation
* @author Innovimax
* @version 1.0
*/
public class DefaultLineZipping extends AbstractLineOperation {
	
	private final ZipType type;
	private final String sep;
	
	/**
	* Constructor
	* @param type The type of zip to process
 	* @param params The list of parameters (maybe empty)
	* @see innovimax.mixthem.operation.ZipType
	* @see innovimax.mixthem.arguments.RuleParam
	* @see innovimax.mixthem.arguments.ParamValue
	*/
	public DefaultLineZipping(final ZipType type, final Map<RuleParam, ParamValue> params) {
		super(params);
		this.type = type;
		this.sep = params.getOrDefault(RuleParam.ZIP_SEP, ZipOperation.DEFAULT_ZIP_SEPARATOR.getValue()).asString();	
	}
	
	@Override
	public void process(final String line1, final String line2, final LineResult result) throws MixException {
		result.reset();
		switch (this.type) {
			case LINE:
				result.setResult((line1 != null ? line1 : "") + this.sep + (line2 != null ? line2 : ""));
				break;
			case CELL:					
				final Iterator<String> iterator1 = line1 != null ? Arrays.asList(line1.split(CellOperation.DEFAULT_SPLIT_CELL_REGEX.getValue().asString())).iterator() : Collections.emptyIterator();
				final Iterator<String> iterator2 = line2 != null ? Arrays.asList(line2.split(CellOperation.DEFAULT_SPLIT_CELL_REGEX.getValue().asString())).iterator() : Collections.emptyIterator();				
				final StringBuffer buf = new StringBuffer();
				while (iterator1.hasNext() || iterator2.hasNext()) {						
					final String cell1 = iterator1.hasNext() ? iterator1.next() : "";
					final String cell2 = iterator2.hasNext() ? iterator2.next() : "";					
					if (buf.length() > 0) {						
						buf.append(CellOperation.DEFAULT_CELL_SEPARATOR.getValue().asString());
					}					
					buf.append(cell1 + this.sep + cell2);					
				}
				result.setResult(buf.toString());
		}
	}

}
