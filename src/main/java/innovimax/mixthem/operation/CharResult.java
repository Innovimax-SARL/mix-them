package innovimax.mixthem.operation;

import java.util.EnumSet;
import java.util.stream.IntStream;

/**
* <p>Describes the result of an character operation on two files.</p>
* @author Innovimax
* @version 1.0
*/
public class CharResult {
    
    private final EnumSet<ResultType> types;
    private IntStream result;    
    
    /**
    * Creates a character result.    
    */
    public CharResult() {
        this.types = EnumSet.noneOf(ResultType.class);
        this.result = null;        
    }

    /**
    * Reset the result.
    */
    void reset() {        
        this.types.clear();
        this.result = null;
    }
    
    /**
    * Set the result (maybe null).
    */
    void setResult(IntStream result) {
        this.result = result;
        if (result != null) {
            this.types.add(ResultType.HAS_RESULT);
        }
    }

    /**
    * Returns the result (maybe null).
    */
    IntStream getResult() {
        return this.result;
    }

    /**
    * Has a result ?
    */
    boolean hasResult() {
        return this.types.contains(ResultType.HAS_RESULT);     
    }

}
