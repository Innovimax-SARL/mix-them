package innovimax.mixthem.io;

import java.util.List;

/**
* This is the implementation of ITokenRange interface.
* @see ITokenRange
* @author Innovimax
* @version 1.0
*/
public class TokenRange implements ITokenRange {

	final private List<IToken> range;

	public TokenRange(List<IToken> range) {
		this.range = range;
	}
	
	public int size(){
		return this.range.size();
	}
	
	public IToken getToken(final int channel) {
		return this.range.get(channel);
	}
	
	public void setToken(final int channel, final IToken token) {
		this.range.set(channel, token);
	}

}