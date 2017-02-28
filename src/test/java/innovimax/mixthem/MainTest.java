package innovimax.mixthem;

import innovimax.mixthem.arguments.ArgumentException;

import org.junit.Assert;
import org.junit.Test;

/*
    Created by innovimax-jim
    Basic tests for this application
*/
public class MainTest {

    @Test(expected=ArgumentException.class)
    public final void testMain() throws ArgumentException {
        final String args[] = {};
        MixThem.main(args);
    }

}
