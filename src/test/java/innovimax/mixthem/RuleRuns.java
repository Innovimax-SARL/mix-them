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

  	/**
  	* Returns a list of test runs for the rule.
  	* @param url The URL of rule additional parameters file
  	* @return Returns a list of test runs for the rule
    	*/	
	public static List<RuleRun> getRuns(URL url) throws FileNotFoundException, IOException {
    		List<RuleRun> runs = new LinkedList<RuleRun>();
    		runs.add(new RuleRun(Collections.emptyList()));
		if (url != null) {
			File file = new File(url.getFile());			
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
