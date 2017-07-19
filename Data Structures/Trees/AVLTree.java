/*
Thomas Gwozdz
July 18, 2017

Adapted heavily from:
https://www.unf.edu/~wkloster/3540/avl.java
http://users.cis.fiu.edu/~weiss/dsaajava3/code/AvlTree.java

For EPC at IUEA, Kampala, Uganda

AVL tree implementation which supports find, insert, empty
and print. AVL trees are really good when you need to 
search through the tree a lot because they are balanced 
and worst case is log(n).
*/

class AVLTree {

    class Node {

        public int iData;  // data item (key)
        public int height; // height of node
        public Node left;  // this node's left child
        public Node right; // this node's right child

        public Node(int id) {

            iData = id;
            height = 0;
            left = null;
            right = null;

            }
        }

    private Node root; // first node of tree

    public AVLTree() {
        root = null;
    }

    public Node getRoot() {
        return root;
    }

    public void emptyTree() {
        root = null;
    }

    // recursive search for node with data=key from foo
    public Node find(int key, Node foo) {

    if (foo == null)
        return null;
    else
    if (foo.iData == key)
        return foo;
    else
    if (foo.iData < key)
        return find(key, foo.right);
    else
        return find(key, foo.left);
    }

    // return height of tree rooted at x
    public int height(Node x) {

    if (x == null) return -1;
    else return x.height;
    }

    private Node rotatewithleft(Node c) {
    System.out.println(c.iData + " rotating with left...");
    Node p = c.left;  // left child of c

    c.left = p.right;
    p.right = c;

    c.height = Math.max(height(c.left), height(c.right)) + 1;
    p.height = Math.max(height(p.left), height(p.right)) + 1;

    return p;
    }

    private Node rotatewithright(Node c) {
    System.out.println(c.iData + " rotating with right...");
    Node p = c.right;  // right child of c

    c.right = p.left;
    p.left = c;

    c.height = Math.max(height(c.left), height(c.right)) + 1;
    p.height = Math.max(height(p.left), height(p.right)) + 1;

    return p;
    }

    private Node doublerotatewithleft(Node c) {
    Node tmp;

    c.left = rotatewithright(c.left);
    tmp = rotatewithleft(c);

    return tmp;
    }


    private Node doublerotatewithright(Node c) {
    Node tmp;

    c.right = rotatewithleft(c.right);
    tmp = rotatewithright(c);

    return tmp;
    }

    // recursive insertion of newNode into tree from par
    private Node avlinsert(Node newNode, Node par) {

    Node newpar = par; // root of subtree par

    if (newNode.iData < par.iData) // branch left
    {
        if (par.left == null)
            par.left = newNode; //attach new node as leaf
        else {
            par.left = avlinsert(newNode, par.left); // branch left
            if ((height(par.left) - height(par.right)) > 1) {
                if (newNode.iData < par.left.iData) {
                    newpar=rotatewithleft(par); // slopes down to the left
                }
                else {
                    newpar=doublerotatewithleft(par); // slopes down left then right
                }
            }
        }
    }

    else if (newNode.iData > par.iData) // branch right
    {
        if (par.right == null)
            par.right = newNode; // attach new node as leaf
        else {
            par.right = avlinsert(newNode, par.right); // branch right
            if ((height(par.right) - height(par.left)) > 1) {
                if (newNode.iData > par.right.iData) {
                    newpar=rotatewithright(par); // slopes down to the right
                }
                else {
                    newpar=doublerotatewithright(par); // slopes down right then left
                }
            }
        }
    }

    // update height
    if ((par.left == null) && (par.right != null))
        par.height = par.right.height + 1;
    else if ((par.right == null) && (par.left != null))
        par.height = par.left.height + 1;
    else
        par.height = Math.max(height(par.left), height(par.right)) + 1;

    return newpar; // return new root of this subtree
    }

    public void insert(int id) {
        Node newNode = new Node(id);    // make new node

        if(root==null)
            root = newNode;
        else
            root = avlinsert(newNode, root);
    }

    // recursive helper method to print tree
    public void display(Node root) {
        if (root != null) {
            display(root.left);
            System.out.print(root.iData + " ");
            display(root.right);
        }
    }

    // print out the tree, in-order traversal
    public void printTree() {
        System.out.println("Current tree:");
        display(root);
        System.out.println("\n-------------");
    }

    public static void main(String[] args) {
    AVLTree tree = new AVLTree();
    tree.insert(50);
    tree.insert(25);
    tree.insert(25);
    tree.insert(75);
    tree.insert(12);
    tree.insert(37);
    tree.insert(43);
    tree.insert(30);
    tree.insert(33);
    tree.insert(87);
    tree.insert(93);
    tree.insert(97);
    tree.printTree();
    tree.insert(100);
    tree.insert(101);
    tree.insert(102);
    tree.printTree();
    tree.emptyTree();
    tree.printTree();

    }
} 