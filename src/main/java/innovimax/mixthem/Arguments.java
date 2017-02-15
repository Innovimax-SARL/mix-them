package innovimax.mixthem;

import innovimax.mixthem.exceptions.ArgumentException;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

/**
* <p>Mix-them command line arguments management.</p>
* @author Innovimax
* @version 1.0
*/
class Arguments {
    
    private Rule rule = null;
    private List<String> ruleParams = null;
    private File file1 = null;
    private File file2 = null;

    void setRule(Rule rule) {
        this.rule = rule;
    }

    Rule getRule() {
        return this.rule;
    }

    void setRuleParameters(List<String> ruleParams) {
        this.ruleParams = ruleParams;
    }

    List<String> getRuleParameters() {
        return this.ruleParams;
    }

    void setFirstFile(File file1) {
        this.file1 = file1;
    }

    File getFirstFile() {
        return this.file1;
    }

    void setSecondFile(File file2) {
        this.file2 = file2;
    }

    File getSecondFile() {
        return this.file2;
    }

    private static Rule getRuleArgument(String[] args, int index, String name) throws ArgumentException {        
        Rule rule = null;
        if (args.length >= 1) {
            final String ruleString = args[index];
            if (ruleString.startsWith("-")) {
                rule = Rule.findByName(ruleString);
                if (rule == null) {
                    throw new ArgumentException(name + " argument is incorrect: " + ruleString);
                }
            }
        }
        return rule;
    }

    private static List<String> getRuleParameters(String[] args, int index, Rule rule) throws ArgumentException {
        List<String> params = new ArrayList<String>();        
        // TODO
        return params;
    }

}
