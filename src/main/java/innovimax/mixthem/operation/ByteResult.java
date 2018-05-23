package innovimax.mixthem.operation;

import java.util.stream.IntStream;

/**
* <p>Describes the result of a byte operation on two files.</p>
* @author Innovimax
* @version 1.0
*/
public class ByteResult {
    
    private IntStream result;    
    
    /**
    * Creates a byte result.    
    */
    public ByteResult() {
        this.result = null;        
    }

    /**
    * Reset the result.
    */
    void reset() {        
        this.result = null;
    }
    
    /**
    * Set the result (maybe null).
    */
    void setResult(final IntStream result) {
        this.result = result;
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
        return this.result != null;
    }

}
