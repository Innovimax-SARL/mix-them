package innovimax.mixthem;

/**
* <p>Rule additional parameters management.</p>
* @author Innovimax
* @version 1.0
*/
public class RuleParam {

	private final String name;	
	private final boolean required;

	/**
 	* Creates a rule parameter.
 	* @param name The name of the parameter
 	* @param required Equals true if this parameter is required
 	*/
	RuleParam(String name, boolean required) {
		this.name = name;
		this.required = required;
	}

	/**
	* Returns the name of the rule parameter.
	* @return The name of the rule parameter
	*/	
	String getName() {
		return this.name;
	}

	/**
 	* Returns true if this parameter is required.
 	* @return Returns true if this parameter is required
 	*/
	boolean isRequired() {
		return this.required;
	}

	// This is the unique optional parameter of rule _RANDOM_ALT_LINE
	static RuleParam RANDOM_ALT_LINE_SEED = new RuleParam("seed", false);
	// This is the first optional parameter of rule _JOIN
	static RuleParam JOIN_COL1 = new RuleParam("col1", false);
	// This is the second optional parameter of rule _JOIN
	static RuleParam JOIN_COL2 = new RuleParam("col2", false);

}
