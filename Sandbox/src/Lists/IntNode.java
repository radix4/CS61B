package Lists;

public class IntNode {
    public int item;
    public IntNode rest;

    public IntNode(int i, IntNode n){
        item = i;
        rest = n;
    }

    public static void main(String[] args) {
        IntNode root = new IntNode(5, null);
        IntNode next = new IntNode(10, root);
        System.out.println(next.item);
    }
}
