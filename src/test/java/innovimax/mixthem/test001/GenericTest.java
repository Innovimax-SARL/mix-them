package innovimax.mixthem.test001;

import innovimax.mixthem.MixThem;
import innovimax.mixthem.Constants;
import innovimax.mixthem.MixException;

import java.io.*;

import org.junit.Assert;
import org.junit.Test;

/*
    Created by innovimax
    Generic tests for this application
*/
public class GenericTest {

   @Test
   public final void check1() throws MixException, FileNotFoundException, IOException {
          ClassLoader classLoader = getClass().getClassLoader();
          File file1 = classLoader.getResource("file1.txt").getFile();
          File file2 = classLoader.getResource("file2.txt").getFile();
          ByteArrayOutputStream baos_rule_1 = new ByteArrayOutputStream();
          MixThem.processFiles(Constants.RULE_1, file1, file2, baos_rule_1);
          Assert.assertTrue(checkFileEquals(file1, baos_rule_1.toByteArray()));
          ByteArrayOutputStream baos_rule_2 = new ByteArrayOutputStream();
          MixThem.processFiles(Constants.RULE_2, file1, file2, baos_rule_2);
          Assert.assertTrue(checkFileEquals(file2, baos_rule_2.toByteArray()));
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
	   if (offset >= result.length) return false;
          int e = result[offset++];
       if (c != e) return false;
       return true;
    }

}
