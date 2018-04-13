package innovimax.mixthem.operation;

public enum AltOperation { 
  DEFAULT_RANDOM_SEED("1789");

  private String value;

  private AltOperation(String value) {
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
