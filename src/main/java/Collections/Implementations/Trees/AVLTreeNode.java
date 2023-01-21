package Collections.Implementations.Trees;

public class AVLTreeNode<T> extends BinaryTreeNode<T> {
    private int height;

    public AVLTreeNode(T element) {
        super(element);
        this.height = 0;
    }

    public AVLTreeNode(T element, AVLTreeNode<T> left, AVLTreeNode<T> right) {
        super(element, left, right);
        this.height = 0;
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }
}
