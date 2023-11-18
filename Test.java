/*
 * AUTHORS: Dexter DeVries, Anisha Munjal, Kayla Pierson, Gurvir Dhillon
 * FILE: Test.java
 * ASSIGNMENT: Project
 * COURSE: CSC 345 Fall 2023
 * Description: Test class for testing the AVLTree
 */

public class Test {
	private static final String passed = "PASSED", failed = "FAILED";
	private static String testName = "AVL Tests";

	/**
	 * Main test method
	 * 
	 * @param args command line arguments (not used here)
	 */
	public static void main(String[] args) {

		String result;
		// Constructor test
		AVLTree tree = new AVLTree();
		result = tree != null ? passed : failed;
		System.out.println("Constructor test: " + result);
		// Init size test
		result = tree.size() == 0 ? passed : failed;
		System.out.println("Init size() test: " + result);
		// Init isEmpty test
		result = tree.isEmpty() ? passed : failed;
		System.out.println("Init isEmpty() test: " + result);

		boolean showTree = false;
		if (args.length == 1) {
			if (args[0].equals("-showTree")) {
				showTree = true;
			} else {
				System.err.println("Invalid command line argument: " + args[0]);
			}
		}

		// AVL tree node Insertion test 1
		testInsertion(showTree);

		// AVL tree isBalanced test 1
		testIsBalanced(showTree);

		// Call to Rebalance Test
		testRebalance(showTree);

		// AVL tree node removal test
		testRemoval(showTree);

		// AVL tree large node count insertion and removal test
		testInsertionLarge(showTree);
	}

	/**
	 * Test insertion of large number of nodes
	 * 
	 * @param showTree, boolean shows tree when true
	 */
	private static void testInsertionLarge(boolean showTree) {
		AVLTree tree = new AVLTree();
		String result = failed;
		int numNodes = 0;
		System.out.println("*Begin insert() test 2*");
		testName = "insert() test 2: ";

		// insert large number of nodes
		for (int i = 1; i <= 100000; i++) {
			tree.insert(i);
			numNodes++;
			if (tree.size() != numNodes) {
				System.out.println(testName + result);
				return;
			}
			if (!tree.isBalanced(tree.root())) {
				System.out.println(testName + result);
				return;
			}
		}
		
		if (!tree.isBalanced(tree.root())) {
			System.out.println(testName + result);
			return;
		}
		System.out.println("isBalanced: " + tree.isBalanced(tree.root()));
		System.out.println("tree height: " + tree.height());
		System.out.println("tree size: " + tree.size());

		// remove large number of nodes
		for (int i = 1; i <= 100000; i++) {
			tree.root = tree.remove(tree.root(), i);
			numNodes--;
			if (tree.size() != numNodes) {
				System.out.println(testName + result);
				return;
			}
			if (!tree.isBalanced(tree.root())) {
				System.out.println(testName + result);
				return;
			}
		}

		if (showTree) {
			System.out.println("Printing tree...");
			System.out.println(tree);
		}
		result = tree.isEmpty() ? passed : failed;
		System.out.println(testName + result);
	}

	/**
	 * Test AVL node insertion
	 * 
	 * @param boolean showTree, a boolean to indicate whether to print the tree
	 *                after running tests
	 * 
	 * @return AVLTree, the AVL tree at the end of the test
	 */
	private static AVLTree testInsertion(boolean showTree) {
		AVLTree tree = new AVLTree();
		String result = failed;
		int numNodes = 0;
		System.out.println("*Begin insert() test 1*");
		testName = "insert() test 1: ";

		// Test insertion: 1 root, 10 left nodes, 5 right nodes
		// root node (val = 11)
		tree.insert(11);
		numNodes++;
		if (tree.size() != numNodes) {
			System.out.println(testName + result);
			return null;
		}

		// These AVL tree nodes should go on the left of root
		for (int i = 1; i <= 10; i++) {
			tree.insert(i);
			numNodes++;
			if (tree.size() != numNodes) {
				System.out.println(testName + result);
				return null;
			}
		}
		// These AVL tree nodes should go on the right of root
		for (int i = 16; i <= 20; i++) {
			tree.insert(i);
			numNodes++;
			if (tree.size() != numNodes) {
				System.out.println(testName + result);
				return null;
			}
		}

		if (showTree) {
			System.out.println("Printing tree...");
			System.out.println(tree);
		}
		result = !tree.isEmpty() ? passed : failed;
		System.out.println(testName + result);
		return tree;
	}

