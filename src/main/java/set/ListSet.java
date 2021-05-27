package set;

import fpinjava.Function;
import list.List;
import stack.Stack;

import static list.List.*;


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
        return list.foldl((x -> y -> x.insert(y)), empty());
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
        return set.elem(e) ? new ListSet<>(set.map(x -> x == e ? e : x)) : new ListSet<>(set.cons(e));
        //e ist drinne
        //BestCase O(1) + O(n) (elem + map)
        //WorstCase O(n) + O(n) (elem + map)
        //e ist nicht drinne dann O(n) + O(1) (elem + cons)

    }

    @Override
    public Set<A> delete(A e) {
        return fromList(set.delete(e));
        //BestCase (O(n) * O(n+1)) + O(1) (fromList + delete)
        //WorstCase (O(n) * O(2n) + O(n) (fromList + delete)
    }

    @Override
    public boolean member(A e) {
        return set.elem(e);
        //BestCase O(1)
        // WorstCase O(n)
    }

    @Override
    public int size() {

        return set.length();
        //BestCase O(1)
        //WorstCase O(n)
    }

    @Override
    public boolean isEmpty() {
        return set.isEmpty();
        //O(1)
    }

    @Override
    public A findEq(A e) {

        return set.elem(e) ? e : null;
        //BestCase O(1)
        // WorstCase O(n)
    }

    @Override
    public List<A> toList() {
        return set;
        //O(1)
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
        return fromList(words(s));

        //BestCase (O(n) * O(n+1)) + O(1)
        //WorstCase (O(n) * O(2n) + O(n)
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
        // BestCase O(1) wenn es leer ist
        // O(n) + O(n) + O(n)
    }


}
