package innovimax.mixthem.operation;

import innovimax.mixthem.arguments.ParamValue;

public enum CellOperation { 
  DEFAULT_SPLIT_CELL_REGEX("\\s"),
  DEFAULT_CELL_SEPARATOR(" ");

  private final ParamValue value;

  CellOperation(final String s) {
    this.value = ParamValue.createString(s);
  }

  @Override
  public String toString() {
    return this.value.asString();
  }

  public ParamValue getValue() {
    return this.value;
  }

}
