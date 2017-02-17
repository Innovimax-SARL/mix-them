package innovimax.mixthem.io;

/**
* <p>This is a enumeration of reading type.</p>
* <p>Here are the types:</p>
* <ul>
* <li>_REGULAR: regular way, read each line/character</li>
* <li>_ALT_SIMPLE: simple alternation, read every two lines/characters</li>
* <li>_ALT_RANDOM: random alternation, read randomly based on a seed</li>
* </ul>
* @author Innovimax
* @version 1.0
*/
public enum ReadType {
	_REGULAR,
	_ALT_SIMPLE,
	_ALT_RANDOM,
	_JOIN_SIMPLE;
}
