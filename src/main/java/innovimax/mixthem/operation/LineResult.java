package innovimax.mixthem.operation;

import java.util.ArrayList;
import java.util.List;

/**
* <p>Describes the result of an line operation on two files.</p>
* @author Innovimax
* @version 1.0
*/
public class LineResult {
    
    private final int rangeSize;    
    private final List<Boolean> nextLineRange;
    private final List<String> readLineRange;
    private final List<String> keptLineRange;    
    private String result;
    
    /**
    * Creates a line result.    
    */
    public LineResult(final int rangeSize) {        
        this.rangeSize = rangeSize;
        this.nextLineRange = new ArrayList<Boolean>();
        this.readLineRange = new ArrayList<String>();
        this.keptLineRange = new ArrayList<String>();
        for (int i=0; i < this.rangeSize; i++) {
            this.nextLineRange.add(Boolean.TRUE);
            this.readLineRange.add(null);
            this.keptLineRange.add(null);
        }
        this.result = null;
    }

    /**
    * Reset the result.
    */
    void reset() {
        this.result = null;
        for (int i=0; i < this.rangeSize; i++) {
            this.nextLineRange.add(Boolean.TRUE);            
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
    String getResult() {
        return this.result;
    }

    /**
    * Has a result ?
    */
    boolean hasResult() {
        return this.result != null;
    }

    /**
    * Get a line at specified position from current range.
    */
    String getRangeLine(final int index) {        
        final String line = this.keptLineRange.get(index);
        return line != null ? line : this.readLineRange.get(index);
    }
       
    /**
    * Has a line at specified position in current range?
    */
    boolean hasRangeLine(final int index) {
        return getRangeLine(index) != null;
    }

    /**
    * Set a line at specified position in current range.
    */
    void setRangeLine(final int index, final String line) {
        if (this.keptLineRange.get(index) != null) {            
            setRangeLineReading(index, false);
            this.keptLineRange.set(index, null);
        } else {
            this.readLineRange.set(index, line);
        }
    }

    /**
    * Keep previous line at specified position in current range and set future line for the same position.
    */
    void keepRangeLine(final int index, final String line) {
        this.keptLineRange.set(index, this.readLineRange.get(index));
        this.readLineRange.set(index, line);
    }
    
    /**
    * Set if file reading is necessary at specified position of the current range.
    */
    void setRangeLineReading(final int index, final boolean reading) {
        this.nextLineRange.set(index, Boolean.valueOf(reading));
    }
   
    /**
    * Has to read file at specified position of the current range?
    */
    boolean readingRangeLine(final int index) {
        return this.nextLineRange.get(index).booleanValue();
    }

}
