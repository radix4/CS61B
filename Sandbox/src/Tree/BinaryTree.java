package Tree;

public class BinaryTree {
    private class Node {
        int key;
        String name;
        Node leftChild;
        Node rightChild;

        public Node(int key, String name) {
            this.key = key;
            this.name = name;
        }

        @Override
        public String toString() {
            return name + " has a key " + key;
        }
    }


        private Node root;

        public void addNode(int key, String name) {
            Node newNode = new Node(key, name);

            if (root == null) {
                root = newNode;
            } else {
                Node focusNode = root;
                Node parent;

                while(true) {
                    parent = focusNode;

                    if (key < focusNode.key) {
                        focusNode = focusNode.leftChild;

                        if (focusNode == null) {
                            parent.leftChild = newNode;
                            return;
                        }
                    } else {
                        focusNode = focusNode.rightChild;

                        if (focusNode == null) {
                            parent.rightChild = newNode;
                            return;
                        }
                    }
                }
            }

    }

    public void inOrderTraverseTree(Node focusNode) {
            if (focusNode != null) {
                inOrderTraverseTree(focusNode.leftChild);
                System.out.println(focusNode);
                inOrderTraverseTree(focusNode.rightChild);
            }
    }

    public void preorderTraverseTree(Node focusNode) {
        if (focusNode != null) {
            System.out.println(focusNode);
            preorderTraverseTree(focusNode.leftChild);
            preorderTraverseTree(focusNode.rightChild);
        }
    }

    public void postorderTraverseTree(Node focusNode) {
        if (focusNode != null) {
            postorderTraverseTree(focusNode.leftChild);
            postorderTraverseTree(focusNode.rightChild);
            System.out.println(focusNode);
        }
    }

    public boolean remove(int key) {
            Node focusNode = root;
            Node parent = root;

            boolean isItALeftChild = true;
            while (focusNode.key != key) {
                parent = focusNode;

                if (key < focusNode.key) {
                    isItALeftChild = true;

                    focusNode = focusNode.leftChild;
                } else {
                    isItALeftChild = false;

                    focusNode = focusNode.rightChild;
                }

                if (focusNode == null) {
                    return false;
                }
            }

            if (focusNode.leftChild == null && focusNode.rightChild == null) {
                if (focusNode == root) {
                    root = null;
                } else if (isItALeftChild) {
                    parent.leftChild = null;
                } else {
                    parent.rightChild = null;
                }
            } else if (focusNode.rightChild == null) {
                if (focusNode == root) root = focusNode.leftChild;
                else if (isItALeftChild) parent.leftChild = focusNode.leftChild;
                else parent.rightChild = focusNode.leftChild;
            } else if (focusNode.leftChild == null) {
                if (focusNode == root) {
                    root = focusNode.rightChild;
                }else if (isItALeftChild) {
                    parent.leftChild = focusNode.rightChild;
                } else {
                    parent.rightChild = focusNode.leftChild;
                }
            } else {
                Node replacement = getReplacementNode(focusNode);

                if (focusNode == root) {
                    root = replacement;
                } else if (isItALeftChild) {
                    parent.leftChild = replacement;
                } else {
                    parent.rightChild = replacement;
                }
            }

            return true;
    }


    public Node getReplacementNode(Node replacedNode) {
            Node replacementParent = replacedNode;
            Node replacement = replacedNode;

            Node focusNode = replacedNode.rightChild;

            while (focusNode != null) {
                replacementParent = replacement;
                replacement = focusNode;
                focusNode = focusNode.leftChild;
            }

            if (replacement != replacedNode.rightChild) {
                replacementParent.leftChild = replacement.rightChild;
                replacement.rightChild = replacedNode.rightChild;
            }

            return replacement;
    }

    public static void main(String[] args) {
        BinaryTree BT = new BinaryTree();

        BT.addNode(4, "Four");
        BT.addNode(3, "Three");
        BT.addNode(5, "Five");
        BT.addNode(1, "One");
        BT.addNode(2, "Two");
        BT.addNode(6, "Six");
        BT.addNode(7, "Seven");


        BT.postorderTraverseTree(BT.root);
    }
}
