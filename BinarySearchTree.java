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
        Node[] nodes = findNodes(root, firstValue, secondValue);
        if (nodes[0] == null || nodes[1] == null) {
            return false;
        }
        int temp = nodes[0].data;
        nodes[0].data = nodes[1].data;
        nodes[1].data = temp;
        return true;
    }

    private Node[] findNodes(Node current, int firstValue, int secondValue) {
        if (current == null) {
            return new Node[] { null, null };
        }
        if (current.data == firstValue) {
            return new Node[] { current, findNode(root, secondValue) };
        }
        if (current.data == secondValue) {
            return new Node[] { findNode(root, firstValue), current };
        }
        Node[] left = findNodes(current.left, firstValue, secondValue);
        if (left[0] != null && left[1] != null) {
            return left;
        }
        Node[] right = findNodes(current.right, firstValue, secondValue);
        if (right[0] != null && right[1] != null) {
            return right;
        }
        if (left[0] != null && left[1] == null) {
            Node[] found = new Node[2];
            found[0] = left[0];
            found[1] = findNode(root, secondValue);
            return found;
        }
        if (right[0] == null && right[1] != null) {
            Node[] found = new Node[2];
            found[0] = findNode(root, firstValue);
            found[1] = right[1];
            return found;
        }
        return new Node[] { null, null };
    }

    private Node findNode(Node current, int value) {
        if (current == null) {
            return null;
        }
        if (current.data == value) {
            return current;
        }
        if (value < current.data) {
            return findNode(current.left, value);
        }
        return findNode(current.right, value);
    }

    /**
     * You need to implement this for the exam, you need to check if the tree is
     * constructed in a valid way, e.g. if a child of a node violates the BST rules
     * it is invalid and returns false
     *
     * @return true if the tree is valid and false if it isn't
     */
    public boolean isValid() {
        return isSubtreeValid(root, null, null);
    }

    private boolean isSubtreeValid(Node current, Node min, Node max) {
        if (current == null) {
            return true;
        }
        if ((min != null && current.data <= min.data) || (max != null && current.data >= max.data)) {
            return false;
        }
        return isSubtreeValid(current.left, min, current) && isSubtreeValid(current.right, current, max);
    }

    /**
     * You need to implement this for the exam, you need to find the two nodes that
     * violate the BST and call swapNodes on them
     *
     * @return true if the tree is valid and false if it isn't
     */
    public boolean fixTree() {
        Node[] nodes = findSwappableNodes(root, null, null);
        if (nodes[0] == null || nodes[1] == null) {
            return false;
        }
        return swapNodes(nodes[0].data, nodes[1].data);
    }

    /**
     * Recursive function to find the two nodes that violate the BST
     *
     * @param current the current node being checked
     * @param prev the previous node in the traversal
     * @param nodes the nodes that violate the BST (if any)
     * @return the nodes that violate the BST (if any)
     */
    private Node[] findSwappableNodes(Node current, Node prev, Node[] nodes) {
        if (current == null) {
            return nodes;
        }

        nodes = findSwappableNodes(current.left, prev, nodes);

        if (prev != null && current.data < prev.data) {
            if (nodes[0] == null) {
                nodes[0] = prev;
            }
            nodes[1] = current;
        }

        nodes = findSwappableNodes(current.right, current, nodes);

        return nodes;
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
