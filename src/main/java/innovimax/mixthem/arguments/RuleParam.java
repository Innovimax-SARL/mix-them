package innovimax.mixthem.arguments;

/**
* <p>This is a detailed enumeration of additional rule parameters.</p>
* @author Innovimax
* @version 1.0
*/
public enum RuleParam { 
	RANDOM_SEED("seed", ParamType.INTEGER),
	JOIN_COL1("col1", ParamType.INTEGER),
	JOIN_COL2("col2", ParamType.INTEGER),
	ZIP_SEP("sep", ParamType.STRING);

	private final String name;
	private final ParamType type;

	private RuleParam(final String name, final ParamType type) {
		this.name = name;
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
 	* Returns the {@link ParamValue} representation of the parameter value.
 	* @param value The value of the parameter on command line
 	* @return The {@link ParamValue} representation of the parameter value
 	*/
	ParamValue createValue(final String value) throws NumberFormatException {
		ParamValue pv = null;
		switch (this.type) {
			case INTEGER:				
            			pv = ParamValue.createInt(Integer.parseInt(value));
            			break;
            		default:
            			pv = ParamValue.createString(value);
		}
		return pv;
	}

}
