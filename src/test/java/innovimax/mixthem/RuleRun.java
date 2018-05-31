package innovimax.mixthem;

import innovimax.mixthem.arguments.ParamValue;
import innovimax.mixthem.arguments.Rule;
import innovimax.mixthem.arguments.RuleParam;

import java.util.Map;
import java.util.Set;

/**
* <p>Describes a specific test run for a rule.</p>
* <p>Depends on the rule, test runs may have some parameter values or not.</p>
* @author Innovimax
* @version 1.0
*/
public class RuleRun {

  final private int index;
  final private Set<Integer> selection;
  final private Map<RuleParam, ParamValue> params;  

  /*
  * Creates a rule run for a specific test.
  * @param index The position of the run
  * @param The file index selection for this run (maybe empty)
  * @param params The list of parameter values for this run (maybe empty)
  */
  public RuleRun(final int index, final Set<Integer> selection, final Map<RuleParam, ParamValue> params) {    
    this.index = index;
    this.selection = selection;
    this.params = params;    
  }
  
  /*
  * Returns true if the run has a specific suffix.  
  * @return Returns true if the run has a specific suffix
  */
  public boolean hasSuffix() {    
    return this.index > 1;
  }
  
  /*
  * Returns the specific suffix attached to the run.
  * @return The specific suffix attached to the run
  */
  public String getSuffix() {    
    return Integer.toString(this.index);
  }

  /*
  * Returns the file index selection for this run.
  * @return The file index selection for this run
  */
  public Set<Integer> getSelection() {
    return this.selection;
  }
  
  /*
  * Returns the list of parameter values for this run.
  * @return The list of parameter values for this run
  */
  public Map<RuleParam, ParamValue> getParams() {
    return this.params;
  }

}
