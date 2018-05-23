package innovimax.mixthem.operation;

import innovimax.mixthem.arguments.FileMode;
import innovimax.mixthem.arguments.RuleParam;
import innovimax.mixthem.arguments.ParamValue;

import java.util.Map;

public class CopyFactory {
  
  	/**
    * @param fileMode The file mode for reading files
    * @param copyMode the copy mode to process
    * @see innovimax.mixthem.arguments.RulePara
    * @see innovimax.mixthem.arguments.ParamValue
    */
    public static ICopyOperation newInstance(final FileMode fileMode, final CopyMode copyMode, final Map<RuleParam, ParamValue> params) {
        return fileMode == FileMode.CHAR ? new DefaultCharCopy(copyMode, params) : new DefaultByteCopy(copyMode, params);
    }    
    
}
