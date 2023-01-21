package Collections.Implementations.Trees;

public class AVLTree<T> extends LinkedBinarySearchTree<T> implements AVLTreeADT<T> {

    @Override
    public boolean isBalanced() {
        return false;
    }

    @Override
    public boolean isFull() {
        return false;
    }
}
