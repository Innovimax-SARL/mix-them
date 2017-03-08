package innovimax.mixthem.operation;

/**
* <p>This is the representation of an operation result.</p>
* @author Innovimax
* @version 1.0
*/
class OperationResult {

  enum ResultType { FILL, EMPTY, STOP }
  
  private final ResultType type;
  private final char[] data;  
  
  public OperationResult(char[] data) {
    this.type = ResultType.FILL;
    this.data = data;    
  }

  public OperationResult(ResultType type) {
    this.type = type;
    this.data = null;
  }
  
  public boolean isEmpty() {
    return this.type == ResultType.EMPTY;
  }
	
  public boolean wantStop() {
    return this.type == ResultType.STOP;
  }
  
  public char[] getData() {
    return this.data;
  }
  
}
