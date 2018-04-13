package innovimax.mixthem.operation;

import java.util.EnumSet;

/**
* <p>Describes the result of an line operation on two files.</p>
* @author Innovimax
* @version 1.0
*/
public class LineResult {
    
    private final EnumSet<ResultType> types;
    private String line1;
    private String line2;
    private String result;
    
    /**
    * Creates a line result.    
    */
    public LineResult() {
        this.types = EnumSet.of(ResultType._READ_BOTH_FILES);        
        this.line1 = null;
        this.line2 = null;
        this.result = null;
    }

    /**
    * Reset the result types.
    */
    void resetTypes() {        
        this.types.clear(); 
    }
    
    /**
    * Reset the read lines.
    */
    void resetLines() {        
        this.line1 = null;
        this.line2 = null;
    }

    /**
    * Set the first line.
    */
    void setFirstLine(String line) {
        this.line1 = line;        
    }
    
    /**
    * Get the first line.
    */
    String getFirstLine() {
        return this.line1;
    }
    
    /**
    * Has first line?
    */
    boolean hasFirstLine() {
        return this.line1 != null;
    }
    
    /**
    * Set the second line.
    */
    void setSecondLine(String line) {
        this.line2 = line;        
    }
    
    /**
    * Get the second line.
    */
    String getSecondLine() {
        return this.line2;
    }
    
    /**
    * Has second line?
    */
    boolean hasSecondLine() {
        return this.line2 != null;
    }
    
    /**
    * Set the result of the operation (maybe null).
    */
    void setResult(String result) {
        this.result = result;        
        this.types.add(result == null ? ResultType._IGNORE_RESULT : ResultType._GET_RESULT);        
    }

    /**
    * Ignores the result of the operation (set to null).
    */
    void ignoreResult() {
        this.types.add(ResultType._IGNORE_RESULT);
    }

    /**
    * Returns the result of the operation (maybe null).
    */
    String getResult() {
        return this.result;
    }

    /**
    * Returns the result of the operation (maybe null).
    */
    boolean hasResult() {
        return this.types.contains(ResultType._GET_RESULT);     
    }
    
    /**
    * Preserves first file from reading
    */
    void preserveFirstLine() {
        this.types.add(ResultType._READ_SECOND_FILE);        
    }

    /**
    * Has to read first file ?
    */
    boolean firstFileReading() {
        return this.types.contains(ResultType._READ_FIRST_FILE) || this.types.contains(ResultType._READ_BOTH_FILES);        
    }

    /**
    * Preserves second file from reading
    */
    void preserveSecondLine() {
        this.types.add(ResultType._READ_FIRST_FILE);        
    }

    /**
    * Has to read second file ?
    */
    boolean secondFileReading() {
        return this.types.contains(ResultType._READ_SECOND_FILE) || this.types.contains(ResultType._READ_BOTH_FILES);        
    }

    /**
    * Explores both files for next reading
    */
    void exploreBothFiles() {
        this.types.add(ResultType._READ_BOTH_FILES);        
    }   

    /**
    * Has to read both files ?
    */
    boolean bothFilesReading() {
        return this.types.contains(ResultType._READ_BOTH_FILES);        
    } 

}
