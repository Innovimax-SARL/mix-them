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

  @Override
  public void process(final String line1, final String line2, final LineResult result) throws MixException {
    final boolean firstPreserved = result.firstLinePreserved();
    final boolean secondPreserved = result.secondLinePreserved();
    result.reset();
    if (line1 != null && line2 != null) {
      final List<String> list1 = Arrays.asList(line1.split(CellOperation.DEFAULT_SPLIT_CELL_REGEX.toString()));
      final List<String> list2 = Arrays.asList(line2.split(CellOperation.DEFAULT_SPLIT_CELL_REGEX.toString()));   
      final String cell1 = list1.size() >= this.col1 ? list1.get(this.col1 - 1) : null;
      final String cell2 = list2.size() >= this.col2 ? list2.get(this.col2 - 1) : null;
      if (cell1 != null && cell2 != null) {
        final List<String> prevList1 = result.hasFirstLine() ?
                        Arrays.asList(result.getFirstLine().split(CellOperation.DEFAULT_SPLIT_CELL_REGEX.getValue().asString())) :
                        Collections.emptyList();
       final  List<String> prevList2 = result.hasSecondLine() ?
                        Arrays.asList(result.getSecondLine().split(CellOperation.DEFAULT_SPLIT_CELL_REGEX.getValue().asString())) :
                        Collections.emptyList();    
        final String prevCell1 = prevList1.size() >= this.col1 ? prevList1.get(this.col1 - 1) : null;
        final String prevCell2 = prevList2.size() >= this.col2 ? prevList2.get(this.col2 - 1) : null;
        /*System.out.println("LINE1=" + line1 + " / CELL1=" + cell1);
        System.out.println("LINE2=" + line2 + " / CELL2=" + cell2);
        System.out.println("PVLINE1=" + result.getFirstLine() + " / PVCELL1=" + prevCell1);
        System.out.println("PVLINE2=" + result.getSecondLine() + " / PVCELL2=" + prevCell2);*/
        if (cell2.equals(prevCell2) && !secondPreserved) {
          //System.out.println("PREVIOUS 2");
          joinLines(prevList1, list2, result);
          result.preserveFirstLine();
          result.keepFirstLine(line1);
          result.setSecondLine(line2);
        } else if (cell1.equals(prevCell1) && !firstPreserved) {
          //System.out.println("PREVIOUS 1");
          joinLines(list1, prevList2, result);
          result.preserveSecondLine();
          result.setFirstLine(line1);
          result.keepSecondLine(line2);
        } else {
          switch (Integer.signum(cell1.compareTo(cell2))) {
            case 0:
              //System.out.println("EQUALS");
              joinLines(list1, list2, result);            
              break;
            case 1:           
              //System.out.println("PRESERVE 1");
              result.preserveFirstLine();
              break;
            default:            
              //System.out.println("PRESERVE 2");
              result.preserveSecondLine();
          }
          result.setFirstLine(line1);
          result.setSecondLine(line2);
        }
      }
    }   
  }
  
  @Override
  public void process(final List<String> lineRange, final LineResult result) throws MixException {
    //TODO
    process(lineRange.get(0), lineRange.get(1), result);
  }
	
  private void joinLines(final List<String> list1, final List<String> list2, final LineResult result) {
    final String part1 = list1.get(this.col1 - 1);
    final String part2 = list1.stream().filter(s -> !s.equals(part1)).collect(Collectors.joining(CellOperation.DEFAULT_CELL_SEPARATOR.getValue().asString()));
    final String part3 = list2.stream().filter(s -> !s.equals(part1)).collect(Collectors.joining(CellOperation.DEFAULT_CELL_SEPARATOR.getValue().asString()));
    result.setResult(part1 + CellOperation.DEFAULT_CELL_SEPARATOR.getValue().asString()  + 
         part2 + CellOperation.DEFAULT_CELL_SEPARATOR.getValue().asString() + part3);
  }

}
