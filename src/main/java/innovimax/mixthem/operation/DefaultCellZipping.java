package innovimax.mixthem.operation;

import innovimax.mixthem.MixException;
import innovimax.mixthem.arguments.RuleParam;
import innovimax.mixthem.arguments.ParamValue;

import java.util.Arrays;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

/**
* <p>Zips two or more lines cell by cell.</p>
* @see ILineOperation
* @author Innovimax
* @version 1.0
*/
public class DefaultCellZipping extends DefaultLineZipping {
	
	/**
	* Constructor	
 	* @param params The list of parameters (maybe empty)
	* @see innovimax.mixthem.arguments.RuleParam
	* @see innovimax.mixthem.arguments.ParamValue
	*/
	public DefaultCellZipping(final Map<RuleParam, ParamValue> params) {
		super(params);		
	}
	
	@Override
	public void process(final String line1, final String line2, final LineResult result) throws MixException {
		result.reset();
		final Iterator<String> iterator1 = line1 != null ? Arrays.asList(line1.split(CellOperation.DEFAULT_SPLIT_CELL_REGEX.getValue().asString())).iterator() : Collections.emptyIterator();
		final Iterator<String> iterator2 = line2 != null ? Arrays.asList(line2.split(CellOperation.DEFAULT_SPLIT_CELL_REGEX.getValue().asString())).iterator() : Collections.emptyIterator();
		final StringBuffer buf = new StringBuffer();
		while (iterator1.hasNext() || iterator2.hasNext()) {
			final String cell1 = iterator1.hasNext() ? iterator1.next() : "";
			final String cell2 = iterator2.hasNext() ? iterator2.next() : "";
			if (buf.length() > 0) {
				buf.append(CellOperation.DEFAULT_CELL_SEPARATOR.getValue().asString());
			}
			buf.append(cell1 + this.sep + cell2);
		}
		result.setResult(buf.toString());
	}
	
	@Override
	public void process(final List<String> lineRange, final LineResult result) throws MixException {
		//process(lineRange.get(0), lineRange.get(1), result);
		result.reset();
		System.out.println("RANGE="+lineRange.toString());
		//if (cellZipable(lineRange)) {
			//TODO
		//}
	}

	/*private boolean cellZipable(final List<String> lineRange) {
		return false;
	}*/

}
