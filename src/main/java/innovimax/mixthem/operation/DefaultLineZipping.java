package innovimax.mixthem.operation;

import innovimax.mixthem.MixException;
import innovimax.mixthem.MixThem;
import innovimax.mixthem.arguments.RuleParam;
import innovimax.mixthem.arguments.ParamValue;

import java.util.Map;

/**
* <p>Zips two lines on a common field.</p>
* @see ILineOperation
* @author Innovimax
* @version 1.0
*/
public class DefaultLineZipping implements ILineOperation {

	private final Map<RuleParam, ParamValue> params;
	
	/**
 	* @param params The list of parameters (maybe empty)
	* @see innovimax.mixthem.operation.RuleParam
	* @see innovimax.mixthem.operation.ParamValue
	*/
	public DefaultLineZipping(Map<RuleParam, ParamValue> params) {
		this.params = params;
	}
	
	/**
 	* Returns the result of zipping two lines.
	* @param line1 The first line to zip
 	* @param line2 The second line to zip	
 	* @return The result of zipping two lines
 	* @throws MixException - If an mixing error occurs
 	*/
	@Override
	public String process(String line1, String line2) throws MixException {
		String zip = null;
		if (line1 != null && line2 != null) {      			
			MixThem.LOGGER.info("Params " + params.toString());									
		}
		return zip;
	}
	
	private class ZipTuple {
		
		private final char c1;
		private final char c2;
		private final String sep;
		
		private ZipTuple(char c1, char c2) {
			this(c1, c2, " ");
		}

		private ZipTuple(char c1, char c2, String sep) {
			this.c1 = c1;
			this.c2 = c2;
			this.sep = sep;
		}
		
		@Override
		public String toString() {
			return '(' + this.c1 + this.sep + this.c2 + ')';
		}
	}

}
