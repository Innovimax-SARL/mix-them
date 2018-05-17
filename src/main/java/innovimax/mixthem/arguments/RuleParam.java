package innovimax.mixthem.arguments;

/**
* <p>This is a detailed enumeration of additional rule parameters.</p>
* @author Innovimax
* @version 1.0
*/
public enum RuleParam { 
	RANDOM_SEED("seed", ParamType.INTEGER),
	JOIN_COLS("cols", ParamType.INTEGER_ARRAY),
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
	* Returns the comment for this parameter.
	* @return The comment for this parameter
	*/
	public String getComment() {
		switch(this.type) {
			case INTEGER:
            			return "is an integer value";
			case INTEGER_ARRAY:
				return "is a list of integer separated by a comma";
            		default:
            			return "is a string value";
		}		
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
			case INTEGER_ARRAY:
				final String[] stringArray = value.split(",");
				final int len = value.endsWith(",") ? stringArray.length+1 : stringArray.length;
				final int[] intArray = new int[len];
				for (int i=0; i < stringArray.length; i++) {
					if (stringArray[i].equals("")) {
						intArray[i] = -1;
					} else {
						intArray[i] = Integer.parseInt(stringArray[i]);
					}
				}
				if (len > stringArray.length) {
					intArray[stringArray.length] = -1;
				}
            			pv = ParamValue.createIntArray(intArray);
            			break;
            		default:
            			pv = ParamValue.createString(value);
		}
		return pv;
	}

}
