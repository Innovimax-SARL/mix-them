package innovimax.mixthem.operation;

import innovimax.mixthem.MixException;
import innovimax.mixthem.arguments.ParamValue;
import innovimax.mixthem.arguments.RuleParam;
import innovimax.mixthem.arguments.TokenType;

import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.IntStream;
import java.util.stream.Stream;

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
	public DefaultLineZipping(final Set<Integer> selection, final TokenType tokenType, final Map<RuleParam, ParamValue> params) {
		super(selection, tokenType, params);
		this.sep = params.getOrDefault(RuleParam.ZIP_SEP, ZipOperation.DEFAULT_ZIP_SEPARATOR.getValue()).asString();	
	}
	
	@Override
	public void process(final List<String> lineRange, final LineResult result) throws MixException {
		//System.out.println("RANGE="+lineRange.toString())
		final String zip = IntStream.range(0, lineRange.size())
				.mapToObj(index -> index > 0 ? 
						Stream.of(this.sep, lineRange.get(index)) :
						Stream.of(lineRange.get(index)))					
				.flatMap(stream -> stream)
				.collect(StringBuilder::new, StringBuilder::append, StringBuilder::append)
            			.toString();
		result.setResult(zip);
	}

}
