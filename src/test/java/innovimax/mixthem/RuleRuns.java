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

  final private Map<Rule, List<RuleRun>> runMap;

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
            runs.add(new RuleRun(3, Collections.emptyList()));            
            //runs.add(new RuleRun(4, Arrays.asList("2", "1")));
            //runs.add(new RuleRun(5, Collections.singletonList("1")));
            break;
	}
      } else {
        runs.add(new RuleRun(Collections.emptyList()));
      }
      runMap.put(rule, runs);
    }
  }

  public List<RuleRun> getRuns(Rule rule) {
    return runMap.get(rule);
  }

}
