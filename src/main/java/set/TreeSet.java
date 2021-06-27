package set;

import fpinjava.Function;
import fpinjava.Result;
import list.List;
import tree.bst.tree.bst.Tree;
import tree.bst.tree.bst.Tree.*;


public class TreeSet<A extends Comparable<A>> implements SortedSet<A> {
    private final Tree<A> tree;

    private TreeSet() {
        tree = Tree.empty();
    }

    private TreeSet(List<A> list) {
        tree = Tree.tree(list);
    }

    public static <A extends Comparable<A>> SortedSet<A> empty() {
        return new TreeSet<>();
    }

    public static <A extends Comparable<A>> SortedSet<A> fromList(List<A> list) {
        return new TreeSet<>(list);
    }

    @SafeVarargs
    public static <A extends Comparable<A>> SortedSet<A> set(A... as) {
        return new TreeSet<>(List.list(as));
    }

    public static <A extends Comparable<A>> SortedSet<A> fromSet(Set<A> s){
        return new TreeSet<>(s.toList());
    }

    public static SortedSet<String> wordSet(String s) {
        return fromList(List.words(s));
    }

    public boolean isEqualTo(Set<A> other) {
        return tree.inorder().toSet().isEqualTo(other);
    }

    @Override
    public Set<A> insert(A e) {
        return tree.insert(e).inorder().toSet();
    } //O(n)

    @Override
    public Set<A> delete(A e) {
        return tree.delete(e).inorder().toSet();
    }//O(n)

    @Override
    public boolean member(A e) {
        return tree.member(e);
    }//O(n)

    @Override
    public int size() {
        return tree.size();
    }//O(n)

    @Override
    public boolean isEmpty() {
        return tree.isEmpty();
    }//O(1)

    @Override
    public A findEq(A e) {
        return tree.findEq(e);
    }//O(n)

    @Override
    public List<A> toList() {
        return tree.inorder();
    }//O(n)

    @Override
    public boolean any(Function<A, Boolean> p) {
        return tree.inorder().any(p);
    }//O(2n)

    @Override
    public boolean all(Function<A, Boolean> p) {
        return tree.inorder().all(p);
    }//O(2n)

    @Override
    public boolean isSubsetOf(Set<A> s) {
        return tree.inorder().toSet().isSubsetOf(s);
    }//O(2n)

    @Override
    public boolean disjoint(Set<A> s) {
        return tree.inorder().toSet().disjoint(s);
    }//O(2n)

    @Override
    public Set<A> union(Set<A> s) {
        return tree.inorder().toSet().union(s);
    }//O(2n)

    @Override
    public Set<A> intersection(Set<A> s) {
        return tree.inorder().toSet().intersection(s);
    }//O(2n)

    @Override
    public <B> B foldr(Function<A, Function<B, B>> f, B s, Set<A> xs) {
        return tree.inorder().toSet().foldr(f, s, xs);
    }//O(2n)

    @Override
    public <B> B foldl(Function<B, Function<A, B>> f, B s, Set<A> xs) {
        return tree.inorder().toSet().foldl(f, s, xs);
    }//O(2n)

    @Override
    public Set<A> filter(Function<A, Boolean> f, Set<A> xs) {
        return tree.inorder().toSet().filter(f, xs);
    }//O(2n)

    @Override
    public <B> Set<B> map(Function<A, B> f, Set<A> xs) {
        return tree.inorder().toSet().map(f, xs);
    }//O(2n)

    @Override
    public Result<A> lookupEq(A x) {
        return findEq(x) == null ? Result.empty(): Result.success(x);
    }


    @Override
    public A findMax() {
        return tree.findMax();
    }

    @Override
    public A findMin() {
        return tree.findMin();
    }

    @Override
    public Result<A> lookupMax() {
        return tree.lookupMax();
    }

    @Override
    public Result<A> lookupMin() {
        return tree.lookupMin();
    }


    @Override
    public String toString() {
        return tree.inorder().toSet().toString();
    }

    public boolean equals(Object o) {
        return (o instanceof Set) && this.isEqualTo((Set<A>) o);
    }









}
