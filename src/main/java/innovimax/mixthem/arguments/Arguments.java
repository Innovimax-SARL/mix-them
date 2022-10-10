package innovimax.mixthem.arguments;

import innovimax.mixthem.io.InputResource;

import java.io.File;
import java.io.InputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.LinkOption;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Enumeration;
import java.util.EnumMap;
import java.util.Iterator;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.zip.ZipEntry;
import java.util.zip.ZipFile;

/**
* <p>Mix-them command line arguments management.</p>
* @author Innovimax
* @version 1.0
*/
public class Arguments {
    
    private FileMode fileMode;
    private Rule rule;
    private Map<RuleParam, ParamValue> ruleParams;
    private Set<Integer> selection;
    private final List<InputResource> inputs = new ArrayList<>();
    
    private void setFileMode(final FileMode fileMode) {
        this.fileMode = fileMode;
    }

    public FileMode getFileMode() {
        return this.fileMode;
    }
    
    private void setRule(final Rule rule) {
        this.rule = rule;
    }

    public Rule getRule() {
        return this.rule;
    }

    private void setRuleParameters(final Map<RuleParam, ParamValue> ruleParams) {
        this.ruleParams = ruleParams;
    }

    public Map<RuleParam, ParamValue> getRuleParameters() {
        return this.ruleParams;
    }
    
     private void setSelection(final Set<Integer> selection) {
        this.selection = selection;
    }
    
    public Set<Integer> getSelection() {
        return this.selection;
    }

    private void addInput(final InputResource input) {
        this.inputs.add(input);
    }

    public List<InputResource> getInputs() {
        return this.inputs;
    }
    
    public static Arguments checkArguments(final String[] args) throws ArgumentException, IOException, java.util.zip.ZipException {
        final Arguments mixArgs = new Arguments();
        int index = 0;
        // get file mode [char|byte]
        FileMode fileMode = findFileModeArgument(args, index);
        if (fileMode != null) {
            index++;
        } else {
            fileMode = FileMode.CHAR;
        }
        mixArgs.setFileMode(fileMode);
        // get selection
        final Set<Integer> selection = findSelectionArgument(args, index);        
        mixArgs.setSelection(selection);
        if (!selection.isEmpty()) {
            index += selection.size();
        }
        // get rule & parameters
        Rule rule = findRuleArgument(args, index, fileMode);
        final Map<RuleParam, ParamValue> ruleParams;
        if (rule != null) {
            index++;
            ruleParams = findRuleParameters(args, index, rule);
            index += ruleParams.size();
        } else {
            rule = Rule.ADD;
            ruleParams = Collections.emptyMap();
        }        
        mixArgs.setRule(rule);        
        mixArgs.setRuleParameters(ruleParams);        
        // get input files
        final String zipOption = findZipOptionArgument(args, index);
        if (zipOption == null) {
            final List<File> files = findFilesArgument(args, index);
            files.forEach(file -> mixArgs.addInput(InputResource.createFile(file)));
        } else {
            final ZipFile zipFile = new ZipFile(findZipFileArgument(args, ++index));
            final List<InputStream> inputs = extractZipEntries(zipFile);
            inputs.forEach(input -> mixArgs.addInput(InputResource.createInputStream(input)));
        }        
        // check selection vs input file count
        checkSelection(mixArgs);
        return mixArgs;
    }

    private static FileMode findFileModeArgument(final String[] args, final int index) {
        if (args.length > index) {            
            return FileMode.findByName(args[index]);
        }
        return null;
    }
    
    private static Set<Integer> findSelectionArgument(final String[] args, int index) throws ArgumentException {
        int index1 = index;
        final Set<Integer> selection = new LinkedHashSet<>();
        while (args.length > index1) {
            final int fileIndex;
            try { 
                fileIndex = Integer.parseInt(args[index1++]);
                if (index1 <= 0) {
                    throw new ArgumentException("Selection index is not valid: " + fileIndex);
                }
                selection.add(fileIndex);
            } catch(final NumberFormatException e) {
                break;
            }
        }
        return selection;
    }
    
    private static Rule findRuleArgument(final String[] args, final int index, final FileMode fileMode) throws ArgumentException {        
        Rule rule = null;
        if (args.length > index) {
            final String ruleString = args[index];
            if (ruleString.startsWith("-") && !ruleString.startsWith("--")) {
                rule = Rule.findByName(ruleString.substring(1), fileMode);
                if (rule == null) {
                    throw new ArgumentException("Rule argument is incorrect: " + ruleString);
                }
            }
        }
        return rule;
    }
   
