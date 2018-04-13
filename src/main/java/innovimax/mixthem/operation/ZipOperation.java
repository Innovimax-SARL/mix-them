package innovimax.mixthem.operation;

public enum ZipOperation { 
  DEFAULT_ZIP_SEPARATOR("");

  private String value;

  private ZipOperation(String value) {
    this.value = value;
  }

  @Override
  public String toString() {
    return this.value;
  }

}
