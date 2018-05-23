package innovimax.mixthem.arguments;

/**
* <p>This is a detailed enumeration of modes used for reading files.</p>
* @author Innovimax
* @version 1.0
*/
public enum FileMode {	
    CHAR("char"),
    BYTE("byte");
    
    private final String name;
    
    private FileMode(final String name) {
        this.name = name;
    }
    
    /**
    * Returns the name of this mode on command line.
    * @return The name of the mode on command line
    */
    public String getName() {
        return this.name;
    }
    
    /**
    * Finds the FileMode object correponding to a name
    * @param name The name of the file mode in command line
    * @return The {@link FileMode} object
    */    
    public static FileMode findByName(final String name) {
        for (FileMode fileMode : values()) {
            if (fileMode.getName().equals(name)) {
                return fileMode;
            }
        }
        return null;
    }
    
}
