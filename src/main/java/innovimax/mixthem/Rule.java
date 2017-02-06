package innovimax.mixthem;

import java.util.List;
import java.util.Collections;
import java.util.Arrays;

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
   public boolean isImplemented() {
     return this.implemented;
   }
   public String getName() {
     return this.name;
   }
   public String getExtension() {
     return this.extension;
   }
   public String geDescription() {
     return this.description;
   }
   public Iterable<String> getParams() {
       return this.params;
   }
   public static Rule findByName(String name) {
      for(Rule rule : values()){
        if (rule.getName().equals(name)) {
           return rule;
        }
      }
      return null;
   }  
}
