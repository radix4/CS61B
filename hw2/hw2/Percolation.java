package hw2;

import edu.princeton.cs.algs4.WeightedQuickUnionUF;

import java.util.Map;
import java.util.TreeMap;

/**
 * This program simulates the probability of percolation using the disjoint set
 * WeightedQuickUnionUF from the princeton standard library.
 * @author  Thang Cao
 * @since   08/04/2020
 * */
public class Percolation {
    private int N;
    private int numberOfOpenSites;
    private WeightedQuickUnionUF percolation;   // only takes in integer
    private int[][] twoDGrid;
    private Map<Integer, Boolean> sites;
    private int topSite;
    private int bottomSite;

    /** create N-by-N grid, with all sites initially blocked */
    public Percolation(int N) {
        if (N <= 0) {
            throw new IllegalArgumentException();
        }

        this.N = N;
        this.numberOfOpenSites = 0;
        this.percolation = new WeightedQuickUnionUF(N * N + 2); // n = number of elements
        this.twoDGrid = twoDGrid();
        this.sites = initialSites();
        this.topSite = 0;
        this.bottomSite = N * N + 1;

        initialUnion();
    }


    /** open the site (row, col) if it is not open already */
    public void open(int row, int col) {
        isInRange(row,col);

        if (isOpen(row,col)) {
            return;
        }

        int site = xyTo1D(row,col);

        sites.put(site,true);

        checkForConnectivity(site);
        numberOfOpenSites++;
    }

    /** is the site (row, col) open? */
    public boolean isOpen(int row, int col) {
        isInRange(row,col);
        int site = xyTo1D(row,col);

        return sites.get(site);
    }

    public boolean isOpen(int site){
        if (site <= 0 || site > N) {
            return false;
        }

        return sites.get(site);
    }


    /** is the site (row, col) full? */
    public boolean isFull(int row, int col) {
        isInRange(row,col);
        int site = xyTo1D(row, col);
        if (!isOpen(site)){
            return false;
        }

        return percolation.connected(topSite,site);
    }

    /** return number of open sites
     * @return int */
    public int numberOfOpenSites() {
        return numberOfOpenSites;
    }

    /** Whether percolates or not.
     * @return boolean */
    public boolean percolates(){
        return false;
    }

    private void initialUnion(){
        for (int i = 1; i <= N; i++) {
            percolation.union(topSite, i);
        }
    }

    /** Whether two sites connected together. */
    private void checkForConnectivity(int recentlyOpenedSite){
        int top = recentlyOpenedSite - N;
        int left = recentlyOpenedSite - 1;
        int right = recentlyOpenedSite + 1;
        int bottom = recentlyOpenedSite + N;

        if (isOpen(top)) {
            percolation.union(top, recentlyOpenedSite);
        }

        if (isOpen(left)) {
            percolation.union(left,recentlyOpenedSite);
        }

        if (isOpen(right)) {
            percolation.union(right, recentlyOpenedSite);
        }

        if (isOpen(bottom)) {
            percolation.union(bottom, recentlyOpenedSite);
        }

    }

    /** All sites are blocked initially. */
    private Map<Integer, Boolean> initialSites(){
        Map<Integer, Boolean> sites = new TreeMap<>();
        for (int y = 0; y < twoDGrid.length; y++){
            for (int x = 0; x < twoDGrid[y].length; x++){
                int site = xyTo1D(y,x);
                sites.put(site, false);
            }
        }

        return sites;
    }

    /** Helper method that returns the site number from a two dimensional coordinate.
     * (0,0) --> 1
     * (0,1) --> 2
     * (1,0) --> 6
     * Assuming N = 5 ( 5x5 grid )
     * This method should be made private, public for testing purposes.
     * */
    public int xyTo1D(int row, int col) {
        isInRange(row,col);
        return twoDGrid[row][col];
    }

    /** Translate the 2D grid into a 2D array. */
    private int[][] twoDGrid(){
        int[][] twoDArray = new int[N][N];
        int siteNumber = 1;

        for (int y = 0; y < N; y++){
            for (int x = 0; x < N; x++){
                twoDArray[y][x] = siteNumber;
                siteNumber++;
            }
        }

        return twoDArray;
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
        Percolation p = new Percolation(5);
        p.percolation.union(1,5);
        p.percolation.union(2,6);
        p.percolation.union(1,2);
        System.out.println(p.percolation.count());
    }
}
