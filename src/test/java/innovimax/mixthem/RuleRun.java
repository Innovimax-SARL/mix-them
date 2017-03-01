package innovimax.mixthem;

import innovimax.mixthem.arguments.ParamValue;
import innovimax.mixthem.arguments.Rule;
import innovimax.mixthem.arguments.RuleParam;

import java.util.Map;

/**
* <p>Describes a specific test run for a rule.</p>
* <p>Depends on the rule, test runs may have some parameter values or not.</p>
* @author Innovimax
* @version 1.0
*/
public class RuleRun {

  final private Map<RuleParam, ParamValue> params;
  final private String suffix;

  /*
  * Creates a rule run for a specific test.  
  * @param params The list of parameter values for this run
  */
  public RuleRun(Map<RuleParam, ParamValue> params) {
    this(null, params);
  }

  /*
  * Creates a rule run for a specific test.
  * @param suffix The specific suffix attached to the run (maybe null)
  * @param params The list of parameter values for this run
  */
  public RuleRun(String suffix, Map<RuleParam, ParamValue> params) {    
    this.suffix = suffix;
    this.params = params;    
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
  * @return Returns the list of parameter values for this run
  */
  public Map<RuleParam, ParamValue> getParams() {
    return this.params;
  }

}
