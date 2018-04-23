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
    private String readLine1;
    private String readLine2;    
    private String keptLine1;
    private String keptLine2;    
    
    /**
    * Creates a line result.    
    */
    public LineResult() {
        this.types = EnumSet.noneOf(ResultType.class);
        this.result = null;
        this.readLine1 = null;
        this.readLine2 = null;
        this.keptLine1 = null;
        this.keptLine2 = null;
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
        return this.keptLine1 != null ? this.keptLine1 : this.readLine1;        
    }
       
    /**
    * Has first line?
    */
    boolean hasFirstLine() {
        return getFirstLine() != null;
    }

    /**
    * Set the first line.
    */
    void setFirstLine(String line) {
        this.readLine1 = line;
    }
    
    /**
    * Keep previous first line and set next first line.
    */
    void keepFirstLine(String line) {
        this.keptLine1 = this.readLine1;
        setFirstLine(line);
    }
    
    /**
    * Get the second line.
    */
    String getSecondLine() {        
        return this.keptLine2 != null ? this.keptLine2 : this.readLine2;        
    }
       
    /**
    * Has second line?
    */
    boolean hasSecondLine() {
        return getSecondLine() != null;
    }

    /**
    * Set the second line.
    */
    void setSecondLine(String line) {
        this.readLine2 = line;
    }
    
    /**
    * Keep previous second line and set next second line.
    */
    void keepSecondLine(String line) {
        this.keptLine2 = this.readLine2;
        setSecondLine(line);
    }
    
    /**
    * Preserves first file from reading (keep line)
    */
    void preserveFirstLine() {        
        this.types.add(ResultType.PRESERVE_FIRST_LINE);
    }

    /**
    * First line preserved ?
    */
    boolean firstLinePreserved() {
        return this.types.contains(ResultType.PRESERVE_FIRST_LINE);
    }
    
    /**
    * Has to read first file ?
    */
    boolean readingFirstFile() {
        return !this.types.contains(ResultType.PRESERVE_FIRST_LINE);
    }

    /**
    * Preserves second file from reading (keep line)
    */
    void preserveSecondLine() {
        this.types.add(ResultType.PRESERVE_SECOND_LINE);        
    }
    
    /**
    * Second line preserved ?
    */
    boolean secondLinePreserved() {
        return this.types.contains(ResultType.PRESERVE_SECOND_LINE);
    }

    /**
    * Has to read second file ?
    */
    boolean readingSecondFile() {
        return !this.types.contains(ResultType.PRESERVE_SECOND_LINE);
    }

}
