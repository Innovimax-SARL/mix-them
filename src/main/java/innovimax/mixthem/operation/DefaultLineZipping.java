package innovimax.mixthem.operation;

import innovimax.mixthem.MixException;
import innovimax.mixthem.arguments.RuleParam;
import innovimax.mixthem.arguments.ParamValue;

import java.util.Arrays;
import java.util.Map;
import java.util.List;

/**
* <p>Zips two lines on a common field.</p>
* @see ILineOperation
* @author Innovimax
* @version 1.0
*/
public class DefaultLineZipping extends AbstractOperation {
	
	private final static String DEFAULT_ZIP_SEPARATOR = "";

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
	
	/**
 	* Returns the result of zipping two lines.
	* @param line1 The first line to zip
 	* @param line2 The second line to zip	
 	* @return The result of zipping two lines
 	* @throws MixException - If an mixing error occurs
 	*/
	@Override
	public String process(String line1, String line2) throws MixException {
		String zip = null;
		String sep = DEFAULT_ZIP_SEPARATOR;
		if (this.params.containsKey(RuleParam._ZIP_SEP)) {
			sep = this.params.get(RuleParam._ZIP_SEP).asString();
		}		
		if (line1 != null && line2 != null) {
			switch (this.type) {
				case _LINE:
					zip = line1 + sep + line2;
					break;
				case _CELL:					
					List<String> list1 = Arrays.asList(line1.split("\\s"));
					List<String> list2 = Arrays.asList(line2.split("\\s"));
					int index = 0;
					while (index < list1.size() && index < list2.size()) {						
						if (index == 0) {
							zip = "";
						} else {
							zip += " ";  // cell separator
						}
						zip += list1.get(index) + sep + list2.get(index);
						index++;
					}
			}
		}
		return zip;
	}

}
