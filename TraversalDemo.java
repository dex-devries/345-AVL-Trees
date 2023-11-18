public class TraversalDemo {
    public static void main(String[] args) {
        AVLTree tree = new AVLTree();
        int[] inserts = {10, 5, 20, 3, 8, 15, 30, 1, 4, 7, 9, 12, 18, 25, 35};
        System.out.print("Inserting");
        for (int i : inserts) {
            System.out.print(" -> " + i);
            tree.insert(i);
        }
        System.out.println();

        System.out.println("Printing tree...");
        System.out.println(tree);

        System.out.print("Inorder traversal: ");
        tree.inOrder(tree.root());
        System.out.println();

        System.out.print("Preorder traversal: ");
        tree.preOrder(tree.root());
        System.out.println();

        System.out.print("Postorder traversal: ");
        tree.postOrder(tree.root());
        System.out.println();

    }
}
