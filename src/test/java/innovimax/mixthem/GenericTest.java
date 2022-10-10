package innovimax.mixthem;

import innovimax.mixthem.arguments.FileMode;
import innovimax.mixthem.arguments.ParamValue;
import innovimax.mixthem.arguments.Rule;
import innovimax.mixthem.arguments.RuleParam;
import innovimax.mixthem.io.InputResource;

import java.io.BufferedReader;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.net.URL;
import java.util.*;
import java.util.logging.Level;

import org.junit.Assert;
import org.junit.Test;

/*
	Created by innovimax
	Generic tests for this application
*/
public class GenericTest {

   @Test
   public final void testCharRules() throws Exception {
	   this.testRules(FileMode.CHAR);
   }

   @Test
   public final void testBytesRules() throws Exception {
	   this.testRules(FileMode.BYTE);
   }

   private void testRules(final FileMode fileMode) throws MixException, IOException, NumberFormatException {
	   MixThem.setLogging(Level.FINE);
	   int testId = 0;
	   final Collection<String> failed = new ArrayList<>();
	   final Set<Integer> locks = this.getTestLocks();
	   boolean result = true;
	   while (true) {
		   testId++;
		   MixThem.LOGGER.info("TEST [" + fileMode.getName().toUpperCase() + "] N° " + testId + "***********************************************************");
		   final String prefix = "test" + String.format("%03d", testId) +"_";
		   final List<URL> urlF = new ArrayList<>();
		   int index = 1;
		   while (true) {
			   final URL url = this.getClass().getResource(prefix + "file" + index + ".txt");
			   if (url != null) {
				   urlF.add(url);
				   index++;
			   } else {
				   break;
			   }
		   }		   		   
		   if( urlF.size() < 2) break;
		   if (locks.contains(testId)) {
			   MixThem.LOGGER.info("Locked!!!");
			   continue;
		   }
		   for (int i=0; i < urlF.size(); i++) {
			   MixThem.LOGGER.info("File " + (i+1) + ": " + urlF.get(i));
		   }
		   for(final Rule rule : Rule.values()) {
			   if (rule.isImplemented() && rule.acceptFileMode(fileMode)) {
				   final String paramsFile = prefix + "params-" + rule.getExtension() + ".txt";
				   final URL urlP = this.getClass().getResource(paramsFile);
				   final List<RuleRun> runs = RuleRuns.getRuns(rule, urlP);
				   for (final RuleRun run : runs) {
					   String resultFile = prefix + "output-" + rule.getExtension();
					   if (run.hasSuffix()) {
						   resultFile += "-" + run.getSuffix();
					   }
					   resultFile += ".txt";
					   URL urlR = this.getClass().getResource(resultFile);
					   if (urlR == null && run.hasSuffix()) {
						   resultFile = prefix + "output-" + rule.getExtension() + ".txt";
						   urlR = this.getClass().getResource(resultFile);
					   }
					   if (urlR != null) {
						   MixThem.LOGGER.info("--------------------------------------------------------------------");
						   if (urlP != null) {
							   MixThem.LOGGER.info("Params file : " + urlP);
						   }
						   MixThem.LOGGER.info("Result file : " + urlR);
						   MixThem.LOGGER.info("--------------------------------------------------------------------");
						   final boolean res = check(urlF, urlR, fileMode, run.getSelection(), rule, run.getParams());
						   MixThem.LOGGER.info("Run " + (res ? "pass" : "FAIL") + " with params " + run.getParams() + " on selection " + run.getSelection());
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
	   MixThem.LOGGER.info("FAILED [" + fileMode.getName().toUpperCase() + "] TESTS : " + (!failed.isEmpty() ? failed.toString() : "None"));
	   MixThem.LOGGER.info("LOCKED [" + fileMode.getName().toUpperCase() + "] TESTS : " + locks);
	   MixThem.LOGGER.info("*********************************************************************");
	   Assert.assertTrue(result);
   }	   

   private static boolean check(final Iterable<URL> filesURL, final URL resultURL, final FileMode fileMode, final Set<Integer> selection, final Rule rule, final Map<RuleParam, ParamValue> params)  throws MixException, IOException  {
	   MixThem.LOGGER.info("Run and check result...");	   
	   final List<InputResource> inputs = new ArrayList<>();
	   for (final URL url : filesURL) {
	   	inputs.add(InputResource.createFile(new File(url.getFile())));
	   }
	   final ByteArrayOutputStream baos_rule = new ByteArrayOutputStream();
	   MixThem mixThem = new MixThem(inputs, baos_rule);
           mixThem.process(fileMode, selection, rule, params);
	   MixThem.LOGGER.info("Run and print result...");
	   mixThem = new MixThem(inputs, System.out);
           mixThem.process(fileMode, selection, rule, params);
	   final File result = new File(resultURL.getFile());
	   return checkFileEquals(result, baos_rule.toByteArray());
   }

   private static boolean checkFileEquals(final File fileExpected, final byte[] result) throws IOException {
	   int last;
	   int offset;
	   try (final FileInputStream fisExpected = new FileInputStream(fileExpected)) {
		   int c;
		   last = -1;
		   offset = 0;
		   while ((c = fisExpected.read()) != -1) {
			   last = c;
			   if (offset >= result.length) {
				   offset++;
				   continue;
			   }
			   final int d = result[offset++];
			   if (c != d) return false;
		   }
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
	   final Set<Integer> locks = new HashSet<>();
	   try {
	   	final URL url = this.getClass().getResource("test_locks.txt");
	   	if (url != null) {
			final File file = new File(url.getFile());
			final List<String> ids;
			try (final BufferedReader reader = Files.newBufferedReader(file.toPath(), StandardCharsets.UTF_8)) {
				ids = Arrays.asList(reader.readLine().split(" "));
			}
			for (final String id : ids) {
				   try {
			   		locks.add(Integer.valueOf(id));
			   	} catch (final NumberFormatException ignored) {}
		   	}
	   	}
	   } catch (final IOException ignored) {}
	   return locks;
   }

}
