package innovimax.mixthem;

import java.io.IOException;
import java.io.File;
import java.io.FileInputStream;
import java.io.OutputStream;

/*
    Created by innovimax-jim
    Mix-them : Mix files togethers
*/
public class MixThem {
    private final static int BYTE_BUFFER_SIZE = 1024;

    public static void main(String[] args) { 
        run(args);  
    }

    private static void run(String[] args) {
        try {
            if (checkArguments(args)) {
                String rule, file1, file2;
                if (args.length >= 3) {
                    rule = args[0];
                    file1 = args[1];
                    file2 = args[2];
                } else {
                    rule = Constants.RULE_1;
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

    public static void processFiles(String rule, File file1, File file2, OutputStream out) throws MixException {
        try {
            if (rule.equals(Constants.RULE_1)) {
                copyChar(file1, out);
            } else if (rule.equals(Constants.RULE_2)) {
                copyChar(file2, out);
            } else {
                System.out.println("This rule has not been implemented yet.");
            }     
        } catch (IOException e) {
            throw new MixException("Unexpected file error", e);
        } catch (MixException e) {
            throw e;
        }

    }   
    // this one copies the files as beeing char
    private static void copyChar(File file, OutputStream out) throws MixException, IOException {
        FileInputStream in = new FileInputStream(file);
        byte[] buffer = new byte[BYTE_BUFFER_SIZE];
        int c;
        while ((c = in.read(buffer)) != -1) {
            out.write(buffer, 0, c);
            in.close();
            // out.close(); 
        }
    }      

    public static boolean checkArguments(String[] args) { 
        String rule = null;
        String file1 = null;
        String file2 = null;
        if (args.length >= 3) {
            rule = args[0];
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
        boolean ruleOk = true;
        if (rule != null) {            
            if (!rule.equals(Constants.RULE_1) && !rule.equals(Constants.RULE_2) && !rule.equals(Constants.RULE_ALT_LINE) 
                && !rule.equals(Constants.RULE_ALT_BYTE) && !rule.equals(Constants.RULE_RANDOM_ALT_LINE)) {
                System.out.println("rule argument is incorrect.");
                ruleOk = false;
            }
        }
        if (ruleOk) {
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
                                return true;
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
        return false;
    }    

    private static void printUsage() {    
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
        System.out.println("  - "+Constants.RULE_1+": will output file1");
        System.out.println("  - "+Constants.RULE_2+": will output file2");
        System.out.println("  - "+Constants.RULE_ALT_LINE+": will output one line of each starting with first line of file1");
        System.out.println("  - "+Constants.RULE_ALT_BYTE+": will output one byte of each starting with first byte of file1");
        System.out.println("  - "+Constants.RULE_RANDOM_ALT_LINE+" [seed]: will output one line of each code randomly based on a seed for reproducability");
        System.out.println("  ");
    }

}
