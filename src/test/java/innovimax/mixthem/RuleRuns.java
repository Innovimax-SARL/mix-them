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
import java.util.EnumMap;
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
	public static List<RuleRun> getRuns(Rule rule, URL url) throws FileNotFoundException, IOException, NumberFormatException {
    		List<RuleRun> runs = new LinkedList<RuleRun>();		
    		runs.add(new RuleRun(Collections.emptyMap()));
		if (url != null) {
			File file = new File(url.getFile());			
			BufferedReader reader = Files.newBufferedReader(file.toPath(), StandardCharsets.UTF_8);
			Stream<String> entries = reader.lines();
			entries.forEach(entry -> {
				String[] parts = entry.split("\\s");
				if (parts.length > 1) {
					String suffix = parts[0];
					Map<RuleParam, ParamValue> params = new EnumMap<RuleParam, ParamValue>(RuleParam.class);
					switch (rule) {
						case _RANDOM_ALT_LINE:
							int seed = Integer.parseInt(parts[1]);
							params.put(RuleParam._RANDOM_SEED, ParamValue.createInt(seed));
							break;
						case _JOIN:
							int col = Integer.parseInt(parts[1]);
							params.put(RuleParam._JOIN_COL1, ParamValue.createInt(col));
							if (parts.length > 2) {
								col = Integer.parseInt(parts[2]);
								params.put(RuleParam._JOIN_COL2, ParamValue.createInt(col));
							}	
							break;
						case _ZIP_LINE:
						case _ZIP_CELL:
						case _ZIP_CHAR:
							String sep = parts[1];
							params.put(RuleParam._ZIP_SEP, ParamValue.createString(sep));
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
