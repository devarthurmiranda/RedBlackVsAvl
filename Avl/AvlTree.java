package Avl;

public class AvlTree {
    public Node root;
    
    public AvlTree(){
        this.root = null;
    }
    
    public int height(Node node){
        if(node == null){
            return 0;
        }
        return node.height;
    }
    
    public int max(int a, int b){
        if(a > b){
            return a;
        }
        return b;
    }

    public void search(Node node, int value){
        if(node == null){
            return;
        }
        if(value < node.value){
            search(node.left, value);
        } else if(value > node.value){
            search(node.right, value);
        } else {
            return;
        }
    }
    
    public Node rightRotate(Node node){
        Node left = node.left;
        Node right = left.right;
        
        left.right = node;
        node.left = right;
        
        node.height = max(height(node.left), height(node.right)) + 1;
        left.height = max(height(left.left), height(left.right)) + 1;
        
        return left;
    }
    
    public Node leftRotate(Node node){
        Node right = node.right;
        Node left = right.left;
        
        right.left = node;
        node.right = left;
        
        node.height = max(height(node.left), height(node.right)) + 1;
        right.height = max(height(right.left), height(right.right)) + 1;
        
        return right;
    }
    
    public int getBalance(Node node){
        if(node == null){
            return 0;
        }
        return height(node.left) - height(node.right);
    }
    
    public Node insert(Node node, int value){
        if(node == null){
            return new Node(value);
        }
        if(value < node.value){
            node.left = insert(node.left, value);
        } else if(value > node.value){
            node.right = insert(node.right, value);
        } else {
            return node;
        }
        
        node.height = 1 + max(height(node.left), height(node.right));
        
        int balance = getBalance(node);
        
        if(balance > 1 && value < node.left.value){
            return rightRotate(node);
        }
        if(balance < -1 && value > node.right.value){
            return leftRotate(node);
        }
        if(balance > 1 && value > node.left.value){
            node.left = leftRotate(node.left);
            return rightRotate(node);
        }
        if(balance < -1 && value < node.right.value){
            node.right = rightRotate(node.right);
            return leftRotate(node);
        }
        
        return node;
    }
    public Node minValueNode(Node node){
        Node current = node;
        while(current.left != null){
            current = current.left;            
        }
        return current;
    }

    public Node delete(Node node, int value){
        if(node == null){
            return node;
        }
        if(value < node.value){
            node.left = delete(node.left, value);
        } else if(value > node.value){
            node.right = delete(node.right, value);
        } else {
            if((node.left == null) || (node.right == null)){
                Node temp = null;
                if(temp == node.left){
                    temp = node.right;
                } else {
                    temp = node.left;
                }
                if(temp == null){
                    temp = node;
                    node = null;
                } else {
                    node = temp;
                }
            } else {
                Node temp = minValueNode(node.right);
                node.value = temp.value;
                node.right = delete(node.right, temp.value);
            }
        }
        if(node == null){
            return node;
        }
        node.height = max(height(node.left), height(node.right)) + 1;
        
        int balance = getBalance(node);
        
        if(balance > 1 && getBalance(node.left) >= 0){
            return rightRotate(node);
        }
        if(balance > 1 && getBalance(node.left) < 0){
            node.left = leftRotate(node.left);
            return rightRotate(node);
        }
        if(balance < -1 && getBalance(node.right) <= 0){
            return leftRotate(node);
        }
        if(balance < -1 && getBalance(node.right) > 0){
            node.right = rightRotate(node.right);
            return leftRotate(node);
        }
        
        return node;
    }

    public void inOrder(Node node){
        if(node != null){
            inOrder(node.left);
            System.out.print(node.value + " ");
            inOrder(node.right);
        }
    }
}