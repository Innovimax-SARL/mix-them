
package innovimax.mixthem.arguments;

/**
* <p>This is the representation of a parameter value.</p>
* @author Innovimax
* @version 1.0
*/
public class ParamValue { 
	
	private final String text;
	private final int number;
	
	/**
	* Constructor
	* @param text The String value of the parameter.	
	*/ 	
	public ParamValue(String text) {
		this.text = text;
		this.number = -1;
	}

	/**
	* Constructor
	* @param number The Integer value of the parameter.	
	*/ 	
	public ParamValue(int number) {
		this.text = null;
		this.number = number;
	}
	
	/**
	* Returns the parameter value as a String.
	* @return The parameter value as a String
	*/
	public String stringValue() {
		return this.text;
	}

	/**
	* Returns the parameter value as an Integer.
	* @return The parameter value as an Integer
	*/
	public int intValue() {
		return this.number;
	}

}
