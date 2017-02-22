package innovimax.mixthem;

import innovimax.mixthem.arguments.Rule;

import java.util.List;

/**
* @author Innovimax
* @version 1.0
*/
public class RuleRun {

  final private int testId;
  final private List<String> params;

  public RuleRun(List<String> params) {
    this(-1, params);
  }
  
  public RuleRun(int testId, List<String> params) {
    this.testId = testId;
    this.params = params;
  }
  
  public boolean accepted(int testId) {
    if (this.testId == -1) {
      return true;
    } else {
      return this.testId == testId;
    }
  }
  
  public List<String> getParams {
    return this.params;
  }

}
