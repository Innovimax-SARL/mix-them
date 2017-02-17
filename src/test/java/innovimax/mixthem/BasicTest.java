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
  	public final void testEmptyArgs() {
  		boolean check = false;
  		try {
  			final String args[] = {};
  			Arguments mixArgs = Arguments.checkArguments(args);
  			check = true;
  		} catch (ArgumentException e) {
  			System.out.println(e.getMessage());
  		}
		Assert.assertFalse(check);
	}
	@Test
  	public final void testWrongArgs() {
  		boolean check = false;
		
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

		@Test
  	public final void testNoRule() {
  		boolean check = false;
		
		check = false;
  		try {
  			final String args[] = { getClass().getResource("test001_file1.txt").getFile(), getClass().getResource("test001_file1.txt").getFile() };
  			Arguments mixArgs = Arguments.checkArguments(args);
  			check = true;
  		} catch (ArgumentException e) {
  			System.out.println(e.getMessage());
  		}
		Assert.assertFalse(check);
		Arguments.printUsage();
  	}
}
