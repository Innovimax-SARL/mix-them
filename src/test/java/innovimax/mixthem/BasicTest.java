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
        try {
            final String args[] = {};
            final Arguments mixArgs = Arguments.checkArguments(args);
        } catch (ArgumentException e) {
            System.out.println("testEmptyArgs: " + e.getMessage());
            throw e;
        }
    }

    @Test(expected=ArgumentException.class)
    public final void testWrongArgs() throws ArgumentException, IOException, ZipException {
        try {
            final String args[] = { "ghost1", "ghost2" };
            final Arguments mixArgs = Arguments.checkArguments(args);
        } catch (ArgumentException e) {
            System.out.println("testWrongArgs: " + e.getMessage());
            throw e;
        }
    }

    @Test
    public final void testNoRule() throws ArgumentException, IOException, ZipException {
        final String args[] = { getClass().getResource("test001_file1.txt").getFile(), getClass().getResource("test001_file2.txt").getFile() };
        final Arguments mixArgs = Arguments.checkArguments(args);
        Assert.assertTrue(true);
    }
    
    @Test
    public final void testAltRule() throws ArgumentException, IOException, ZipException {
        final String args[] = { "-alt-line", getClass().getResource("test001_file1.txt").getFile(), getClass().getResource("test001_file2.txt").getFile() };
        final Arguments mixArgs = Arguments.checkArguments(args);
        Assert.assertTrue(true);
    }
    
    @Test(expected=ArgumentException.class)
    public final void testUnknownRule() throws ArgumentException, IOException, ZipException {
        try {
            final String args[] = { "-x", getClass().getResource("test001_file1.txt").getFile(), getClass().getResource("test001_file2.txt").getFile() };
            final Arguments mixArgs = Arguments.checkArguments(args);
        } catch (ArgumentException e) {
            System.out.println("testUnknownRule: " + e.getMessage());
            throw e;
        }
    }

    @Test(expected=ArgumentException.class)
    public final void testUnexpectedParam() throws ArgumentException, IOException, ZipException {
        try {
            final String args[] = { "alt-line", "#val", getClass().getResource("test001_file1.txt").getFile(), getClass().getResource("test001_file2.txt").getFile() };
            final Arguments mixArgs = Arguments.checkArguments(args);
        } catch (ArgumentException e) {
            System.out.println("testUnexpectedParam: " + e.getMessage());
            throw e;
        }
    }

    @Test(expected=ArgumentException.class)
    public final void testWrongSeedParam() throws ArgumentException, IOException, ZipException {
        try {
            final String args[] = { "-random-alt-line", "#val", getClass().getResource("test001_file1.txt").getFile(), getClass().getResource("test001_file2.txt").getFile() };
            final Arguments mixArgs = Arguments.checkArguments(args);
        } catch (ArgumentException e) {
            System.out.println("testWrongSeedParam: " + e.getMessage());
            throw e;
        }
    }

    @Test
    public final void testValidSeedParam() throws ArgumentException, IOException, ZipException {
        final String args[] = { "-random-alt-line", "#1789", getClass().getResource("test001_file1.txt").getFile(), getClass().getResource("test001_file2.txt").getFile() };
        Arguments mixArgs = Arguments.checkArguments(args);
        Assert.assertTrue(true);        
    }
    
    @Test(expected=ArgumentException.class)
    public final void testNotAParam() throws ArgumentException, IOException, ZipException {
        try {
            final String args[] = { "-random-alt-line", "1789", getClass().getResource("test001_file1.txt").getFile(), getClass().getResource("test001_file2.txt").getFile() };
            final Arguments mixArgs = Arguments.checkArguments(args);
        } catch (ArgumentException e) {
            System.out.println("testNotAParam: " + e.getMessage());
            throw e;
        }
    }

    @Test
    public final void testOptionalParam() throws ArgumentException, IOException, ZipException {
        final String args[] = { "-random-alt-line", getClass().getResource("test001_file1.txt").getFile(), getClass().getResource("test001_file2.txt").getFile() };
        final Arguments mixArgs = Arguments.checkArguments(args);
        Assert.assertTrue(true);        
    }

    @Test(expected=ArgumentException.class)
    public final void testZipEmptyArgs() throws ArgumentException, IOException, ZipException {
        try {
            final String args[] = { "--zip" };
            Arguments mixArgs = Arguments.checkArguments(args);
        } catch (ArgumentException e) {
            System.out.println("testZipEmptyArgs: " + e.getMessage());
            throw e;
        }
    }
    
    @Test(expected=ArgumentException.class)
    public final void testZipWrongArgs() throws ArgumentException, IOException, ZipException {
        try {
            final String args[] = { "--zip", "zip/ghost1" };
            final Arguments mixArgs = Arguments.checkArguments(args);
        } catch (ArgumentException e) {
            System.out.println("testZipWrongArgs: " + e.getMessage());
            throw e;
        }
    }
    
    @Test
    public final void testZipNoRule() throws ArgumentException, IOException, ZipException {
        final String args[] = { "--zip", getClass().getResource("zip/test001.zip").getFile() };
        final Arguments mixArgs = Arguments.checkArguments(args);
        Assert.assertTrue(true);
    }
    
    @Test
    public final void testJarAltRule() throws ArgumentException, IOException, ZipException {
        final String args[] = { "-alt-line", "--jar", getClass().getResource("zip/test001.jar").getFile() };
        final Arguments mixArgs = Arguments.checkArguments(args);
        Assert.assertTrue(true);
    }
    
    @Test(expected=ArgumentException.class)
    public final void testZipOneFile() throws ArgumentException, IOException, ZipException {
        try {
            final String args[] = { "--zip", getClass().getResource("zip/wrong.zip").getFile() };
            final Arguments mixArgs = Arguments.checkArguments(args);
        } catch (ArgumentException e) {
            System.out.println("testZipOneFile: " + e.getMessage());
            throw e;
        }        
    }
    
    @Test(expected=ArgumentException.class)
    public final void testJarEmpty() throws ArgumentException, IOException, ZipException {
        try {
            final String args[] = { "--jar", getClass().getResource("zip/empty.jar").getFile() };
            final Arguments mixArgs = Arguments.checkArguments(args);
        } catch (ArgumentException e) {
            System.out.println("testJarEmpty: " + e.getMessage());
            throw e;
        }
    }
    
}
