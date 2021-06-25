package set;

import fpinjava.Result;

public interface SortedSet<A extends Comparable<A>> extends Set<A>{

    A findMax();
    A findMin();




}
