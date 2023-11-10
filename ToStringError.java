public class ToStringError {

    // This shows an error with toString that occurs after rebalancing - my guess is that the height is not updated in rebalancing

    public static void main(String[] args) {
        AVLTree tree = new AVLTree();
        tree.insert(5);
        tree.insert(4);
        tree.insert(3); // works up to here - rebalance occurs, 4 becomes root (maybe height isn't updated properly in rebalance?)
        tree.insert(2); // error in printed tree after this insert


        System.out.println(tree);
        System.out.println("root: " + tree.root().val);
        System.out.println(tree.isBalanced(tree.root()));
        System.out.println(tree.height());
    }
}
