package DisjointSets;

public class QuickUnionDS implements DisjointSets{
    private int[] parent;

    /** Upon called, initialize parent array with indexes from 0 to the number of elements.
     * @param num = number of elements.*/
    public QuickUnionDS(int num) {
        this.parent = new int[num];
        for (int i = 0; i < num; i++) {
            parent[i] = i;
        }
    }

    /** Find the value 'p' then returns its parent.
     * Parent == -1 when the value stands on its own and has no parent.
     * @param p is the value in the array. */
    private int find(int p) {
        while (parent[p] >= 0) {
            p = parent[p];
        }

        return p;
    }

    /** i = the index of parent p.
     * j = the index of parent q.
     * Connect p to q, i points ---> to j. */
    @Override
    public void connect(int p, int q) {
        int i = find(p);
        int j = find(q);
        parent[i] = j;
    }

    /** Returns true if have same parent. */
    @Override
    public boolean isConnected(int p, int q) {
        return find(p) == find(q);
    }
}
