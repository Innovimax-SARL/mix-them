package innovimax.mixthem;

public enum Constants {
   
	RULE_1("1"),
	RULE_2("2"),
	RULE_BOTH("+"),
	RULE_ALT_LINE("alt-line"),
	RULE_ALT_CHAR("alt-char"),
	RULE_RANDOM_ALT_LINE("random-alt-line"),
	RULE_JOIN("join");

	final String name;

	Constants(String name) {
		this.name = name;
	}

	public String getName() {
		return this.name;
	}

}
