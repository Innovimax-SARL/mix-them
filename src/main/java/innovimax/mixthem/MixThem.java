package innovimax.mixthem;

import java.io.File;

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
            String file1 = args[0];
            String file2 = args[1];   
            if (checkArguments(args)) {
                processFiles(file1, file2);
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

    private static void processFiles(String files1, String file2) throws MixException { 
        //TODO
    }   

    public static boolean checkArguments(String[] args) { 
        String file1 = args[0];
        String file2 = args[1];
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