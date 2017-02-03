package innovimax.mixthem;

import java.io.IOException;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;

/*
    Created by innovimax-jim
    Mix-them : Mix files togethers
*/
public class MixThem {

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
                    rule = "1";
                    file1 = args[0];
                    file2 = args[1]; 
                }                 
                processFiles(rule, new File(file1), new File(file2));
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

    private static void processFiles(String rule, File file1, File file2) throws MixException {
        try {
            if (rule.equals("1")) {
                copy(file1);
            } else if (rule.equals("2")) {
                copy(file2);
            } else {
                System.out.println("This rule has not been implemented yet.");
            }     
        } catch (IOException e) {
            throw new MixException("Unexpected file error", e);
        } catch (MixException e) {
            throw e;
        }

    }   

    private static void copy(File file) throws MixException, IOException {
        FileReader in = new FileReader(file);
        FileWriter out = new FileWriter(file.getName() + "mix");
        char[] buffer = new char[1024]; // TODO extract constant
        int c;
        while ((c = in.read(buffer)) != -1) {
            out.write(buffer, 0, c);
            in.close();
            out.close(); 
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
            if (!rule.equals("1") && !rule.equals("2") && !rule.equals("alt-line") 
                && !rule.equals("alt-byte") && !rule.equals("rand-alt-line")) {
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
        System.out.println("  - [ ] 1 : will output file1");
        System.out.println("  - [ ] 2 : will output file2");
        System.out.println("  - [ ] alt-line : will output one line of each starting with first line of file1");
        System.out.println("  - [ ] alt-byte : will output one byte of each starting with first byte of file1");
        System.out.println("  - [ ] rand-alt-line [seed] : will output one line of each code randomly based on a seed for reproducability");
        System.out.println("  ");
    }

}
