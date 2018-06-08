package innovimax.mixthem.operation;

import innovimax.mixthem.io.IToken;
import innovimax.mixthem.io.ITokenRange;
import innovimax.mixthem.io.ITokenStatusRange;
import innovimax.mixthem.io.TokenRange;
import innovimax.mixthem.io.TokenStatusRange;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.IntStream;

/**
* <p>Describes the result of an operation on two or more files.</p>
* @author Innovimax
* @version 1.0
*/
public class TokenResult {
    
    private final int rangeSize;
    private final ITokenStatusRange tokenStatusRange;
    private final ITokenRange tokenRange;
    private List<IToken> result;
    
    /**
    * Creates a token result.    
    */
    public TokenResult(final int rangeSize) {        
        this.rangeSize = rangeSize;
        this.tokenStatusRange = new TokenStatusRange();
        this.tokenRange = new TokenRange();
        IntStream.range(0, this.rangeSize)
            .forEach(channel -> {
                this.tokenStatusRange.addTokenStatus(true);
                this.tokenRange.addToken(null);    
            });      
        this.result = null;
    }

    /**
    * Reset the result (but keep last read lines).
    */
    void reset() {
        this.result = null;
        IntStream.range(0, this.rangeSize)
            .forEach(channel -> this.tokenStatusRange.setTokenStatus(channel, true));       
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
    * Get last read token at specified channel from current range.
    */
    IToken getRangeToken(final int channel) {
        return this.tokenRange.getToken(channel);
    }

    /**
    * Set last read line at specified channel in current range.
    */
    void setRangeToken(final int channel, final IToken token) {
        this.tokenRange.setToken(channel, token);
    }
    
    /**
    * Set if next reading is necessary at specified channel of the current range.
    */
    void setRangeTokenStatus(final int channel, final boolean reading) {
        this.tokenStatusRange.setTokenStatus(channel, reading);
    }
   
    /**
    * Get next reading range
    */
    ITokenStatusRange getTokenStatusRange() {
        return this.tokenStatusRange;
    }

}
