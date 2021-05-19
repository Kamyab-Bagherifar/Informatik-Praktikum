package set;

import fpinjava.Function;
import list.List;


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
        return set.isEqualTo(other.toList());
    }

    @Override
    public Set<A> insert(A e) {
        return set.elem(e) ? fromList(set.map(x -> x == e ? e: x)) : fromList(set.cons(e));

    }

    @Override
    public Set<A> delete(A e) {
        return fromList(set.delete(e));
    }

    @Override
    public boolean member(A e) {
        return  set.elem(e);
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

    @Override
    public String toString() {
        return set.toString();
    }
}
