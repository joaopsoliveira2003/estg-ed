package Collections.Implementations.Trees;

public interface AVLTreeADT<T> extends BinarySearchTreeADT<T> {

    /**
     * Returns true if this tree is balanced.
     *
     * @return true if this tree is balanced
     */
    public boolean isBalanced();

    /**
     * Returns true if this tree is full.
     *
     * @return true if this tree is full
     */
    public boolean isFull();
}
