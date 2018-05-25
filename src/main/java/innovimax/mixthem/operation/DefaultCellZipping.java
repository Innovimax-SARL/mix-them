package innovimax.mixthem.operation;

import innovimax.mixthem.MixException;
import innovimax.mixthem.arguments.RuleParam;
import innovimax.mixthem.arguments.ParamValue;

import java.util.Arrays;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
* <p>Zips two or more lines cell by cell.</p>
* <p>Zipping doesn't operate when a cell is missing in one oh the lines.</p>
* @see ILineOperation
* @author Innovimax
* @version 1.0
*/
public class DefaultCellZipping extends DefaultLineZipping {
	
	/**
	* Constructor	
	* @param selection The file index selection (maybe empty)
 	* @param params The list of parameters (maybe empty)
	* @see innovimax.mixthem.arguments.RuleParam
	* @see innovimax.mixthem.arguments.ParamValue
	*/
	public DefaultCellZipping(final Set<Integer> selection, final Map<RuleParam, ParamValue> params) {
		super(selection, params);		
	}
	
	@Override
	public void process(final List<String> lineRange, final LineResult result) throws MixException {
		//System.out.println("RANGE="+lineRange.toString());
		StringBuilder zip = new StringBuilder();
		final List<Iterator<String>> cellIterators = new ArrayList<Iterator<String>>();
		for (String line : lineRange) {
			cellIterators.add(Arrays.asList(line.split(CellOperation.DEFAULT_SPLIT_CELL_REGEX.getValue().asString())).iterator());
		}				
		while (hasCellRange(cellIterators)) {
			if (zip.length() > 0) {
				zip.append(CellOperation.DEFAULT_CELL_SEPARATOR.getValue().asString());
			}
			final List<String> cellRange = nextCellRange(cellIterators);
			//System.out.println("CELLS="+cellRange);
			int index = 0;
			for (String cell : cellRange) {
				if (index > 0) {
					zip.append(this.sep);
				}
				zip.append(cell);
				index++;
			}
		}			
		result.setResult(zip.toString());
	}

	private boolean hasCellRange(List<Iterator<String>> cellIterators) {
		for (Iterator<String> cellIterator : cellIterators) {
			if (!cellIterator.hasNext()) {
				return false;
			}
		}
		return true;
	}
	
	private List<String> nextCellRange(List<Iterator<String>> cellIterators) {
		final List<String> cellRange = new ArrayList<String>();
		for (Iterator<String> cellIterator : cellIterators) {
			cellRange.add(cellIterator.next());
		}
		return cellRange;
	}
	
}
