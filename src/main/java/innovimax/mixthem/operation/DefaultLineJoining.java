package innovimax.mixthem.operation;

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
	
	/**
 	* @param params The list of parameters (maybe empty)
	* @see innovimax.mixthem.operation.RuleParam
	* @see innovimax.mixthem.operation.ParamValue
	*/
	public DefaultLineJoining(Map<RuleParam, ParamValue> params) {
		super(params);
	}	

	/**
 	* Returns the result of joining two lines regarding the parameters or null if none.
	* @param line1 The first line to join
 	* @param line2 The second line to join
 	* @return The result of joining two lines regarding the parameters or null if none
 	* @throws MixException - If an mixing error occurs
 	*/
	@Override
	public String process(String line1, String line2) throws MixException {
		if (line1 == null || line2 == null) {
			return null;
		} else {
			String join = null;
			int col1 = 1;
			int col2 = 1;
			if (this.params.containsKey(RuleParam._JOIN_COL1)) {
				col1 = this.params.get(RuleParam._JOIN_COL1).asInt();
				col2 = col1;
			}
			if (this.params.containsKey(RuleParam._JOIN_COL2)) {
				col2 = this.params.get(RuleParam._JOIN_COL2).asInt();			
			}
			List<String> list1 = Arrays.asList(line1.split("\\s"));
			List<String> list2 = Arrays.asList(line2.split("\\s"));		
			if (list1.size() >= col1 && list2.size() >= col2 && list1.get(col1 - 1).equals(list2.get(col2 - 1))) {
				String part1 = list1.get(col1 - 1);
				String part2 = list1.stream().filter(s -> !s.equals(part1)).collect(Collectors.joining(" "));
				String part3 = list2.stream().filter(s -> !s.equals(part1)).collect(Collectors.joining(" "));
				join = part1 + " " + part2 + " " + part3;				
			}		
			return join;
		}
	}

}
