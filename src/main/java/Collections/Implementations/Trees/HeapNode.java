package Collections.Implementations.Trees;

public class HeapNode<T> extends BinaryTreeNode<T> {
    protected HeapNode<T> parent;

    public HeapNode(T element) {
        super(element);
        parent = null;
    }

    public HeapNode(T element, HeapNode<T> parent) {
        super(element);
        this.parent = parent;
    }

    public HeapNode<T> getParent() {
        return parent;
    }

    public void setParent(HeapNode<T> parent) {
        this.parent = parent;
    }
}
