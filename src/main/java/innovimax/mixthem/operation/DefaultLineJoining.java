package innovimax.mixthem.operation;

import innovimax.mixthem.MixException;
import innovimax.mixthem.arguments.ParamValue;
import innovimax.mixthem.arguments.RuleParam;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
* <p>Joins two or more lines on a common field.</p>
* <p>Joining stops when no common field is found.</p>
* @see ILineOperation
* @author Innovimax
* @version 1.0
*/
public class DefaultLineJoining extends AbstractLineOperation {
  
	private final int col1;
	private final int col2;
	
	/**
	* @param params The list of parameters (maybe empty)
	* @see innovimax.mixthem.operation.RuleParam
	* @see innovimax.mixthem.operation.ParamValue
	*/
	public DefaultLineJoining(final Map<RuleParam, ParamValue> params) {
		super(params);
		this.col1 = this.params.getOrDefault(RuleParam.JOIN_COL1, JoinOperation.DEFAULT_JOIN_COLUMN.getValue()).asInt();
		this.col2 = this.params.getOrDefault(RuleParam.JOIN_COL2, JoinOperation.DEFAULT_JOIN_COLUMN.getValue()).asInt();
	} 

	@Deprecated
	public void process(final String line1, final String line2, final LineResult result) throws MixException {
		final boolean firstPreserved = !result.getLineReadingRange().get(0).booleanValue();
		final boolean secondPreserved = !result.getLineReadingRange().get(1).booleanValue();
		result.reset();
		if (line1 != null && line2 != null) {
			final List<String> list1 = Arrays.asList(line1.split(CellOperation.DEFAULT_SPLIT_CELL_REGEX.toString()));
			final List<String> list2 = Arrays.asList(line2.split(CellOperation.DEFAULT_SPLIT_CELL_REGEX.toString()));   
			final String cell1 = list1.size() >= this.col1 ? list1.get(this.col1 - 1) : null;
			final String cell2 = list2.size() >= this.col2 ? list2.get(this.col2 - 1) : null;
			if (cell1 != null && cell2 != null) {
				final List<String> prevList1 = result.hasRangeLine(0) ?
					Arrays.asList(result.getRangeLine(0).split(CellOperation.DEFAULT_SPLIT_CELL_REGEX.getValue().asString())) :
					Collections.emptyList();
				final  List<String> prevList2 = result.hasRangeLine(1) ?
					Arrays.asList(result.getRangeLine(1).split(CellOperation.DEFAULT_SPLIT_CELL_REGEX.getValue().asString())) :
					Collections.emptyList();
				final String prevCell1 = prevList1.size() >= this.col1 ? prevList1.get(this.col1 - 1) : null;
				final String prevCell2 = prevList2.size() >= this.col2 ? prevList2.get(this.col2 - 1) : null;
				/*System.out.println("LINE1=" + line1 + " / CELL1=" + cell1);
				System.out.println("LINE2=" + line2 + " / CELL2=" + cell2);
				System.out.println("PVLINE1=" + result.getRangeLine(0) + " / PVCELL1=" + prevCell1);
				System.out.println("PVLINE2=" + result.getRangeLine(1) + " / PVCELL2=" + prevCell2);*/
				if (cell2.equals(prevCell2) && !secondPreserved) {
					//System.out.println("PREVIOUS 2");
					joinLines(prevList1, list2, result);
					result.setRangeLineReadingStatus(0, false);
					result.keepRangeLine(0, line1);
					result.setRangeLine(1, line2);
				} else if (cell1.equals(prevCell1) && !firstPreserved) {
					//System.out.println("PREVIOUS 1");
					joinLines(list1, prevList2, result);
					result.setRangeLineReadingStatus(1, false);
					result.setRangeLine(0, line1);
					result.keepRangeLine(1, line2);
				} else {
					switch (Integer.signum(cell1.compareTo(cell2))) {
						case 0:
							//System.out.println("EQUALS");
							joinLines(list1, list2, result);
							break;
						case 1:
							//System.out.println("PRESERVE 1");
							result.setRangeLineReadingStatus(0, false);
							break;
						default:
							//System.out.println("PRESERVE 2");
							result.setRangeLineReadingStatus(1, false);
					}
					result.setRangeLine(0, line1);
					result.setRangeLine(1, line2);
				}
			}
		}
	}

	@Deprecated
	private void joinLines(final List<String> list1, final List<String> list2, final LineResult result) {
		final String part1 = list1.get(this.col1 - 1);
		final String part2 = list1.stream().filter(s -> !s.equals(part1)).collect(Collectors.joining(CellOperation.DEFAULT_CELL_SEPARATOR.getValue().asString()));
		final String part3 = list2.stream().filter(s -> !s.equals(part1)).collect(Collectors.joining(CellOperation.DEFAULT_CELL_SEPARATOR.getValue().asString()));
		result.setResult(part1 + CellOperation.DEFAULT_CELL_SEPARATOR.getValue().asString()  + 
				 part2 + CellOperation.DEFAULT_CELL_SEPARATOR.getValue().asString() + part3);
	}
	
	@Override
	public void process(final List<String> lineRange, final LineResult result) throws MixException {		
		process(lineRange.get(0), lineRange.get(1), result);
		/*System.out.println("LINES="+lineRange.toString());
		final List<Boolean> lineReadingRange = new ArrayList<Boolean>(result.getLineReadingRange());
		System.out.println("READ="+lineReadingRange.toString());		
		result.reset();		
		if (linesJoinable(lineRange)) {
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

		}*/		
	}
	
	private boolean linesJoinable(final List<String> lineRange) {		
		for (int i=0; i < lineRange.size(); i++) {			
			if (lineRange.get(i) == null) {
				return false;				
			}
		}
		return true;	
	}
	
	private List<List<String>> getLineCellsRange(final List<String> lineRange) {
		final List<List<String>> lineCellsRange = new ArrayList<List<String>>();
		for (String line : lineRange) {
			lineCellsRange.add(Arrays.asList(line.split(CellOperation.DEFAULT_SPLIT_CELL_REGEX.getValue().asString())));
		}
		return lineCellsRange;
	}
	
	private boolean linesComparable(final List<List<String>> lineCellsRange) {		
		for (int i=0; i < lineCellsRange.size(); i++) {
			final List<String> lineCells = lineCellsRange.get(i);
			if (lineCells.size() < (i == 1 ? this.col2 : this.col1)) {
				return false;
			}			
		}
		return true;
	}
	
	private boolean linesJoined(final List<List<String>> lineCellsRange) {		
		String joinCell = null;		
		for (int i=0; i < lineCellsRange.size(); i++) {
			final List<String> lineCells = lineCellsRange.get(i);
			final String cell = lineCells.get((i == 1 ? this.col2 : this.col1) - 1);
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
			final String cell = lineCells.get((i == 1 ? this.col2 : this.col1) - 1);
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
		String join = lineCellsRange.get(0).get(this.col1 - 1);
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
