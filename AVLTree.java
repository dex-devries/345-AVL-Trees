/*
 * AUTHORS: Dexter DeVries, Anisha Munjal, Kayla Pierson, Gurvir Dhillon
 * FILE: AVLTree.java
 * ASSIGNMENT: Project
 * COURSE: CSC 345 Fall 2023
 * Description: An implementation of a self-balancing binary search tree (AVL tree).
 * An AVL tree is a type of binary search tree named after it's inventors Adelson, 
 * Velskii, and Landis, AVL trees have the property of dynamic self-balancing 
 * in addition to all the other properties exhibited by binary search trees. 
 */

public class AVLTree {
	// class Node - a node in a BST
	class Node {
		int val; // the integer value for this node
		int height; // the height of this node in the tree
		Node left; // the left child node - inits to null
		Node right; // the right child node - inits to null

		// constructor with int val as parameter - creates a Node with value val
		public Node(int val) {
			left = null;
			right = null;
			this.val = val;
			height = 0;
		}
	}

	private int nodeCount; // number of nodes in AVL tree
	public Node root; // the root of the tree
	private int height; // the height of the tree
	private int depth = 0;

	/**
	 * constructor
	 */
	public AVLTree() {
		this.nodeCount = 0;
		this.root = null;
		this.height = 0;
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
	 * Return the height of the AVLTree
	 * 
	 * @return int height
	 */
	public int height() {
		return height;
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
	 * Returns the root node of the AVL tree
	 * 
	 * @return Node root
	 */
	public Node root() {
		return root;
	}

	/**
	 * Search for an integer value to see if it is in the tree
	 * 
	 * @param val, the integer to search for
	 * 
	 * @return Node, the Node that contains val if it exists, null if the value does not exist in the tree
	 */
	public Node get(int val) {
		Node node = root;
		while (node != null) {
			if (node.val == val) {
				return node;
			}
			else if (val < node.val) {
				node = node.left;
			}
			else if (val > node.val) {
				node = node.right;
			}
		}
		return null;
	}

	/**
	 * Insert a new Node into the AVLTree with value val Call recursive helper
	 * function insertHelper with (root, val) Note: pass root node to helper
	 * 
	 * @return integer, the number of elements in the AVL tree
	 */
	public void insert(int val) {
		root = insertHelper(root, val); // Ensure the root is updated with the new tree structure
		this.height = root.height; // Update the tree height after insertion
	}

	/**
	 * Recursive helper function for insert Creates new Node object with value val
	 * and iterates through the tree to find the correct position to insert
	 * 
	 * @param node, the node that val will be compared against to determine position
	 *              for insert
	 * @param val,  the integer value to insert (new Node with value val) into the
	 *              AVL tree
	 * @return AVLTree, the root node of the AVL tree
	 */
	public Node insertHelper(Node node, int val) {
		if (node == null) {
			nodeCount++;
			return (new Node(val));
		}

		if (val < node.val) {
			node.left = insertHelper(node.left, val);
		} else if (val > node.val) {
			node.right = insertHelper(node.right, val);
		} else {
			// Duplicate keys not allowed
			return node;
		}

		// Update height of this ancestor node
		node.height = 1 + Math.max(findHeight(node.left), findHeight(node.right));

		// Rebalance the node if needed
		return rebalance(node);
	}

	/**
	 * Find height of AVL tree node
	 * 
	 * @param node, Node of the AVL tree
	 * @return int, height of the node
	 */
	public int findHeight(Node node) {
		// If the node is null, we return -1, which is useful for balance factor
		// calculations.
		if (node == null) {
			return -1;
		}
		// Since each node maintains its height, we just return it.
		return node.height;
	}

	/**
	 * Checks whether the AVL Tree is balanced or not
	 * 
	 * @param root, the root of the AVL tree
	 * @return true/false, true if AVL tree is balanced
	 */
	public boolean isBalanced(Node root) {
		if (root == null) {
			return true;
		} else {
			int left_treeHeight = findHeight(root.left);
			int right_treeHeight = findHeight(root.right);

			if (Math.abs(left_treeHeight - right_treeHeight) <= 1 && isBalanced(root.left) && isBalanced(root.right)) {
				return true; // is balanced
			}
			return false;
		}
	}

	/**
	 * Removes a node from the AVL tree given its value. If the tree is unbalanced
	 * after removal, the balance function is called.
	 * 
	 * @param root, the root of the tree
	 * @param val,  the value of the node that is going to be removed
	 * @return Node object, the removed node
	 */

	public Node remove(Node root, int val) {

		if (root == null) {
			return root;
		}

		if (val < root.val) {
			root.left = remove(root.left, val);
		} else if (val > root.val) {
			root.right = remove(root.right, val);
		} else {
			// Node with only one child or no child
			if ((root.left == null) || (root.right == null)) {
				Node temp = null;
				if (temp == root.left) {
					temp = root.right;
				} else {
					temp = root.left;
				}

				// No child case
				if (temp == null) {
					temp = root;
					root = null;
				} else { // One child case
					root = temp; // Copy the contents of the non-empty child
				}
				nodeCount--;
			} else {

				// Node with two children: Get the inorder successor (smallest in the right
				// subtree)
				Node temp = findMin(root.right);

				// Copy the inorder successor's data to this node
				root.val = temp.val;

				// Delete the inorder successor
				root.right = remove(root.right, temp.val);
			}
		}

		// If the tree had only one node then return
		if (root == null) {
			return root;
		}

		// Update height of the current node
		root.height = Math.max(findHeight(root.left), findHeight(root.right)) + 1;

		// Rebalance the tree
		Node retval = rebalance(root);
		return retval;
	}

	/**
	 * String representation of the AVL tree
	 * 
	 * @return String, the string generated for the representation
	 */
	public String toString() {
		String str = "";
		Node tmp = root;

		// check if AVL tree is empty
		if (tmp == null) {
			str += "AVL tree is empty.";
			return str;
		}

		// return string representation of the tree
		return str + toStringHelper(tmp, str);
	}

	/**
	 * Helper method to generate string representation of tree
	 * 
	 * @param tmp, the Node to display
	 * @param str, the string representation to add into
	 * @return String, the string generated for the representation
	 */
	private String toStringHelper(Node tmp, String str) {
		if (tmp == null)
			return null;

		int h = depth;
		while (h-- > 1)
			System.out.print("-");
		System.out.print("(" + tmp.val + " ,h=" + findHeight(tmp));
		System.out.print(", lh=" + (tmp.left != null ? findHeight(tmp.left) : 0));
		System.out.print(", rh=" + (tmp.right != null ? findHeight(tmp.right) : 0));
		System.out.print(")\n");
		System.out.print("|-");

		// display left subtree
		if (tmp.left != null) {
			depth++;
			str += toStringHelper(tmp.left, str);
			depth--;
		}

		// display right subtree
		if (tmp.right != null) {
			depth++;
			str += toStringHelper(tmp.right, str);
			depth--;
		}
		return str;
	}

	/**
	 * Utility method to perform a right rotation
	 * 
	 * @param y, the node of the AVL tree
	 * @return node, the rotated AVL tree node
	 */
	private Node rotateRight(Node y) {

		Node x = y.left;
		Node T2 = x.right;

		// Perform rotation
		x.right = y;
		y.left = T2;

		// Update heights
		y.height = Math.max(findHeight(y.left), findHeight(y.right)) + 1;
		x.height = Math.max(findHeight(x.left), findHeight(x.right)) + 1;

		// Return new root
		return x;
	}

	/**
	 * Utility method to perform a left rotation
	 * 
	 * @param x, the node of the AVL tree
	 * @return node, the rotated AVL tree node 
	 */
	private Node rotateLeft(Node x) {

		Node y = x.right;
		Node T2 = y.left;

		// Perform rotation
		y.left = x;
		x.right = T2;

		// Update heights
		x.height = Math.max(findHeight(x.left), findHeight(x.right)) + 1;
		y.height = Math.max(findHeight(y.left), findHeight(y.right)) + 1;

		// Return new root
		return y;
	}

	/**
	 * Get balance factor of a node
	 * 
	 * @param N, the node of the AVL tree
	 * @return int, the balance factor
	 */
	private int getBalance(Node N) {
		if (N == null)
			return 0;
		return findHeight(N.left) - findHeight(N.right);
	}
	
	/**
	 * Rebalance the tree at node and return the new root
	 * 
	 * @param z, the Node where the rebalancing starts
	 * @return node, the rebalanced subtree
	 */
	Node rebalance(Node z) {
		// Update height of this ancestor node
		z.height = 1 + Math.max(findHeight(z.left), findHeight(z.right));

		// Get the balance factor
		int balance = getBalance(z);

		// If this node becomes unbalanced, then there are 4 cases

		// Left Left Case
		if (balance > 1 && getBalance(z.left) >= 0) {

			return rotateRight(z);
		}

		// Left Right Case
		if (balance > 1 && getBalance(z.left) < 0) {
			z.left = rotateLeft(z.left);
			return rotateRight(z);
		}

		// Right Right Case
		if (balance < -1 && getBalance(z.right) <= 0) {
			return rotateLeft(z);
		}

		// Right Left Case
		if (balance < -1 && getBalance(z.right) > 0) {
			z.right = rotateRight(z.right);
			return rotateLeft(z);
		}

		// Return the (unchanged) node pointer
		return z;
	}

	/**
	 * Find min
	 * 
	 * @param node, the node of the AVL tree
	 * @return node, the minimum node
	 */
	private Node findMin(Node node) {
		Node current = node;
		while (current.left != null) {
			current = current.left;
		}
		return current;
	}

	/**
	 * This is a recursive implementation of an in order traversal of the tree
	 * Prints the values in the tree in correct order according to in order algorithm below
	 * Process left subtree of node (recursive call), visit node, process right subtree of node (recursive call)
	 * 
	 * @param node
	 */
	public void inOrder(Node node) {
		if (node == null) {return;}

        // left subtree call
        inOrder(node.left);
 
        // visit node between left and right subtree calls
        System.out.print(node.val + " ");
 
        // right subtree call
        inOrder(node.right);
	}

	/**
	 * This is a recursive implementation of a pre order traversal of the tree
	 * Prints the values in the tree in correct order according to pre order algorithm below
	 * Visit node, process left subtree of node (recursive call), process right subtree of node (recursive call)
	 * 
	 * @param node
	 */
	public void preOrder(Node node) {
		if (node == null) {return;}
      		
    	// visit node before left and right subtree calls
    	System.out.print(node.val + " ");
 
    	// left subtree call
    	preOrder(node.left);
 
    	// right subtree call
    	preOrder(node.right);
	}

	/**
	 * This is a recursive implementation of a post order traversal of the tree
	 * Prints the values in the tree in correct order according to post order algorithm below
	 * Process left subtree of node (recursive call), process right subtree of node (recursive call), visit node
	 * 
	 * @param node
	 */
	public void postOrder(Node node) {
		if (node == null) {return;}
 
    	// left subtree call
    	postOrder(node.left);
 
    	// right subtree call
    	postOrder(node.right);

    	// visit node after left and right subtree calls
    	System.out.print(node.val + " ");
	}

}
