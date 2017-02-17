package innovimax.mixthem.arguments;

import innovimax.mixthem.exceptions.ArgumentException;

import java.io.File;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
* <p>Mix-them command line arguments management.</p>
* @author Innovimax
* @version 1.0
*/
public class Arguments {
    
    private Rule rule = null;
    private List<String> ruleParams = null;
    private File file1 = null;
    private File file2 = null;

    private void setRule(Rule rule) {
        this.rule = rule;
    }

    public Rule getRule() {
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

    public File getFirstFile() {
        return this.file1;
    }

    void setSecondFile(File file2) {
        this.file2 = file2;
    }

    public File getSecondFile() {
        return this.file2;
    }

    public static Arguments checkArguments(String[] args) throws ArgumentException { 
        Arguments mixArgs = new Arguments();
        int index = 0;
        Rule rule = getRuleArgument(args, index, "rule");
        List<String> ruleParams = null;
        if (rule != null) {
            index++;
            ruleParams = getRuleParameters(args, index, rule);
            index += ruleParams.size();
        }
        File file1 = getFileArgument(args, index, "file1");
        File file2 = getFileArgument(args, ++index, "file2");
        mixArgs.setRule(rule);
        mixArgs.setRuleParameters(ruleParams);
        mixArgs.setFirstFile(file1);
        mixArgs.setSecondFile(file2);
        return mixArgs;
    }

    private static Rule getRuleArgument(String[] args, int index, String name) throws ArgumentException {        
        Rule rule = null;
        if (args.length > index) {
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
        Iterator<RuleParam> iterator = rule.getParams().iterator();
        if (iterator.hasNext()) {
            RuleParam param = iterator.next();
            if (args.length > index) {
                String value = args[index];
                if (param.checkValue(value)) {
                    params.add(value);
                    index++;
                } else {
                    if (param.isRequired()) {
                        throw new ArgumentException("[" + param.getName() + "] parameter is incorrect: " + value);    
                    }
                }
            } else {
                if (param.isRequired()) {
                    throw new ArgumentException("[" + param.getName() + "] parameter is required.");    
                }
            }            
        }     
        return params;
    }

    private static File getFileArgument(String[] args, int index, String name) throws ArgumentException {
        File file = null;
        if (args.length > index) {
            String filepath = args[index];
            file = new File(filepath);
            if (file.exists()) {
                if (!file.canRead()) {                    
                    throw new ArgumentException(name + " cannot be read: " + filepath);    
                }
            } else {
                throw new ArgumentException(name + " not found: " + filepath);
            }
        } else {
            throw new ArgumentException(name + " argument missing.");
        }
        return file;
    }

}
