package innovimax.mixthem.operation;

import innovimax.mixthem.MixException;
import innovimax.mixthem.arguments.ParamValue;
import innovimax.mixthem.arguments.RuleParam;
import innovimax.mixthem.arguments.TokenType;

import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

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
	public DefaultCellZipping(final Set<Integer> selection, final TokenType tokenType, final Map<RuleParam, ParamValue> params) {
		super(selection, tokenType, params);		
	}
	
	@Override
	public void process(final List<String> lineRange, final LineResult result) throws MixException {
		//System.out.println("RANGE="+lineRange.toString());
		StringBuilder zip = new StringBuilder();
		final List<Iterator<String>> cellIterators = lineRange.stream()
			.map(line -> Arrays.asList(line.split(CellOperation.DEFAULT_SPLIT_CELL_REGEX.getValue().asString())).iterator())
			.collect(Collectors.toList());
		while (cellIterators.stream().allMatch(iterator -> iterator.hasNext())) {
			if (zip.length() > 0) {
				zip.append(CellOperation.DEFAULT_CELL_SEPARATOR.getValue().asString());
			}
			final List<String> cellRange = cellIterators.stream()
				.map(iterator -> iterator.next())
				.collect(Collectors.toList());
			//System.out.println("CELLS="+cellRange);
			IntStream.range(0, cellRange.size())
				.mapToObj(index -> index > 0 ? 
						Stream.of(this.sep, cellRange.get(index)) : 
						Stream.of(cellRange.get(index)))					
				.flatMap(stream -> stream)
				.forEach(token -> zip.append(token));
		}			
		result.setResult(zip.toString());
	}
	
}
