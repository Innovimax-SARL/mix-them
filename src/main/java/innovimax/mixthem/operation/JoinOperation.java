package innovimax.mixthem.operation;

public enum JoinOperation { 
  DEFAULT_JOIN_COLUMN("1");

  private String value;

  private JoinOperation(String value) {
    this.value = value;
  }

  @Override
  public String toString() {
    return this.value;
  }

  public int toInteger() {
    try {
      return Integer.parseInt(this.value);
    } catch (Exception e) {
      return 0;
    }
  }

}
