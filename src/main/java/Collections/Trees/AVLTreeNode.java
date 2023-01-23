package Collections.Trees;

public class AVLTreeNode<T> extends BinaryTreeNode<T> {
    private int height;
    private AVLTreeNode<T> parent;

    public AVLTreeNode(T element) {
        super(element);
        this.height = 0;
        this.parent = null;
    }

    public AVLTreeNode(T element, AVLTreeNode<T> left, AVLTreeNode<T> right) {
        super(element, left, right);
        this.height = 0;
        this.parent = null;
    }

    public AVLTreeNode(T element, AVLTreeNode<T> left, AVLTreeNode<T> right, int height) {
        super(element, left, right);
        this.height = height;
        this.parent = null;
    }

    public AVLTreeNode(T element, AVLTreeNode<T> left, AVLTreeNode<T> right, int height, AVLTreeNode<T> parent) {
        super(element, left, right);
        this.height = height;
        this.parent = parent;
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public AVLTreeNode<T> getParent() {
        return parent;
    }

    public void setParent(AVLTreeNode<T> parent) {
        this.parent = parent;
    }
}
