package DisjointSets;

public class QuickFindDS implements DisjointSets{
    private int[] id;   // value at an index is the set the element belongs to

    /** Θ(N)
     * @param N = number of elements */
    public QuickFindDS(int N){
        id = new int[N];
        for (int i = 0; i < N; i++){
            id[i] = i;
        }
    }

    /** need to iterate through the array => Θ(N) */
    @Override
    public void connect(int p, int q) {
        int pid = id[p];
        int qid = id[q];
        for (int i = 0; i < id.length; i++){
            if (id[i] == pid){
                id[i] = qid;
            }
        }
    }

    /** Θ(1)
     * Returns a boolean in constant time. */
    @Override
    public boolean isConnected(int p, int q) {
        return (id[p] == id[q]);
    }
}
