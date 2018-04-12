package innovimax.mixthem.operation;

import static innovimax.mixthem.MixConstants.*;
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
		this.sep = params.containsKey(RuleParam._ZIP_SEP) ? params.get(RuleParam._ZIP_SEP).asString() : DEFAULT_ZIP_SEPARATOR;	
	}
	
	@Override
	public void process(LineResult result) throws MixException {
		result.resetTypes();
		switch (this.type) {
			case _LINE:
				result.setResult((result.hasFirstLine() ? result.getFirstLine() : "") + this.sep + (result.hasLastLine() ? result.getLastLine() : ""));
				break;
			case _CELL:					
				Iterator<String> iterator1 = result.hasFirstLine() ? Arrays.asList(result.getFirstLine().split(DEFAULT_SPLIT_CELL_REGEX )).iterator() : Collections.emptyIterator();
				Iterator<String> iterator2 = result.hasLastLine() ? Arrays.asList(result.getLastLine().split(DEFAULT_SPLIT_CELL_REGEX )).iterator() : Collections.emptyIterator();				
				StringBuffer buf = new StringBuffer();
				while (iterator1.hasNext() || iterator2.hasNext()) {						
					String cell1 = iterator1.hasNext() ? iterator1.next() : "";
					String cell2 = iterator2.hasNext() ? iterator2.next() : "";					
					if (buf.length() > 0) {						
						buf.append(DEFAULT_CELL_SEPARATOR);
					}					
					buf.append(cell1 + this.sep + cell2);					
				}
				result.setResult(buf.toString());
				break;
			default:
				result.ignoreResult();
		}
		result.exploreBoth();
	}

}
