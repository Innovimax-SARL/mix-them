package innovimax.mixthem.io;

import java.util.ArrayList;
import java.util.List;

/**
* This is the implementation of ITokenRange interface.
* @see ITokenRange
* @author Innovimax
* @version 1.0
*/
public class TokenRange implements ITokenRange {

	final private List<IToken> range;

	public TokenRange(final List<IToken> range) {
		super();
		this.range = range;
	}

	public TokenRange() {
		this(new ArrayList<>());
	}

	@Override
	public String toString() {
		return this.range.toString();
	}
	
	public int size(){
		return this.range.size();
	}

	public List<IToken> asList() {
		return this.range;
	}
	
	public IToken getToken(final int channel) {
		return this.range.get(channel);
	}
	
	public void setToken(final int channel, final IToken token) {
		this.range.set(channel, token);
	}

	public void addToken(final IToken token) {
		this.range.add(token);
	}

}