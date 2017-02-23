package innovimax.mixthem.io;

/**
* <p>This is a enumeration of joining type.</p>
* <p>Here are the types:</p>
* <ul>
* <li>_DEFAULT: join on first column in both files</li>
* <li>_SAME_COL: join on same column in both files</li>
* <li>_DIFF_COL: join on different columns in both files</li>
* </ul>
* @author Innovimax
* @version 1.0
*/
public enum JoinType {
  _DEFAULT,
  _SAME_COL,
  _DIFF_COL;
}
