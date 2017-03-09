package innovimax.mixthem.operation;

import static innovimax.mixthem.MixConstants.*;
import innovimax.mixthem.MixException;
import innovimax.mixthem.arguments.RuleParam;
import innovimax.mixthem.arguments.ParamValue;

import java.util.Arrays;
import java.util.Iterator;
import java.util.Map;
import java.util.List;

/**
* <p>Zips two lines on a common field.</p>
* @see ILineOperation
* @author Innovimax
* @version 1.0
*/
public class DefaultLineZipping extends AbstractLineOperation {
	
	private final ZipType type;
	
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
	}
	
	@Override
	public String process(String line1, String line2) throws MixException {
		String zip = null;
		String sep = DEFAULT_ZIP_SEPARATOR;
		if (this.params.containsKey(RuleParam._ZIP_SEP)) {
			sep = this.params.get(RuleParam._ZIP_SEP).asString();
		}		
		switch (this.type) {
			case _LINE:
				zip = (line1 != null ? line1 : "") + sep + (line2 != null ? line2 : "");
				break;
			case _CELL:					
				List<String> list1 = line1 != null ? Arrays.asList(line1.split("\\s")) : Collections.emptyList();
				List<String> list2 = line2 != null ? Arrays.asList(line2.split("\\s")) : Collections.emptyList();
				Iterator<String> iterator1 = list1.iterator();
				Iterator<String> iterator2 = list2.iterator();
				StringBuffer buf = new StringBuffer();
				while (iterator1.hasNext() || iterator2.hasNext()) {						
					String cell1 = iterator1.hasNext() ? iterator1.next() : "";
					String cell2 = iterator2.hasNext() ? iterator2.next() : "";					
					if (buf.length() > 0) {						
						buff.append(" ");  // cell separator
					}					
					buf.append(cell1 + sep + cell2);					
				}
				zip = buf.toString();
		}
		return zip;
	}

}
