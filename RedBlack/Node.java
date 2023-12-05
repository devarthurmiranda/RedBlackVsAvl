package RedBlack;

public class Node {
    public int value;
    public Node left;
    public Node right;
    public boolean isRed;

    public Node(int value) {
        this.value = value;
        this.left = null;
        this.right = null;
        this.isRed = true;
    }
}