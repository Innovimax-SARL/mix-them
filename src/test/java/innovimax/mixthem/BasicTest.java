package innovimax.mixthem;

import innovimax.mixthem.arguments.Arguments;
import innovimax.mixthem.exceptions.ArgumentException;

import org.junit.Assert;
import org.junit.Test;

/*
    Created by innovimax-jim
    Basic tests for this application
*/
public class BasicTest {

	@Test
  	public final void failedPrintUsage() {
  		boolean check = false;
  		try {
  			final String args[] = {};
  			Arguments mixArgs = Arguments.checkArguments(args);
  			check = true;
  		} catch (ArgumentException e) {
  			System.out.println(e.getMessage());
  		}
		Assert.assertFalse(check);
		check = false;
  		try {
  			final String args[] = { "ghost1", "ghost2" };
  			Arguments mixArgs = Arguments.checkArguments(args);
  			check = true;
  		} catch (ArgumentException e) {
  			System.out.println(e.getMessage());
  		}
		Assert.assertFalse(check);
		Arguments.printUsage();
  	}

}
