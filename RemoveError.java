public class RemoveError {

    // This shows an error with remove()
    // After removing 
    public static void main(String[] args) {
        AVLTree tree = new AVLTree();
        tree.insert(5);
        tree.insert(4);
        tree.insert(3); // rebalances here
        tree.insert(2); 
        tree.insert(1);
        tree.insert(0); // rebalances here
        tree.remove(tree.root(), 0); // works up to here - removing 0 should not rebalance
        tree.remove(tree.root(), 1); // fails here - removing 1 should rebalance to root 4
                                        // tree should be in following state
                                        // (4) 
                                        // -(2)
                                        // --(3)
                                        // -(5)
        System.out.println(tree);
        System.out.println("root: " + tree.root().val);
        System.out.println("isBalanced: " + tree.isBalanced(tree.root()));
        System.out.println("tree height: " + tree.height());
    }
}
