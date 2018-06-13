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
	* @return The byte value
	*/
	byte asByte();
	/**
	* Returns the token value as a character.
	* @return The character value
	*/
	int asCharacter();
	/**
	* Returns the token value as a byte array.
	* @return The byte array
	*/
	byte[] asByteArray();
	/**
	* Returns the token value as a character array.
	* @return The character array
	*/
	char[] asCharacterArray();
	/**
	* Returns the token value as a string.
	* @return The string value
	*/
	String asString();
	/**
	* Returns the parameter value as a file input stream.
	* @return The file input stream
	*/
	InputStream asInputStream();
	/**
	* Returns the parameter value as a file reader.
	* @return The file reader
	*/
	Reader asReader();
}
