package innovimax.mixthem;

import innovimax.mixthem.arguments.Rule;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
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
		for (Rule rule : Rule.values()) {
			List<List<String>> runs = new ArrayList<List<String>>();
			if (rule.hasParams()) {
				switch (rule) {
                	case _RANDOM_ALT_LINE:                		
                		runs.add(Collections.emptyList());
                		runs.add(Collections.singletonList("1789"));                		
                		break;
                	case _JOIN:                  		
                		runs.add(Collections.emptyList());
                		runs.add(Collections.singletonList("1"));
                		runs.add(Arrays.asList("2", "1"));            
                		break;
                }
            } else {
            	runs.add(Collections.emptyList());            	
			}
			runMap.put(rule, runs);
		}
	}

    public List<List<String>> getRuns(Rule rule) {    	    	
    	return runMap.get(rule);
    }

}
