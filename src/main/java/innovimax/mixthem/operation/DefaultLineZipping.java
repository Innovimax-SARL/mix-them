package innovimax.mixthem.operation;

import innovimax.mixthem.MixException;
import innovimax.mixthem.MixThem;

import java.util.logging.Level;

/**
* <p>Zips two lines on a common field.</p>
* <p>This is the default implementation of IZipLine.</p>
* @see IZipLine
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
