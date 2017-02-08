package innovimax.mixthem;

import innovimax.mixthem.interfaces.*;
import innovimax.mixthem.io.*;

import java.io.*;

/**
* <p>Mix files together using variety of rules.</p>
* <p>Here are the rules:</p>
* <ul>
* <li> 1: will output file1</li>
* <li> 2: will output file2</li>
* <li> +: will output file1+file2</li>
* <li> alt-line: will output one line of each starting with first line of file1</li>
* <li> alt-char: will output one char of each starting with first char of file1</li>
* <li> random-alt-line[#seed]: will output one line of each code randomly based on a seed for reproducability</li>
* <li> join[#col1][#col2]: will output merging of lines that have common occurrence</li>
* </ul>
* @author Innovimax
* @version 1.0
*/
public class MixThem {

    private final static int CHAR_BUFFER_SIZE = 1024;

    /**
    * Main entry.
    * @param args The command line arguments
    */
    public static void main(String[] args) { 
        run(args);  
    }

    private static void run(String[] args) {
        try {
            Rule rule;
            if ((rule = checkArguments(args)) != null) {
                String file1, file2;
                if (args.length >= 3) {
                    file1 = args[1];
                    file2 = args[2];
                } else {
                    rule = Rule._1;
                    file1 = args[0];
                    file2 = args[1]; 
                }                 
                processFiles(rule, new File(file1), new File(file2), System.out);
            } else {
                printUsage(); 
            }  
        } catch (MixException e) {
            System.err.println("Files mixing has been aborted due to following reason:"); 
            System.err.println(e.getMessage());
        } catch (Exception e) {
            System.err.println("An unexpected error occurs.");
            e.printStackTrace();
        }
    }

    /**
    * Mix files together using rules.
    * @param rule The rule to be used for mixing
    * @param file1 The first file to be mixed
    * @param file2 The second file to be mixed
    * @param out The output stream to write mixing result
    * @throws MixException - If any error occurs during mixing
    * @see innovimax.mixthem.Rule
    */  
    public static void processFiles(Rule rule, File file1, File file2, OutputStream out) throws MixException {
        try {
            switch(rule) {
                case _1:
                  copyChar(file1, out);
                  break;
                case _2:               
                  copyChar(file2, out);  
                  break; 
                case _ADD:    
                  copyChar(file1, out);
                  copyChar(file2, out);
                  break;
                case _ALT_LINE:    
                  copyAltLine(file1, file2, out);
                  break;
                case _ALT_CHAR:
                  copyAltChar(file1, file2, out);
                  break;
                case _RANDOM_ALT_LINE:
                case _JOIN:               
                //TODO
                //    break;
                default:    
                   System.out.println("This rule has not been implemented yet.");                
            }
        } catch (IOException e) {
            throw new MixException("Unexpected file error", e);
        } catch (MixException e) {
            throw e;
        }

    }   

    // this one copies one file as beeing char
    private static void copyChar(File file, OutputStream out) throws MixException, IOException {
        char[] buffer = new char[CHAR_BUFFER_SIZE];
        IInputChar reader = new DefaultCharReader(file);
        IOutputChar writer = new DefaultCharWriter(out);
        System.out.println("reader.hasCharacter()=" + reader.hasCharacter());
        while (reader.hasCharacter()) {
            final int len = reader.nextCharacters(buffer, CHAR_BUFFER_SIZE);
            writer.writeCharacters(buffer, len);
        }
        reader.close();
        writer.close();
    }    

    // this one copies two files alternativly line by line
    private static void copyAltLine(File file1, File file2, OutputStream out) throws MixException, IOException {
        IInputLine reader1 = new DefaultLineReader(file1);
        IInputLine reader2 = new DefaultLineReader(file2);
        IOutputLine writer = new DefaultLineWriter(out);
        boolean read1 = true;
        boolean read2 = true;
        boolean odd = true;
        while(read1 || read2) {            
            if (read1) {
                if (reader1.hasLine()) {
                    final String line = reader1.nextLine();
                    if (odd || !read2) {
                        writer.writeLine(line);
                    }                    
                } else {
                    read1 = false;
                }
            }  
            if (read2) {
                if (reader2.hasLine()) {
                    final String line = reader2.nextLine();
                    if (!odd || !read1) {
                        writer.writeLine(line);
                    }                    
                } else {
                    read2 = false;
                }
            }
            odd = !odd;
        }
        reader1.close();
        reader2.close();
        writer.close();
    }
/*
    private static void printLine(String line, OutputStream out) throws MixException, IOException {
        byte[] array = line.getBytes("UTF-8");
        for (byte b : array){
            out.write(b);
        }
        out.write(10); // LF
        //out.write(13); // CR
    }
*/
    // this one copies two files alternativly char by char
    private static void copyAltChar(File file1, File file2, OutputStream out) throws MixException, IOException {
        FileInputStream in1 = new FileInputStream(file1);
        FileInputStream in2 = new FileInputStream(file2);
        boolean read1 = true;
        boolean read2 = true;
        boolean first = true;
        while(read1 || read2) {            
            if (read1) {
                final int c = in1.read();
                if (c == -1) {
                    read1 = false;
                } else {
                    if (first || !read2) {
                        printChar(c, out, !read2);
                    }
                }
            }  
            if (read2) {
                final int c = in2.read();
                if (c == -1) {
                    read2 = false;
                } else {
                    if (!first || !read1) {
                        printChar(c, out, true);
                    }                    
                }
            }
            first = !first;
        }
        in1.close();
        in2.close();
        // out.close();
    }

    private static void printChar(int c, OutputStream out, boolean printLF) throws MixException, IOException {
        if (c == 10) {
            if (printLF) {
                out.write(c);
            }
        } else {
            out.write(c);
        }
    }    

    public static Rule checkArguments(String[] args) { 
        String ruleString = null;
        String file1 = null;
        String file2 = null;
        if (args.length >= 3) {
            ruleString = args[0];
            file1 = args[1];
            file2 = args[2];
        } else {            
            if (args.length > 0) {
                file1 = args[0];
            }
            if (args.length > 1) {
                file2 = args[1];
            }
        }
        Rule rule = null;
        if (ruleString != null) {            
            rule = Rule.findByName(ruleString);
            if (rule == null) {
                System.out.println("rule argument is incorrect.");
            }
        }
        if (rule != null) {
            if (file1 == null) {
                System.out.println("file1 argument missing.");
            } else if (file2 == null) {
                System.out.println("file2 argument missing.");
            } else {
                File file = new File(file1);
                if (file.exists()) {
                    if (file.canRead()) {
                    file = new File(file2); 
                        if (file.exists()) {  
                            if (file.canRead()) {
                                return rule;
                            } else {
                            System.out.println("file2 cannot be read."); 
                            }
                        } else {
                        System.out.println("file2 not found.");
                        }
                    } else {
                        System.out.println("file1 cannot be read.");    
                    }
                } else {
                    System.out.println("file1 not found.");
                }
            }
        }
        return null;
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
          for(String param : rule.getParams()) {
              System.out.print(" ["+param+"]");
          }
          System.out.println(": "+rule.getDescription());
        }
        System.out.println("  ");
    }

}
