package innovimax.mixthem;

/*
    Created by innovimax-jim
    Mix-them : Mix files togethers
*/
public class MixThem {

    public static void main(String[] args) {    	
        printUsage(args);   
    }

    private static void printUsage(String[] args) {        
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