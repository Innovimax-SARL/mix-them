package innovimax.mixthem.operation;

import java.util.ArrayList;
import java.util.List;

/**
* Will be deprecated in future version !!!
* Some functions has been yet deprecated to avoid CPD failures
* <p>Describes the result of a line operation on two or more files.</p>
* @author Innovimax
* @version 1.0
*/
public class LineResult {
    
    private final int rangeSize;
    private final List<Boolean> lineReadingRange;
    private final List<String> readLineRange;
    private String result;
    
    /**
    * Creates a line result.    
    */
    @Deprecated
    public LineResult(final int rangeSize) {        
        this.rangeSize = rangeSize;
        this.lineReadingRange = new ArrayList<Boolean>();
        this.readLineRange = new ArrayList<String>();
        for (int i=0; i < this.rangeSize; i++) {
            this.lineReadingRange.add(Boolean.TRUE);
            this.readLineRange.add(null);
        }
        this.result = null;
    }

    /**
    * Reset the result (but keep last read lines).
    */
    @Deprecated
    void reset() {
        this.result = null;
        for (int i=0; i < this.rangeSize; i++) {
            this.lineReadingRange.set(i, Boolean.TRUE);            
        }
    }
    
    /**
    * Set the result (maybe null).
    */
    void setResult(final String result) {
        this.result = result;
    }

    /**
    * Returns the result (maybe null).
    */
    @Deprecated
    String getResult() {
        return this.result;
    }

    /**
    * Has a non null result ?
    */
    @Deprecated
    boolean hasResult() {
        return this.result != null;
    }

    /**
    * Get last read line at specified position from current range.
    */
    @Deprecated
    String getRangeLine(final int index) {
        return this.readLineRange.get(index);
    }

    /**
    * Set last read line at specified position in current range.
    */
    @Deprecated
    void setRangeLine(final int index, final String line) {
        this.readLineRange.set(index, line);
    }
    
    /**
    * Set if next line reading is necessary at specified position of the current range.
    */
    @Deprecated
    void setRangeLineReadingStatus(final int index, final boolean reading) {
        this.lineReadingRange.set(index, Boolean.valueOf(reading));
    }
   
    /**
    * Get next line reading range
    */
    @Deprecated
    List<Boolean> getLineReadingRange() {
        return this.lineReadingRange;
    }

}
