package innovimax.mixthem;

import innovimax.mixthem.arguments.Rule;

import java.util.List;

/**
* <p>Describes a specific test run for a rule.</p>
* <p>Depends on the rule, test runs may have some parameter values or not.</p>
* @author Innovimax
* @version 1.0
*/
public class RuleRun {

  final private int testId;
  final private List<String> params;
  final private String suffix;

  /*
  * Creates a rule run for all tests.
  */
  public RuleRun(List<String> params) {
    this(-1, null, params);
  }
 
  /*
  * Creates a rule run for a specific test.
  * @param testId The test identifier attached
  * @param params The list if parameter values for this run
  */
  public RuleRun(int testId, List<String> params) {
    this(testId, null, params);
  }

  /*
  * Creates a rule run for a specific test.
  * @param testId The test identifier attached
  * @param suffix The specific suffix attached to the run (maybe null)
  * @param params The list if parameter values for this run
  */
  public RuleRun(int testId, String suffix, List<String> params) {
    this.testId = testId;
    this.suffix = suffix;
    this.params = params;    
  }
  
  /*
  * Returns true if the test is authorized for the run.
  * @param testId The test identifier attached
  * @param params The list if parameter values for this run
  * @return Returns true if the test is authorized for the run
  */
  public boolean accept(int testId) {
    if (this.testId == -1) {
      return true;
    } else {
      return this.testId == testId;
    }
  }

  /*
  * Returns true if the run has a specific suffix.  
  * @return Returns true if the run has a specific suffix
  */
  public boolean hasSuffix() {    
    return this.suffix != null;
  }
  
  /*
  * Returns the specific suffix attached to the run.
  * @return The specific suffix attached to the run
  */
  public String getSuffix() {    
    return this.suffix;
  }
  
  /*
  * Returns the list of parameter values for this run.
  * @return Returns the list of parameter values for this run.
  */
  public List<String> getParams() {
    return this.params;
  }

}
