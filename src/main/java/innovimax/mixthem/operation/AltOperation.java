package innovimax.mixthem.operation;

import innovimax.mixthem.arguments.ParamValue;

public enum AltOperation { 
  DEFAULT_RANDOM_SEED(1789);

  private ParamValue value;

  private AltOperation(int value) {
    this.value = ParamValue.createInt(value);
  }

  @Override
  public String toString() {
    return Integer.toString(this.value.asInt());
  }

  public ParamValue getValue() {
    return this.value;
  }

}
