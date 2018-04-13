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
	public DefaultLineZipping(ZipType type, Map<RuleParam, ParamValue> params) {
		super(params);
		this.type = type;
		this.sep = params.containsKey(RuleParam.ZIP_SEP) ? params.get(RuleParam.ZIP_SEP).asString() : ZipOperation.DEFAULT_ZIP_SEPARATOR.toString();	
	}
	
	@Override
	public void process(String line1, String line2, LineResult result) throws MixException {
		result.reset();
		switch (this.type) {
			case LINE:
				result.setResult((line1 != null ? line1 : "") + this.sep + (line2 != null ? line2 : ""));
				break;
			case CELL:					
				Iterator<String> iterator1 = line1 != null ? Arrays.asList(line1.split(CellOperation.DEFAULT_SPLIT_CELL_REGEX.toString())).iterator() : Collections.emptyIterator();
				Iterator<String> iterator2 = line2 != null ? Arrays.asList(line2.split(CellOperation.DEFAULT_SPLIT_CELL_REGEX.toString())).iterator() : Collections.emptyIterator();				
				StringBuffer buf = new StringBuffer();
				while (iterator1.hasNext() || iterator2.hasNext()) {						
					String cell1 = iterator1.hasNext() ? iterator1.next() : "";
					String cell2 = iterator2.hasNext() ? iterator2.next() : "";					
					if (buf.length() > 0) {						
						buf.append(CellOperation.DEFAULT_CELL_SEPARATOR.toString());
					}					
					buf.append(cell1 + this.sep + cell2);					
				}
				result.setResult(buf.toString());
		}
	}

}
