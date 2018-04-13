package innovimax.mixthem.operation;

import java.util.EnumSet;

/**
* <p>Describes the result of an line operation on two files.</p>
* @author Innovimax
* @version 1.0
*/
public class LineResult {
    
    private final EnumSet<ResultType> types;
    private String result;
    private String line1;
    private String line2;    
    
    /**
    * Creates a line result.    
    */
    public LineResult() {
        this.types = EnumSet.noneOf(ResultType.class);
        this.result = null;
        this.line1 = null;
        this.line2 = null;        
    }

    /**
    * Reset the result.
    */
    void reset() {        
        this.types.clear();
        this.result = null;
    }
    
    /**
    * Set the result (maybe null).
    */
    void setResult(String result) {
        this.result = result;
        if (result != null) {
            this.types.add(ResultType.HAS_RESULT);
        }
    }

    /**
    * Returns the result (maybe null).
    */
    String getResult() {
        return this.result;
    }

    /**
    * Has a result ?
    */
    boolean hasResult() {
        return this.types.contains(ResultType.HAS_RESULT);     
    }
    
    /**
    * Get the first line.
    */
    String getFirstLine() {
        return this.line1;
    }
    
    /**
    * Get the second line.
    */
    String getSecondLine() {
        return this.line2;
    }
    
    /**
    * Preserves first file from reading
    */
    void preserveFirstLine(String line) {        
        this.types.add(ResultType.PRESERVE_FIRST_LINE);
        this.line1 = line;
    }

    /**
    * Has to read first file ?
    */
    boolean readingFirstFile() {
        return !this.types.contains(ResultType.PRESERVE_FIRST_LINE);
    }

    /**
    * Preserves second file from reading
    */
    void preserveSecondLine(String line) {
        this.types.add(ResultType.PRESERVE_SECOND_LINE);
        this.line2 = line;
    }

    /**
    * Has to read second file ?
    */
    boolean readingSecondFile() {
        return !this.types.contains(ResultType.PRESERVE_SECOND_LINE);
    }

}
