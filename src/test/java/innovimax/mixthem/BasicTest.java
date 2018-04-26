package innovimax.mixthem;

import innovimax.mixthem.arguments.Arguments;
import innovimax.mixthem.arguments.ArgumentException;

import java.io.IOException;
import java.util.zip.ZipException;

import org.junit.Assert;
import org.junit.Test;

/*
    Created by innovimax-jim
    Basic tests for this application
*/
public class BasicTest {
        @Test
    public final void testPrintUsage() {
        Arguments.printUsage();
        Assert.assertTrue(true);
    }
    
    @Test(expected=ArgumentException.class)
    public final void testEmptyArgs() throws ArgumentException, IOException, ZipException {
        final String args[] = {};
        Arguments mixArgs = Arguments.checkArguments(args);
    }

    @Test(expected=ArgumentException.class)
    public final void testWrongArgs() throws ArgumentException, IOException, ZipException {
        final String args[] = { "ghost1", "ghost2" };
        Arguments mixArgs = Arguments.checkArguments(args);
    }

    @Test
    public final void testNoRule() throws ArgumentException, IOException, ZipException {
        final String args[] = { getClass().getResource("test001_file1.txt").getFile(), getClass().getResource("test001_file2.txt").getFile() };
        Arguments mixArgs = Arguments.checkArguments(args);
        Assert.assertTrue(true);
    }
    @Test
    public final void test1Rule() throws ArgumentException, IOException, ZipException {
        final String args[] = { "-1", getClass().getResource("test001_file1.txt").getFile(), getClass().getResource("test001_file2.txt").getFile() };
        Arguments mixArgs = Arguments.checkArguments(args);
        Assert.assertTrue(true);
    }
    @Test(expected=ArgumentException.class)
    public final void testUnknownRule() throws ArgumentException, IOException, ZipException {
        final String args[] = { "-x", getClass().getResource("test001_file1.txt").getFile(), getClass().getResource("test001_file2.txt").getFile() };
        Arguments mixArgs = Arguments.checkArguments(args);
    }

    @Test(expected=ArgumentException.class)
    public final void testUnexpectedParam() throws ArgumentException, IOException, ZipException {
        final String args[] = { "-1", "#val", getClass().getResource("test001_file1.txt").getFile(), getClass().getResource("test001_file2.txt").getFile() };
        Arguments mixArgs = Arguments.checkArguments(args);
    }

    @Test(expected=ArgumentException.class)
    public final void testWrongSeedParam() throws ArgumentException, IOException, ZipException {
        final String args[] = { "-random-alt-line", "#val", getClass().getResource("test001_file1.txt").getFile(), getClass().getResource("test001_file2.txt").getFile() };
        Arguments mixArgs = Arguments.checkArguments(args);
    }

    @Test
    public final void testValidSeedParam() throws ArgumentException, IOException, ZipException {
        final String args[] = { "-random-alt-line", "#1789", getClass().getResource("test001_file1.txt").getFile(), getClass().getResource("test001_file2.txt").getFile() };
        Arguments mixArgs = Arguments.checkArguments(args);
        Assert.assertTrue(true);        
    }
    
    @Test(expected=ArgumentException.class)
    public final void testNotAParam() throws ArgumentException, IOException, ZipException {
        final String args[] = { "-random-alt-line", "1789", getClass().getResource("test001_file1.txt").getFile(), getClass().getResource("test001_file2.txt").getFile() };
        Arguments mixArgs = Arguments.checkArguments(args);
    }

    @Test
    public final void testOptionalParam() throws ArgumentException, IOException, ZipException {
        final String args[] = { "-random-alt-line", getClass().getResource("test001_file1.txt").getFile(), getClass().getResource("test001_file2.txt").getFile() };
        Arguments mixArgs = Arguments.checkArguments(args);
        Assert.assertTrue(true);        
    }

    @Test(expected=ArgumentException.class)
    public final void testZipEmptyArgs() throws ArgumentException, IOException, ZipException {
        final String args[] = { "--zip" };
        Arguments mixArgs = Arguments.checkArguments(args);
    }
    
    @Test(expected=ArgumentException.class)
    public final void testZipWrongArgs() throws ArgumentException, IOException, ZipException {
        final String args[] = { "--zip", "zip/ghost1" };
        Arguments mixArgs = Arguments.checkArguments(args);
    }
    
    @Test
    public final void testZipNoRule() throws ArgumentException, IOException, ZipException {
        final String args[] = { "--zip", getClass().getResource("zip/test001.zip").getFile() };
        Arguments mixArgs = Arguments.checkArguments(args);
        Assert.assertTrue(true);
    }
    
    @Test
    public final void testJar1Rule() throws ArgumentException, IOException, ZipException {
        final String args[] = { "-1", "--jar", getClass().getResource("zip/test001.jar").getFile() };
        Arguments mixArgs = Arguments.checkArguments(args);
        Assert.assertTrue(true);
    }
    
}
