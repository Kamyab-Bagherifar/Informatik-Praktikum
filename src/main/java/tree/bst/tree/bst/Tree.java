package tree.bst.tree.bst;


import list.List;

public abstract class Tree<A extends Comparable<A>> {
    @SuppressWarnings("rawtypes")
    private final static Tree EMPTY = new Empty();

    public abstract A value();

    abstract Tree<A> left();

    abstract Tree<A> right();

    public abstract Tree<A> insert(A a);

    public abstract boolean member(A a);

    public abstract int size();

    public abstract int height();

    public abstract Tree<A> remove(A a);

    protected abstract Tree<A> removeMerge(Tree<A> ta);

    protected abstract boolean isEmpty();

    public abstract A findEq(A x);

    public abstract A findMin();

    public abstract A findMax();

    public abstract List<A> preorder();

    public abstract List<A> postorder();

    public abstract List<A> inorder();

    public abstract List<A> levelorder();

    public abstract int sizeLeaf();

    public abstract int sizeInner();

    public abstract int sizeHalf();

    public abstract int sizeFull();

    public abstract int sizeEmpty();


    private static class Empty<A extends Comparable<A>> extends Tree<A> {
        @Override
        public A value() {
            throw new IllegalStateException("value() called on empty");
        }

        Tree<A> left() {
            throw new IllegalStateException("left() called on empty");
        }

        @Override
        Tree<A> right() {
            throw new IllegalStateException("right() called on empty");
        }

        @Override
        public Tree<A> insert(A a) {
            return new T<>(empty(), a, empty());
        }

        @Override
        public boolean member(A a) {
            return false;
        }

        @Override
        public int size() {
            return 0;
        }

        @Override
        public int height() {
            return -1;
        }

        @Override
        public Tree<A> remove(A a) {
            throw new IllegalStateException("remove() called on empty");
        }

        @Override
        protected Tree<A> removeMerge(Tree<A> ta) {
            return ta;
        }

        @Override
        protected boolean isEmpty() {
            return true;
        }

        @Override
        public A findEq(A x) {
            throw new IllegalStateException("findEq called on empty");
        }

        @Override
        public A findMin() {
            throw new IllegalStateException("findMin called on empty");
        }

        @Override
        public A findMax() {
            throw new IllegalStateException("findMax called on empty");
        }

        @Override
        public List<A> preorder() {
            return List.list();
        }

        @Override
        public List<A> postorder() {
            return List.list();
        }

        @Override
        public List<A> inorder() {
            return List.list();
        }

        @Override
        public List<A> levelorder() {
            return List.list();
        }

        @Override
        public int sizeLeaf() {
            return 0;
        }

        @Override
        public int sizeInner() {
            return 0;
        }

        @Override
        public int sizeHalf() {
            return 0;
        }

        @Override
        public int sizeFull() {
            return 0;
        }

        @Override
        public int sizeEmpty() {
            return 1;
        }

        @Override
        public String toString() {
            return "E";
        }
    }

    private static class T<A extends Comparable<A>> extends Tree<A> {
        private final Tree<A> left;
        private final Tree<A> right;
        private final A value;

        private T(Tree<A> left, A value, Tree<A> right) {
            this.left = left;
            this.right = right;
            this.value = value;
        }

        @Override
        public A value() {
            return value;
        }

        @Override
        Tree<A> left() {
            return left;
        }

        @Override
        Tree<A> right() {
            return right;
        }

        @Override
        public Tree<A> insert(A a) {
            return a.compareTo(this.value) < 0
                    ? new T<>(left.insert(a), this.value, right)
                    : a.compareTo(this.value) > 0
                    ? new T<>(left, this.value, right.insert(a))
                    : new T<>(this.left, a, this.right);
        }

        @Override
        public boolean member(A a) {
            return a.compareTo(this.value) < 0
                    ? left.member(a)
                    : a.compareTo(this.value) <= 0 || right.member(a);
        }

        @Override
        public int size() {
            return 1 + left.size() + right.size();
        }

        @Override
        public int height() {
            return 1 + Math.max(left.height(), right.height());
        }

        @Override
        public Tree<A> remove(A a) {
            if (a.compareTo(this.value) < 0) {
                return new T<>(left.remove(a), value, right);
            } else if (a.compareTo(this.value) > 0) {
                return new T<>(left, value, right.remove(a));
            } else {
                return left.removeMerge(right);
            }
        }

