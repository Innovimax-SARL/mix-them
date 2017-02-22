package innovimax.mixthem;

import innovimax.mixthem.arguments.Rule;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
* @author Innovimax
* @version 1.0
*/
public class RuleRuns {

	final private Map<Rule, List<List<String>>> runMap;

	public RuleRuns() {
		runMap = new HashMap<Rule, List<List<String>>>();
	}

    public List<List<String>> getRuns(Rule rule) {    	    	
    	return runMap.get(rule);
    }

}
