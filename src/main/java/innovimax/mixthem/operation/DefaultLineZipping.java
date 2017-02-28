package innovimax.mixthem.operation;

import innovimax.mixthem.exceptions.MixException;
import innovimax.mixthem.interfaces.IZipLine;

/**
* <p>Joins two lines on a common field.</p>
* <p>This is the default implementation of IJoinLine.</p>
* @see IJoinLine
* @author Innovimax
* @version 1.0
*/
public class DefaultLineZipping implements IZipLine {

	@Override
	public String zip(String line1, String line2) throws MixException {
		String zippedLine = null;
		if (line1 != null && line2 != null) {
      			MixThem.LOGGER.logp(Level.INFO, "DefaultLineZipping", "zip", "TO IMPLEMENT");
		}
		return zippedLine;
	}

}
