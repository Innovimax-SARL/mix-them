package innovimax.mixthem;

import innovimax.mixthem.arguments.Mode;
import innovimax.mixthem.arguments.ParamValue;
import innovimax.mixthem.arguments.Rule;
import innovimax.mixthem.arguments.RuleParam;
import innovimax.mixthem.io.InputResource;

import java.io.BufferedReader;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
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
	   int testId = 0;
	   final List<String> failed = new ArrayList<String>();
	   final Set<Integer> locks = getTestLocks();	   
	   boolean result = true;
	   while (true) {
		   testId++;
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
		   if (locks.contains(Integer.valueOf(testId))) {
			   MixThem.LOGGER.info("Locked!!!");
			   continue;
		   }
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
	   }
	   MixThem.LOGGER.info("*********************************************************************");
	   MixThem.LOGGER.info("FAILED [" + mode.getName().toUpperCase() + "] TESTS : " + (failed.size() > 0 ? failed.toString() : "None"));
	   MixThem.LOGGER.info("LOCKED [" + mode.getName().toUpperCase() + "] TESTS : " + locks.toString());	   
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
	
   private Set<Integer> getTestLocks() {
	   final Set<Integer> locks = new HashSet<Integer>();
	   try {
	   	final URL url = getClass().getResource("test_locks.txt");
	   	if (url != null) {
			final File file = new File(url.getFile());
		  	final BufferedReader reader = Files.newBufferedReader(file.toPath(), StandardCharsets.UTF_8);
		  	final  List<String> ids = Arrays.asList(reader.readLine().split(" "));
		   	for (String id : ids) {
				   try {
			   		locks.add(Integer.valueOf(id));
			   	} catch (NumberFormatException ignored) {}
		   	}
	   	}
	   } catch (IOException ignored) {}
	   return locks;
   }

}
