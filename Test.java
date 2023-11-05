/*
 * Used for testing the AVLTree
 */

public class Test {
	public static void main(String[] args) {
        String result;
        // Constructor test
        AVLTree tree = new AVLTree();
        result = tree != null ? "PASSED" : "FAILED";
        System.out.println("Constructor test: " + result);
        // Init size test
        result = tree.size() == 0 ? "PASSED" : "FAILED" ;
        System.out.println("Init size() test: " + result);
        // Init isEmpty test
        result = tree.isEmpty() ? "PASSED" : "FAILED" ;
        System.out.println("Init isEmpty() test: " + result);
        // Insert test 1
         tree.insert(20);
         for (int i = 0; i < 10; i++) {
        	 tree.insert(i);
         }
         for (int i = 11; i < 20; i++) {
        	 tree.insert(i);
         }

         System.out.println(tree);
         result = !tree.isEmpty() ? "PASSED" : "FAILED" ;
         System.out.println("Insert() test 1: " + result);
    }
}
