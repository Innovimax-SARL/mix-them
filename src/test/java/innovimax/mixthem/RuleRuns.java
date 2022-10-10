package innovimax.mixthem;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import innovimax.mixthem.arguments.ParamValue;
import innovimax.mixthem.arguments.Rule;
import innovimax.mixthem.arguments.RuleParam;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.util.Collections;
import java.util.EnumMap;
import java.util.LinkedHashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Stream;

/**
* Provides different runs for testing a rule according to the additional parameters
* @author Innovimax
* @version 1.0
*/
class RuleRuns {

  	/**
  	* Returns a list of test runs for the rule.
  	* @param url The URL of rule additional parameters file
  	* @return Returns a list of test runs for the rule
    	*/	
	public static List<RuleRun> getRuns(final Rule rule, final URL url) throws IOException, NumberFormatException {
    		final List<RuleRun> runs = new LinkedList<>();
    		runs.add(new RuleRun(1, Collections.emptySet(), Collections.emptyMap()));
		if (url != null) {
			final File file = new File(url.getFile());
			final Stream<String> entries;
			try (final BufferedReader reader = Files.newBufferedReader(file.toPath(), StandardCharsets.UTF_8)) {
				entries = reader.lines();
			}
			entries.forEach(entry -> {
				try {
					final ObjectMapper jsonMapper = new ObjectMapper();
					final JsonNode jsonParams = jsonMapper.readTree(entry);
					// get selection
					final Set<Integer> selection = new LinkedHashSet<>();
					if (jsonParams.has("selection")) {
						final JsonNode node = jsonParams.get("selection");
						if (node.isArray()) {
							node.elements().forEachRemaining(value -> {
								if (value.isInt()) {									
									selection.add(value.asInt());
								}
							});
						}
					}
					// get rule parameters
					final Map<RuleParam, ParamValue> params = new EnumMap<>(RuleParam.class);
					switch (rule) {
						case RANDOM_ALT_LINE:
						case RANDOM_ALT_CHAR:
						case RANDOM_ALT_BYTE:							
							if (jsonParams.has(RuleParam.RANDOM_SEED.getName())) {
								final JsonNode seed = jsonParams.get(RuleParam.RANDOM_SEED.getName());
								if (seed.isInt()) {
									params.put(RuleParam.RANDOM_SEED, ParamValue.createInt(seed.asInt()));
								}
							}
							break;
						case JOIN:							
							if (jsonParams.has(RuleParam.JOIN_COLS.getName())) {
								final JsonNode cols = jsonParams.get(RuleParam.JOIN_COLS.getName());
								//TODO: get as an array ?
								if (cols.isTextual()) {
									params.put(RuleParam.JOIN_COLS, RuleParam.JOIN_COLS.createValue(cols.asText()));
								}
							}
							break;
						case ZIP_LINE:
						case ZIP_CELL:
						case ZIP_CHAR:							
							if (jsonParams.has(RuleParam.ZIP_SEP.getName())) {
								final JsonNode sep = jsonParams.get(RuleParam.ZIP_SEP.getName());
								if (sep.isTextual()) {
									params.put(RuleParam.ZIP_SEP, ParamValue.createString(sep.asText()));
								}
							}
					}
					// add a rule run
					if (!selection.isEmpty() || !params.isEmpty()) {
						runs.add(new RuleRun(runs.size()+1, selection, params));
					}					
				} catch (JsonProcessingException e) {
					throw new RuntimeException(e);
				}
			});
		}
    		return runs;
  	}

}
