package innovimax.mixthem.join;

import innovimax.mixthem.exceptions.MixException;
import innovimax.mixthem.interfaces.IJoinLine;

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
	public String join(String line1, String line2) throws MixException {
		if (line1 != null && line2 != null) {
			List<String> list1 = Arrays.asList(line1.split("\\s"));
			List<String> list2 = Arrays.asList(line2.split("\\s"));
			if (list1.size() > 0 && list2.contains(list1.get(0))) {
				String part1 = list1.stream().collect(Collectors.joining(" "));
				String part2 = list2.stream().filter(s -> !list1.contains(s)).collect(Collectors.joining(" "));
				return part1 + " " + part2;				
			}
		}
		return null;
	}

	@Override
	public String join(String line1, String line2, int index) throws MixException {
		System.out.println("trace> join on same column : " + index);
		System.out.println("trace> first line : " + line1);
		System.out.println("trace> first line : " + line2);
		if (line1 != null && line2 != null) {
			List<String> list1 = Arrays.asList(line1.split("\\s"));
			List<String> list2 = Arrays.asList(line2.split("\\s"));
			if (list1.size() >= index && list2.size() >= index && list1.get(index - 1).equals(list2.get(index - 1))) {
				String part1 = list1.get(index - 1);
				String part2 = list1.stream().filter(s -> !s.equals(part1)).collect(Collectors.joining(" "));
				String part3 = list2.stream().filter(s -> !list1.contains(s)).collect(Collectors.joining(" "));
				return part1 + " " + part2 + " " + part3;				
			}
		}
		return null;
	}

	@Override
	public String join(String line1, String line2, int index1, int index2) throws MixException {
		System.out.println("join col1 col2 todo");
		return null;
	}

}
