
package innovimax.mixthem.arguments;

import java.util.Arrays;

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
		public int[] asIntArray() {
			throw new UnsupportedOperationException("ParamStringValue does not have an int[] representation");
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
		public int[] asIntArray() {
			throw new UnsupportedOperationException("ParamIntValue does not have an int[] representation");
		}
		@Override
		public String toString() {
			return Integer.toString(i);
		}
	}
	
	private static class ParamIntArrayValue extends ParamValue {
		private final int[] array;
		private ParamIntArrayValue(final int[] array) {
			this.array = array;
		}			
		@Override
		public String asString() {
			throw new UnsupportedOperationException("ParamIntArrayValue does not have a String representation");
		}
		@Override
		public int asInt() {
			throw new UnsupportedOperationException("ParamIntArrayValue does not have an int representation");
		}
		@Override
		public int[] asIntArray() {
			return this.array;
		}
		@Override
		public String toString() {
			return Arrays.toString(array);
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
	
	public static ParamValue createIntArray(final int[] array) {
		return new ParamIntArrayValue(array);
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
