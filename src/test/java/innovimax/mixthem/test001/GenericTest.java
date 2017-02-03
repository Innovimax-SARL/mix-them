package innovimax.mixthem.test001;

import innovimax.mixthem.MixThem;
import innovimax.mixthem.Constants;

import java.io.*;

import org.junit.Assert;
import org.junit.Test;

/*
    Created by innovimax
    Generic tests for this application
*/
public class GenericTest {

	@Test
  	public final void check1() {
      ClassLoader classLoader = getClass().getClassLoader();
      File file1 = new File(classLoader.getResource("file1.txt").getFile());
      File file2 = new File(classLoader.getResource("file2.txt").getFile());
      Assert.assertTrue(checkFileEquals(file1, MixThem.processFiles(Constants.RULE_1, file1, file2)));
      Assert.assertTrue(checkFileEquals(file2, MixThem.processFiles(Constants.RULE_2, file1, file2)));
  	}
    private static boolean checkFileEquals(File fileExpected, File fileResult) {
       FileInputStream fisExpected = new FileInputStream(fileExpected);
       FileInputStream fisResult = new FileInputStream(fileResult);
       int c;
       while ((c = fisExpected.read()) != -1) {
          int d = fisResult.read();
          if (c != d) return false;
       }
       int e = fisResult.read();
       if (c != e) return false;
       return true;
    }

}
