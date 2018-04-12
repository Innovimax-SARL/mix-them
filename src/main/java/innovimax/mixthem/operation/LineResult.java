package innovimax.mixthem.operation;

import java.util.EnumSet;

/**
* <p>Describes the result of an line operation on two files.</p>
* @author Innovimax
* @version 1.0
*/
public class LineResult {
    
    private final EnumSet<ResultType> types = EnumSet.noneOf(ResultType.class);
    private String result = null;

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
    * Preserves first file from reading
    */
    void preserveFirst() {
        this.types.add(ResultType._READ_LAST);        
    }

    /**
    * Has to read first file ?
    */
    boolean readFirst() {
        return this.types.contains(ResultType._READ_FIRST);        
    }

    /**
    * Preserves last file from reading
    */
    void preserveLast() {
        this.types.add(ResultType._READ_FIRST);        
    }

    /**
    * Has to read last file ?
    */
    boolean readLast() {
        return this.types.contains(ResultType._READ_LAST);        
    }

    /**
    * Explores both files for next reading
    */
    void exploreBoth() {
        this.types.add(ResultType._READ_BOTH);        
    }   

    /**
    * Has to read both files ?
    */
    boolean readBoth() {
        return this.types.contains(ResultType._READ_BOTH);        
    } 

}
