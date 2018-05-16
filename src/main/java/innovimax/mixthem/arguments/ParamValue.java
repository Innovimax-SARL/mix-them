
package innovimax.mixthem.arguments;

/**
* <p>This is the representation of a parameter value.</p>
* @author Innovimax
* @version 1.0
*/
public abstract class ParamValue {
	private static class ParamStringValue extends ParamValue {
		private final String text;
		private ParamStringValue(final String text) {
			this.text = text;
		}
		
		@Override
		public String asString() {
			return this.text;
		}
		@Override
		public int asInt() {
			throw new UnsupportedOperationException("ParamStringValue does not have an int representation");
		}
		@Override
		public String toString() {
			return this.text;
		}
	}
	private static class ParamIntValue extends ParamValue {
		private final int i;
		private ParamIntValue(final int i) {
			this.i = i;
		}			
		
		@Override
		public String asString() {
			throw new UnsupportedOperationException("ParamIntValue does not have a String representation");
		}

		@Override
		public int asInt() {
			return this.i;
		}
		
		@Override
		public String toString() {
			return Integer.toString(i);
		}
	}
	
	/**
	* private Constructor
	*/ 	
	public ParamValue() {}
	
	public static ParamValue createString(final String str) {
		return new ParamStringValue(str);
	}
	
	public static ParamValue createInt(final int i) {
		return new ParamIntValue(i);
	}
	
	/**
	* Returns the parameter value as a string.
	* @return The parameter value as a string
	*/
	public abstract String asString();

	/**
	* Returns the parameter value as an integer.
	* @return The parameter value as an integer
	*/
	public abstract int asInt();
	
	/**
	* Returns the parameter value as an array of integer.
	* @return The parameter value as an array of integer
	*/
	public abstract int[] asIntArray();
	
}
