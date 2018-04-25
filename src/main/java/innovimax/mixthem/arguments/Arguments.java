package innovimax.mixthem.arguments;

import innovimax.mixthem.io.InputResource;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.LinkOption;
import java.nio.file.Path;
import java.util.EnumMap;
import java.util.Iterator;
import java.util.Map;

/**
* <p>Mix-them command line arguments management.</p>
* @author Innovimax
* @version 1.0
*/
public class Arguments {
    
    private Rule rule = null;
    private Map<RuleParam, ParamValue> ruleParams = null;
    private InputResource input1 = null;
    private InputResource input2 = null;

    private void setRule(Rule rule) {
        this.rule = rule;
    }

    public Rule getRule() {
        return this.rule;
    }

    void setRuleParameters(Map<RuleParam, ParamValue> ruleParams) {
        this.ruleParams = ruleParams;
    }

    public Map<RuleParam, ParamValue> getRuleParameters() {
        return this.ruleParams;
    }

    void setFirstInput(InputResource input) {
        this.input1 = input;
    }

    public InputResource getFirstInput() {
        return this.input1;
    }

    void setSecondInput(InputResource input) {
        this.input2 = input;
    }

    public InputResource getSecondInput() {
        return this.input2;
    }

    public static Arguments checkArguments(String[] args) throws ArgumentException { 
        Arguments mixArgs = new Arguments();
        int index = 0;
        Rule rule = findRuleArgument(args, index, "rule");
        Map<RuleParam, ParamValue> ruleParams = null;
        if (rule != null) {
            index++;
            ruleParams = findRuleParameters(args, index, rule);
            index += ruleParams.size();
        } else {
            rule = Rule.ADD;
        }        
        File file1 = findFileArgument(args, index, "file1");
        File file2 = findFileArgument(args, ++index, "file2");
        mixArgs.setRule(rule);
        mixArgs.setRuleParameters(ruleParams);
        mixArgs.setFirstInput(InputResource.createFile(file1));
        mixArgs.setSecondInput(InputResource.createFile(file2));
        return mixArgs;
    }

    private static Rule findRuleArgument(String[] args, int index, String name) throws ArgumentException {        
        Rule rule = null;
        if (args.length > index) {
            final String ruleString = args[index];
            if (ruleString.startsWith("-")) {
                rule = Rule.findByName(ruleString.substring(1));
                if (rule == null) {
                    throw new ArgumentException(name + " argument is incorrect: " + ruleString);
                }
            }
        }
        return rule;
    }
   
    private static Map<RuleParam, ParamValue> findRuleParameters(String[] args, int index, Rule rule) throws ArgumentException {
        Map<RuleParam, ParamValue> map = new EnumMap<RuleParam, ParamValue>(RuleParam.class);
        Iterator<RuleParam> iterator = rule.getParams().iterator();
        if (iterator.hasNext()) {
            RuleParam param = iterator.next();
            if (args.length > index) {
                String arg = args[index];
                if (arg.startsWith("#")) {
                    final String paramString = arg.substring(1);
                    try {
                        ParamValue value = param.createValue(paramString);
                        map.put(param, value);
                        index++;
                    } catch (NumberFormatException e) {                    
                            throw new ArgumentException("[" + param.getName() + "] parameter is incorrect: " + paramString);                        
                    }
                }
            }            
        }     
        return map;
    }

    private static File findFileArgument(String[] args, int index, String name) throws ArgumentException {
        File file = null;
        if (args.length > index) {
            String filepath = args[index];
            file = new File(filepath);
            final Path path = file.toPath();
            if (Files.exists(path, LinkOption.NOFOLLOW_LINKS)) {
                if (!Files.isReadable(path)) {                    
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
    
    private static String findZipArgument(String[] args, int index) throws ArgumentException {
        String zipArg = null;
        if (args.length > index && (args[index].equals("--zip") || args[index].equals("--jar"))) {
            zipArg = args[index].substring(2);
        }
        return zipArg;
    }

    public static void printUsage() {    
        System.out.println("  ");    
        System.out.println("Usage:");
        System.out.println("  ");
        System.out.println("  mix-them file1 file2");
        System.out.println("  (will generate any file based on file1 and file2)");
        System.out.println("  ");
        System.out.println("  mix-them -[rule] file1 file2");
        System.out.println("  (will generate a file based on the rule)");
        System.out.println("  ");
        System.out.println("  Here are the list of rules");
        for(Rule rule : Rule.values()) {
            System.out.print("  - " + rule.getName());
            for(RuleParam param : rule.getParams()) {                                
                System.out.print(" [#" +  param.getName() + "]");
            }
            System.out.println(": " + rule.getDescription());
        }
        System.out.println("  ");
    }

}
