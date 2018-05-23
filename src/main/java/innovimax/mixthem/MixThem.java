package innovimax.mixthem;

import innovimax.mixthem.arguments.*;
import innovimax.mixthem.io.*;
import innovimax.mixthem.operation.*;

import java.io.IOException;
import java.io.OutputStream;
import java.util.List;
import java.util.Map;
import java.util.logging.ConsoleHandler;
import java.util.logging.Handler;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
* <p>Mix files together using variety of rules.</p>
* @author Innovimax
* @version 1.0
*/
public class MixThem {
    
    public final static Logger LOGGER = Logger.getLogger(MixThem.class.getName());

    private final List<InputResource> inputs;
    private final OutputStream output;
    /**
     * Constructor
     * @param inputs The list of input resource to be mixed     
     * @param out The output stream to write mixing result
     */ 
    public MixThem(List<InputResource> inputs, OutputStream output) {
        this.inputs = inputs;        
        this.output = output;
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
            MixThem mixThem = new MixThem(mixArgs.getInputs(), System.out);
            mixThem.process(mixArgs.getFileMode(), mixArgs.getRule(), mixArgs.getRuleParameters());
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
    * @param fileMode The mode to be used for reading files
    * @param rule The rule to be used for mixing
    * @param params The rule parameters to be used for mixing
    * @throws MixException - If any error occurs during mixing
    * @see innovimax.mixthem.FileMode
    * @see innovimax.mixthem.Rule
    * @see innovimax.mixthem.RuleParam
    * @see innovimax.mixthem.ParamValue
    */  
    public void process(FileMode fileMode, Rule rule, Map<RuleParam, ParamValue> params) throws MixException {
        try {
            LOGGER.info("Started mixing for [" +  fileMode.getName() + "] rule '" + rule.getName() + "'...");
            switch(rule) {
                case FILE_1:
                    final IOperation copyFile1Op = CopyFactory.newInstance(fileMode, CopyMode.FIRST, params);
                    copyFile1Op.processFiles(this.inputs, this.output);                    
                    break;
                case FILE_2:
                    final IOperation copyFile2Op = CopyFactory.newInstance(fileMode, CopyMode.SECOND, params);
                    copyFile2Op.processFiles(this.inputs, this.output);          
                    break;
                case FILE_N:
                    final IOperation copyFileNOp = CopyFactory.newInstance(fileMode, CopyMode.UMPTEENTH, params);
                    copyFileNOp.processFiles(this.inputs, this.output);          
                    break;
                case ADD:                    
                    final IOperation copyAddOp = CopyFactory.newInstance(fileMode, CopyMode.ALL, params);
                    copyAddOp.processFiles(this.inputs, this.output);          
                    break;
                case ALT_CHAR:
                    final IOperation altCharOp = new DefaultCharAlternation(AltMode.NORMAL, params);
                    altCharOp.processFiles(this.inputs, this.output); 
                    break;
                case ALT_BYTE:
                    final IOperation altByteOp = new DefaultByteAlternation(AltMode.NORMAL, params);
                    altByteOp.processFiles(this.inputs, this.output); 
                    break;
                case ALT_LINE:
                    final IOperation altLineOp = new DefaultLineAlternation(AltMode.NORMAL, params);
                    altLineOp.processFiles(this.inputs, this.output);
                    break;
                case RANDOM_ALT_BYTE:
                    final IOperation randomAltByteOp = new DefaultByteAlternation(AltMode.RANDOM, params);
                    randomAltByteOp.processFiles(this.inputs, this.output); 
                    break;
                case RANDOM_ALT_CHAR:
                    final IOperation randomAltCharOp = new DefaultCharAlternation(AltMode.RANDOM, params);
                    randomAltCharOp.processFiles(this.inputs, this.output); 
                    break;
                case RANDOM_ALT_LINE:
                    final IOperation randomAltLineOp = new DefaultLineAlternation(AltMode.RANDOM, params);
                    randomAltLineOp.processFiles(this.inputs, this.output); 
                    break;
                case JOIN:
                    final IOperation joinLineOp = new DefaultLineJoining(params);
                    joinLineOp.processFiles(this.inputs, this.output);
                    break;
                case ZIP_LINE:
                    final IOperation zipLineOp = new DefaultLineZipping(params);
                    zipLineOp.processFiles(this.inputs, this.output);
                    break;
                case ZIP_CELL:
                    final IOperation zipCellOp = new DefaultCellZipping(params);
                    zipCellOp.processFiles(this.inputs, this.output);
                    break;
                case ZIP_CHAR:
                    final IOperation zipCharOp = new DefaultCharZipping(params);
                    zipCharOp.processFiles(this.inputs, this.output);
                    /*break;
                default:    
                   System.out.println("This rule has not been implemented yet.");*/
            }
            LOGGER.info("Ended mixing for [" +  fileMode.getName() + "] rule '" + rule.getName() + "'.");
        } catch (IOException e) {
            throw new MixException("Unexpected file error", e);
        }

    }     

}
