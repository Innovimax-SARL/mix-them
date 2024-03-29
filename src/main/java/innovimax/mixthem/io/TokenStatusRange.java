package innovimax.mixthem.io;

import java.util.ArrayList;
import java.util.List;

/**
* This is the implementation of ITokenStatusRange interface.
* @see ITokenStatusRange
* @author Innovimax
* @version 1.0
*/
public class TokenStatusRange implements ITokenStatusRange {

	final private List<Boolean> range;

	public TokenStatusRange() {
		super();
		this.range = new ArrayList<>();
	}
	
	public int size(){
		return this.range.size();
	}
	
	public void addTokenStatus(final boolean reading) {
		this.range.add(reading);
	}
	
	public void setTokenStatus(final int channel, final boolean reading) {
		this.range.set(channel, reading);
	}

	public boolean readingToken(final int channel) {
		return this.range.get(channel);
	}

}