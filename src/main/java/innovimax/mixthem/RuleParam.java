package innovimax.mixthem;

import java.io.File;

/**
* <p>Rule additional parameters management.</p>
* @author Innovimax
* @version 1.0
*/
public class RuleParam {

	private static final String TYPE_STRING = "string";
	private static final String TYPE_INTEGER = "integer";

	private final String name;	
	private final boolean required;
	private final String type;

	/**
 	* Creates a rule parameter.
 	* @param name The name of the parameter
 	* @param required Equals true if this parameter is required
 	*/
	RuleParam(String name, boolean required, String type) {
		this.name = name;
		this.required = required;
		this.type = type;
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


	/**
 	* Returns true if the parameter value is correct.
 	* @param value The value of the parameter on command line
 	* @return Returns true if the parameter value is correct
 	*/
	boolean checkValue(String value) {
		switch (this.type) {
        	case TYPE_STRING:
    			File file = new File(value);
        		if (file.exists()) {
        			return false;
        		} else {
        			return true;
        		}
        	case TYPE_INTEGER:
            	try {
            		Integer number = new Integer(value);
            		return true;
            	} catch (Exception e) {
            		return false;
            	}
            default:
            	return false;
		}
	}

	// This is the unique optional parameter of rule _RANDOM_ALT_LINE
	static RuleParam RANDOM_ALT_LINE_SEED = new RuleParam("seed", false, TYPE_INTEGER);
	// This is the first optional parameter of rule _JOIN
	static RuleParam JOIN_COL1 = new RuleParam("col1", false, TYPE_STRING);
	// This is the second optional parameter of rule _JOIN
	static RuleParam JOIN_COL2 = new RuleParam("col2", false, TYPE_STRING);

}
