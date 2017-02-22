package innovimax.mixthem;

import innovimax.mixthem.arguments.Rule;
import innovimax.mixthem.exceptions.MixException;

import java.io.*;
import java.net.URL;
import java.util.Collections;
import java.util.List;

import org.junit.Assert;
import org.junit.Test;
import org.junit.experimental.theories.*;
import org.junit.runner.RunWith;

/*
	Created by innovimax
	Generic tests for this application
*/
public class GenericTest {
   
   @Test
   public final void parameter() throws MixException, FileNotFoundException, IOException {
	   
	   int testId = 1;
	   boolean result = true;
	   RuleRuns ruleRuns = new RuleRuns();
	   while (true) {
		   System.out.println("TEST n°" + testId);
		   String prefix = "test" + String.format("%03d", testId) +"_";
		   URL url1 = getClass().getResource(prefix + "file1.txt");
		   URL url2 = getClass().getResource(prefix + "file2.txt");
		   System.out.println("URL 1 ("+prefix + "file1.txt"+")="+url1+"; URL 2 ("+prefix + "file2.txt"+")="+url2);
		   if( url1 == null || url2 == null) break;
		   for(Rule rule : Rule.values()) {
			   String resource = prefix+"output-"+ rule.getExtension()+".txt";
			   URL url = getClass().getResource(resource);
			   System.out.println(rule+" implemented = "+rule.isImplemented()+" ; resource ("+resource+") = "+url);
			   if (rule.isImplemented() && url != null) {			   	
				List<RuleRun> runs = ruleRuns.getRuns(rule);
				for (RuleRun run : runs) {
					if (run.accept(testId)) {
						boolean res = check(new File(url1.getFile()), new File(url2.getFile()), new File(url.getFile()), rule, run.getParams());
						System.out.println("RULE PASS : "+res + " / PARAMS" + run.getParams().toString());
						result &= res;   
					}
				}
			   }
		   }   
		   testId++;
	   }
	   Assert.assertTrue(result);
   }	   
   private final static boolean check(File file1, File file2, File expected, Rule rule, List<String> params)  throws MixException, FileNotFoundException, IOException  {
	   ByteArrayOutputStream baos_rule = new ByteArrayOutputStream();
	   MixThem mixThem = new MixThem(file1, file2, baos_rule);
           mixThem.process(rule, params);
	   return checkFileEquals(expected, baos_rule.toByteArray());
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
