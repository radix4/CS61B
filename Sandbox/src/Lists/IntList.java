package Lists;

public class IntList {
    public int first;
    public IntList rest;

    public IntList(int first, IntList rest) {
        this.first = first;
        this.rest = rest;
    }

    /** Using recursion */
    public int size(){
        while (rest == null){
            return 1;
        }
        return 1 + this.rest.size();
    }

    /** Using Iteration */
    public int iterativeSize(){
        int size = 0;
        IntList p = this;

        while (p != null){
            p = p.rest;
            size++;
        }


        return size;
    }

    /** Return the ith item iteratively */
    public int get(int i){
        int j = 0;
        IntList p = this;

        while (j != i){
            p = p.rest;
            j++;
        }

        return p.first;
    }

    /** Return the ith item recursively */
    public int getRecursively(int i){
        if (i == 0) {
            return first;
        } else {
            return rest.getRecursively(i - 1);
        }
    }

    public static void main(String[] args) {
        IntList L = new IntList(15,null); //null points to no where
        L = new IntList(10,L); // L here points to previous node
        L = new IntList(5,L); // L here points to the previous node

        L.getRecursively(2);

        System.out.println(L.get(2));

        System.out.println(L.size());
    }

}
