package innovimax.mixthem;

import innovimax.mixthem.arguments.Mode;
import innovimax.mixthem.arguments.ParamValue;
import innovimax.mixthem.arguments.Rule;
import innovimax.mixthem.arguments.RuleParam;
import innovimax.mixthem.io.InputResource;

import java.io.*;
import java.net.URL;
import java.util.ArrayList;
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
   public final void testCharRules() throws MixException, FileNotFoundException, IOException, NumberFormatException {
	   testRules(Mode.CHAR);
   }

   @Test
   public final void testBytesRules() throws MixException, FileNotFoundException, IOException, NumberFormatException {
	   testRules(Mode.BYTE);
   }

   private final void testRules(final Mode mode) throws MixException, FileNotFoundException, IOException, NumberFormatException {
	   MixThem.setLogging(Level.FINE);
	   int testId = 1;
	   List<String> failed = new ArrayList<String>();
	   boolean result = true;
	   while (true) {
		   MixThem.LOGGER.info("TEST [" + mode.getName().toUpperCase() + "] N° " + testId + "***********************************************************");
		   final String prefix = "test" + String.format("%03d", testId) +"_";
		   final List<URL> urlF = new ArrayList<URL>();
		   int index = 1;
		   while (true) {
			   final URL url = getClass().getResource(prefix + "file" + index + ".txt");
			   if (url != null) {
				   urlF.add(url);
				   index++;
			   } else {
				   break;
			   }
		   }		   		   
		   if( urlF.size() < 2) break;
		   for (int i=0; i < urlF.size(); i++) {
			   MixThem.LOGGER.info("File " + (i+1) + ": " + urlF.get(i));
		   }
		   for(Rule rule : Rule.values()) {			   
			   if (rule.isImplemented() && rule.acceptMode(mode)) {
				   String paramsFile = prefix + "params-" + rule.getExtension() + ".txt";
				   URL urlP = getClass().getResource(paramsFile);
				   List<RuleRun> runs = RuleRuns.getRuns(rule, urlP);
				   for (RuleRun run : runs) {
					   String resultFile = prefix + "output-" + rule.getExtension();
					   if (run.hasSuffix()) {
						   resultFile += "-" + run.getSuffix();
					   }
					   resultFile += ".txt";
					   URL urlR = getClass().getResource(resultFile);					   
					   if (urlR != null) {
						   MixThem.LOGGER.info("--------------------------------------------------------------------");
						   if (urlP != null) {
							   MixThem.LOGGER.info("Params file : " + urlP);
						   }
						   MixThem.LOGGER.info("Result file : " + urlR);
						   MixThem.LOGGER.info("--------------------------------------------------------------------");
						   boolean res = check(urlF, urlR, mode, rule, run.getParams());
						   MixThem.LOGGER.info("Run " + (res ? "pass" : "FAIL") + " with params " + run.getParams().toString());
						   result &= res;
						   if (!res) {
							   failed.add(Integer.toString(testId));
						   }
					   }
				   }
			   }
		   }
		   testId++;
	   }
	   MixThem.LOGGER.info("*********************************************************************");
	   MixThem.LOGGER.info("FAILED [" + mode.getName().toUpperCase() + "] TESTS : " + (failed.size() > 0 ? failed.toString() : "None"));
	   MixThem.LOGGER.info("*********************************************************************");
	   Assert.assertTrue(result);
   }	   

   private final static boolean check(final List<URL> filesURL, final URL resultURL, final Mode mode, final Rule rule, final Map<RuleParam, ParamValue> params)  throws MixException, FileNotFoundException, IOException  {
	   MixThem.LOGGER.info("Run and check result...");	   
	   final List<InputResource> inputs = new ArrayList<InputResource>();
	   for (URL url : filesURL) {
	   	inputs.add(InputResource.createFile(new File(url.getFile())));
	   }
	   final ByteArrayOutputStream baos_rule = new ByteArrayOutputStream();
	   MixThem mixThem = new MixThem(inputs, baos_rule);
           mixThem.process(mode, rule, params);
	   MixThem.LOGGER.info("Run and print result...");
	   mixThem = new MixThem(inputs, System.out);
           mixThem.process(mode, rule, params);
	   final File result = new File(resultURL.getFile());
	   return checkFileEquals(result, baos_rule.toByteArray());
   }

   private static boolean checkFileEquals(final File fileExpected, final byte[] result) throws FileNotFoundException, IOException {
	   FileInputStream fisExpected = new FileInputStream(fileExpected);
	   int c;
	   int last = -1;
	   int offset = 0;
	   while ((c = fisExpected.read()) != -1) {
		   last = c;
		   if (offset >= result.length) {
			   offset++;
			   continue;		   
		   }
		   int d = result[offset++];		   
		   if (c != d) return false;
	   }
	   if (offset < result.length) {
		   // result is longer than expected
		   return false;
	   } else if (offset-1 > result.length) {
		   // result is smaller than expected
		   return false;
	   } else if (offset-1 == result.length && last != 10) {
		   // result is one char smaller than expected and last char expected isn't CR
		   return false;
	   }
	   return true;
   }

}
