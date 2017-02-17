package innovimax.mixthem;

import innovimax.mixthem.arguments.Rule;
import innovimax.mixthem.exceptions.MixException;

import java.io.*;
import java.net.URL;

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
	   
	   int i = 1;
	   while (true) {
		   System.out.println("TEST n°" + i);
		   String prefix = "test" + String.format("%03d", i) +"_";
		   URL url1 = getClass().getResource(prefix + "file1.txt");
		   URL url2 = getClass().getResource(prefix + "file2.txt");
		   System.out.println("URL 1 ("+prefix + "file1.txt"+")="+url1+"; URL 2 ("+prefix + "file2.txt"+")="+url2);
		   if( url1 == null || url2 == null) break;
		   for(Rule rule : Rule.values()) {
			   String resource = prefix+"output-"+ rule.getExtension()+".txt";
			   URL url = getClass().getResource(resource);
			   System.out.println(rule+" implemented = "+rule.isImplemented()+" ; resource ("+resource+") = "+url);
			   if (rule.isImplemented() && url != null) {
			   	check(new File(url1.getFile()), new File(url2.getFile()), new File(url.getFile()), rule);
			   }
		   }   
		   i++;
	   }
	   Assert.assertTrue(true);
   }	   
   private final static void check(File file1, File file2, File expected, Rule rule)  throws MixException, FileNotFoundException, IOException  {
	   ByteArrayOutputStream baos_rule = new ByteArrayOutputStream();
	   MixThem mixThem = new MixThem(file1, file2, baos_rule);
           mixThem.process(rule);
	   Assert.assertTrue(checkFileEquals(expected, baos_rule.toByteArray()));
   }
	/*
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
	public final void checkRuleAltChar() throws MixException, FileNotFoundException, IOException {
		URL url1 = getClass().getResource("test002_file1.txt");
		URL url2 = getClass().getResource("test002_file2.txt");
		URL urlComp = getClass().getResource("test002_output-altchar.txt");
		File file1 = new File(url1.getFile());
		File file2 = new File(url2.getFile());
		File fileComp = new File(urlComp.getFile());
		ByteArrayOutputStream baos_rule = new ByteArrayOutputStream();
		MixThem mixThem = new MixThem(file1, file2, baos_rule);
		mixThem.process(Rule._ALT_CHAR);        
		Assert.assertTrue(checkFileEquals(fileComp, baos_rule.toByteArray()));
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
	public final void checkRuleRandomAltLine() throws MixException, FileNotFoundException, IOException {
		URL url1 = getClass().getResource("test001_file1.txt");
		URL url2 = getClass().getResource("test001_file2.txt");
		URL urlComp = getClass().getResource("test001_output-random-altline.txt");
		File file1 = new File(url1.getFile());
		File file2 = new File(url2.getFile());
		File fileComp = new File(urlComp.getFile());
		ByteArrayOutputStream baos_rule = new ByteArrayOutputStream();
		MixThem mixThem = new MixThem(file1, file2, baos_rule);
		mixThem.process(Rule._RANDOM_ALT_LINE);        
		Assert.assertTrue(checkFileEquals(fileComp, baos_rule.toByteArray()));
	}

	@Test
	public final void dumpRule1() throws MixException, FileNotFoundException, IOException {
		URL url1 = getClass().getResource("test001_file1.txt");
		URL url2 = getClass().getResource("test001_file2.txt");
		File file1 = new File(url1.getFile());
		File file2 = new File(url2.getFile());        
		System.out.println("test001/File1:");
		MixThem mixThem = new MixThem(file1, file2, System.out);
		mixThem.process(Rule._1);        
		System.out.println("test001/File2:");
		mixThem = new MixThem(file1, file2, System.out);
		mixThem.process(Rule._2);  
	}

	@Test
	public final void dumpRuleAltChar() throws MixException, FileNotFoundException, IOException {
		URL url1 = getClass().getResource("test002_file1.txt");
		URL url2 = getClass().getResource("test002_file2.txt");
		URL urlComp = getClass().getResource("test002_output-altchar.txt");
		File file1 = new File(url1.getFile());
		File file2 = new File(url2.getFile());
		File fileComp = new File(urlComp.getFile());
		System.out.println("test002/File1:");
		MixThem mixThem = new MixThem(file1, file2, System.out);
		mixThem.process(Rule._1);        
		System.out.println("test002/File2:");
		mixThem = new MixThem(file1, file2, System.out);
		mixThem.process(Rule._2);         
		System.out.println("test002/Mixed/alt-char:");
		mixThem = new MixThem(file1, file2, System.out);
		mixThem.process(Rule._ALT_CHAR);
		
		//System.out.println("test002/Expected/alt-char:");
		//String line;
		//BufferedReader br = new BufferedReader(new FileReader(fileComp));
		//while ((line = br.readLine()) != null) {
		//	System.out.println(line);
		//}
		//br.close();
		
	}

	@Test
	public final void dumpRuleAltLine() throws MixException, FileNotFoundException, IOException {
		URL url1 = getClass().getResource("test001_file1.txt");
		URL url2 = getClass().getResource("test001_file2.txt");
		URL urlComp = getClass().getResource("test001_output-altline.txt");
		File file1 = new File(url1.getFile());
		File file2 = new File(url2.getFile());
		File fileComp = new File(urlComp.getFile());        
		System.out.println("test001/Mixed/alt-line:");
		MixThem mixThem = new MixThem(file1, file2, System.out);
		mixThem.process(Rule._ALT_LINE);  
		        
		//System.out.println("test001/Expected/alt-line:");
		//String line;
		//BufferedReader br = new BufferedReader(new FileReader(fileComp));
		//while ((line = br.readLine()) != null) {
		//	System.out.println(line);
		//}
		//br.close();
		
	}

	@Test
	public final void dumpRuleRandomAltLine() throws MixException, FileNotFoundException, IOException {
		URL url1 = getClass().getResource("test001_file1.txt");
		URL url2 = getClass().getResource("test001_file2.txt");
		URL urlComp = getClass().getResource("test001_output-random-altline.txt");
		File file1 = new File(url1.getFile());
		File file2 = new File(url2.getFile());
		File fileComp = new File(urlComp.getFile());        
		System.out.println("test002/Mixed/random-alt-line:");
		MixThem mixThem = new MixThem(file1, file2, System.out);
		mixThem.process(Rule._RANDOM_ALT_LINE);  
		
		//System.out.println("test001/Expected/random-alt-line:");
		//String line;
		//BufferedReader br = new BufferedReader(new FileReader(fileComp));
		//while ((line = br.readLine()) != null) {
		//	System.out.println(line);
		//}
		//br.close();
		
	}
	*/

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
