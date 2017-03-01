package innovimax.mixthem.operation;

import innovimax.mixthem.MixException;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
* <p>Joins two lines on a common field.</p>
* <p>This is the default implementation of IJoinLine.</p>
* @see IJoinLine
* @author Innovimax
* @version 1.0
*/
public class DefaultLineJoining implements IJoinLine {

	@Override
	public String join(String line1, String line2, JoinType type, Map<RuleParam, ParamValue> params) throws MixException {
		String join = null;
		if (line1 != null && line2 != null) {
			List<String> list1 = Arrays.asList(line1.split("\\s"));
			List<String> list2 = Arrays.asList(line2.split("\\s"));
			switch (params.size()) {
				case 0:				
					if (list1.size() > 0 && list2.contains(list1.get(0))) {
						String part1 = list1.stream().collect(Collectors.joining(" "));
						String part2 = list2.stream().filter(s -> !list1.contains(s)).collect(Collectors.joining(" "));
						join = part1 + " " + part2;
					}				
					break;
				case 1:		
					int col = map.get(RuleParam._JOIN_COL1).intValue();
					if (list1.size() >= col && list2.size() >= col && list1.get(col - 1).equals(list2.get(col - 1))) {
						String part1 = list1.get(col - 1);
						String part2 = list1.stream().filter(s -> !s.equals(part1)).collect(Collectors.joining(" "));
						String part3 = list2.stream().filter(s -> !list1.contains(s)).collect(Collectors.joining(" "));
						join = part1 + " " + part2 + " " + part3;				
					}
					break;
				case 2:
					int col1 = map.get(RuleParam._JOIN_COL1).intValue();
					int col2 = map.get(RuleParam._JOIN_COL2).intValue();
					if (list1.size() >= col1 && list2.size() >= col2 && list1.get(col1 - 1).equals(list2.get(col2 - 1))) {
						String part1 = list1.get(col1 - 1);
						String part2 = list1.stream().filter(s -> !s.equals(part1)).collect(Collectors.joining(" "));
						String part3 = list2.stream().filter(s -> !list1.contains(s)).collect(Collectors.joining(" "));
						join = part1 + " " + part2 + " " + part3;				
					}
					break;
			}
		}
		return join;
	}

}
