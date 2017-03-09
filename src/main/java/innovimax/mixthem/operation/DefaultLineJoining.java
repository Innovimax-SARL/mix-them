package innovimax.mixthem.operation;

import static innovimax.mixthem.MixConstants.*;
import innovimax.mixthem.MixException;
import innovimax.mixthem.arguments.ParamValue;
import innovimax.mixthem.arguments.RuleParam;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
* <p>Joins two lines on a common field.</p>
* @see ILineOperation
* @author Innovimax
* @version 1.0
*/
public class DefaultLineJoining extends AbstractLineOperation {
	
	private final int col1;
	private final int col2;
	
	/**
 	* @param params The list of parameters (maybe empty)
	* @see innovimax.mixthem.operation.RuleParam
	* @see innovimax.mixthem.operation.ParamValue
	*/
	public DefaultLineJoining(Map<RuleParam, ParamValue> params) {
		super(params);
		this.col1 = this.params.containsKey(RuleParam._JOIN_COL1) ? this.params.get(RuleParam._JOIN_COL1).asInt() : 1;
		this.col2 = this.params.containsKey(RuleParam._JOIN_COL2) ? this.params.get(RuleParam._JOIN_COL2).asInt() : 1;		
	}	

	@Override
	public String process(String line1, String line2) throws MixException {
		String join = null;
		if (line1 !=null && line2 != null) {
			List<String> list1 = Arrays.asList(line1.split(DEFAULT_SPLIT_CELL_REGEX));
			List<String> list2 = Arrays.asList(line2.split(DEFAULT_SPLIT_CELL_REGEX));		
			if (list1.size() >= this.col1 && list2.size() >= this.col2 && list1.get(this.col1 - 1).equals(list2.get(this.col2 - 1))) {
				String part1 = list1.get(this.col1 - 1);
				String part2 = list1.stream().filter(s -> !s.equals(part1)).collect(Collectors.joining(DEFAULT_CELL_SEPARATOR));
				String part3 = list2.stream().filter(s -> !s.equals(part1)).collect(Collectors.joining(DEFAULT_CELL_SEPARATOR));
				join = part1 + DEFAULT_CELL_SEPARATOR  + part2 + DEFAULT_CELL_SEPARATOR + part3;				
			}
		}
		return join;
	}

}
