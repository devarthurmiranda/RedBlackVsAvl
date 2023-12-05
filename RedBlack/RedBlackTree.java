package RedBlack;

public class RedBlackTree {
    public Node root;

    public RedBlackTree() {
        this.root = null;
    }

    public Node insert(Node node, int value) {
        if (node == null) {
            return new Node(value);
        }

        if (value < node.value) {
            node.left = insert(node.left, value);
        } else if (value > node.value) {
            node.right = insert(node.right, value);
        } else {
            return node;
        }

        if (isRed(node.right) && !isRed(node.left)) {
            node = rotateLeft(node);
        }
        if (isRed(node.left) && isRed(node.left.left)) {
            node = rotateRight(node);
        }
        if (isRed(node.left) && isRed(node.right)) {
            flipColors(node);
        }

        return node;
    }

    public boolean isRed(Node node) {
        if (node == null) {
            return false;
        }
        return node.isRed;
    }

    public Node rotateLeft(Node node) {
        Node right = node.right;
        Node left = right.left;

        right.left = node;
        node.right = left;

        right.isRed = node.isRed;
        node.isRed = true;

        return right;
    }

    public Node rotateRight(Node node) {
        Node left = node.left;
        Node right = left.right;

        left.right = node;
        node.left = right;

        left.isRed = node.isRed;
        node.isRed = true;

        return left;
    }

    public void flipColors(Node node) {
        node.isRed = true;
        node.left.isRed = false;
        node.right.isRed = false;
    }

    public void insert(int value) {
        this.root = insert(this.root, value);
        this.root.isRed = false;
    }

    public void printTree(Node node) {
        if (node == null) {
            return;
        }
        printTree(node.left);
        System.out.println(node.value + " " + node.isRed);
        printTree(node.right);
    }

    public void inOrder(Node node) {
        if (node == null) {
            return;
        }
        inOrder(node.left);
        System.out.println(node.value + " " + node.isRed);
        inOrder(node.right);
    }



    public void printTree() {
        printTree(this.root);
    }

    public void search(Node node, int value) {
        if (node == null) {
            return;
        }
        if (value < node.value) {
            search(node.left, value);
        } else if (value > node.value) {
            search(node.right, value);
        } else {
            return;
        }
    }    

    public void search(int value) {
        search(this.root, value);
    }

    public Node delete(Node node, int value) {
        if (node == null) {
            return null;
        }

        if (value < node.value) {
            node.left = delete(node.left, value);
        } else if (value > node.value) {
            node.right = delete(node.right, value);
        } else {
            if (node.left == null) {
                return node.right;
            } else if (node.right == null) {
                return node.left;
            }

            Node temp = node;
            node = min(temp.right);
            node.right = deleteMin(temp.right);
            node.left = temp.left;
        }

        return node;
    }

    public Node min(Node node) {
        if (node.left == null) {
            return node;
        }
        return min(node.left);
    }

    public Node deleteMin(Node node) {
        if (node.left == null) {
            return node.right;
        }
        node.left = deleteMin(node.left);
        return node;
    }

    public void delete(int value) {
        this.root = delete(this.root, value);
    }

    public void deleteMin() {
        this.root = deleteMin(this.root);
    }

    
}
