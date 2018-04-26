package innovimax.mixthem.arguments;

import innovimax.mixthem.io.InputResource;

import java.io.File;
import java.io.InputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.LinkOption;
import java.nio.file.Path;
import java.util.Enumeration;
import java.util.EnumMap;
import java.util.Iterator;
import java.util.Map;
import java.util.zip.ZipEntry;
import java.util.zip.ZipException;
import java.util.zip.ZipFile;

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

    private void setRule(final Rule rule) {
        this.rule = rule;
    }

    public Rule getRule() {
        return this.rule;
    }

    void setRuleParameters(final Map<RuleParam, ParamValue> ruleParams) {
        this.ruleParams = ruleParams;
    }

    public Map<RuleParam, ParamValue> getRuleParameters() {
        return this.ruleParams;
    }

    void setFirstInput(final InputResource input) {
        this.input1 = input;
    }

    public InputResource getFirstInput() {
        return this.input1;
    }

    void setSecondInput(final InputResource input) {
        this.input2 = input;
    }

    public InputResource getSecondInput() {
        return this.input2;
    }

    public static Arguments checkArguments(final String[] args) throws ArgumentException, IOException, ZipException { 
        final Arguments mixArgs = new Arguments();
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
        mixArgs.setRule(rule);
        mixArgs.setRuleParameters(ruleParams);        
        final String zipOption = findZipOptionArgument(args, index);
        if (zipOption == null) {                        
            final File file1 = findFileArgument(args, index, "file1");
            final File file2 = findFileArgument(args, ++index, "file2");        
            mixArgs.setFirstInput(InputResource.createFile(file1));
            mixArgs.setSecondInput(InputResource.createFile(file2));   
        } else {
            final ZipFile zipFile = new ZipFile(findFileArgument(args, ++index, zipOption));
            final InputStream input1 = extractZipEntry(zipFile, 1, "file1");
            final InputStream input2 = extractZipEntry(zipFile, 2, "file2");
            mixArgs.setFirstInput(InputResource.createInputStream(input1));
            mixArgs.setSecondInput(InputResource.createInputStream(input2));
        }        
        return mixArgs;
    }

    private static Rule findRuleArgument(final String[] args, final int index, final String name) throws ArgumentException {        
        Rule rule = null;
        if (args.length > index) {
            final String ruleString = args[index];
            if (ruleString.startsWith("-") && !ruleString.startsWith("--")) {
                rule = Rule.findByName(ruleString.substring(1));
                if (rule == null) {
                    throw new ArgumentException(name + " argument is incorrect: " + ruleString);
                }
            }
        }
        return rule;
    }
   
    private static Map<RuleParam, ParamValue> findRuleParameters(final String[] args, final int index, final Rule rule) throws ArgumentException {
        final Map<RuleParam, ParamValue> map = new EnumMap<RuleParam, ParamValue>(RuleParam.class);
        final Iterator<RuleParam> iterator = rule.getParams().iterator();
        if (iterator.hasNext()) {
            final RuleParam param = iterator.next();
            if (args.length > index) {
                final String arg = args[index];
                if (arg.startsWith("#")) {
                    final String paramString = arg.substring(1);
                    try {
                        final ParamValue value = param.createValue(paramString);
                        map.put(param, value);                        
                    } catch (NumberFormatException e) {
                        throw new ArgumentException("[" + param.getName() + "] parameter is incorrect: " + paramString);                        
                    }
                }
            }            
        }     
        return map;
    }

    private static File findFileArgument(final String[] args, final int index, final String name) throws ArgumentException {
        File file = null;
        if (args.length > index) {
            final String filepath = args[index];
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
    
    private static String findZipOptionArgument(final String[] args, final int index) {        
        if (args.length > index && (args[index].equals("--zip") || args[index].equals("--jar"))) {
            return args[index].substring(2);
        }
        return null;
    }
    
    private static InputStream extractZipEntry(final ZipFile zipFile, final int index, final String name) throws ArgumentException, IOException, ZipException {
        InputStream input = null;
        int i = 1;
        final Enumeration entries = zipFile.entries();        
        while (entries.hasMoreElements()) {
            ZipEntry entry = (ZipEntry) entries.nextElement();
            if (entry.getName().toUpperCase().startsWith("META-INF")) {
                continue;        
            }
            if (i == index) {
                input = zipFile.getInputStream(entry);
                break;
            }
            i++;
        }
        if (input == null) {
            throw new ArgumentException(name + " entry missing.");
        }        
        return input;
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
        System.out.println("  mix-them --zip zipfile");
        System.out.println("  mix-them --jar jarfile");
        System.out.println("  (will generate any entry based on zip/jar file first and second entries)");
        System.out.println("  ");
        System.out.println("  mix-them -[rule] --zip zipFile");
        System.out.println("  mix-them -[rule] --jar jarFile");
        System.out.println("  (will generate a file based on the rule)");
        System.out.println("  ");
    }

}
