package set;

import fpinjava.Function;
import fpinjava.Result;
import list.List;


public interface Set<A>  {

  boolean isEqualTo(Set<A> other);

    Set<A> insert(A e);
    Set<A> delete(A e);
    boolean member(A e);
    int size();
    boolean isEmpty();
    A findEq(A e);
    List<A> toList();
    boolean any(Function<A, Boolean> p);
    boolean all(Function<A, Boolean> p);
    boolean isSubsetOf(Set<A> s);
    boolean disjoint(Set<A> s);
    Set<A> union(Set<A> s);
    Set<A> intersection(Set<A> s);
    <B> B foldr(Function<A, Function<B,B>> f, B s, Set<A> xs);
    <B> B foldl(Function<B, Function<A,B>> f, B s, Set<A> xs);
    Set<A> filter(Function<A, Boolean> f, Set<A> xs);
    <B> Set<B> map(Function<A, B> f, Set<A> xs);
    Result<A> lookupEq(A x);






}
