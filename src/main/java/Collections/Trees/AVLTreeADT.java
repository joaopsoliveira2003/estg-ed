package Collections.Trees;

import Collections.Exceptions.NonComparableElementException;

public interface AVLTreeADT<T> extends BinarySearchTreeADT<T> {

    @Override
    public void addElement(T element) throws NonComparableElementException;

    @Override
    public T removeElement(T element) throws NonComparableElementException;

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
