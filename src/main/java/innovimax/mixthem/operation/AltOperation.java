package innovimax.mixthem.operation;

import innovimax.mixthem.arguments.ParamValue;

public enum AltOperation { 
  DEFAULT_RANDOM_SEED(1789);

  private final ParamValue value;

  AltOperation(final int i) {
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
