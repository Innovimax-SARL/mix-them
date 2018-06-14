package innovimax.mixthem.operation;

import innovimax.mixthem.io.IToken;
import innovimax.mixthem.io.ITokenStatusRange;

import java.util.List;

/**
* This interface provides for manage the result of mix operations.
* @author Innovimax
* @version 1.0
*/
public interface ITokenResult {
    /**
    * Reset the result.
    */
    void reset();
    /**
    * Set the result from a list of tokens (maybe null).
    * @param tokens The resulting tokens
    */
    void setResult(final List<IToken> tokens);
    /**
    * Set the result from one token (maybe null).
    * @param token The resulting token
    */
    void setResult(final IToken token);
    /**
    * Returns the result (maybe null).
    * @return Returns The token(s) result
    */
    List<IToken> getResult();
    /**
    * Has a mixing result ?
    * @return Returns true if result not null
    */
    boolean hasResult();
    /**
    * Set if next reading is necessary at specified channel of the current range.
    */
    void setRangeTokenStatus(final int channel, final boolean reading);
    /**
    * Get next reading range
    */
    ITokenStatusRange getTokenStatusRange();
}
