package innovimax.mixthem.operation;

import innovimax.mixthem.arguments.ParamValue;

public enum ZipOperation { 
  DEFAULT_ZIP_SEPARATOR("");

  private ParamValue value;

  private ZipOperation(String value) {
    this.value = ParamValue.createString(value);
  }

  @Override
  public String toString() {
    return this.value.asString();
  }
  
  public ParamValue getValue() {
    return this.value;
  }

}
