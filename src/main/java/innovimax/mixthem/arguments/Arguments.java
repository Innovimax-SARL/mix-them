package innovimax.mixthem.arguments;

import innovimax.mixthem.io.InputResource;

import java.io.File;
import java.io.InputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.LinkOption;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.EnumMap;
import java.util.Iterator;
import java.util.List;
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
    
    private Mode mode = null;
    private Rule rule = null;
    private Map<RuleParam, ParamValue> ruleParams = null;
    private final List<InputResource> inputs = new ArrayList<InputResource>();
    
    private void setMode(final Mode mode) {
        this.mode = mode;
    }

    public Mode getMode() {
        return this.mode;
    }
    
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

    void addInput(final InputResource input) {
        this.inputs.add(input);
    }

    public List<InputResource> getInputs() {
        return this.inputs;
    }
    
    public static Arguments checkArguments(final String[] args) throws ArgumentException, IOException, ZipException { 
        final Arguments mixArgs = new Arguments();
        int index = 0;
        Mode mode = findModeArgument(args, index);
        if (mode != null) {
            index++;
        } else {
            mode = Mode.CHAR;
        }
        Rule rule = findRuleArgument(args, index, mode);
        Map<RuleParam, ParamValue> ruleParams = null;
        if (rule != null) {
            index++;
            ruleParams = findRuleParameters(args, index, rule);
            index += ruleParams.size();
        } else {
            rule = Rule.ADD;
        }
        mixArgs.setMode(mode);
        mixArgs.setRule(rule);
        mixArgs.setRuleParameters(ruleParams);        
        final String zipOption = findZipOptionArgument(args, index);
        if (zipOption == null) {
            final List<File> files = findFilesArgument(args, index);
            files.stream().forEach(file -> mixArgs.addInput(InputResource.createFile(file)));
        } else {
            final ZipFile zipFile = new ZipFile(findZipFileArgument(args, ++index));
            final List<InputStream> inputs = extractZipEntries(zipFile);
            inputs.stream().forEach(input -> mixArgs.addInput(InputResource.createInputStream(input)));
        }        
        return mixArgs;
    }

    private static Mode findModeArgument(final String[] args, final int index) throws ArgumentException {        
        if (args.length > index) {            
            return Mode.findByName(args[index]);
        }
        return null;
    }
    
    private static Rule findRuleArgument(final String[] args, final int index, final Mode mode) throws ArgumentException {        
        Rule rule = null;
        if (args.length > index) {
            final String ruleString = args[index];
            if (ruleString.startsWith("-") && !ruleString.startsWith("--")) {
                rule = Rule.findByName(ruleString.substring(1), mode);
                if (rule == null) {
                    throw new ArgumentException("Rule argument is incorrect: " + ruleString);
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
    
    private static List<File> findFilesArgument(final String[] args, int index) throws ArgumentException {
        final List<File> files = new ArrayList<File>();
        while (args.length > index) {
            final String filepath = args[index++];
            final File file = new File(filepath);
            final Path path = file.toPath();
            if (Files.exists(path, LinkOption.NOFOLLOW_LINKS)) {
                if (Files.isReadable(path)) {
                    files.add(file);
                } else {
                    throw new ArgumentException("Input file cannot be read: " + filepath);    
                }
            } else {
                throw new ArgumentException("Input file not found: " + filepath);
            }
        }
        switch (files.size()) {
            case 0: 
                throw new ArgumentException("First input file argument missing.");
            case 1: 
                throw new ArgumentException("Second input file argument missing.");
        }
        return files;
    }
    
    private static String findZipOptionArgument(final String[] args, final int index) {        
        if (args.length > index && (args[index].equals("--zip") || args[index].equals("--jar"))) {
            return args[index].substring(2);
        }
        return null;
    }
    
    private static File findZipFileArgument(final String[] args, final int index) throws ArgumentException {
        File file = null;
        if (args.length > index) {
            final String filepath = args[index];
            file = new File(filepath);
            final Path path = file.toPath();
            if (Files.exists(path, LinkOption.NOFOLLOW_LINKS)) {
                if (!Files.isReadable(path)) {                    
                    throw new ArgumentException("Zip/Jar file cannot be read: " + filepath);    
                }
            } else {
                throw new ArgumentException("Zip/Jar file not found: " + filepath);
            }
        } else {
            throw new ArgumentException("Zip/Jar argument missing.");
        }
        return file;
    }

    private static List<InputStream> extractZipEntries(final ZipFile zipFile) throws ArgumentException, IOException, ZipException {
        final List<InputStream> inputs = new ArrayList<InputStream>();        
        final Enumeration entries = zipFile.entries();        
        while (entries.hasMoreElements()) {
            ZipEntry entry = (ZipEntry) entries.nextElement();
            if (entry.getName().toUpperCase().startsWith("META-INF")) {
                continue;        
            }           
            inputs.add(zipFile.getInputStream(entry));
        }
        switch (inputs.size()) {
            case 0: 
                throw new ArgumentException("First input entry missing.");
            case 1: 
                throw new ArgumentException("Second input entry missing.");
        }
        return inputs;
    }
    
    public static void printUsage() {    
        System.out.println("  ");    
        System.out.println("Usage:");
        System.out.println("  ");
        System.out.println("  mix-them file1 file2... fileN");
        System.out.println("  (will generate any file based on file1 and file2 to fileN)");
        System.out.println("  ");
        System.out.println("  mix-them -[rule] file1 file2... fileN");
        System.out.println("  (will generate a file based on the rule)");
        System.out.println("  ");
        System.out.println("  Here are the list of rules");
        for(Rule rule : Rule.values()) {
            System.out.print("  - " + rule.getName());
            for(RuleParam param : rule.getParams()) {                                
                System.out.print(" [#" +  param.getName() + "]");
            }
            System.out.println(": " + rule.getDescription());            
            for(RuleParam param : rule.getParams()) {                                
                System.out.println("    (#" +param.getName() + " " + param.getComment() + ")");
            }            
        }
        System.out.println("  ");
        System.out.println("  mix-them --zip zipfile");
        System.out.println("  mix-them --jar jarfile");
        System.out.println("  (will generate any entry based on zip/jar file first and second to nth entries)");
        System.out.println("  ");
        System.out.println("  mix-them -[rule] --zip zipFile");
        System.out.println("  mix-them -[rule] --jar jarFile");
        System.out.println("  (will generate a file based on the rule)");
        System.out.println("  ");
    }

}
