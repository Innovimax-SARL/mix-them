package innovimax.mixthem.operation;

import innovimax.mixthem.arguments.ParamValue;

public enum JoinOperation { 
  DEFAULT_JOIN_COLUMN(1);

  private ParamValue value;

  private JoinOperation(int i) {
    this.value = ParamValue.createInt(i);
  }

  @Override
  public String toString() {
    return Integer.toString(this.value.asInt());
  }

  public ParamValue getValue() {
    return this.value;
  }

}
