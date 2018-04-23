package innovimax.mixthem.operation;

import innovimax.mixthem.MixException;
import innovimax.mixthem.arguments.ParamValue;
import innovimax.mixthem.arguments.RuleParam;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
* <p>Joins two lines on a common field.</p>
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
  public DefaultLineJoining(Map<RuleParam, ParamValue> params) {
    super(params);
    this.col1 = this.params.getOrDefault(RuleParam.JOIN_COL1, JoinOperation.DEFAULT_JOIN_COLUMN.getValue()).asInt();
    this.col2 = this.params.getOrDefault(RuleParam.JOIN_COL2, JoinOperation.DEFAULT_JOIN_COLUMN.getValue()).asInt();
  } 

  @Override
  public void process(String line1, String line2, LineResult result) throws MixException {
    result.reset();
    if (line1 != null && line2 != null) {
      List<String> list1 = Arrays.asList(line1.split(CellOperation.DEFAULT_SPLIT_CELL_REGEX.toString()));
      List<String> list2 = Arrays.asList(line2.split(CellOperation.DEFAULT_SPLIT_CELL_REGEX.toString()));   
      String cell1 = list1.size() >= this.col1 ? list1.get(this.col1 - 1) : null;
      String cell2 = list2.size() >= this.col2 ? list2.get(this.col2 - 1) : null;
      if (cell1 != null && cell2 != null) {
        List<String> prevList1 = result.hasFirstLine() ?
                        Arrays.asList(result.getFirstLine().split(CellOperation.DEFAULT_SPLIT_CELL_REGEX.toString())) :
                        Collections.emptyList();
        List<String> prevList2 = result.hasSecondLine() ?
                        Arrays.asList(result.getSecondLine().split(CellOperation.DEFAULT_SPLIT_CELL_REGEX.toString())) :
                        Collections.emptyList();    
        String prevCell1 = prevList1.size() >= this.col1 ? prevList1.get(this.col1 - 1) : null;
        String prevCell2 = prevList2.size() >= this.col2 ? prevList2.get(this.col2 - 1) : null;
        if (cell1.equals(prevCell1)) {
          joinLines(list1, prevList2, result);
          result.preserveSecondLine();
          result.setFirstLine(line1);
        } else if (cell2.equals(prevCell2)) {
          joinLines(prevList1, list2, result);
          result.preserveFirstLine();
          result.setSecondLine(line2);
        } else {
          switch (cell1.compareTo(cell2)) {
            case 0:
              joinLines(list1, list2, result);            
              break;
            case 1:           
              result.preserveFirstLine();
              break;
            default:            
              result.preserveSecondLine();
          }
          result.setFirstLine(line1);
          result.setSecondLine(line2);
        }
      }
    }   
  }

  private void joinLines(List<String> list1, List<String> list2, LineResult result) {
    String part1 = list1.get(this.col1 - 1);
    String part2 = list1.stream().filter(s -> !s.equals(part1)).collect(Collectors.joining(CellOperation.DEFAULT_CELL_SEPARATOR.toString()));
    String part3 = list2.stream().filter(s -> !s.equals(part1)).collect(Collectors.joining(CellOperation.DEFAULT_CELL_SEPARATOR.toString()));
    result.setResult(part1 + CellOperation.DEFAULT_CELL_SEPARATOR.toString()  + 
         part2 + CellOperation.DEFAULT_CELL_SEPARATOR.toString() + part3);
  }

}
