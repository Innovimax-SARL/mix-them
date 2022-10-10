package innovimax.mixthem;

import innovimax.mixthem.arguments.Arguments;
import innovimax.mixthem.arguments.ArgumentException;

import java.util.Objects;

import org.junit.Assert;
import org.junit.Test;

import static org.hamcrest.CoreMatchers.*;
import static org.hamcrest.MatcherAssert.*;

/*
    Created by innovimax-jim
    Basic tests for this application
*/
public class BasicTest {
    
    @Test
    public final void testPrintUsage() {
        Arguments.printUsage();
        assertThat(true, is(true));
    }
    
    @Test(expected=ArgumentException.class)
    public final void testEmptyArgs() throws Exception {
        try {
            final String[] args = {};
            final Arguments mixArgs = Arguments.checkArguments(args);
        } catch (final ArgumentException e) {
            System.out.println("testEmptyArgs: " + e.getMessage());
            throw e;
        }
    }

    @Test(expected=ArgumentException.class)
    public final void testWrongArgs() throws Exception {
        try {
            final String[] args = { "ghost1", "ghost2" };
            final Arguments mixArgs = Arguments.checkArguments(args);
        } catch (final ArgumentException e) {
            System.out.println("testWrongArgs: " + e.getMessage());
            throw e;
        }
    }

    @Test
    public final void testNoRule() throws Exception {
        final String[] args = { Objects.requireNonNull(this.getClass().getResource("test001_file1.txt")).getFile(), Objects.requireNonNull(this.getClass().getResource("test001_file2.txt")).getFile() };
        final Arguments mixArgs = Arguments.checkArguments(args);
        Assert.assertTrue(true);
    }
    
    @Test
    public final void testAltRule() throws Exception {
        final String[] args = { "-alt-line", Objects.requireNonNull(this.getClass().getResource("test001_file1.txt")).getFile(), Objects.requireNonNull(this.getClass().getResource("test001_file2.txt")).getFile() };
        final Arguments mixArgs = Arguments.checkArguments(args);
        Assert.assertTrue(true);
    }
    
    @Test(expected=ArgumentException.class)
    public final void testUnknownRule() throws Exception {
        try {
            final String[] args = { "-x", Objects.requireNonNull(this.getClass().getResource("test001_file1.txt")).getFile(), Objects.requireNonNull(this.getClass().getResource("test001_file2.txt")).getFile() };
            final Arguments mixArgs = Arguments.checkArguments(args);
        } catch (final ArgumentException e) {
            System.out.println("testUnknownRule: " + e.getMessage());
            throw e;
        }
    }

    @Test(expected=ArgumentException.class)
    public final void testUnexpectedParam() throws Exception {
        try {
            final String[] args = { "alt-line", "#val", Objects.requireNonNull(this.getClass().getResource("test001_file1.txt")).getFile(), Objects.requireNonNull(this.getClass().getResource("test001_file2.txt")).getFile() };
            final Arguments mixArgs = Arguments.checkArguments(args);
        } catch (final ArgumentException e) {
            System.out.println("testUnexpectedParam: " + e.getMessage());
            throw e;
        }
    }

    @Test(expected=ArgumentException.class)
    public final void testWrongSeedParam() throws Exception {
        try {
            final String[] args = { "-random-alt-line", "#val", Objects.requireNonNull(this.getClass().getResource("test001_file1.txt")).getFile(), Objects.requireNonNull(this.getClass().getResource("test001_file2.txt")).getFile() };
            final Arguments mixArgs = Arguments.checkArguments(args);
        } catch (final ArgumentException e) {
            System.out.println("testWrongSeedParam: " + e.getMessage());
            throw e;
        }
    }

    @Test
    public final void testValidSeedParam() throws Exception {
        final String[] args = { "-random-alt-line", "#1789", Objects.requireNonNull(this.getClass().getResource("test001_file1.txt")).getFile(), Objects.requireNonNull(this.getClass().getResource("test001_file2.txt")).getFile() };
        final Arguments mixArgs = Arguments.checkArguments(args);
        Assert.assertTrue(true);        
    }
    @Test(expected=ArgumentException.class)
    public final void testNotAParam() throws Exception {
        try {
            final String[] args = { "-random-alt-line", "1789", Objects.requireNonNull(this.getClass().getResource("test001_file1.txt")).getFile(), Objects.requireNonNull(this.getClass().getResource("test001_file2.txt")).getFile() };
            final Arguments mixArgs = Arguments.checkArguments(args);
        } catch (final ArgumentException e) {
            System.out.println("testNotAParam: " + e.getMessage());
            throw e;
        }
    }

    @Test
    public final void testOptionalParam() throws Exception {
        final String[] args = { "-random-alt-line", Objects.requireNonNull(this.getClass().getResource("test001_file1.txt")).getFile(), Objects.requireNonNull(this.getClass().getResource("test001_file2.txt")).getFile() };
        final Arguments mixArgs = Arguments.checkArguments(args);
        Assert.assertTrue(true);        
    }

    @Test(expected=ArgumentException.class)
    public final void testZipEmptyArgs() throws Exception {
        try {
            final String[] args = { "--zip" };
            final Arguments mixArgs = Arguments.checkArguments(args);
        } catch (final ArgumentException e) {
            System.out.println("testZipEmptyArgs: " + e.getMessage());
            throw e;
        }
    }
    
    @Test(expected=ArgumentException.class)
    public final void testZipWrongArgs() throws Exception {
        try {
            final String[] args = { "--zip", "zip/ghost1" };
            final Arguments mixArgs = Arguments.checkArguments(args);
        } catch (final ArgumentException e) {
            System.out.println("testZipWrongArgs: " + e.getMessage());
            throw e;
        }
    }
    
    @Test
    public final void testZipNoRule() throws Exception {
        final String[] args = { "--zip", Objects.requireNonNull(this.getClass().getResource("zip/test001.zip")).getFile() };
        final Arguments mixArgs = Arguments.checkArguments(args);
        Assert.assertTrue(true);
    }
    
    @Test
    public final void testJarAltRule() throws Exception {
        final String[] args = { "-alt-line", "--jar", Objects.requireNonNull(this.getClass().getResource("zip/test001.jar")).getFile() };
        final Arguments mixArgs = Arguments.checkArguments(args);
        Assert.assertTrue(true);
    }
    
    @Test(expected=ArgumentException.class)
    public final void testZipOneFile() throws Exception {
        try {
            final String[] args = { "--zip", Objects.requireNonNull(this.getClass().getResource("zip/wrong.zip")).getFile() };
            final Arguments mixArgs = Arguments.checkArguments(args);
        } catch (final ArgumentException e) {
            System.out.println("testZipOneFile: " + e.getMessage());
            throw e;
        }        
    }
    
    @Test(expected=ArgumentException.class)
    public final void testJarEmpty() throws Exception {
        try {
            final String[] args = { "--jar", Objects.requireNonNull(this.getClass().getResource("zip/empty.jar")).getFile() };
            final Arguments mixArgs = Arguments.checkArguments(args);
        } catch (final ArgumentException e) {
            System.out.println("testJarEmpty: " + e.getMessage());
            throw e;
        }
    }
    
}
