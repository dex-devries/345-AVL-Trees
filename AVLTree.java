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
	private boolean debug = false;
	private int depth = 0;
	private boolean debugRemove = true;

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

	/**
	 * Insert a new Node into the AVLTree with value val Call recursive helper
	 * function insertHelper with (root, val) Note: pass root node to helper
	 * 
	 * @return integer, the number of elements in the AVL tree
	 */

	// Modify the insert method like so:
	public void insert(int val) {
		root = insertHelper(root, val); // Ensure the root is updated with the new tree structure
		this.height = root.height; // Update the tree height after insertion
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
		if (debug)
			System.out.println("Node height updated for ancestor node h=" + node.height);

		// Rebalance the node if needed
		return rebalance(node);
	}

	/**
	 * Find height of AVL tree node
	 * 
	 * @param node
	 * @return
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
			if (debugRemove)
				System.out.println("Removing " + val + " from AVL tree: exiting due to root is null");
			return root;
		}
		if (debugRemove)
			System.out.println("Removing " + val + " from AVL tree: at node " + root.val);

		if (val < root.val) {
			if (debugRemove)
				System.out.println("Removing " + val + "(left recursive) from AVL tree");

			root.left = remove(root.left, val);
			
			if (debugRemove) {
				System.out.println("Just after root.left initialized after return from remove() ");
				System.out.println(this);
			}
		} else if (val > root.val) {
			if (debugRemove)
				System.out.println("Removing " + val + "(right recursive) from AVL tree");

			root.right = remove(root.right, val);
			if (debugRemove) {
				System.out.println("Just after root.right initialized after return from remove() ");
				System.out.println(this);
			}
		} else {
			// Node with only one child or no child
			if ((root.left == null) || (root.right == null)) {
				Node temp = null;
				if (temp == root.left) {
					if (debugRemove)
						System.out.println("setting temp to root.right for node " + root.val);
					
					temp = root.right;
				} else {
					if (debugRemove)
						System.out.println("setting temp to root.left for node " + root.val);
					
					temp = root.left;
				}

				// No child case
				if (temp == null) {
					if (debugRemove)
						System.out.println("processing no child case for node " + root.val);
					temp = root;
					if (debugRemove) {
						System.out.println("Just before setting root to null");
						System.out.println(this);
					}
					root = null;
					if (debugRemove) {
						System.out.println("Just after setting root to null");
						System.out.println(this);
					}
				} else { // One child case
					if (debugRemove)
						System.out.println("processing one child case for node " + root.val);
					root = temp; // Copy the contents of the non-empty child
				}
				nodeCount--;
			} else {
				
				if (debugRemove)
					System.out.println("processing node with 2 children for node " + root.val);
				
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
			if (debugRemove) {
				System.out.println("Just before returning from remove");
				System.out.println(this);
				System.out.println("Done with remove");
			}
			return root;
		}
		
		if (debugRemove)
			System.out.print("Done with remove, updating height of node " + root.val);

		// Update height of the current node
		root.height = Math.max(findHeight(root.left), findHeight(root.right)) + 1;

		if (debugRemove)
			System.out.println(" to " + root.height);
		
		// Rebalance the tree
		if (debugRemove) {
			System.out.println("Just before rebalancing before exiting remove");
			System.out.println(this);
		}
		Node retval = rebalance(root);
		if (debugRemove) {
			System.out.println("Just after rebalancing before exiting remove");
			System.out.println(this);
		}
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

	// Utility method to perform a right rotation
	private Node rotateRight(Node y) {
		if (debug)
			System.out.println("rotateRight now...");

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

	// Utility method to perform a left rotation
	private Node rotateLeft(Node x) {
		if (debug)
			System.out.println("rotateLeft now...");

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

	// Get balance factor of a node
	private int getBalance(Node N) {
		if (N == null)
			return 0;
		return findHeight(N.left) - findHeight(N.right);
	}

	// Rebalance the tree at node and return the new root
	Node rebalance(Node z) {
		if (debug) {
			System.out.println("Tree before rebalancing occured...");
			System.out.println(this.toString());
			System.out.println("Rebalancing now...");
			System.out.println("Node z in rebalance() call has value:" + z.val);
		}
		// Update height of this ancestor node
		z.height = 1 + Math.max(findHeight(z.left), findHeight(z.right));

		// Get the balance factor
		int balance = getBalance(z);

		// If this node becomes unbalanced, then there are 4 cases

		// Left Left Case
		if (balance > 1 && getBalance(z.left) >= 0) {
			if (debug) {
				System.out.println("Rebalancing Left Left Case");
			}

			return rotateRight(z);
		}

		// Left Right Case
		if (balance > 1 && getBalance(z.left) < 0) {
			if (debug) {
				System.out.println("Rebalancing Left Right Case");
			}
			z.left = rotateLeft(z.left);
			return rotateRight(z);
		}

		// Right Right Case
		if (balance < -1 && getBalance(z.right) <= 0) {
			if (debug) {
				System.out.println("Rebalancing Right Right Case");
			}
			return rotateLeft(z);
		}

		// Right Left Case
		if (balance < -1 && getBalance(z.right) > 0) {
			if (debug) {
				System.out.println("Rebalancing Right Left Case");
			}
			z.right = rotateRight(z.right);
			return rotateLeft(z);
		}

		// Return the (unchanged) node pointer
		return z;
	}

	private Node findMin(Node node) {
		Node current = node;
		while (current.left != null) {
			current = current.left;
		}
		return current;
	}

}
