package innovimax.mixthem.operation;

import innovimax.mixthem.MixException;
import innovimax.mixthem.arguments.RuleParam;
import innovimax.mixthem.arguments.ParamValue;

import java.util.List;
import java.util.Map;
import java.util.Set;

/**
* <p>Zips two or more lines.</p>
* @see ILineOperation
* @author Innovimax
* @version 1.0
*/
public class DefaultLineZipping extends AbstractLineOperation {
	
	protected final String sep;
	
	/**
	* Constructor
	* @param selection The file index selection (maybe empty)
 	* @param params The list of parameters (maybe empty)
	* @see innovimax.mixthem.arguments.RuleParam
	* @see innovimax.mixthem.arguments.ParamValue
	*/
	public DefaultLineZipping(final Set<Integer> selection, final Map<RuleParam, ParamValue> params) {
		super(selection, params);
		this.sep = params.getOrDefault(RuleParam.ZIP_SEP, ZipOperation.DEFAULT_ZIP_SEPARATOR.getValue()).asString();	
	}
	
	@Override
	public void process(final List<String> lineRange, final LineResult result) throws MixException {
		//System.out.println("RANGE="+lineRange.toString())
		/*StringBuilder zip = new StringBuilder();
		int index = 0;
		for (String line : lineRange) {
			if (index > 0) {
				zip.append(this.sep);
			}
			zip.append(line);
			index++;
		}
		result.setResult(zip.toString());*/
		final String zip = lineRange.stream()
				.collect(StringBuilder::new, StringBuilder::append, StringBuilder::append)
            			.toString();
		result.setResult(zip);
	}

}
