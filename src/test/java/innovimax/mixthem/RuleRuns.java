package innovimax.mixthem;

import innovimax.mixthem.arguments.Rule;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
* Provides different runs for testing a rule according ths additional parameters
* @author Innovimax
* @version 1.0
*/
public class RuleRuns {

  final private Map<Rule, List<RuleRun>> runMap;

  /*
  * Creates the rule run maker.
  */
  public RuleRuns() {
    runMap = new HashMap<Rule, List<RuleRun>>();
    for (Rule rule : Rule.values()) {
      List<RuleRun> runs = new ArrayList<RuleRun>();
      if (rule.hasParams()) {
        switch (rule) {
          case _RANDOM_ALT_LINE:
            runs.add(new RuleRun(1, Collections.emptyList()));
            runs.add(new RuleRun(1, Collections.singletonList("1789")));
            break;
          case _JOIN:
            //runs.add(new RuleRun(3, Collections.emptyList()));            
	    //runs.add(new RuleRun(4, "same", Collections.singletonList("2")));
            //runs.add(new RuleRun(4, "diff", Arrays.asList("4", "1")));
	    runs.add(new RuleRun(3, "first", Collections.emptyList()));
	    runs.add(new RuleRun(3, "same", Collections.singletonList("2")));
            runs.add(new RuleRun(3, "diff", Arrays.asList("4", "3")));			
            break;
	}
      } else {
        runs.add(new RuleRun(Collections.emptyList()));
      }
      runMap.put(rule, runs);
    }
  }

  /**
  * Returns a list of test runs for the rule.
  * @param The rule to test
  * @return Returns a list of test runs for the rule
    */	
  public List<RuleRun> getRuns(Rule rule) {
    return runMap.get(rule);
  }

}
