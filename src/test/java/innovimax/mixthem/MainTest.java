package innovimax.mixthem;

import org.junit.Assert;
import org.junit.Test;

import java.util.Objects;

/*
    Created by innovimax-jim
    Basic tests for this application
*/
public class MainTest {

    @Test
    public final void testMainEmptyArgs() {
        final String[] args = {};
        MixThem.main(args);
        Assert.assertTrue(true);
    }
    
    @Test
    public final void testMainRuleAlt() {
        final String[] args = { "-alt-line", Objects.requireNonNull(this.getClass().getResource("test001_file1.txt")).getFile(), Objects.requireNonNull(this.getClass().getResource("test001_file1.txt")).getFile() };
        MixThem.main(args);
        Assert.assertTrue(true);
    }

    @Test
    public final void testMainZip() {
        final String[] args = { "-+", "--zip", Objects.requireNonNull(this.getClass().getResource("zip/test001.zip")).getFile() };
        MixThem.main(args);
        Assert.assertTrue(true);
    }
}
