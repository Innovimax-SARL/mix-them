package innovimax.mixthem.join;

import innovimax.mixthem.exceptions.MixException;
import innovimax.mixthem.interfaces.IJoinLine;

import java.util.LinkedList;
import java.util.StringTokenizer;
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
		LinkedList<String> list1 = new LinkedList<String>();
		StringTokenizer st1 = new StringTokenizer(" ");
		while (st1.hasMoreTokens()) {
			list1.add(st1.nextToken());
		}
		LinkedList<String> list2 = new LinkedList<String>();
		StringTokenizer st2 = new StringTokenizer(" ");
		while (st2.hasMoreTokens()) {
			list2.add(st2.nextToken());
		}
		if (list2.contains(list1.getFirst())) {
			String part1 = list1.stream().collect(Collectors.joining(" "));
			String part2 = list2.stream().filter(s -> !list1.contains(s)).collect(Collectors.joining(" "));
			return part1 + " " + part2;
		}
		return null;
	}

}
