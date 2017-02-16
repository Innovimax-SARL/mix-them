package innovimax.mixthem;

import innovimax.mixthem.exceptions.*;
import innovimax.mixthem.interfaces.*;
import innovimax.mixthem.io.*;

import java.io.File;
import java.io.IOException;
import java.io.OutputStream;

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

    private final File file1, file2;
    private final OutputStream out;
    /**
     * Constructor
     * @param file1 The first file to be mixed
     * @param file2 The second file to be mixed
     * @param out The output stream to write mixing result
     */ 
    public MixThem(File file1, File file2, OutputStream out) {
        this.file1 = file1;
        this.file2 = file2;
        this.out = out;
    }


    /**
    * Main entry.
    * @param args The command line arguments
    */
    public static void main(String[] args) { 
        run(args);  
    }

    private static void run(String[] args) {
        try {
            Arguments mixArgs = Arguments.checkArguments(args);        
            MixThem mixThem = new MixThem(mixArgs.getFirstFile(), mixArgs.getSecondFile(), System.out);
            mixThem.process(mixArgs.getRule());
        } catch (ArgumentException e) {
            System.err.println("Files mixing can't be run due to following reason:"); 
            System.err.println(e.getMessage());
            printUsage(); 
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
    * @throws MixException - If any error occurs during mixing
    * @see innovimax.mixthem.Rule
    */  
    public void process(Rule rule) throws MixException {
        try {
            switch(rule) {
                case _1:
                  copyChar(this.file1, this.out);
                  break;
                case _2:               
                  copyChar(this.file2, this.out);  
                  break; 
                case _ADD:    
                  copyChar(this.file1, this.out);
                  copyChar(this.file2, this.out);
                  break;
                case _ALT_LINE:    
                  copyAltLine(this.file1, this.file2, this.out);
                  break;
                case _ALT_CHAR:
                  copyAltChar(this.file1, this.file2, this.out);
                  break;
                case _RANDOM_ALT_LINE:
                  copyRandomAltLine(this.file1, this.file2, this.out);
                  break;
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

    // this one copies two files alternativly char by char
    private static void copyAltChar(File file1, File file2, OutputStream out) throws MixException, IOException {
        IInputChar reader1 = new DefaultCharReader(file1);
        IInputChar reader2 = new DefaultCharReader(file2);
        IOutputChar writer = new DefaultCharWriter(out);
        boolean read1 = true;
        boolean read2 = true;
        boolean odd = true;
        while(read1 || read2) {            
            if (read1) {
                if (reader1.hasCharacter()) {
                    final int c = reader1.nextCharacter();
                    if (odd || !read2) {
                        writer.writeCharacter(c);
                    }                    
                } else {
                    read1 = false;
                }
            }  
            if (read2) {
                if (reader2.hasCharacter()) {
                    final int c = reader2.nextCharacter();
                    if (!odd || !read1) {
                        writer.writeCharacter(c);
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

    // this one copies two files randomly alternativly line by line
    private static void copyRandomAltLine(File file1, File file2, OutputStream out) throws MixException, IOException {
        IInputLine reader1 = new DefaultLineReader(file1);
        IInputLine reader2 = new DefaultLineReader(file2);
        IOutputLine writer = new DefaultLineWriter(out);           
        //TODO
        reader1.close();
        reader2.close();
        writer.close();
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
              System.out.print(" ["+param.getName()+"]");
          }
          System.out.println(": "+rule.getDescription());
        }
        System.out.println("  ");
    }

}
