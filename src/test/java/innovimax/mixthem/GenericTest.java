package innovimax.mixthem;

import innovimax.mixthem.MixThem;
import innovimax.mixthem.Rule;
import innovimax.mixthem.MixException;

import java.io.*;
import java.net.URL;

import org.junit.Assert;
import org.junit.Test;

/*
    Created by innovimax
    Generic tests for this application
*/
public class GenericTest {

   @Test
   public final void checkRule1() throws MixException, FileNotFoundException, IOException {
        URL url1 = getClass().getResource("test001_file1.txt");
        URL url2 = getClass().getResource("test001_file2.txt");
        File file1 = new File(url1.getFile());
        File file2 = new File(url2.getFile());
        ByteArrayOutputStream baos_rule_1 = new ByteArrayOutputStream();
        MixThem mixThem = new MixThem(file1, file2, baos_rule_1);
        mixThem.process(Rule._1);
        Assert.assertTrue(checkFileEquals(file1, baos_rule_1.toByteArray()));
        ByteArrayOutputStream baos_rule_2 = new ByteArrayOutputStream();
        mixThem = new MixThem(file1, file2, baos_rule_2);
        mixThem.process(Rule._2);
        Assert.assertTrue(checkFileEquals(file2, baos_rule_2.toByteArray()));
    }
  
    @Test
    public final void checkRuleAdd() throws MixException, FileNotFoundException, IOException {
        URL url1 = getClass().getResource("test001_file1.txt");
        URL url2 = getClass().getResource("test001_file2.txt");
        URL url12 = getClass().getResource("test001_output-add.txt");
        File file1 = new File(url1.getFile());
        File file2 = new File(url2.getFile());
        File file12 = new File(url12.getFile());          
        ByteArrayOutputStream baos_rule_12 = new ByteArrayOutputStream();
        MixThem mixThem = new MixThem(file1, file2, baos_rule_12);
        mixThem.process(Rule._ADD);        
        Assert.assertTrue(checkFileEquals(file12, baos_rule_12.toByteArray()));
    }

    @Test
    public final void checkRuleAltLine() throws MixException, FileNotFoundException, IOException {
        URL url1 = getClass().getResource("test001_file1.txt");
        URL url2 = getClass().getResource("test001_file2.txt");
        URL urlComp = getClass().getResource("test001_output-altline.txt");
        File file1 = new File(url1.getFile());
        File file2 = new File(url2.getFile());
        File fileComp = new File(urlComp.getFile());
        ByteArrayOutputStream baos_rule = new ByteArrayOutputStream();
        MixThem mixThem = new MixThem(file1, file2, baos_rule);
        mixThem.process(Rule._ALT_LINE);        
        Assert.assertTrue(checkFileEquals(fileComp, baos_rule.toByteArray()));
    }

    @Test
    public final void checkRuleAltChar() throws MixException, FileNotFoundException, IOException {
        URL url1 = getClass().getResource("test001_file1.txt");
        URL url2 = getClass().getResource("test001_file2.txt");
        URL urlComp = getClass().getResource("test001_output-altchar.txt");
        File file1 = new File(url1.getFile());
        File file2 = new File(url2.getFile());
        File fileComp = new File(urlComp.getFile());
        ByteArrayOutputStream baos_rule = new ByteArrayOutputStream();
        MixThem mixThem = new MixThem(file1, file2, baos_rule);
        mixThem.process(Rule._ALT_LINE);        
        Assert.assertTrue(checkFileEquals(fileComp, baos_rule.toByteArray()));
    }

    @Test
    public final void dumpRule1() throws MixException, FileNotFoundException, IOException {
        URL url1 = getClass().getResource("test001_file1.txt");
        URL url2 = getClass().getResource("test001_file2.txt");
        File file1 = new File(url1.getFile());
        File file2 = new File(url2.getFile());        
        System.out.println("File1:");
        MixThem mixThem = new MixThem(file1, file2, System.out);
        mixThem.process(Rule._1);        
        System.out.println("File2:");
        mixThem = new MixThem(file1, file2, System.out);
        mixThem.process(Rule._2);  
    }

    @Test
    public final void dumpRuleAltLine() throws MixException, FileNotFoundException, IOException {
        URL url1 = getClass().getResource("test001_file1.txt");
        URL url2 = getClass().getResource("test001_file2.txt");
        URL urlComp = getClass().getResource("test001_output-altline.txt");
        File file1 = new File(url1.getFile());
        File file2 = new File(url2.getFile());
        File fileComp = new File(urlComp.getFile());
        System.out.println("Mixed:");
        MixThem mixThem = new MixThem(file1, file2, System.out);
        mixThem.process(Rule._ALT_LINE);          
        System.out.println("Expected:");
        String line;
        BufferedReader br = new BufferedReader(new FileReader(fileComp));
        while ((line = br.readLine()) != null) {
            System.out.println(line);
        }
        br.close();
    }

    private static boolean checkFileEquals(File fileExpected, byte[] result) throws FileNotFoundException, IOException {
       FileInputStream fisExpected = new FileInputStream(fileExpected);
       int c;
       int offset = 0;
       while ((c = fisExpected.read()) != -1) {
           if (offset >= result.length) return false;
           int d = result[offset++];
           if (c != d) return false;
       }
       if (offset > result.length) return false;
       return true;
    }

}