        @Override
        protected Tree<A> removeMerge(Tree<A> ta) {
            if (ta.isEmpty()) {
                return this;
            }
            if (ta.value().compareTo(value) < 0) {
                return new T<>(left.removeMerge(ta), value, right);
            } else if (ta.value().compareTo(value) > 0) {
                return new T<>(left, value, right.removeMerge(ta));
            }
            throw new IllegalStateException("We shouldn't be here");
        }

        @Override
        protected boolean isEmpty() {
            return false;
        }

        @Override
        public A findEq(A x) {
            return x.compareTo(this.value) < 0
                    ? left.findEq(x)
                    : x.compareTo(this.value) > 0
                    ? right.findEq(x)
                    : x.compareTo(this.value) == 0
                    ? x
                    : null;
        }

        @Override
        public A findMin() {
            return this.left.isEmpty()
                    ? this.value
                    : this.left.findMin();
        }

        @Override
        public A findMax() {
            return this.right.isEmpty()
                    ? this.value
                    : this.right.findMax();
        }

        @Override
        public List<A> preorder() {
            return List.append(List.list(value), List.append(left.preorder(), right.preorder()));
        }

        @Override
        public List<A> postorder() {
            return List.append(List.append(left.postorder(), right.postorder()), List.list(value));
        }

        @Override
        public List<A> inorder() {
            return List.append(List.append(left.inorder(), List.list(value)), right.inorder());
        }
        //O(n)

        @Override
        public List<A> levelorder() {
            if (left.isEmpty() && right.isEmpty()) return List.list(value);
            if (left.isEmpty()) return List.append(List.list(value), right.levelorder());
            if (right.isEmpty()) return List.append(List.list(value), left.levelorder());
            return List.append(List.list(value), List.append(left.levelorder(), right.levelorder()));
            //ich hab im Net geguckt, dass einigen Queue benutzt haben. ich hab zu erst versucht


            //Erste Immpletierung
            /*if(left.isEmpty() && right.isEmpty()) return List.append(List.append(List.list(value),left.levelorder()),right.levelorder());
            if(left.isEmpty()) return List.append(List.append(List.list(value, right.value()),left.levelorder()),right.levelorder());
            if(right.isEmpty()) return List.append(List.append(List.list(value, left.value()),left.levelorder()),right.levelorder());
            return List.append(List.append(List.list(value, left.value(), right.value()),left.levelorder()),right.levelorder());*/

        }

        @Override
        public int sizeLeaf() {
            if (left.isEmpty() && right.isEmpty()) {
                return 1;
            }
            else if (left.isEmpty()) {
                return right.sizeLeaf();
            }
            else if (right.isEmpty()) {
                return left.sizeLeaf();
            } else {
                return left.sizeLeaf() + right.sizeLeaf();
            }


        }
        //O(n)

        @Override
        public int sizeInner() {
            if (left.isEmpty() && right.isEmpty()){
                return 0;
            }
            else if (left.isEmpty()){
                return 1 + right.sizeInner();
            }
            else if (right.isEmpty()){
                return 1 + left.sizeInner();
            }else {
                return left.sizeInner() + right.sizeInner() + 1;
            }

        }
        //O(n)

        @Override
        public int sizeHalf() {
            if (left.isEmpty() && right.isEmpty()){
                return 0;
            }
            else if (left.isEmpty()){
                return 1 + right.sizeHalf();
            }
            else if (right.isEmpty()){
                return 1 + left.sizeHalf();
            }else{
                return left.sizeHalf() + right.sizeHalf();
            }

        }
        //O(n)

        @Override
        public int sizeFull() {
            if (left.isEmpty() && right.isEmpty()){
                return 0;
            }
            else if (left.isEmpty()){
                return right.sizeFull();
            }
            else if (right.isEmpty()){
                return left.sizeFull();
            }else{
                return left.sizeFull() + right.sizeFull() + 1;
            }

        }
        //O(n)

        @Override
        public int sizeEmpty() {
            return this.sizeLeaf();
        }
        //O(n)


        @Override
        public String toString() {
            return String.format("(T %s %s %s)", left, value, right);
        }
    }


    @SuppressWarnings("unchecked")
    public static <A extends Comparable<A>> Tree<A> empty() {
        return EMPTY;
    }

    public static <A extends Comparable<A>> Tree<A> tree(List<A> list) {
        return list.foldl(t -> t::insert, empty());
    }

    @SafeVarargs
    public static <A extends Comparable<A>> Tree<A> tree(A... as) {
        return tree(List.list(as));
    }




}

