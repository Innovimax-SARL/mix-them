package innovimax.mixthem.arguments;

/**
* <p>This is a detailed enumeration of tokens used to mix files.</p>
* @author Innovimax
* @version 1.0
*/
public enum Token { 
	BYTE("byte", "atom-byte", "byte by byte"),
	CHAR("char", "atom-char", "character by character"),
	LINE("line", "line-char", "line by line (of characters)"),	
	FILEBYTE("filebyte", "file-byte", "file by file (of bytes)"),
	FILECHAR("filechar", "file-char", "file by file (of characters)");

	private final String name, alias, description;

	private Token(final String name, final String alias, final String description) {
		this.name = name;
		this.alias = alias;
		this.description = description;
	}

	/**
	* Returns the name of this token.
	* @return The name of the token
	*/
	public String getName() {
		return this.name;
	}

	/**
	* Returns the alias of this token.
	* @return The alias of the token
	*/
	public String getAlias() {
		return this.alias;
	}

	/**
	* Returns the description of this token.
	* @return The description of the token
	*/ 
	public String getDescription() {
		return this.description;
	}

}
