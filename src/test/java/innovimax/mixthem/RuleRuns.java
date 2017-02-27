package innovimax.mixthem;

import innovimax.mixthem.arguments.Rule;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.stream.Stream;

/**
* Provides different runs for testing a rule according to the additional parameters
* @author Innovimax
* @version 1.0
*/
public class RuleRuns {
	
	final private static String DEFAULT_OUTPUT_FILE = "default";

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
  * @param rule The rule to test
  * @return Returns a list of test runs for the rule
    */	
  public List<RuleRun> getRuns(Rule rule) {
    return runMap.get(rule);
  }

  	/**
  	* Returns a list of test runs for the rule.
  	* @param params The URL of rule additional parameters file
  	* @return Returns a list of test runs for the rule
    	*/	
	public List<RuleRun> getRuns(URL params) throws FileNotFoundException, IOException {
    		List<RuleRun> runs = new LinkedList<RuleRun>();
    		runs.add(new RuleRun(Collections.emptyList()));
		if (params != null) {
			File file = new File(params.getFile());			
			BufferedReader reader = Files.newBufferedReader(file.toPath(), StandardCharsets.UTF_8);
			Stream<String> entries = reader.lines();
			entries.forEach(entry -> {
				String[] parts = entry.split("\\s");
				if (parts.length > 1) {
					String suffix = parts[0];
					List<String> params = Arrays.asList(Arrays.copyOfRange(parts, 1, parts.length));
					if (suffix.equals(DEFAULT_OUTPUT_FILE)) {
						runs.add(new RuleRun(-1, null, params));
					} else {
						runs.add(new RuleRun(-1, suffix, params));
					}
				}
			});
		}
    		return runs;
  	}

}
