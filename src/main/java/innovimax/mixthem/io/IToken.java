package innovimax.mixthem.io;

import java.io.InputStream;
import java.io.Reader;

/**
* This interface provides for any token representation.
* @author Innovimax
* @version 1.0
*/
public interface IToken {
	/**
	* Indicates if token is empty.
	* @return True if token is empty
	*/
	boolean isEmpty();
	/**
	* Returns the token value as a byte.
	* @return The token value as a byte
	*/
	byte asByte();
	/**
	* Returns the token value as a character.
	* @return The token value as a character
	*/
	int asCharacter();
	/**
	* Returns the token value as a string.
	* @return The token value as a string
	*/
	String asString();
	/**
	* Returns the parameter value as a file input stream.
	* @return The parameter value as a file input stream
	*/
	InputStream asInputStream();
	/**
	* Returns the parameter value as a file reader.
	* @return The parameter value as a file reader
	*/
	Reader asReader();
}
