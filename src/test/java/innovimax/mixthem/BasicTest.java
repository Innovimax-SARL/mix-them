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
    	Assert.assertFalse(MixThem.checkArguments(null));
    	//String[] args = { "ghost1", "host2" };
    	//Assert.assertFalse(MixThem.checkArguments(args));
  	}

}