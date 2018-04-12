package innovimax.mixthem;

import innovimax.mixthem.arguments.ParamValue;
import innovimax.mixthem.arguments.Rule;
import innovimax.mixthem.arguments.RuleParam;

import java.io.*;
import java.net.URL;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;

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
   public final void parameter() throws MixException, FileNotFoundException, IOException, NumberFormatException {
	   MixThem.setLogging(Level.FINE);
	   int testId = 1;
	   boolean result = true;
	   while (true) {
		   MixThem.LOGGER.info("TEST N° " + testId + "***********************************************************");
		   String prefix = "test" + String.format("%03d", testId) +"_";
		   URL url1 = getClass().getResource(prefix + "file1.txt");
		   URL url2 = getClass().getResource(prefix + "file2.txt");
		   MixThem.LOGGER.info("--> URL 1 (" + prefix + "file1.txt"+") : " + (url1 != null ? "FOUND" : "NOT FOUND"));
		   MixThem.LOGGER.info("--> URL 2 (" + prefix + "file2.txt"+") : " + (url2 != null ? "FOUND" : "NOT FOUND"));
		   if( url1 == null || url2 == null) break;
		   for(Rule rule : Rule.values()) {
			   MixThem.LOGGER.info("RULE " + rule + " (" + (rule.isImplemented() ? "" : "NOT ") + "IMPLEMENTED)");
			   if (rule.isImplemented()) {
				   String paramsFile = prefix + "params-" + rule.getExtension() + ".txt";
				   URL urlP = getClass().getResource(paramsFile);
				   if (urlP != null) {
					   MixThem.LOGGER.info("--> Params (" + paramsFile + ") : FOUND");
				   }
				   List<RuleRun> runs = RuleRuns.getRuns(rule, urlP);
				   for (RuleRun run : runs) {
					   String resultFile = prefix + "output-" + rule.getExtension();
					   if (run.hasSuffix()) {
						   resultFile += "-" + run.getSuffix();
					   }
					   resultFile += ".txt";
					   URL urlR = getClass().getResource(resultFile);
					   MixThem.LOGGER.info("--> Result (" + resultFile + ") : " + (urlR != null ? "FOUND" : "NOT FOUND"));
					   if (urlR != null) {
						   boolean res = check(new File(url1.getFile()), new File(url2.getFile()), new File(urlR.getFile()), rule, run.getParams());
						   MixThem.LOGGER.info("RUN " + (res ? "PASS" : "FAIL") + " WITH PARAMS " + run.getParams().toString());
						   result &= res;
					   }
				   }
			   }
		   }
		   testId++;
	   }
	   Assert.assertTrue(result);
   }	   
   private final static boolean check(File file1, File file2, File expected, Rule rule, Map<RuleParam, ParamValue> params)  throws MixException, FileNotFoundException, IOException  {
	   ByteArrayOutputStream baos_rule = new ByteArrayOutputStream();
	   MixThem mixThem = new MixThem(file1, file2, baos_rule);
           mixThem.process(rule, params);	   	   
	   mixThem = new MixThem(file1, file2, System.out);
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
