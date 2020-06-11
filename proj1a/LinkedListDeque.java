
/**
 * This program demonstrates the double ended queue similar to SLList and AList
 *
 * @author Thang Cao
 * @since 06/05/2020
 */

public class LinkedListDeque<T> {

    /**
     * IntNode nested class (supports LinkedListDeque class)
     * contain previous and next node
     * imply that this is a DLList (Doubly Linked List)
     */
    private class Node {
        private T item;
        private Node prev;
        private Node next;

        /**
         * Node constructor takes 3 params
         */
        public Node(T item, Node prev, Node next) {
            this.item = item;
            this.prev = prev;
            this.next = next;
        }
    }

    /******************************************************************/


    private int size;
    /**
     * A way of caching size of the list very quickly.
     */
    private Node sentinel; /** This is circular sentinel */


    /**
     * Everything is pointing to each other.
     */
    public LinkedListDeque() {
        sentinel = new Node(null, null, null); // sentinel is null (first node)
        sentinel.prev = sentinel;
        sentinel.next = sentinel;
        size = 0;
    }

    /**
     * Add a node to the front of the list.
     */
    public void addFirst(T item) {
        Node oldFrontNode = sentinel.next;
        Node newNode = new Node(item, sentinel, oldFrontNode);
        sentinel.next = newNode;
        newNode.next.prev = newNode;
        size++;
    }

    /**
     * Add a node to the back of the list.
     */
    public void addLast(T item) {
        Node oldLastNode = sentinel.prev;
        Node newLastNode = new Node(item, oldLastNode, sentinel);
        oldLastNode.next = newLastNode;
        // newLastNode.prev = oldLastNode;  redundant
        // newLastNode.next = sentinel;     redundant
        sentinel.prev = newLastNode;
        size++;
    }

    /**
     * Return the first item in the node
     */
    private T getFirst() {
        return sentinel.next.item;
    }


    public boolean isEmpty() {
        return (size == 0);
    }

    public int size() {
        return size;
    }

    public void printDeque() {
        Node p = sentinel;
        while (p.next != sentinel) {
            p = p.next;
            System.out.print(p.item + " ");
        }
    }

    public T removeFirst() {
        if (sentinel.next == null || isEmpty()) {
            return null;
        }

        T item = sentinel.next.item;

        sentinel.next = sentinel.next.next;

        sentinel.next.prev = sentinel;

        size--;

        return item;
    }

    public T removeLast() {
        if (sentinel.prev == null || isEmpty()) {
            return null;
        }

        T item = sentinel.prev.item;
        sentinel.prev = sentinel.prev.prev;
        sentinel.prev.next = sentinel;


        size--;
        return item;
    }

    public T get(int index) {
        // index = 0; // sentinel.next.item
        // index = 1; sentinel.next.next.item

        Node p = sentinel;
        for (int i = 0; i < index; i++) {
            p = p.next;
        }
        return p.next.item;
    }

    private T getHelper(Node n, int index) {
        if (index == 0) {
            return n.item;
        }

        return getHelper(n.next, index - 1);
    }

    public T getRecursive(int index) {
        return getHelper(sentinel.next, index);
    }

}
