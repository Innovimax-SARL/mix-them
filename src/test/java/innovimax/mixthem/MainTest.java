package innovimax.mixthem;

import innovimax.mixthem.arguments.ArgumentException;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.nio.channels.FileChannel;
import java.nio.channels.FileLock;

import org.junit.Assert;
import org.junit.Test;

/*
    Created by innovimax-jim
    Basic tests for this application
*/
public class MainTest {

    @Test
    public final void testMainEmptyArgs() {
        final String args[] = {};
        MixThem.main(args);
        Assert.assertTrue(true);
    }
    
    @Test
    public final void testMainRule1() {
        final String args[] = { "-1", getClass().getResource("test001_file1.txt").getFile(), getClass().getResource("test001_file1.txt").getFile() };
        MixThem.main(args);
        Assert.assertTrue(true);
    }
    
    @Test
    public final void testMainRule1Lock() {
        FileLock lock = null;
        try {
            File file = new File(getClass().getResource("test001_file1.txt").getFile());
            FileChannel fileChannel = new RandomAccessFile(file, "rw").getChannel();
            lock = fileChannel.lock();
            System.out.println("LOCK=" + lock.isValid());
            final String args[] = { "-1", getClass().getResource("test001_file1.txt").getFile(), getClass().getResource("test001_file1.txt").getFile() };
            MixThem.main(args);
            Assert.assertTrue(true);
        } catch (FileNotFoundException e) {
            Assert.assertTrue(false);
        } catch (IOException e) {
            Assert.assertTrue(false);
        } finally {
            if (lock != null) {
                try {
                    lock.release();
                } cattch (Exception e) {
                    Assert.assertTrue(false);
                }
            }
        }
    }
    
}
