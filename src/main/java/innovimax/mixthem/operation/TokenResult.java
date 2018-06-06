package innovimax.mixthem.operation;

import innovimax.mixthem.io.Token;

import java.util.ArrayList;
import java.util.List;

/**
* <p>Describes the result of an operation on two or more files.</p>
* @author Innovimax
* @version 1.0
*/
public class TokenResult {
    
    private final int rangeSize;
    private final List<Boolean> readingRange;
    private final List<Token> tokenRange;
    private List<Token> result;
    
    /**
    * Creates a token result.    
    */
    public TokenResult(final int rangeSize) {        
        this.rangeSize = rangeSize;
        this.readingRange = new ArrayList<Boolean>();
        this.tokenRange = new ArrayList<Token>();
        for (int i=0; i < this.rangeSize; i++) {
            this.readingRange.add(Boolean.TRUE);
            this.tokenRange.add(null);
        }
        this.result = null;
    }

    /**
    * Reset the result (but keep last read lines).
    */
    void reset() {
        this.result = null;
        for (int i=0; i < this.rangeSize; i++) {
            this.readingRange.set(i, Boolean.TRUE);            
        }
    }
    
    /**
    * Set the result (maybe null).
    */
    void setResult(final List<Token> tokens) {
        this.result = tokens;
    }

    /**
    * Set the result (maybe null).
    */
    void setResult(final Token token) {
        this.result = new ArrayList<Token>();
        this.result.add(token);
    }

    /**
    * Returns the result (maybe null).
    */
    List<Token> getResult() {
        return this.result;
    }

    /**
    * Has a non null result ?
    */
    boolean hasResult() {
        return this.result != null;
    }

    /**
    * Get last read token at specified position from current range.
    */
    Token getRangeToken(final int index) {
        return this.tokenRange.get(index);
    }

    /**
    * Set last read line at specified position in current range.
    */
    void setRangeToken(final int index, final Token token) {
        this.tokenRange.set(index, token);
    }
    
    /**
    * Set if next reading is necessary at specified position of the current range.
    */
    void setRangeReading(final int index, final boolean reading) {
        this.readingRange.set(index, Boolean.valueOf(reading));
    }
   
    /**
    * Get next reading range
    */
    List<Boolean> getReadingRange() {
        return this.readingRange;
    }

}
