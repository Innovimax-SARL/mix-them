package innovimax.mixthem.operation;

/**
* <p>This is the representation of an operation result.</p>
* @author Innovimax
* @version 1.0
*/
class OperationResult {
 
  private final ResultType type;
  private final char[] data;  
  
  public OperationResult(char[] data) {
    this.type = ResultType._FILLED;
    this.data = data;    
  }

  public OperationResult(ResultType type) {
    this.type = type;
    this.data = null;
  }
  
  public boolean hasNone() {
    return this.type == ResultType._NONE;
  }
	
  public boolean wantStop() {
    return this.type == ResultType._WANT_STOP;
  }
  
  public char[] getData() {
    return this.data;
  }
  
}
