package innovimax.mixthem.arguments;

/**
* <p>This is a detailed enumeration of additional rule parameters.</p>
* @author Innovimax
* @version 1.0
*/
public enum RuleParam { 
	_RANDOM_SEED("seed", false, ParamType._INTEGER),
	_JOIN_COL1("col1", false, ParamType._INTEGER),
	_JOIN_COL2("col2", false, ParamType._INTEGER);

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
 	* Returns the {@link ParamValue} representation of the parameter value.
 	* @param value The value of the parameter on command line
 	* @return The {@link ParamValue} representation of the parameter value
 	*/
	ParamValue createValue(String value) throws NumberFormatException {
		ParamValue pv = null;
		switch (this.type) {
			case _INTEGER:				
            			pv = new ParamValue(Integer.parseInt(value));
            			break;
            		default:
            			pv = new ParamValue(value);
		}
		return pv;
	}

}
