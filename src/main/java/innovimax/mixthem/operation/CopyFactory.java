package innovimax.mixthem.operation;

import innovimax.mixthem.arguments.Mode;
import innovimax.mixthem.arguments.Rule;
import innovimax.mixthem.arguments.RuleParam;
import innovimax.mixthem.arguments.ParamValue;

import java.util.Map;

public class CopyFactory {
  
    public static ICopyOperation newInstance(final Mode mode, final Rule rule, final Map<RuleParam, ParamValue> params) {
        return mode == Mode.CHAR ? new DefaultCharCopy(rule, params) : new DefaultByteCopy(rule, params);
    }    
    
}
