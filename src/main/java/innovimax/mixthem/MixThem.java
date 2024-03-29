package innovimax.mixthem;

import innovimax.mixthem.arguments.*;
import innovimax.mixthem.io.*;
import innovimax.mixthem.operation.*;

import java.io.IOException;
import java.io.OutputStream;
import java.util.List;
import java.util.Map;
import java.util.Set;
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
     * @param output The output stream to write mixing result
     */ 
    public MixThem(final List<InputResource> inputs, final OutputStream output) {
        super();
        this.inputs = inputs;
        this.output = output;
    }
    
    static void setLogging(final Level level) {
        if (LOGGER.getHandlers().length == 0) {
            //System.setProperty("java.util.logging.SimpleFormatter.format", "[%4$s] MixThem: %5$s [%1$tc]%n");
            System.setProperty("java.util.logging.SimpleFormatter.format", "[%4$s] MixThem: %5$s%n");
            LOGGER.setUseParentHandlers(false);
            LOGGER.setLevel(Level.ALL);
            final Handler handler = new ConsoleHandler();
            LOGGER.addHandler(handler);
            handler.setLevel(Level.OFF);
            final String prop = System.getProperty("mixthem.logging");
            if (prop == null || "true".equals(prop)) {
                handler.setLevel(level);            
            }
        }
    }

    /**
    * Main entry.
    * @param args The command line arguments
    */
    public static void main(final String[] args) {
        run(args);  
    }

    private static void run(final String[] args) {
        try {
            setLogging(Level.INFO);
            LOGGER.info("Started application");   
            final Arguments mixArgs = Arguments.checkArguments(args);
            final MixThem mixThem = new MixThem(mixArgs.getInputs(), System.out);
            mixThem.process(mixArgs.getFileMode(), mixArgs.getSelection(), mixArgs.getRule(), mixArgs.getRuleParameters());
            LOGGER.info("Exited application with no errors");
        } catch (final ArgumentException e) {
            LOGGER.severe("Exited application with errors...");
            LOGGER.severe("Files mixing can't be run due to following reason:"); 
            LOGGER.severe(e.getMessage());
            Arguments.printUsage(); 
        } catch (final MixException e) {
            LOGGER.severe("Exited application with errors...");
            LOGGER.severe("Files mixing has been aborted due to following reason:"); 
            LOGGER.severe(e.getMessage());
        } catch (IOException e) {
            throw new RuntimeException(e);
        } catch (final Exception e) {
            LOGGER.severe("Exited application with errors...");
            LOGGER.severe("An unexpected error occurs:");
            e.printStackTrace();
        }
    }

    /**
    * Mix files together using rules.
    * @param fileMode The mode to be used for reading files
    * @param selection The file index selection (maybe empty)
    * @param rule The rule to be used for mixing
    * @param params The rule parameters to be used for mixing
    * @throws MixException - If any error occurs during mixing
    * @see innovimax.mixthem.arguments.FileMode
    * @see innovimax.mixthem.arguments.Rule
    * @see innovimax.mixthem.arguments.RuleParam
    * @see innovimax.mixthem.arguments.ParamValue
    */  
    public void process(final FileMode fileMode, final Set<Integer> selection, final Rule rule, final Map<RuleParam, ParamValue> params) throws MixException {
        try {
            LOGGER.info("Started mixing for [" +  fileMode.getName() + "] rule '" + rule.getName() + "'...");
            switch(rule) {
                case ADD:
                    final IOperation copyAddOp = CopyFactory.newInstance(fileMode, selection, params);
                    copyAddOp.processFiles(this.inputs, this.output);          
                    break;
                case ALT_CHAR:
                    final IOperation altCharOp = new DefaultCharAlternation(AltMode.NORMAL, selection, TokenType.CHAR, params);
                    altCharOp.processFiles(this.inputs, this.output); 
                    break;
                case ALT_BYTE:
                    final IOperation altByteOp = new DefaultByteAlternation(AltMode.NORMAL, selection, TokenType.BYTE, params);
                    altByteOp.processFiles(this.inputs, this.output); 
                    break;
                case ALT_LINE:
                    final IOperation altLineOp = new DefaultLineAlternation(AltMode.NORMAL, selection, TokenType.LINE, params);
                    altLineOp.processFiles(this.inputs, this.output);
                    break;
                case RANDOM_ALT_BYTE:
                    final IOperation randomAltByteOp = new DefaultByteAlternation(AltMode.RANDOM, selection, TokenType.BYTE, params);
                    randomAltByteOp.processFiles(this.inputs, this.output); 
                    break;
                case RANDOM_ALT_CHAR:
                    final IOperation randomAltCharOp = new DefaultCharAlternation(AltMode.RANDOM, selection, TokenType.CHAR, params);
                    randomAltCharOp.processFiles(this.inputs, this.output); 
                    break;
                case RANDOM_ALT_LINE:
                    final IOperation randomAltLineOp = new DefaultLineAlternation(AltMode.RANDOM, selection, TokenType.LINE, params);
                    randomAltLineOp.processFiles(this.inputs, this.output); 
                    break;
                case JOIN:
                    final IOperation joinLineOp = new DefaultLineJoining(selection, TokenType.LINE, params);
                    joinLineOp.processFiles(this.inputs, this.output);
                    break;
                case ZIP_LINE:
                    final IOperation zipLineOp = new DefaultLineZipping(selection, TokenType.LINE, params);
                    zipLineOp.processFiles(this.inputs, this.output);
                    break;
                case ZIP_CELL:
                    final IOperation zipCellOp = new DefaultCellZipping(selection, TokenType.LINE, params);
                    zipCellOp.processFiles(this.inputs, this.output);
                    break;
                case ZIP_CHAR:
                    final IOperation zipCharOp = new DefaultCharZipping(selection, TokenType.CHAR, params);
                    zipCharOp.processFiles(this.inputs, this.output);
                    /*break;
                default:    
                   System.out.println("This rule has not been implemented yet.");*/
            }
            LOGGER.info("Ended mixing for [" +  fileMode.getName() + "] rule '" + rule.getName() + "'.");
        } catch (final IOException e) {
            throw new MixException("Unexpected file error", e);
        }

    }     

}
