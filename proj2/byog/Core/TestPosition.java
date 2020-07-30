package byog.Core;

import org.junit.Test;
import static org.junit.Assert.*;

public class TestPosition {

    @Test
    public void testCompareTo(){
        Position p = new Position(2,1);
        Position p2 = new Position(1,1);
        int actual = p.compareTo(p2);
        assertEquals(1, actual);
    }
}
