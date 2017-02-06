package innovimax.mixthem;

import java.io.*;
import java.util.Scanner;

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
                  //copyAltLine2(file1, file2, out);
                  break;
                case _ALT_CHAR:
                case _RANDOM_ALT_LINE:
                case _JOIN:               
                //TODO
                    break;
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
        FileInputStream in = new FileInputStream(file);
        byte[] buffer = new byte[BYTE_BUFFER_SIZE];
        int c;
        while ((c = in.read(buffer)) != -1) {
            out.write(buffer, 0, c);
        }
        in.close();
        // out.close();
    }    

    // this one copies two files alternativly line by line (InpuStreamReader)
    private static void copyAltLine(File file1, File file2, OutputStream out) throws MixException, IOException {
        BufferedReader br1 = new BufferedReader(new InputStreamReader(new FileInputStream(file1)));
        BufferedReader br2 = new BufferedReader(new InputStreamReader(new FileInputStream(file2)));
        boolean read1 = true;
        boolean read2 = true;
        boolean first = true;
        while(read1 || read2) {            
            if (read1) {
                final String line = br1.readLine();
                if (line  == null) {
                    read1 = false;
                } else {
                    if (first || !read2) {
                        printLine(line, out);    
                    }
                }
            }  
            if (read2) {
                final String line = br2.readLine();
                if (line  == null) {
                    read2 = false;
                } else {
                    if (!first || !read1) {
                        printLine(line, out);    
                    }                    
                }
            }
            first = !first;
        }
        br1.close();
        br2.close();
        // out.close();
    }

    private static void printLine(String line, OutputStream out) throws MixException, IOException {
        byte[] array = line.getBytes("UTF-8");
        for (byte b : array){
            out.write(b);
        }
        out.write(10); // LF
        //out.write(13); // CR
    }

    // this one copies two files alternativly line by line (FileInputStream+Scanner)
    private static void copyAltLine2(File file1, File file2, OutputStream out) throws MixException, IOException {        
        Scanner scan1 = new Scanner(new FileInputStream(file1));        
        Scanner scan2 = new Scanner(new FileInputStream(file2));
        boolean read1 = true;
        boolean read2 = true;
        boolean first = true;
        while(read1 || read2) {            
            if (read1) {
                if (scan1.hasNextLine()) {
                    final String line = scan1.nextLine();
                    if (first || !read2) {
                        printLine(line, out);    
                    }                    
                } else {                
                    read1 = false;
                }
            }  
            if (read2) {
                if (scan2.hasNextLine()) {
                    final String line = scan2.nextLine();
                    if (!first || !read1) {
                        printLine(line, out);    
                    }                    
                } else {                
                    read2 = false;
                }
            }
            first = !first;
        }
        scan1.close();
        scan2.close();
        // out.close();
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
            Rule rule = Rule.findByName(ruleString);
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
        return rule;
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
        for(Rule rule : Rule.values()) {
          System.out.print("  - " + rule.getName());
          for(String param : rule.getParams()) {
              System.out.print(" ["+param+"]");
          }
          System.out.println(rule.getDescription());
        }
        System.out.println("  ");
    }

}
