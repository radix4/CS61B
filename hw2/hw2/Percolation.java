package hw2;

import edu.princeton.cs.algs4.WeightedQuickUnionUF;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 * This program simulates the probability of percolation using the disjoint set
 * WeightedQuickUnionUF from the princeton standard library.
 * @author  Thang Cao
 * @since   08/04/2020
 * */
public class Percolation {
    private int N;
    private int numberOfOpenSites;
    private WeightedQuickUnionUF percolation;

    /** create N-by-N grid, with all sites initially blocked */
    public Percolation(int N) {
        if (N < 0) {
            throw new IllegalArgumentException();
        }

        this.N = N;
        this.numberOfOpenSites = 0;
        this.percolation = new WeightedQuickUnionUF(10); // n = number of elements

    }


    /** open the site (row, col) if it is not open already */
    public void open(int row, int col) {
        isInRange(row,col);


    }

    /** is the site (row, col) open? */
    public boolean isOpen(int row, int col) {
        isInRange(row,col);

        return false;
    }

    // is the site (row, col) full?
    public boolean isFull(int row, int col) {
        isInRange(row,col);

        return false;
    }

    // number of open sites
    public int numberOfOpenSites() {
        return numberOfOpenSites;
    }

    // does the system percolate?
    public boolean percolates(){
        return false;
    }

    /** Helper method that returns the site number from a two dimensional coordinate. */
    public int xyTo1D(int row, int col) {
        isInRange(row,col);

        return 0;
    }

    /** Helper method, throws exception if parameters 'row' or 'col' exceeds the
     * boundaries */
    private boolean isInRange(int row, int col) {
        if (row < 0 && row > N - 1) {
            throw new IndexOutOfBoundsException();
        }

        if (col < 0 && col > N - 1) {
            throw new IndexOutOfBoundsException();
        }

        return true;
    }



    // use for unit testing (not required)
    public static void main(String[] args)  {

    }
}
