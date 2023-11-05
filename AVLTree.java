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

	private final int childrenTypeAll = 0; // # of child nodes
	private final int childrenTypeLeft = 1; // # of left children of a node
	private final int childrenTypeRight = 2; // # of right children of a node

	// constructor - initialize fields
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

	private int numChildren(Node node, int typeOfChildren) {
		int count = 0;

		if (typeOfChildren == childrenTypeLeft) {
			if (node.left == null)
				return count;
		}
		if (typeOfChildren == childrenTypeRight) {
			if (node.right == null)
				return count;
		}

		if (node.left != null) {
			count += numChildren(node.left, typeOfChildren);
		}
		if (node.right != null) {
			count += numChildren(node.right, typeOfChildren);
		}
		return count;
	}

	/**
	 * Insert a new Node into the AVLTree with value val Call recursive helper
	 * function insertHelper with (root, val) Note: pass root node to helper
	 * 
	 * @return integer, the number of elements in the AVL tree
	 */

	public void insert(int val) {
		if (root == null) {
			// create a new node of AVL tree if the tree is empty
			root = new Node(val);
			nodeCount = 1;
			this.height = 0;
			return;
		} else {
			insertHelper(root, val, 1);
		}
	}

	/**
	 * Recursive helper function for insert Creates new Node object with value val
	 * and iterates through the tree to find the correct position to insert
	 * 
	 * @param node, the node tha val will be compared against to determine position
	 *              for insert
	 * @param val,  the integer value to insert (new Node with value val) into the
	 *              AVL tree
	 * @return AVLTree, the root node of the AVL tree
	 */

	public Node insertHelper(Node node, int val, int height) {
		Node newNode = null;

		if (val < node.val) {
			if (node.left == null) {
				newNode = new Node(val);
				newNode.height = height;
				node.left = newNode;
				nodeCount++;
				return node.left;
			}
			return insertHelper(node.left, val, height + 1);
		}

		if (val > node.val) {
			if (node.right == null) {
				newNode = new Node(val);
				newNode.height = height;
				node.right = newNode;
				nodeCount++;
				return node.right;
			}
			return insertHelper(node.right, val, height + 1);
		}
		return root;
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
			int left_treeHeight = root.left.height;
			int right_treeHeight = root.right.height;

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
		if (root == null) // if tree is empty
		{
			return root;
		}

		if (root.left == null && root.right == null) // if tree has one node
		{
			root = null;
		}

		else // if tree has more than one node
		{
			if (val < root.val) {
				root.left = remove(root.left, val);
			} else if (val > root.val) {
				root.right = remove(root.right, val);
			} else // found node, delete
			{
				// no children
				if (root.left == null && root.right == null) {
					root = null;
				}

				// two children; replacement will be its right node
				else if (root.left != null && root.right != null) {
					Node replacement = root.right;
					root.val = replacement.val;

					// remove the replacements old position Node
					root.right = remove(root.right, replacement.val);

				}
				// node has one child
				else {
					if (root.left == null) {
						root = root.right;
					} else {
						root = root.left;
					}
				}
			}
		}
		root.height = 1 + Math.max(root.left.height, root.right.height);

		if (isBalanced(root()) == false) {
			// balance()
			// call the balance function to balance the tree
		}

		return root;
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

		// add spaces based on node height
		int spaces = (tmp.height * 3);
		while (spaces-- != 0) {
			System.out.print("");
		}

		int h = tmp.height;
		while (h-- > 1)
			System.out.print("-");
		System.out.print("(" + tmp.val + " ,h=" + tmp.height);
		System.out.print(", #lc=" + numChildren(tmp, childrenTypeLeft));
		System.out.print(", #rc=" + numChildren(tmp, childrenTypeRight) + ")\n|-");


		// display left subtree
		if (tmp.left != null) {
			str += toStringHelper(tmp.left, str);
		}

		// display right subtree
		if (tmp.right != null)
			str += toStringHelper(tmp.right, str);

		return str;
	}
}
