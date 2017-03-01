package innovimax.mixthem;

import innovimax.mixthem.arguments.ArgumentException;

import org.junit.Assert;
import org.junit.Test;

/*
    Created by innovimax-jim
    Basic tests for this application
*/
public class MainTest {

    @Test
    public final void testMainEmptyArgs() throws ArgumentException {
        final String args[] = {};
        MixThem.main(args);
        Assert.assertTrue(true);
    }
    
    @Test
    public final void testMainRule1() throws ArgumentException {
        final String args[] = { "-1", getClass().getResource("test001_file1.txt").getFile(), getClass().getResource("test001_file1.txt").getFile() };
        MixThem.main(args);
        Assert.assertTrue(true);
    }

}