    private static Map<RuleParam, ParamValue> findRuleParameters(final String[] args, final int index, final Rule rule) throws ArgumentException {
        final Map<RuleParam, ParamValue> map = new EnumMap<>(RuleParam.class);
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
                    } catch (final NumberFormatException e) {
                        throw new ArgumentException("#" + param.getName() + " parameter is incorrect: " + paramString);                        
                    }
                }
            } 
            if (param.isMandatory() && !map.containsKey(param)) {
                throw new ArgumentException("#" + param.getName() + " parameter is mandatory.");
            }            
        }     
        return map;
    }
    
    private static List<File> findFilesArgument(final String[] args, int index) throws ArgumentException {
        int index1 = index;
        final List<File> files = new ArrayList<>();
        while (args.length > index1) {
            final String filepath = args[index1++];
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
        if (args.length > index && ("--zip".equals(args[index]) || "--jar".equals(args[index]))) {
            return args[index].substring(2);
        }
        return null;
    }
    
    private static File findZipFileArgument(final String[] args, final int index) throws ArgumentException {
        final File file;
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

    private static List<InputStream> extractZipEntries(final ZipFile zipFile) throws ArgumentException, IOException {
        final List<InputStream> inputs = new ArrayList<>();
        final Enumeration<? extends ZipEntry> entries = zipFile.entries();
        while (entries.hasMoreElements()) {
            final ZipEntry entry = entries.nextElement();
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
    
    private static void checkSelection(final Arguments mixArgs) throws ArgumentException {
        for (final Integer index : mixArgs.getSelection()) {
            if (index > mixArgs.getInputs().size()) {
                throw new ArgumentException("Selection index is greater than file count: " + index);
            }
        }
    }
    
    public static void printUsage() {    
        System.out.println("  ");    
        System.out.println("Usage:");
        System.out.println("  ");
        System.out.println("  mix-them [char|byte] <file1> <file2>[ <file3>... <fileN>]");
        System.out.println("  (will generate on standard out any file based on file1 to fileN)");
        System.out.println("  (by default it assumes that all files are character based, not binary)");
        System.out.println("  ");        
        System.out.println("  mix-them [char|byte] <rule> <file1> <file2>[ <file3>... <fileN>]");
        System.out.println("  (will generate on standard out a file based on the rule)");
        System.out.println("  ");        
        System.out.println("  mix-them [char|byte] <index1> <index2>[ <index3>...] <rule> <file1> <file2>[ <file3>... <fileN>]");
        System.out.println("  (will generate on standard out a file based on the rule and a selection of files designed by their index)");
        System.out.println("  ");        
        System.out.println("  mix-them --zip <zipfile>");
        System.out.println("  mix-them --jar <jarfile>");
        System.out.println("  (will generate on standard out any file based on entry1 to entryN of zip/jar file)");
        System.out.println("  (by default it assumes zip/jar entries are character based, not binary)");
        System.out.println("  ");        
        System.out.println("  mix-them <rule> --zip <zipFile>");
        System.out.println("  mix-them <rule> --jar <jarFile>");
        System.out.println("  (will generate on standard out a file based on the rule)");
        System.out.println("  ");
        System.out.println("  mix-them <index1> <index2>[ <index3>...] <rule> --zip <zipFile>");
        System.out.println("  mix-them <index1> <index2>[ <index3>...] <rule> --jar <jarFile>");
        System.out.println("  (will generate on standard out a file based on the rule and a selection of entries designed by their index)");
        System.out.println("  ");
        System.out.println("Here are the list of rules:");
        for(final Rule rule : Rule.values()) {
            System.out.print("  -" + rule.getName());
            for(final RuleParam param : rule.getParams()) {
                if (param.isMandatory()) {
                    System.out.print(" #" +  param.getName());
                } else {
                    System.out.print(" [#" +  param.getName() + "]");
                }
            }
            System.out.println(": " + rule.getDescription());            
            for(final RuleParam param : rule.getParams()) {
                System.out.println("    (#" +param.getName() + " " + param.getComment() + ")");
            }            
        }
        System.out.println("  ");
    }

}
