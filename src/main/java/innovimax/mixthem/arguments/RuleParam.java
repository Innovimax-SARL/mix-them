package innovimax.mixthem.arguments;

/**
* <p>This is a detailed enumeration of additional rule parameters.</p>
* @author Innovimax
* @version 1.0
*/
public enum RuleParam { 
	_SEED("seed", false, ParamType._INTEGER),
	_COL1("col1", false, ParamType._INTEGER),
	_COL2("col2", false, ParamType._INTEGER);

	private final String name;
	private final boolean required;
	private final ParamType type;

	private RuleParam(String name, boolean required, ParamType type) {
		this.name = name;
		this.required = required;
		this.type = type;
	}

	/**
	* Returns the alias name of this parameter.
	* @return The alias name of this parameter
	*/
	public String getName() {
		return this.name;
	}

	/**
	* Returns true if the parameter is required.
	* @return True if the parameter is required
	*/
	public boolean isRequired() {
		return this.required;
	}

	/**
 	* Returns true if the parameter value is correct.
 	* @param value The value of the parameter on command line
 	* @return Returns true if the parameter value is correct
 	*/
	boolean checkValue(String value) {
		switch (this.type) {
        	case _INTEGER:
            	try {
            		Integer.parseInt(value);
            		return true;
            	} catch (Exception e) {
            		return false;
            	}
            default:
            	return true;
		}
	}

}
