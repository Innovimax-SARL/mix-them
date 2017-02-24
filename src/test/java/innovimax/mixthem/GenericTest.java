package innovimax.mixthem;

import innovimax.mixthem.arguments.Rule;
import innovimax.mixthem.exceptions.MixException;

import java.io.*;
import java.net.URL;
import java.util.Collections;
import java.util.List;
import java.util.logging.ConsoleHandler;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.junit.Assert;
import org.junit.Test;
import org.junit.experimental.theories.*;
import org.junit.runner.RunWith;

/*
	Created by innovimax
	Generic tests for this application
*/
public class GenericTest {
	
   private static Logger LOGGER = Logger.getLogger(GenericTest.class.getName());

   private static void setLogging() {
        System.setProperty("java.util.logging.SimpleFormatter.format", "[%4$s] MixThemUT: %5$s [%1$tc]%n");
        String prop = System.getProperty("mixthem.logging");
        if (prop == null || prop.equals("true")) {
            LOGGER.setLevel(Level.FINE);
        } else {
            LOGGER.setLevel(Level.OFF);
        }
        LOGGER.addHandler(new ConsoleHandler());        
   }
	
   @Test
   public final void parameter() throws MixException, FileNotFoundException, IOException {
	   setLogging();
	   int testId = 1;
	   boolean result = true;
	   RuleRuns ruleRuns = new RuleRuns();
	   while (true) {
		   LOGGER.info("TEST N° " + testId);
		   String prefix = "test" + String.format("%03d", testId) +"_";
		   URL url1 = getClass().getResource(prefix + "file1.txt");
		   URL url2 = getClass().getResource(prefix + "file2.txt");
		   LOGGER.fine("--> URL 1 (" + prefix + "file1.txt"+") : " + url1);
		   LOGGER.fine("--> URL 2 (" + prefix + "file2.txt"+") : " + url2);
		   if( url1 == null || url2 == null) break;
		   for(Rule rule : Rule.values()) {
			   String resource = prefix+"output-"+ rule.getExtension()+".txt";
			   URL url = getClass().getResource(resource);
			   LOGGER.info("  RULE " + rule + " (" + (rule.isImplemented() ? "" : "NOT ") + "IMPLEMENTED)");
			   LOGGER.fine("  --> Resource (" + resource + ") : " + url);
			   if (rule.isImplemented() && url != null) {			   	
				List<RuleRun> runs = ruleRuns.getRuns(rule);
				for (RuleRun run : runs) {
					if (run.accept(testId)) {						
						boolean res = check(new File(url1.getFile()), new File(url2.getFile()), new File(url.getFile()), rule, run.getParams());
						LOGGER.info("    RUN " + (res ? "PASS" : "FAIL") + " WITH PARAMS " + run.getParams().toString());
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
	   //mixThem = new MixThem(file1, file2, System.out);
           //mixThem.process(rule, params);
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
