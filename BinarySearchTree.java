package app;
public class BinarySearchTree {
    private Node root;

    private static class Node {
        int value;
        Node left;
        Node right;

        public Node(int value) {
            this.value = value;
            this.left = null;
            this.right = null;
        }
    }


    public BinarySearchTree() {
        root = null;
    }

    public void insert(int data) {
        root = insertNode(root, data);
    }

    private Node insertNode(Node current, int data) {
        if (current == null) {
            return new Node(data);
        }

        if (data < current.value) {
            current.left = insertNode(current.left, data);
        } else if (data > current.value) {
            current.right = insertNode(current.right, data);
        }

        return current;
    }

    public boolean search(int data) {
        return searchNode(root, data);
    }

    private boolean searchNode(Node current, int data) {
        if (current == null) {
            return false;
        }

        if (data == current.value) {
            return true;
        } else if (data < current.value) {
            return searchNode(current.left, data);
        } else {
            return searchNode(current.right, data);
        }
    }

    public boolean swapNodes(int firstValue, int secondValue) {
        Node firstNode = findNode(root, firstValue);
        Node secondNode = findNode(root, secondValue);

        if (firstNode == null || secondNode == null) {
            return false;
        }

        int temp = firstNode.value;
        firstNode.value = secondNode.value;
        secondNode.value = temp;

        return true;
    }

    private Node findNode(Node current, int value) {
        if (current == null) {
            return null;
        }

        if (value == current.value) {
            return current;
        } else if (value < current.value) {
            return findNode(current.left, value);
        } else {
            return findNode(current.right, value);
        }
    }

    public boolean isValid() {
        return isBST(root, Integer.MIN_VALUE, Integer.MAX_VALUE);
    }

    private boolean isBST(Node current, int minValue, int maxValue) {
        if (current == null) {
            return true;
        }

        if (current.value < minValue || current.value > maxValue) {
            return false;
        }

        return isBST(current.left, minValue, current.value - 1) && isBST(current.right, current.value + 1, maxValue);
    }

    public void fixTree() {
        Node firstNode = null;
        Node secondNode = null;

        findNodesToSwap(root, null, Integer.MIN_VALUE, Integer.MAX_VALUE, firstNode, secondNode);

        if (firstNode != null && secondNode != null) {
            swapNodes(firstNode.value, secondNode.value);
        }
    }

    private void findNodesToSwap(Node current, Node parent, int minValue, int maxValue, Node firstNode,
                                 Node secondNode) {
        if (current == null) {
            return;
        }

        findNodesToSwap(current.left, current, minValue, current.value, firstNode, secondNode);

        if (current.value < minValue || current.value > maxValue) {
            if (firstNode == null) {
                firstNode = parent;
            } else {
                secondNode = current;
            }
        }

        findNodesToSwap(current.right, current, current.value, maxValue, firstNode, secondNode);
    }

    public void add(int value) {
        root = addRecursive(root, value);
    }

    private Node addRecursive(Node current, int value) {
        if (current == null) {
            return new Node(value);
        }

        if (value < current.value) {
            current.left = addRecursive(current.left, value);
        } else if (value > current.value) {
            current.right = addRecursive(current.right, value);
        } else {
            // value already exists
            return current;
        }

        return current;
    }
}
