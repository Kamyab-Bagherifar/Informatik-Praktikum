package set;

import list.List;
import net.jqwik.api.ForAll;
import net.jqwik.api.Property;
import net.jqwik.api.constraints.IntRange;

public abstract class ADTSortedSetJqwikTemplate extends ADTSetJqwikTemplate {

	@Override
	protected abstract <A extends Comparable<A>> SortedSet<A> empty();

	@Override
	protected abstract <A extends Comparable<A>> SortedSet<A> fromList(List<A> list);

	@Override
	protected abstract  <A extends Comparable<A>> SortedSet<A> set(A... list);

	// ∀s: SortedSet<A> :  any(x → x==findMin(s),s)   = true, falls s nicht leer
	@Property
	<A extends Comparable<A>> boolean findMin_any(@ForAll("sets") SortedSet<A> s){
		return  false;
	}

	// ∀s: SortedSet<A> :  all(x → x>=findMin(s),s)   = true, falls s nicht leer
	@Property
	<A extends Comparable<A>> boolean findMin_all(@ForAll("sets") SortedSet<A> s){
		return  false;
	}

	// formulieren Sie ein Axiom analog zu findMin_any!
	@Property
	<A extends Comparable<A>> boolean findMax_any(@ForAll("sets") SortedSet<A> s){
		return  false;
	}

	// formulieren Sie ein Axiom analog zu findMin_all!
	@Property
	<A extends Comparable<A>> boolean findMax_all(@ForAll("sets") SortedSet<A> s){
		return  false;
	}

	// ∀ s: SortedSet, ∀ start,end: Integer :	findMin(fromList([start..end]))  = start, falls end >= start
	@Property
	boolean findMin_range(@ForAll @IntRange(min=1,max=100)  int start, @ForAll @IntRange(min=100,max=1000) int end) {
		return false;
	}

	// formulieren Sie ein Axiom analog zu findMin_range
	@Property
	boolean findMax_range(@ForAll @IntRange(min=1,max=100)  int start, @ForAll @IntRange(min=100,max=1000) int end) {
		return false;
	}

	// ∀s: SortedSet<A> : any(x → exists(y->x==y,lookupMin(s)),s)   = true, falls s nicht leer
	@Property
	<A extends Comparable<A>> boolean lookupMin_any(@ForAll("sets") SortedSet<A> s){
		return  false; // Methode exists der Klasse Result hilft hier
	}

	// ∀s: SortedSet<A> : all(x → exists(y->x>=y,lookupMin(s)),s)   = true, falls s nicht leer
	@Property
	<A extends Comparable<A>> boolean lookupMin_all(@ForAll("sets") SortedSet<A> s){
		return  false; // Methode exists der Klasse Result hilft hier
	}

	// formulieren Sie ein Axiom analog zu lookupMin_any!
	@Property
	<A extends Comparable<A>> boolean lookupMax_any(@ForAll("sets") SortedSet<A> s){
		return  false; // Methode exists der Klasse Result hilft hier
	}

	// formulieren Sie ein Axiom analog zu lookupMin_all
	@Property
	<A extends Comparable<A>> boolean lookupMax_all(@ForAll("sets") SortedSet<A> s){
		return  false; // Methode exists der Klasse Result hilft hier
	}

	//∀l:List : findMin(fromList(l)) = minimum(l), falls l nicht leer
	@Property
	public <A extends Comparable<A>> boolean findMin_minimum(@ForAll("lists") List<A> l) {
		return false;
	}

  // formulieren Sie ein Axiom analog zu findMin_minimum
  @Property
	public <A extends Comparable<A>> boolean findMax_maximum(@ForAll("lists") List<A> l) {
		return false;
	}
}
