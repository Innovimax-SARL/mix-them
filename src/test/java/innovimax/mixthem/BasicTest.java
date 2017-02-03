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
  	public final void printUsage() {
		final String args1[] = {};
    	Assert.assertFalse(MixThem.checkArguments(args1));
    	final String args2[] = { "ghost1", "ghost2" };
    	Assert.assertFalse(MixThem.checkArguments(args2));
  	}

}