	/**
	 * Test isBalanced method
	 * 
	 * @param boolean showTree, a boolean to indicate whether to print the tree
	 *                after running tests
	 * 
	 * @return AVLTree, the AVL tree at the end of the test
	 * 
	 */
	private static AVLTree testIsBalanced(boolean showTree) {
		AVLTree tree = new AVLTree();
		String result;
		System.out.println("*Begin isBalanced() test 1*");
		testName = "isBalanced() test 1: ";

		// isbalanced test 1
		// this creates a tree with four levels
		// due to the order of the inserted elements, at no point should the tree be
		// unbalanced

		int[] balancedInserts = { 100, 50, 150, 25, 75, 125, 175, 20, 30, 60, 85, 110, 130, 160, 200, 10, 23, 28, 40,
				55, 70, 80, 92, 105, 120, 128, 140, 155, 168, 190, 220 };
		boolean[] isBalanced = new boolean[31]; // store result of isBalanced after each insert

		System.out.print("Inserting");
		int i = 0;
		// insert each elements from inserts array and use method isBalanced on tree
		// store boolean result in correct index of isBalanced
		while (i < balancedInserts.length) { // length is 31
			System.out.print("->" + balancedInserts[i]);
			tree.insert(balancedInserts[i]);
			isBalanced[i] = tree.isBalanced(tree.root());
			i++;
		}
		System.out.println();

		result = passed;
		i = 0;
		// iterate through isBalanced and if false is encountered, test failed
		// print helpful error message
		while (i < isBalanced.length) { // length is 31
			if (!isBalanced[i]) { // isBalanced[i] == false
				result = failed;
				System.out.println("isBalanced() failed after inserting: " + balancedInserts[i]);
			}
			i++;
		}

		if (showTree) {
			System.out.println("Printing tree...");
			System.out.println(tree);
		}
		System.out.println(testName + result);

		return tree;
	}

	/**
	 * Test AVL node rebalancing
	 * 
	 * @param boolean showTree, a boolean to indicate whether to print the tree
	 *                after running tests
	 * 
	 * @return AVLTree, the AVL tree at the end of the test
	 */
	private static AVLTree testRebalance(boolean showTree) {
		AVLTree tree = new AVLTree();
		String result = failed;
		System.out.println("*Begin Rebalance() test 1*");
		testName = "Rebalance() test 1: ";

		// Create Tree: 1 root, 10 left nodes, 5 right nodes
		// root node (val = 11)
		tree.insert(11);
		// create 10 left nodes
		for (int i = 1; i <= 10; i++) {
			tree.insert(i);
		}
		// create 5 right nodes
		for (int i = 16; i <= 20; i++) {
			tree.insert(i);
		}

		int sizePreRebalance = tree.size();
		// Tree before rebalance
		if (showTree) {
			System.out.println(tree);
		}

		// Perform a manual check for balance at the root, if unbalanced, rebalance
		if (!tree.isBalanced(tree.root())) {
			tree.rebalance(tree.root());
		}

		// Tree after rebalance
		if (showTree) {
			System.out.println("Printing tree...");
			System.out.println(tree);
		}
		int sizePostRebalance = tree.size();

		if (sizePreRebalance != sizePostRebalance) {
			// size of tree doesn't match so test failed
			System.out.println(testName + result);
			return null;
		}

		result = !tree.isEmpty() ? "PASSED" : "FAILED";
		System.out.println(testName + result);
		return tree;
	}

	/**
	 * Test AVL node insertion
	 * 
	 * @param boolean showTree, a boolean to indicate whether to print the tree
	 *                after running tests
	 * 
	 * @return AVLTree, the AVL tree at the end of the test
	 */
	private static AVLTree testRemoval(boolean showTree) {
		AVLTree tree = new AVLTree();
		String result = failed;
		System.out.println("*Begin Remove() test 1*");
		testName = "Remove() test 1: ";

		// Create Tree: 1 root, 10 left nodes, 5 right nodes
		// root node (val = 11)
		tree.insert(11);
		// create 10 left nodes
		for (int i = 1; i <= 10; i++) {
			tree.insert(i);
		}
		// create 5 right nodes
		for (int i = 16; i <= 20; i++) {
			tree.insert(i);
		}

		int sizePreRebalance = tree.size();
		// Tree before rebalance

		if (showTree) {
			System.out.println("Printing tree before remove:");
			System.out.println(tree);
		}

		tree.remove(tree.root, 17);

		// Tree after rebalance
		if (showTree) {
			System.out.println("Printing tree after remove:");
			System.out.println(tree);
		}
		int sizePostRebalance = tree.size();

		// Size after deletion is 1 less than before deletion
		if (sizePreRebalance != (sizePostRebalance + 1)) {
			// size of tree doesn't match so test failed
			System.out.print(sizePreRebalance);
			System.out.println(testName + result);
			System.out.println(sizePostRebalance + 1);
			return null;
		}

		result = !tree.isEmpty() ? "PASSED" : "FAILED";
		System.out.println(testName + result);
		return tree;
	}
}
