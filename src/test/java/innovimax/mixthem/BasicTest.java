package innovimax.mixthem;

import innovimax.mixthem.MixThem;

import org.junit.Assert;
import org.junit.Test;

/*
    Created by innovimax-jim
    Basic tests for this application
*/
public class BasicTest {

	@Test
  	public final void failedPrintUsage() {
		final String args1[] = {};
    	Assert.assertNull(MixThem.checkArguments(args1));
    	final String args2[] = { "ghost1", "ghost2" };
    	Assert.assertNull(MixThem.checkArguments(args2));
		MixThem.printUsage();
  	}

}
