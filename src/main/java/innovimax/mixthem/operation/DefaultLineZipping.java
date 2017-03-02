package innovimax.mixthem.operation;

import innovimax.mixthem.MixException;
import innovimax.mixthem.MixThem;

import java.util.logging.Level;
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
		String zippedLine = null;
		if (line1 != null && line2 != null) {
      			MixThem.LOGGER.logp(Level.INFO, "DefaultLineZipping", "zip", "TO IMPLEMENT");
		}
		return zippedLine;
	}

}
