package innovimax.mixthem;

import innovimax.mixthem.arguments.ParamValue;
import innovimax.mixthem.arguments.Rule;
import innovimax.mixthem.arguments.RuleParam;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Stream;

/**
* Provides different runs for testing a rule according to the additional parameters
* @author Innovimax
* @version 1.0
*/
public class RuleRuns {
	
	final private static String DEFAULT_OUTPUT_FILE = "default";

  	/**
  	* Returns a list of test runs for the rule.
  	* @param url The URL of rule additional parameters file
  	* @return Returns a list of test runs for the rule
    	*/	
	public static List<RuleRun> getRuns(Rule rule, URL url) throws FileNotFoundException, IOException, NumberFormatException {
    		List<RuleRun> runs = new LinkedList<RuleRun>();
		Map<RuleParam, ParamValue> params = new HashMap<RuleParam, ParamValue>();
    		runs.add(new RuleRun(params));
		if (url != null) {
			File file = new File(url.getFile());			
			BufferedReader reader = Files.newBufferedReader(file.toPath(), StandardCharsets.UTF_8);
			Stream<String> entries = reader.lines();
			entries.forEach(entry -> {
				String[] parts = entry.split("\\s");
				if (parts.length > 1) {
					String suffix = parts[0];
					map.clear();
					switch (rule) {
						case _RANDOM_ALT_LINE:
							int seed = Integer.parseInt(parts[1]);
							map.put(RuleParam._RANDOM_SEED, new ParamValue(seed));
							break;
						case _JOIN:
							int col = Integer.parseInt(parts[1]);
							map.put(RuleParam._JOIN_COL1, new ParamValue(col));
							if (parts.length > 2) {
								col = Integer.parseInt(parts[2]);
								map.put(RuleParam._JOIN_COL2, new ParamValue(col));
							}					
					}
					if (suffix.equals(DEFAULT_OUTPUT_FILE)) {
						runs.add(new RuleRun(null, params));
					} else {
						runs.add(new RuleRun(suffix, params));
					}
				}
			});
		}
    		return runs;
  	}

}
