package innovimax.mixthem;

import java.io.File;

/**
* <p>This is a detailed enumeration of additional rule parameters.</p>
* @author Innovimax
* @version 1.0
*/
public enum RuleParam { 
	_SEED("seed", false, ParamType._INTEGER),
	_COL1("col1", false, ParamType._STRING),
	_COL2("col2", false, ParamType._STRING);

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
	* Returns the type of this parameter.
	* @return The type of this parameter
	*/
	public ParamType getType() {
		return this.type;
	}

	/**
 	* Returns true if the parameter value is correct.
 	* @param value The value of the parameter on command line
 	* @return Returns true if the parameter value is correct
 	*/
	boolean checkValue(String value) {
		switch (this.type) {
        	case _STRING:
    			File file = new File(value);
        		if (file.exists()) {
        			return false;
        		} else {
        			return true;
        		}
        	case _INTEGER:
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

}
