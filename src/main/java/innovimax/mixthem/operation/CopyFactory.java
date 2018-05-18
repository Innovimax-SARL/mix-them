package innovimax.mixthem.operation;

import innovimax.mixthem.arguments.Mode;
import innovimax.mixthem.arguments.RuleParam;
import innovimax.mixthem.arguments.ParamValue;

import java.util.Map;

public class CopyFactory {
  
    public static ICopy newInstance(final Mode mode, final Map<RuleParam, ParamValue> params) {
        return mode == Mode.CHAR ? new DefaultCharCopy(params) : new DefaultByteCopy(params);
    }    
    
}
