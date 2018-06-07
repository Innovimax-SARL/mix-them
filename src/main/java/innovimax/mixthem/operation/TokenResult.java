package innovimax.mixthem.operation;

import innovimax.mixthem.io.IToken;

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
    private final List<IToken> tokenRange;
    private List<IToken> result;
    
    /**
    * Creates a token result.    
    */
    public TokenResult(final int rangeSize) {        
        this.rangeSize = rangeSize;
        this.readingRange = new ArrayList<Boolean>();
        this.tokenRange = new ArrayList<IToken>();
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
    void setResult(final List<IToken> tokens) {
        this.result = tokens;
    }

    /**
    * Set the result (maybe null).
    */
    void setResult(final IToken token) {
        this.result = new ArrayList<IToken>();
        this.result.add(token);
    }

    /**
    * Returns the result (maybe null).
    */
    List<IToken> getResult() {
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
    IToken getRangeToken(final int index) {
        return this.tokenRange.get(index);
    }

    /**
    * Set last read line at specified position in current range.
    */
    void setRangeToken(final int index, final IToken token) {
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
