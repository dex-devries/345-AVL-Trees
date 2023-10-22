/*
 * AUTHORS: Dexter DeVries, Anisha Munjal, Kayla Pierson, 
 * FILE: AVLTree.java
 * ASSIGNMENT: Project
 * COURSE: CSC 345 Fall 2023
 * Description: An implementation of a self-balancing binary search tree (AVL tree).
 * An AVL tree is a type of binary search tree named after it's inventors Adelson, 
 * Velskii, and Landis, AVL trees have the property of dynamic self-balancing 
 * in addition to all the other properties exhibited by binary search trees. 
 */

public class AVLTree {
	private int nodeCount;	// number of nodes in AVL tree
	private AVLTree root;   // the root of the tree

    // class Node - a node in a BST
    class Node {      
        int val;  // the integer value for this node
        int height;  // the height of this node in the tree
        Node left;  // the left child node - inits to null
        Node right;  // the right child node - inits to null
      
        // constructor with int val as parameter - creates a Node with value val
        public Node(int val) {  
            left = null;  
            right = null;  
            this.val = val;  
            height = 0;  
        }       
    }  

    // constructor - initialize fields
    public AVLTree() {
        this.nodeCount = 0;
        this.root = null;
    }
    
    /**
     * Returns true when AVL tree is empty, false otherwise
     * 
     * @return boolean true, if empty, false otherwise
     */
    public boolean isEmpty() {
    	return nodeCount == 0;
    }
    
    /**
     * Returns the number of elements in the AVL tree
     * 
     * @return integer, the number of elements in the AVL tree
     */
    public int size() {
    	return nodeCount;
    }
    
    /**
     * Insert value into an AVL tree
     * 
     * @param val, the integer value to insert into the AVL tree
     * @return AVLTree, the root node of the AVL tree
     */
    public AVLTree insert(int val) {
    	// check if AVL tree is empty
    	if (root == null) {
    		// create a new node of AVL tree if the tree is empty
    		root = new AVLTree();
    	
    		// set the node's value to val
    		
    		return root;
    	}
    	return root;
    }
    
    /**
     * String representation of the AVL tree
     */
    public String toString() {
    	String str = null;
    	
    	// check if AVL tree is empty
    	if (root == null) {
    		str = "AVL tree is empty.";
    		return str;
    	}
    	
    	// print contents of tree in order traversal
    	
		return str;
    }
}