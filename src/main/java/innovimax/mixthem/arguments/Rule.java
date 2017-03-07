package innovimax.mixthem.arguments;

import java.util.EnumSet;

/**
* <p>This is a detailed enumeration of rules used to mix files.</p>
* <p>Some rules may not be implemented yet.<.p>
* <p>(Also used to print usage.)</p>
* @author Innovimax
* @version 1.0
*/
public enum Rule { 
    _1(              "1",               "1",               "will output file1", true, EnumSet.noneOf(RuleParam.class)),
    _2(              "2",               "2",               "will output file2", true, EnumSet.noneOf(RuleParam.class)),
    _ADD(            "+",               "add",             "will output file1+file2", true, EnumSet.noneOf(RuleParam.class)),
    _ALT_LINE(       "alt-line",        "altline",         "will output one line of each starting with first line of file1", true, EnumSet.noneOf(RuleParam.class)), 
    _ALT_CHAR(       "alt-char",        "altchar",         "will output one char of each starting with first char of file1", true, EnumSet.noneOf(RuleParam.class)),
    _RANDOM_ALT_LINE("random-alt-line", "random-altline",  "will output one line of each code randomly based on a seed for reproducability", true, EnumSet.of(RuleParam._RANDOM_SEED)),
    _JOIN(           "join",            "join",            "will output merging of lines that have common occurrence", true, EnumSet.of(RuleParam._JOIN_COL1, RuleParam._JOIN_COL2)),    
    _ZIP_LINE(       "zip-line",        "zipline",         "will output zip of line from file1 and file2", true, EnumSet.of(RuleParam._ZIP_SEP)),
    _ZIP_CHAR(       "zip-char",        "zipchar",         "will output zip of char from file1 and file2", true, EnumSet.of(RuleParam._ZIP_SEP)),
    _ZIP_CELL(       "zip-cell",        "zipcell",         "will output zip of cell from file1 and file2", true, EnumSet.of(RuleParam._ZIP_SEP));

    private final String name, extension, description;
    private final boolean implemented;
    private final EnumSet<RuleParam> params;

    private Rule(String name, String extension, String description, boolean implemented, EnumSet<RuleParam> params) {
        this.name = name;
        this.extension = extension;
        this.description = description;
        this.implemented = implemented;
        this.params = params;
    }

    /**
    * Returns true if the rule is currently implemented.
    * @return True if the rule is currently implemented
    */
    public boolean isImplemented() {
        return this.implemented;
    }

    /**
    * Returns the name of this rule on command line.
    * @return The name of the rule on command line
    */
    public String getName() {
        return this.name;
    }

    /**
    * Returns the file extension used for outputting.
    * @return The file extension for the rule
    */ 
    public String getExtension() {
        return this.extension;
    }

    /**
    * Returns the description of this rule.
    * @return The description of the rule
    */ 
    public String getDescription() {
        return this.description;
    }

    /**
    * Returns an iterator over the additional parameters in this rule.
    * @return An iterator over the additional parameters in this rule
    */ 
    public Iterable<RuleParam> getParams() {
        return this.params;
    }

    /**
    * Finds the Rule object correponding to a name
    * @param name The name of the rule in command line
    * @return The {@link Rule} object
    */    
    public static Rule findByName(String name) {
        for(Rule rule : values()){
            if (rule.getName().equals(name)) {
                return rule;
            }
        }
        return null;
    }

}
