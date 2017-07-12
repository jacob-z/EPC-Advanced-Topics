/*
BinarySearchTree.java

This class represents the binary search tree (BST)
data structure and its associated functions, using Nodes.
*/

public class BinarySearchTree {

    class Node {
    int data;    // id of Node
    Node left;   // left child of Node
    Node right;  // right child of Node

    public Node(int data) {
        this.data = data;
        left = null;
        right = null;
        }
    }

    public static Node root; // first, top Node in BST tree

    // initialize a new, empty tree
    public BinarySearchTree(){
        this.root = null;
    }
    
    // search for node with data=id in BST
    public boolean search(int id){
        Node current = root;
        while (current != null) {
            if (current.data==id) {
                return true;
            }
            else if (current.data>id) {
                current = current.left;
            }
            else {
                current = current.right;
            }
        }
        return false;
    }

    // insert a node with data=id into the BST
    public void insert(int id){
        Node newNode = new Node(id);

        // if BST is empty
        if (root==null) {
            root = newNode;
            return;
        }

        Node current = root;
        Node parent = null;
        while(true) {
            parent = current;
            if (current.data == id) {
                return;
            }

            else if (current.data > id) {                
                current = current.left;
                if(current==null){
                    parent.left = newNode;
                    return;
                }
            }
            else {
                current = current.right;
                if (current==null) {
                    parent.right = newNode;
                    return;
                }
            }
        }
    }

    // delete id from BST
    public boolean delete(int id){
        Node parent = root;
        Node current = root;
        boolean isLeftChild = false;

        // if the tree is empty
        if (current == null) {
            return false;
        }

        // locate node with data=id
        while (current.data != id) {
            parent = current;
            if (current.data > id) {
                isLeftChild = true;
                current = current.left;
            }
            else {
                isLeftChild = false;
                current = current.right;
            }
            if (current == null) {
                return false;
            }
        }

        //if we are here that means we have found the node

        // case 1: if node to be deleted has no children
        if(current.left == null && current.right == null){
            if (current == root) {
                root = null;
            }
            if (isLeftChild) {
                parent.left = null;
            }
            else {
                parent.right = null;
            }
        }

        // case 2 : if node to be deleted has only one child
        else if (current.right == null){
            if(current == root){
                root = current.left;
            }
            else if (isLeftChild) {
                parent.left = current.left;
            }
            else {
                parent.right = current.left;
            }
        }

        else if (current.left == null) {
            if (current == root) {
                root = current.right;
            }
            else if (isLeftChild) {
                parent.left = current.right;
            }
            else {
                parent.right = current.right;
            }
        }

        // case 3: node to be deleted has two children
        else if (current.left != null && current.right != null) {
            
            // successor is the minimum element in the right sub tree from current
            // successor cannot be null because node has two children
            // successor cannot have a left child (or else that would be successor)
            Node successor   = getAndDeleteSuccessor(current);
            current.data = successor.data;
        }       
        return true;        
    }
    
    // get the node with id just after id of pred and delete it
    public Node getAndDeleteSuccessor(Node pred){
        Node successorParent = pred;
        Node current = pred.right;
        Node successor = current;

        // the successor will be the left-most node of the pred.right subtree
        while (current != null) {
            successorParent = successor;
            successor = current;
            current = current.left;
        }

        // deleting node...
        // check if successor has the right child (it cannot have left child)
        // if it does have the right child, add it to the left of successorParent.
        if (successor == pred.right) {
            pred.right = successor.right;
        }
        else if (successor != pred.right) {
            successorParent.left = successor.right;
        }
        return successor;
    }

    // print out the BST, in-order traversal
    public void display(Node root){
        if (root != null) {
            display(root.left);
            System.out.print(root.data + " ");
            display(root.right);
        }
    }

    // intialization of example BST and testing
    public static void main(String arg[]){
        BinarySearchTree b = new BinarySearchTree();
        b.insert(3);b.insert(8);b.insert(1);b.insert(4);b.insert(6);
        b.insert(2);b.insert(10);b.insert(9);b.insert(20);b.insert(25);
        b.insert(15);b.insert(16);b.insert(16);
        System.out.println("Current Tree: ");
        b.display(b.root);   
        System.out.println("");
        System.out.println("Check whether Node with value 4 exists: " + b.search(4));
        System.out.println("Check whether Node with value 17 exists: " + b.search(17));
        System.out.println("Delete Node that doesn't exist (17): " + b.delete(17));        
        System.out.println("Current Tree: ");
        b.display(b.root);
        System.out.println("");
        System.out.println("Delete Node with no children (2): " + b.delete(2));        
        System.out.println("Current Tree: ");
        b.display(b.root);
        System.out.println("");
        System.out.println("Delete Node with one child (4): " + b.delete(4));       
        System.out.println("Current Tree: ");
        b.display(b.root);
        System.out.println("");
        System.out.println("Delete Node with Two children (10): " + b.delete(10));      
        System.out.println("Current Tree: ");
        b.display(b.root);
        System.out.println("");
    }
}

