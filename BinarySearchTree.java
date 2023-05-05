package app;

public class BinarySearchTree {
    // just a root variable
    Node root;

    /**
     * Empty constructor is all we need for now
     */
    public BinarySearchTree() {
        this.root = null;
    }

    /**
     * Adds the data to the tree, duplicates are not allowed
     *
     * @param data value that you want to insert into the tree
     */
    public void add(int data) {
        this.root = this.addNode(root, data);
    }

    /**
     * You need to implement this for the exam, you need to swap the two values, so
     * find the node that contains the first value and find the node that contains
     * the second value, then swap them.
     *
     * @param firstValue  the value of the first node to swap
     * @param secondValue the value of the second node to swap
     * @return true if the swap was successful and false if it wasn't (e.g. one of
     *         the values wasn't there)
     */
    public boolean swapNodes(int firstValue, int secondValue) {
        return false;
    }

    /**
     * You need to implement this for the exam, you need to check if the tree is
     * constructed in a valid way, e.g. if a child of a node violates the BST rules
     * it is invalid and returns false
     *
     * @return true if the tree is valid and false if it isn't
     */
    public boolean isValid() {
        return false;
    }

    /**
     * You need to implement this for the exam, you need to find the two nodes that
     * violate the BST and call swapNodes on them
     *
     * @return true if the tree is valid and false if it isn't
     */
    public boolean fixTree() {
        return false;
    }

    /**
     * Recursive function to find where to insert a node, no duplicates
     *
     * @param current the node that we are comparing to
     * @param data    the data we want to insert into tree
     * @return the modified node, not the inserted node
     */
    private Node addNode(Node current, int data) {
        // time to insert node
        if (current == null) {
            return new Node(data);
        }

        // compare the data to the current node to see which way to traverse
        if (data < current.data) {
            current.left = this.addNode(current.left, data);
        } else if (data > current.data) {
            current.right = this.addNode(current.right, data);
        }

        // if the data is already there, just return current
        return current;
    }

    @Override
    public String toString() {
        String result = this.inOrderTraversal(this.root);
        return result.trim();
    }

    private String inOrderTraversal(Node current) {
        StringBuilder strBldr = new StringBuilder();

        // check if we have anything to add to the string
        if (current != null) {
            // go left first because this is inorder
            strBldr.append(this.inOrderTraversal(current.left));

            // no print the current node
            strBldr.append(current.data + " ");

            // go right last because inorder
            strBldr.append(this.inOrderTraversal(current.right));
        }

        return strBldr.toString();
    }
}