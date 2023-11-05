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

		// Insert test 1
		testInsertion();
		
		// TODO: Gurvir - Add call to Rebalance Test here
		// testRebalance()
		
		// TODO: Kayla - Add Deletion Test here (delete 1 node)
		// testRemoval();
	}

	/**
	 * Test AVL node insertion
	 * 
	 * @return AVLTree, the AVL tree at the end of the test
	 */
	private static AVLTree testInsertion() {
		AVLTree tree = new AVLTree();
		String result = failed;
		int numNodes = 0;
		testName = "Insert() test 1: ";
		boolean showTree = true;

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
			System.out.println(tree);
		}
		result = !tree.isEmpty() ? "PASSED" : "FAILED";
		System.out.println(testName + result);
		return tree;
	}

	/**
	 * Test AVL node rebalancing
	 * 
	 * @return AVLTree, the AVL tree at the end of the test
	 */
	private static AVLTree testRebalance() {
		AVLTree tree = new AVLTree();
		String result = failed;
		testName = "Rebalance() test 1: ";
		boolean showTree = true;

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
		
		// TODO: Gurvir - Add Rebalance Test here

		// Tree after rebalance
		if (showTree) {
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
	 * @return AVLTree, the AVL tree at the end of the test
	 */
	private static AVLTree testRemoval() {
		AVLTree tree = new AVLTree();
		String result = failed;
		testName = "Rebalance() test 1: ";
		boolean showTree = true;

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
		
		// TODO: Kayla - Add Deletion Test here (delete 1 node)

		// Tree after rebalance
		if (showTree) {
			System.out.println(tree);
		}
		int sizePostRebalance = tree.size();

		// Size after deletion is 1 less than before deletion
		if (sizePreRebalance != (sizePostRebalance + 1)) {
			// size of tree doesn't match so test failed
			System.out.println(testName + result);
			return null;
		}

		result = !tree.isEmpty() ? "PASSED" : "FAILED";
		System.out.println(testName + result);
		return tree;
	}
}
