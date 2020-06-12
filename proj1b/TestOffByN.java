import org.junit.Test;
import static org.junit.Assert.*;

public class TestOffByN {
    static CharacterComparator test;

    @Test
    public void testEqualChars() {
        test = new OffByN(5);

        assertTrue(test.equalChars('a', 'f'));
        assertTrue(test.equalChars('f', 'a'));
        assertFalse(test.equalChars('f', 'h'));
    }
}
