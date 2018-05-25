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

  	/**
  	* Returns a list of test runs for the rule.
  	* @param url The URL of rule additional parameters file
  	* @return Returns a list of test runs for the rule
    	*/	
	public static List<RuleRun> getRuns(final Rule rule, final URL url) throws FileNotFoundException, IOException, NumberFormatException {
    		final List<RuleRun> runs = new LinkedList<RuleRun>();
    		runs.add(new RuleRun(1, Collections.emptyMap()));
		if (url != null) {
			final File file = new File(url.getFile());			
			final BufferedReader reader = Files.newBufferedReader(file.toPath(), StandardCharsets.UTF_8);
			final Stream<String> entries = reader.lines();
			entries.forEach(entry -> {
				final String[] parts = entry.split("\\s");
				if (parts.length > 0) {
					final String value = parts[0];
					final Map<RuleParam, ParamValue> params = new EnumMap<RuleParam, ParamValue>(RuleParam.class);
					switch (rule) {
						case ADD:
							params.put(RuleParam.FILE_LIST, RuleParam.FILE_LIST.createValue(value));
							break;
						case RANDOM_ALT_LINE:
						case RANDOM_ALT_CHAR:
						case RANDOM_ALT_BYTE:							
							params.put(RuleParam.RANDOM_SEED, ParamValue.createInt(Integer.parseInt(value)));
							break;
						case JOIN:							
							params.put(RuleParam.JOIN_COLS, RuleParam.JOIN_COLS.createValue(value));
							break;
						case ZIP_LINE:
						case ZIP_CELL:
						case ZIP_CHAR:
							params.put(RuleParam.ZIP_SEP, ParamValue.createString(value));
					}					
					runs.add(new RuleRun(runs.size()+1, params));
				}
			});
		}
    		return runs;
  	}

}
