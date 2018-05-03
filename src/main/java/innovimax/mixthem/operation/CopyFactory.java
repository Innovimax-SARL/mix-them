package innovimax.mixthem.operation;

import innovimax.mixthem.arguments.Mode;

public class CopyFactory {
  
    public static ICopy newInstance(Mode mode) {
        return mode == Mode.CHAR ? new DefaultCharCopy() : new DefaultByteCopy();
    }    
    
}
