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
public class DefaultLineJoining implements ILineOperation {
	private final Map<RuleParam, ParamValue> params;
	/**
 	* @param params The list of parameters (maybe empty)
	* @see innovimax.mixthem.operation.RuleParam
	* @see innovimax.mixthem.operation.ParamValue
	*/
	public DefaultLineJoining(Map<RuleParam, ParamValue> params) {
		this.params = params;
	}	

	/**
 	* Returns the result of joining two lines regarding the parameters.
	* @param line1 The first line to join
 	* @param line2 The second line to join
 	* @return The result of joining two lines regarding the parameters
 	* @throws MixException - If an mixing error occurs
 	*/
	@Override
	public String process(String line1, String line2) throws MixException {
		String join = null;
		List<String> list1 = Arrays.asList(line1.split("\\s"));
		List<String> list2 = Arrays.asList(line2.split("\\s"));
		switch (this.params.size()) {
			case 0:				
				if (list1.size() > 0 && list2.contains(list1.get(0))) {
					String part1 = list1.stream().collect(Collectors.joining(" "));
					String part2 = list2.stream().filter(s -> !list1.contains(s)).collect(Collectors.joining(" "));
					join = part1 + " " + part2;
				}				
				break;
			case 1:		
				int col = this.params.get(RuleParam._JOIN_COL1).asInt();
				if (list1.size() >= col && list2.size() >= col && list1.get(col - 1).equals(list2.get(col - 1))) {
					String part1 = list1.get(col - 1);
					String part2 = list1.stream().filter(s -> !s.equals(part1)).collect(Collectors.joining(" "));
					String part3 = list2.stream().filter(s -> !s.equals(part1)).collect(Collectors.joining(" "));
					join = part1 + " " + part2 + " " + part3;				
				}
				break;
			case 2:
				int col1 = this.params.get(RuleParam._JOIN_COL1).asInt();
				int col2 = this.params.get(RuleParam._JOIN_COL2).asInt();
				if (list1.size() >= col1 && list2.size() >= col2 && list1.get(col1 - 1).equals(list2.get(col2 - 1))) {
					String part1 = list1.get(col1 - 1);
					String part2 = list1.stream().filter(s -> !s.equals(part1)).collect(Collectors.joining(" "));
					String part3 = list2.stream().filter(s -> !s.equals(part1)).collect(Collectors.joining(" "));
					join = part1 + " " + part2 + " " + part3;				
				}
			}
		return join;
	}

}
