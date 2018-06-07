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
	
	public IToken getToken(final int index) {
		return this.range.get(index);
	}
	
	public void setToken(final int index, final IToken token) {
		this.range.set(index, token);
	}

}