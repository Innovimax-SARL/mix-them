package innovimax.mixthem.operation;

import innovimax.mixthem.MixException;
import innovimax.mixthem.arguments.ParamValue;
import innovimax.mixthem.arguments.RuleParam;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
* <p>Joins two or more lines on a common field.</p>
* <p>Joining doesn't operate when no common field is found for all lines.</p>
* <p>In this case, we prevent bigger join field file(s) from beeing read, keeping current line(s).</p>
* @see ILineOperation
* @author Innovimax
* @version 1.0
*/
public class DefaultLineJoining extends AbstractLineOperation {
  
	private final int[] joinCols;	
	
	/**
	* @param params The list of parameters (maybe empty)
	* @see innovimax.mixthem.operation.RuleParam
	* @see innovimax.mixthem.operation.ParamValue
	*/
	public DefaultLineJoining(final Map<RuleParam, ParamValue> params) {
		super(params);		
		if (this.params.containsKey(RuleParam.JOIN_COLS)) {
			this.joinCols = this.params.get(RuleParam.JOIN_COLS).asIntArray();
		} else {
			this.joinCols = new int[1];
			this.joinCols[0] = JoinOperation.DEFAULT_JOIN_COLUMN.getValue().asInt();
		}
		System.out.println("COLS="+Arrays.toString(this.joinCols));
	} 

	@Override
	public void process(final List<String> lineRange, final LineResult result) throws MixException {				
		System.out.println("LINES="+lineRange.toString());
		final List<Boolean> lineReadingRange = new ArrayList<Boolean>(result.getLineReadingRange());
		System.out.println("READ="+lineReadingRange.toString());		
		result.reset();				
		final List<List<String>> lineCellsRange = getLineCellsRange(lineRange);
		if (linesComparable(lineCellsRange)) {				
			if (linesJoined(lineCellsRange)) {					
				joinLines(lineCellsRange, result);					
				System.out.println("JOINED="+result.getResult());				
			} else {										
				setLineReadingPreservation(lineCellsRange, result);
			}
			for (int i=0; i < lineRange.size(); i++) {
				result.setRangeLine(i, lineRange.get(i));
			}
		}		
	}
	
	private List<List<String>> getLineCellsRange(final List<String> lineRange) {
		final List<List<String>> lineCellsRange = new ArrayList<List<String>>();
		for (String line : lineRange) {
			lineCellsRange.add(Arrays.asList(line.split(CellOperation.DEFAULT_SPLIT_CELL_REGEX.getValue().asString())));
		}
		return lineCellsRange;
	}
	
	private int getJoinedColumn(int index) {
		if (this.joinCols.length == 1) {
			return this.joinCols[0];
		} else if (index < this.joinCols.length && this.joinCols[index] > 0) {
			return this.joinCols[index];
		} else {
			return JoinOperation.DEFAULT_JOIN_COLUMN.getValue().asInt();
		}
	}
	
	private boolean linesComparable(final List<List<String>> lineCellsRange) {		
		for (int i=0; i < lineCellsRange.size(); i++) {
			final List<String> lineCells = lineCellsRange.get(i);
			if (lineCells.size() < getJoinedColumn(i)) {
				return false;
			}			
		}
		return true;
	}
	
	private boolean linesJoined(final List<List<String>> lineCellsRange) {		
		String joinCell = null;		
		for (int i=0; i < lineCellsRange.size(); i++) {			
			final List<String> lineCells = lineCellsRange.get(i);
			final String cell = lineCells.get(getJoinedColumn(i) - 1);
			if (joinCell == null) {
				joinCell = cell;
			}
			if (!cell.equals(joinCell)) {
				return false;
			}
		}
		return true;
	}
	
	private void setLineReadingPreservation(final List<List<String>> lineCellsRange, final LineResult result) {
		List<String> cellRange = new ArrayList<String>();
		String greaterCell = null;		
		for (int i=0; i < lineCellsRange.size(); i++) {
			final List<String> lineCells = lineCellsRange.get(i);
			final String cell = lineCells.get(getJoinedColumn(i) - 1);
			if (greaterCell == null) {
				greaterCell = cell;
			}
			if (cell.compareTo(greaterCell) > 0) {
				greaterCell = cell;
			}
			cellRange.add(cell);			
		}		
		for (int i=0; i < cellRange.size(); i++) {
			final String cell = cellRange.get(i);
			if (cell.equals(greaterCell)) {
				result.setRangeLineReadingStatus(i, false);
			}
		}
	}

	private void joinLines(final List<List<String>> lineCellsRange, final LineResult result) {
		final StringBuilder joinedCells = new StringBuilder();
		String join = lineCellsRange.get(0).get(getJoinedColumn(0) - 1);
		joinedCells.append(join);
		for (List<String> lineCells : lineCellsRange) {
			joinedCells.append(CellOperation.DEFAULT_CELL_SEPARATOR.getValue().asString());
			joinedCells.append(
				lineCells.stream()
					.filter(s -> !s.equals(join))
					.collect(Collectors.joining(CellOperation.DEFAULT_CELL_SEPARATOR.getValue().asString())));
		}
		result.setResult(joinedCells.toString());
	}

}
