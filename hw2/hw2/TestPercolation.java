package hw2;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class TestPercolation {

    @Test
    public void testXyTo1D(){
        Percolation perc = new Percolation(5);
        int expected;
        int actual;

        expected = 1;
        actual = perc.xyTo1D(0,0);
        assertEquals(expected,actual);

        expected = 2;
        actual = perc.xyTo1D(1,0);
        assertEquals(expected,actual);

        expected = 3;
        actual = perc.xyTo1D(2,0);
        assertEquals(expected,actual);

        expected = 6;
        actual = perc.xyTo1D(0,1);
        assertEquals(expected,actual);

        expected = 7;
        actual = perc.xyTo1D(1,1);
        assertEquals(expected,actual);


    }
}
