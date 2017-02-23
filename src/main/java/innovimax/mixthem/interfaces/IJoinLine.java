package innovimax.mixthem.interfaces;

import innovimax.mixthem.exceptions.MixException;
import innovimax.mixthem.join.JoinType;

import java.util.List;

/**
* This interface provides for joining two lines
* @author Innovimax
* @version 1.0
*/
public interface IJoinLine {	
	String join(String line1, String line2, JoinType type, List<String> params) throws MixException;	
	JoinType getType(List<String> params) throws MixException;
}
