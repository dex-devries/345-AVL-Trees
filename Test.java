/*
 * Used for testing the AVLTree
 */

public class Test {
	private static String passed = "PASSED", failed = "FAILED";
	
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
		testInsertion(tree);
	}

	/**
	 * Test AVL node insertion
	 * 
	 * @param tree, AVLTree root node
	 * @return String, the string result
	 */
	private static void testInsertion(AVLTree tree) {
		String result;
		int numNodes = 0;
		String testName = "Insert() test 1: ";
		boolean showTree = true;
		
		// Test insertion: 1 root, 10 left nodes, 5 right nodes
		// root node (val = 11)
		tree.insert(11);
		numNodes++;
		if (tree.size() != numNodes) {
			System.out.println("Insert() test 1: " + failed);
			return;
		}
		
		// These AVL tree nodes should go on the left of root
		for (int i = 1; i <= 10; i++) {
			tree.insert(i);
			numNodes++;
			if (tree.size() != numNodes) {
				System.out.println("Insert() test 1: " + failed);
				return;
			}
		}
		// These AVL tree nodes should go on the right of root
		for (int i = 16; i <= 20; i++) {
			tree.insert(i);
			numNodes++;
			if (tree.size() != numNodes) {
				System.out.println("Insert() test 1: " + failed);
				return;
			}
		}

		if (showTree) {
			System.out.println(tree);
		}
		result = !tree.isEmpty() ? "PASSED" : "FAILED";
		System.out.println(testName + result);
	}
}
