package innovimax.mixthem.operation;

import innovimax.mixthem.arguments.ParamValue;
import innovimax.mixthem.arguments.RuleParam;
import innovimax.mixthem.arguments.TokenType;
import innovimax.mixthem.io.ITokenRange;
import innovimax.mixthem.io.Token;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

/**
* <p>Joins two or more lines on a common field.</p>
* <p>Joining doesn't operate when no common field is found for all lines.</p>
* <p>In this case, we prevent bigger join field file(s) from being read, keeping current line(s).</p>
* @see ITokenOperation
* @author Innovimax
* @version 1.0
*/
public class DefaultLineJoining extends AbstractTokenOperation {
  
	private final int[] joinCols;	
	
	/**
	* @param selection The file index selection (maybe empty)
	* @param params The list of parameters (maybe empty)
	* @see innovimax.mixthem.arguments.RuleParam
	* @see innovimax.mixthem.arguments.ParamValue
	*/
	public DefaultLineJoining(final Set<Integer> selection, final TokenType tokenType, final Map<RuleParam, ParamValue> params) {
		super(selection, tokenType, params);		
		if (this.params.containsKey(RuleParam.JOIN_COLS)) {
			this.joinCols = this.params.get(RuleParam.JOIN_COLS).asIntArray();
		} else {
			this.joinCols = new int[1];
			this.joinCols[0] = JoinOperation.DEFAULT_JOIN_COLUMN.getValue().asInt();
		}
		//System.out.println("COLS="+Arrays.toString(this.joinCols));
	} 

	@Override
	public void process(final ITokenRange tokenRange, final ITokenResult result) {
		//System.out.println("LINES="+tokenRange.toString());		
		//System.out.println("READING="+result.getTokenStatusRange().toString());			
		final List<List<String>> lineCellsRange =
			tokenRange.asList().stream()
				.map(token -> Arrays.asList(token.asString()
					.split(CellOperation.DEFAULT_SPLIT_CELL_REGEX.getValue().asString())))
				.collect(Collectors.toList());
		if (IntStream.range(0, lineCellsRange.size())
				.allMatch(index -> lineCellsRange.get(index).size() >= this.getJoinedColumn(index))) {
			final String joinCell = lineCellsRange.get(0).get(this.getJoinedColumn(0) - 1);
			if (IntStream.range(0, lineCellsRange.size())
					.allMatch(index -> joinCell.equals(lineCellsRange.get(index).get(this.getJoinedColumn(index) - 1)))) {
				joinLines(lineCellsRange, joinCell, result);			
				//System.out.println("JOINED="+result.getResult());
			} else {
				this.setRangeTokenStatus(lineCellsRange, result);
			}
		}
	}
	
	private int getJoinedColumn(final int index) {
		if (this.joinCols.length == 1) {
			return this.joinCols[0];
		} else if (index < this.joinCols.length && this.joinCols[index] > 0) {
			return this.joinCols[index];
		} else {
			return JoinOperation.DEFAULT_JOIN_COLUMN.getValue().asInt();
		}
	}

	private static void joinLines(final Iterable<List<String>> lineCellsRange, final String joinCell, final ITokenResult result) {
		final StringBuilder joinedCells = new StringBuilder();		
		joinedCells.append(joinCell);
		for (final List<String> lineCells : lineCellsRange) {
			joinedCells.append(CellOperation.DEFAULT_CELL_SEPARATOR.getValue().asString());
			joinedCells.append(
				lineCells.stream()
					.filter(s -> !s.equals(joinCell))
					.collect(Collectors.joining(CellOperation.DEFAULT_CELL_SEPARATOR.getValue().asString())));
		}
		result.setResult(Token.createLineToken(joinedCells.toString()));
	}

	private void setRangeTokenStatus(final List<List<String>> lineCellsRange, final ITokenResult result) {
		final List<String> cellRange = 
			IntStream.range(0, lineCellsRange.size())
				.mapToObj(channel -> lineCellsRange.get(channel).get(this.getJoinedColumn(channel) - 1))
				.collect(Collectors.toList());
		final String greaterCell = this.searchGreaterCell(lineCellsRange);
		IntStream.range(0, cellRange.size())
				.filter(channel -> cellRange.get(channel).equals(greaterCell))
				.forEach(channel -> result.setRangeTokenStatus(channel, false));
	}

	private String searchGreaterCell(final List<List<String>> lineCellsRange) {
		String greaterCell = null;
		for (int i=0; i < lineCellsRange.size(); i++) {			
			final String cell = lineCellsRange.get(i).get(this.getJoinedColumn(i) - 1);
			if (greaterCell == null) {
				greaterCell = cell;
			}
			if (cell.compareTo(greaterCell) > 0) {
				greaterCell = cell;
			}
		}	
		return greaterCell;
	}

}
