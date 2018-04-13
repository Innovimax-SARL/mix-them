package innovimax.mixthem.operation;

public enum CellOperation { 
  DEFAULT_SPLIT_CELL_REGEX("\\s"),
  DEFAULT_CELL_SEPARATOR(" ");

  private String value;

  private CellOperation(String value) {
    this.value = value;
  }

  @Override
  public String toString() {
    return this.value;
  }

}
