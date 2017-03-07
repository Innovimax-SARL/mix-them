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
                case _ALT_CHAR:
                  alternateChar(this.file1, this.file2, this.out, ReadType._ALT_SIMPLE);
                  break;
                case _ALT_LINE:    
                  alternateLine(this.file1, this.file2, this.out, ReadType._ALT_SIMPLE, params);
                  break;
                case _RANDOM_ALT_LINE:
                  alternateLine(this.file1, this.file2, this.out, ReadType._ALT_RANDOM, params);
                  break;
                case _JOIN:  
                  joinLine(this.file1, this.file2, this.out, params);
                  break;
                case _ZIP_LINE:
		  zipLine(this.file1, this.file2, this.out, ZipType._LINE, params);
                  break;
		case _ZIP_CELL:
		  zipLine(this.file1, this.file2, this.out, ZipType._CELL, params);
		  break;
		case _ZIP_CHAR:			    
		  zipChar(this.file1, this.file2, this.out, params);
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

    // this one copies two files alternativly char by char
    private static void alternateChar(File file1, File file2, OutputStream out, ReadType type) throws MixException, IOException {
        IInputChar reader1 = new DefaultCharReader(file1, true);
        IInputChar reader2 = new DefaultCharReader(file2, false);
        IOutputChar writer = new DefaultCharWriter(out);
        while (reader1.hasCharacter() || reader2.hasCharacter()) {             
            final int c1 = reader1.nextCharacter(reader2.hasCharacter() ? type : ReadType._REGULAR);
            if (c1 >= 0) {
                writer.writeCharacter(c1);
            }            
            final int c2 = reader2.nextCharacter(reader1.hasCharacter() ? type : ReadType._REGULAR);
            if (c2 >= 0) {
                writer.writeCharacter(c2);
            }
        }
        reader1.close();
        reader2.close();
        writer.close();
    }

    // this one copies two files alternativly line by line
    private static void alternateLine(File file1, File file2, OutputStream out, ReadType type,  Map<RuleParam, ParamValue> params) throws MixException, IOException {
        IInputLine reader1 = new DefaultLineReader(file1, true);
        IInputLine reader2 = new DefaultLineReader(file2, false);
        IOutputLine writer = new DefaultLineWriter(out);
        if (type == ReadType._ALT_RANDOM && params.containsKey(RuleParam._RANDOM_SEED)) {
            int seed = params.get(RuleParam._RANDOM_SEED).asInt();
            reader1.setSeed(seed);
            reader2.setSeed(seed);
        }
        while (reader1.hasLine() || reader2.hasLine()) {            
            final String line1 = reader1.nextLine(reader2.hasLine() ? type : ReadType._REGULAR);
            if (line1 != null) {
                writer.writeLine(line1);
            }            
            final String line2 = reader2.nextLine(reader1.hasLine() ? type : ReadType._REGULAR);
            if (line2 != null) {
                writer.writeLine(line2);
            }
        }
        reader1.close();
        reader2.close();
        writer.close();
    }

    // this one join lines of two files on a common field
    private static void joinLine(File file1, File file2, OutputStream out,  Map<RuleParam, ParamValue> params) throws MixException, IOException {
        IInputLine reader1 = new DefaultLineReader(file1, true);
        IInputLine reader2 = new DefaultLineReader(file2, false);
        IOutputLine writer = new DefaultLineWriter(out);
        ILineOperation joining = new DefaultLineJoining(params);   
        while (reader1.hasLine() && reader2.hasLine()) {            
            final String line1 = reader1.nextLine(ReadType._REGULAR);
            final String line2 = reader2.nextLine(ReadType._REGULAR);                        
            String join = joining.process(line1, line2);
            if (join != null) {
                writer.writeLine(join);
            }
        }
        reader1.close();
        reader2.close();
        writer.close();
    }

    // this one zine lines of two files
    private static void zipLine(File file1, File file2, OutputStream out,  ZipType type, Map<RuleParam, ParamValue> params) throws MixException, IOException {
        IInputLine reader1 = new DefaultLineReader(file1, true);
        IInputLine reader2 = new DefaultLineReader(file2, false);
        IOutputLine writer = new DefaultLineWriter(out);
        ILineOperation zipping = new DefaultLineZipping(type, params);   
        while (reader1.hasLine() && reader2.hasLine()) {            
            final String line1 = reader1.nextLine(ReadType._REGULAR);
            final String line2 = reader2.nextLine(ReadType._REGULAR);                        
            String zip = zipping.process(line1, line2);
            writer.writeLine(zip);
        }
        reader1.close();
        reader2.close();
        writer.close();
    }

    // this one zine characters of two files
    private static void zipChar(File file1, File file2, OutputStream out,  Map<RuleParam, ParamValue> params) throws MixException, IOException {
        IInputChar reader1 = new DefaultCharReader(file1, true);
        IInputChar reader2 = new DefaultCharReader(file2, false);
        IOutputChar writer = new DefaultCharWriter(out);
        ICharOperation zipping = new DefaultCharZipping(params);   
        while (reader1.hasCharacter() && reader2.hasCharacter()) {            
	    final int c1 = reader1.nextCharacter(ReadType._REGULAR);
	    final int c2 = reader2.nextCharacter(ReadType._REGULAR);                                    
            int[] zip = zipping.process(c1, c2);
	    for (int i = 0; i < zip.length; i++) {
                writer.writeCharacter(zip[i]);
            }
        }
        reader1.close();
        reader2.close();
        writer.close();
    }

    private static void processLine(File file1, File file2, OutputStream out,  Map<RuleParam, ParamValue> params, ILineOperation operation) 
	    throws MixException, IOException {
	IInputLine reader1 = new DefaultLineReader(file1, true);
	IInputLine reader2 = new DefaultLineReader(file2, false);
	IOutputLine writer = new DefaultLineWriter(out);
	while (reader1.hasLine() && reader2.hasLine()) {
		final String line1 = reader1.nextLine(ReadType._REGULAR).
		final String line2 = reader2.nextLine(ReadType._REGULAR);
		String result = operation.process(line1, line2).
		if (result != null) {
			writer.writeLine(result);
		}
        }
        reader1.close();
        reader2.close();
        writer.close();
    }

    private static void processChar(File file1, File file2, OutputStream out,  Map<RuleParam, ParamValue> params, ICharOperation operation) 
	    throws MixException, IOException {
        IInputChar reader1 = new DefaultCharReader(file1, true);
        IInputChar reader2 = new DefaultCharReader(file2, false);
        IOutputChar writer = new DefaultCharWriter(out); 
        while (reader1.hasCharacter() && reader2.hasCharacter()) {
		final int c1 = reader1.nextCharacter(ReadType._REGULAR);
		final int c2 = reader2.nextCharacter(ReadType._REGULAR);
		int[] result = operation.process(c1, c2);
		for (int i = 0; i < result.length; i++) {
			writer.writeCharacter(result[i]);
		}		
        }
        reader1.close();
        reader2.close();
        writer.close();
    }

}
