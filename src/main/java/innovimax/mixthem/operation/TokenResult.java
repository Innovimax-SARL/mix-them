package innovimax.mixthem.operation;

import innovimax.mixthem.io.IToken;
import innovimax.mixthem.io.ITokenStatusRange;
import innovimax.mixthem.io.TokenStatusRange;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.IntStream;

/**
* This is the implementation of ITokenResult interface.
* @author Innovimax
* @version 1.0
*/
public class TokenResult implements ITokenResult {
    
    private final int rangeSize;    
    private List<IToken> result;
    private final ITokenStatusRange tokenStatusRange;
    
    /**
    * Creates a token result.    
    */
    public TokenResult(final int rangeSize) {        
        this.rangeSize = rangeSize;
        this.result = null;
        this.tokenStatusRange = new TokenStatusRange();
        IntStream.range(0, this.rangeSize)
            .forEach(channel -> this.tokenStatusRange.addTokenStatus(true));
    }

    @Override
    public void reset() {
        this.result = null;
        IntStream.range(0, this.rangeSize)
            .forEach(channel -> this.tokenStatusRange.setTokenStatus(channel, true));       
    }
    
    @Override
    public void setResult(final List<IToken> tokens) {
        this.result = tokens;
    }

    @Override
    public void setResult(final IToken token) {
        this.result = new ArrayList<IToken>();
        this.result.add(token);
    }

    @Override
    public List<IToken> getResult() {
        return this.result;
    }

    @Override
    public boolean hasResult() {
        return this.result != null;
    }

    @Override
    public void setRangeTokenStatus(final int channel, final boolean reading) {
        this.tokenStatusRange.setTokenStatus(channel, reading);
    }
   
    @Override
    public ITokenStatusRange getTokenStatusRange() {
        return this.tokenStatusRange;
    }

}
