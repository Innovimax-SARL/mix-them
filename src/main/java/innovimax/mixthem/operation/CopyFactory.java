package innovimax.mixthem.operation;

import innovimax.mixthem.arguments.FileMode;
import innovimax.mixthem.arguments.ParamValue;
import innovimax.mixthem.arguments.RuleParam;
import innovimax.mixthem.arguments.TokenType;

import java.util.Map;
import java.util.Set;

public class CopyFactory {
  
	/**
	* @param fileMode The file mode for reading files
	* @param selection The file index selection (maybe empty)
	* @param params The list of parameters (maybe empty)
	* @see innovimax.mixthem.arguments.ParamValue
	* @see innovimax.mixthem.arguments.RuleParam
	* @see innovimax.mixthem.arguments.TokenType
	*/
	public static ICopyOperation newInstance(final FileMode fileMode, final Set<Integer> selection, final Map<RuleParam, ParamValue> params) {
		return fileMode == FileMode.CHAR ? 
                        new DefaultCharCopy(selection, TokenType.FILECHAR, params) : 
                        new DefaultByteCopy(selection, TokenType.FILEBYTE, params);
	}    
    
}
