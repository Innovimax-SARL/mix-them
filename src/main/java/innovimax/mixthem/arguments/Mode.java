package innovimax.mixthem.operation;

/**
* <p>This is a detailed enumeration of modes used to mix files.</p>
* @author Innovimax
* @version 1.0
*/
public enum Mode {	
	CHAR("char"),
	BYTE("byte");
	
	private final String name;

    private Mode(final String name) {
        this.name = name;
    }
	
	/**
    * Returns the name of this mode on command line.
    * @return The name of the mode on command line
    */
    public String getName() {
        return this.name;
    }
}
