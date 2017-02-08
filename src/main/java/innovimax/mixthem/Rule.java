package innovimax.mixthem;

import java.util.List;
import java.util.Collections;
import java.util.Arrays;

/**
* <p>This is a detailed enumeration of rules used to mix files.</p>
* <p>Some rules may not be implemented yet.<.p>
* <p>(Also used to print usage.)</p>
* @author Innovimax
* @version 1.0
*/
public enum Rule { 
    _1(              "1",               "1",               "will output file1", true, Collections.emptyList()),
    _2(              "2",               "2",               "will output file2", true, Collections.emptyList()),
    _ADD(            "+",               "add",             "will output file1+file2", true, Collections.emptyList()),
    _ALT_LINE(       "alt-line",        "alt-line",        "will output one line of each starting with first line of file1", true, Collections.emptyList()),
    _ALT_CHAR(       "alt-char",        "alt-char",        "will output one char of each starting with first char of file1", true, Collections.emptyList()),
    _RANDOM_ALT_LINE("random-alt-line", "random-alt-line", "will output one line of each code randomly based on a seed for reproducability", false, Collections.singletonList("seed")),
    _JOIN(           "join",            "join",            "will output merging of lines that have common occurrence", false, Arrays.asList("col1", "col2"));
   
   private final String name, extension, description;
   private final boolean implemented;
   private final List<String> params;

   private Rule(String name, String extension, String description, boolean implemented, List<String> params) {
     this.name = name;
     this.extension = extension;
     this.description = description;
     this.implemented = implemented;
     this.params = params;
   }

   /**
   * Returns true if the rule is currently implemented.
   * @return An iterator over the elements in this rule
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
   * Returns an iterator over the elements in this rule.
   * @param name The name of the rule in command line
   * @return An iterator over the elements in this rule
   */	
   public Iterable<String> getParams() {
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
