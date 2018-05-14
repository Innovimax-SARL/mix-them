package innovimax.mixthem.operation;

import innovimax.mixthem.MixException;
import innovimax.mixthem.arguments.RuleParam;
import innovimax.mixthem.arguments.ParamValue;

import java.util.List;
import java.util.Map;

/**
* <p>Zips two or more lines.</p>
* @see ILineOperation
* @author Innovimax
* @version 1.0
*/
public class DefaultLineZipping extends AbstractLineOperation {
	
	private final String sep;
	
	/**
	* Constructor
 	* @param params The list of parameters (maybe empty)
	* @see innovimax.mixthem.operation.ZipType
	* @see innovimax.mixthem.arguments.RuleParam
	* @see innovimax.mixthem.arguments.ParamValue
	*/
	public DefaultLineZipping(final Map<RuleParam, ParamValue> params) {
		super(params);
		this.sep = params.getOrDefault(RuleParam.ZIP_SEP, ZipOperation.DEFAULT_ZIP_SEPARATOR.getValue()).asString();	
	}
	
	@Override
	public void process(final String line1, final String line2, final LineResult result) throws MixException {
		result.reset();
		result.setResult((line1 != null ? line1 : "") + this.sep + (line2 != null ? line2 : ""));
	}
	
	@Override
	public void process(final List<String> lineRange, final LineResult result) throws MixException {
		//process(lineRange.get(0), lineRange.get(1), result);
		result.reset();
		System.out.println("RANGE="+lineRange.toString());
		if (zipable(lineRange)) {	
			StringBuilder zip = new StringBuilder();
			int index = 0;
			for (String line : lineRange) {
				if (index > 0) {
					zip.append(this.sep);
				}
				zip.append(line);
				index++;
			}
			result.setResult(zip.toString());
		}
	}
	
	private boolean zipable(final List<String> lineRange) {		
		for (int i=0; i < lineRange.size(); i++) {			
			if (lineRange.get(i) == null) {
				return false;				
			}
		}
		return true;	
	}

}
