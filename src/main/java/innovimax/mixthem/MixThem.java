package innovimax.mixthem;

import innovimax.mixthem.arguments.*;
import innovimax.mixthem.io.*;
import innovimax.mixthem.operation.*;

import java.io.File;
import java.io.IOException;
import java.io.OutputStream;
import java.util.Map;
import java.util.logging.ConsoleHandler;
import java.util.logging.Handler;
import java.util.logging.Level;
import java.util.logging.Logger;

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
    
    public final static Logger LOGGER = Logger.getLogger(MixThem.class.getName());
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
    
    static void setLogging(Level level) {	
	if (LOGGER.getHandlers().length == 0) {
            //System.setProperty("java.util.logging.SimpleFormatter.format", "[%4$s] MixThem: %5$s [%1$tc]%n");            
	    System.setProperty("java.util.logging.SimpleFormatter.format", "[%4$s] MixThem: %5$s%n");
	    LOGGER.setUseParentHandlers(false);	 
	    LOGGER.setLevel(Level.ALL);
	    Handler handler = new ConsoleHandler();
	    LOGGER.addHandler(handler);        
	    handler.setLevel(Level.OFF);
	    String prop = System.getProperty("mixthem.logging");
            if (prop == null || prop.equals("true")) {
                handler.setLevel(level);            
            }
	}
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
	    setLogging(Level.INFO);
	    LOGGER.info("Started application");		
            Arguments mixArgs = Arguments.checkArguments(args);        
            MixThem mixThem = new MixThem(mixArgs.getFirstFile(), mixArgs.getSecondFile(), System.out);
            mixThem.process(mixArgs.getRule(), mixArgs.getRuleParameters());
            LOGGER.info("Exited application with no errors");
        } catch (ArgumentException e) {
            LOGGER.severe("Exited application with errors...");
            LOGGER.severe("Files mixing can't be run due to following reason:"); 
            LOGGER.severe(e.getMessage());
            Arguments.printUsage(); 
        } catch (MixException e) {
            LOGGER.severe("Exited application with errors...");
            LOGGER.severe("Files mixing has been aborted due to following reason:"); 
            LOGGER.severe(e.getMessage());
        } catch (Exception e) {
            LOGGER.severe("Exited application with errors...");
            LOGGER.severe("An unexpected error occurs:");
            e.printStackTrace();
        }
    }

    /**
    * Mix files together using rules.
    * @param rule The rule to be used for mixing
    * @param params The rule parameters to be used for mixing
    * @throws MixException - If any error occurs during mixing
    * @see innovimax.mixthem.Rule
    * @see innovimax.mixthem.RuleParam
    * @see innovimax.mixthem.ParamValue
    */  
    public void process(Rule rule, Map<RuleParam, ParamValue> params) throws MixException {
        try {
	    LOGGER.info("Started mixing for rule '" + rule.getName() + "'...");
            switch(rule) {
                case FILE_1:
                  copyChar(this.file1, this.out);
                  break;
                case FILE_2:               
                  copyChar(this.file2, this.out);  
                  break; 
                case ADD:    
                  copyChar(this.file1, this.out);
                  copyChar(this.file2, this.out);
                  break;
                case ALT_CHAR:
		  IOperation altCharOp = new DefaultCharAlternation(params);
		  altCharOp.processFiles(this.file1, this.file2, this.out);	
                  break;
                case ALT_LINE:    
		  IOperation altLineOp = new DefaultLineAlternation(AltMode._NORMAL, params);
		  altLineOp.processFiles(this.file1, this.file2, this.out);			    
                  break;
                case RANDOM_ALT_LINE:
		  IOperation randomAltLineOp = new DefaultLineAlternation(AltMode._RANDOM, params);
		  randomAltLineOp.processFiles(this.file1, this.file2, this.out);	
                  break;
                case JOIN:  
		  IOperation joinLineOp = new DefaultLineJoining(params);
		  joinLineOp.processFiles(this.file1, this.file2, this.out);
                  break;
                case ZIP_LINE:
		  IOperation zipLineOp = new DefaultLineZipping(ZipType._LINE, params);
		  zipLineOp.processFiles(this.file1, this.file2, this.out);
                  break;
		case ZIP_CELL:		  
		  IOperation zipCellOp = new DefaultLineZipping(ZipType._CELL, params);
		  zipCellOp.processFiles(this.file1, this.file2, this.out);
		  break;
		case ZIP_CHAR:			    
		  IOperation zipCharOp = new DefaultCharZipping(params);
		  zipCharOp.processFiles(this.file1, this.file2, this.out);
		  /*break;
                default:    
                   System.out.println("This rule has not been implemented yet.");*/
            }
	    LOGGER.info("Ended mixing for rule '" + rule.getName() + "'.");
        } catch (IOException e) {
            throw new MixException("Unexpected file error", e);
        }

    }   

    // this one copies one file as beeing char
    private static void copyChar(File file, OutputStream out) throws IOException {	
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

}
