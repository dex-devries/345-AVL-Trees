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
        // tree.insert()
        // result = !tree.isEmpty() ? "PASSED" : "FAILED" ;
        // System.out.println("Insert() test 1: " + result);
    }
}
