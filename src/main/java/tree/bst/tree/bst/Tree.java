package tree.bst.tree.bst;


public class Tree<A extends Comparable<A>> {
    private final Tree<A> left;
    private final Tree<A> right;
    private final A value;

    private Tree(Tree<A> left, A value, Tree<A> right) {
        this.left = left;
        this.right = right;
        this.value = value;
    }

    public A value() {
        return value;
    }

    public Tree<A> left() {
        return left;
    }

    public Tree<A> right() {
        return right;
    }

    public String toString() {
        return String.format("(T %s %s %s)", left, value, right);
    }
}


