package set;

import fpinjava.Function;
import list.List;
import stack.Stack;


public class ListSet<A> implements Set<A> {
    private List<A> set;

    //Kontruktor
    private ListSet() {
        set = List.list();
    }

    private ListSet(List<A> l) {
        set = l;
    }

    // Fabrik Methoden
    public static <A> Set<A> empty() {
        return new ListSet<>();
    }

    public static <A> Set<A> fromList(List<A> list) {
        return new ListSet<>(list);
    }

    @SafeVarargs
    public static <A> Set<A> set(A... as) {
        return new ListSet<>(List.list(as));
    }

    public boolean isEqualTo(Set<A> other) {
        return this.isSubsetOf(other) && other.isSubsetOf(this);
    }

    @Override
    public Set<A> insert(A e) {
        return set.elem(e) ? fromList(set.map(x -> x == e ? e : x)) : fromList(set.cons(e));

    }

    @Override
    public Set<A> delete(A e) {
        return fromList(set.delete(e));
    }

    @Override
    public boolean member(A e) {
        return set.elem(e);
    }

    @Override
    public int size() {
        return set.length();
    }

    @Override
    public boolean isEmpty() {
        return set.isEmpty();
    }

    @Override
    public A findEq(A e) {
        return set.elem(e) ? e : null;
    }

    @Override
    public List<A> toList() {
        return set;
    }

    //Aufagebe D Part I
    @Override
    public boolean any(Function<A, Boolean> p) {
        return set.any(p);
    }

    @Override
    public boolean all(Function<A, Boolean> p) {
        return set.all(p);
    }


    //Aufgabe D Part II
    @Override
    public boolean isSubsetOf(Set<A> s) {
        return this.all(x -> s.member(x));
    }

    @Override
    public boolean disjoint(Set<A> s) {
        return !s.isEmpty() && !this.isEmpty() && this.all(x -> !s.member(x));
    }


    //Aufgabe G
    @Override
    public Set<A> union(Set<A> s) {
        return s.isEmpty() ? this : this.insert(s.toList().head()).union(fromList(s.toList().tail()));
    }

    @Override
    public Set<A> intersection(Set<A> s) {
        return fromList(this.toList().filter(x -> s.member(x)));
    }



    //Aufgabe H
    public boolean equals(Object o){
        return this == o || o instanceof Set && this.isEqualTo((Set<A>) o);
    }


    //Aufgabe I
    public static Set<String> wordSet(String s){
        Set<String> set = empty();
        fromList(List.words(s).filter(x-> this))
        return set;

    }




    //Aufgabe F
    public static <A, B> B foldr(Function<A, Function<B, B>> f, B s, Set<A> xs) {
        return xs.toList().foldr(f, s);
    }

    public static <A, B> B foldl(Function<B, Function<A, B>> f, B s, Set<A> xs) {
        return xs.toList().foldl(f, s);
    }

    public static <A> Set<A> filter(Function<A, Boolean> f, Set<A> xs) {
        return fromList(xs.toList().filter(f));
    }

    public static <A, B> Set<B> map(Function<A, B> f, Set<A> xs) {
        return fromList(xs.toList().map(f));
    }




    @Override
    public String toString() {

        return set.isEmpty() ? "{}" : "{" + set.toString().substring(1, set.toString().length() - 6) + "}";
    }


}
