package innovimax.mixthem;

/**
* <p>This is a detailed enumeration of rule parameters types.</p>
* @author Innovimax
* @version 1.0
*/
public enum ParamType { 
	_STRING("string"),
	_INTEGER("integer");	

	private final String name;

	private ParamType(String name) {
		this.name = name;
	}

	/**
	* Returns the name of this type.
	* @return The name of this type
	*/
	public String getName() {
		return this.name;
	}

}
