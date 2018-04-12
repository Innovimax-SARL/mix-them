package innovimax.mixthem.operation;

import java.util.EnumSet;

public class LineResult {
    
    private final EnumSet<ResultType> types = EnumSet.noneOf(ResultType.class);
    private String result;

    void setResult(String result) {
        this.result = result;        
        this.types.add(result == null ? ResultType._IGNORE_RESULT : ResultType._GET_RESULT);        
    }

    String getResult() {
        return this.result;
    }

    void preserveFirst() {
        this.types.add(ResultType._READ_LAST);        
    }

    boolean readFirst() {
        return this.types.contains(ResultType._READ_FIRST);        
    }

    void preserveLast() {
        this.types.add(ResultType._READ_FIRST);        
    }

    boolean readLast() {
        return this.types.contains(ResultType._READ_LAST);        
    }

    void exploreBoth() {
        this.types.add(ResultType._READ_BOTH);        
    }   

    boolean readBoth() {
        return this.types.contains(ResultType._READ_BOTH);        
    } 

}
