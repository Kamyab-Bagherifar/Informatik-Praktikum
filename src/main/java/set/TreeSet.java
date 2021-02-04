package set;

import fpinjava.Function;
import list.List;


public class TreeSet<A extends Comparable<A>> implements SortedSet<A> {
	

	private TreeSet(){
	}

	public static <A extends Comparable<A>> SortedSet<A> empty() {
		return new TreeSet<>();
	}

  public static <A extends Comparable<A>> SortedSet<A> fromList(List<A> list) {
		return new TreeSet<>();
  }

  @SafeVarargs
  public static <A extends Comparable<A>> SortedSet<A> set(A... as) {
		return new TreeSet<>();
  }

	public boolean isEqualTo(Set<A> other){
		return false;
	}

}
