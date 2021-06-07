package map;

import fpinjava.Function;
import list.List;
import set.ListSet;
import set.Set;
import tuple.Tuple;


public class ListMap<K,V> implements Map<K,V> {
	private final Set<Entry<K, V>> set;

	private ListMap() {
		this(ListSet.empty());
	}
	private ListMap(Set<Entry<K, V>> s) {
		set = s;
	}

	public static <K, V> Map<K, V> empty() {
		return new ListMap<>();
	}

	public static <K, V> Map<K, V> fromList(List<Tuple<K, V>> list) {

	    return list.foldl(x-> y -> x.insert(y.fst, y.snd), empty());
	}

    public static <K, V> Map<K, V> fromSet(Set<Entry<K, V>> s) {
        return new ListMap<>(s);
    }

	public static Map<String, Integer> wordMap(String s) {
		return new ListMap<>();
	}

	public boolean isEqualTo(Map<K, V> o) {

	    return false;
	}

	public Map<K, V> insert(K key, V value) {


	    return fromSet(set.insert(new Entry<>(key,value)));
	}



    public Map<K, V> insertWith(Function<V, Function<V, V>> f, K key, V value) {
	    if(this.get(key) == null) return this.insert(key,value);
	    return this.insert(key,f.apply(value).apply(this.get(key)));

	}


	public boolean isEmpty() {
		return false;
	}

    @Override
    public int size() {
        return 0;
    }

    @Override
    public boolean member(K key) {
        return false;
    }

    @Override
    public V get(K key) {
        Entry<K, V> e = set.findEq(new Entry<>(key, null));
        return e == null ? null : e.value;

	}

    @Override
    public Set<Entry<K, V>> entrySet() {
        return this.set;
    }

    public static void main(String[] args) {
        Map<Character,Integer> m = fromList(List.list(new Tuple('a',1),new Tuple('b',2)));
        System.out.println(m);
        System.out.println((m.insertWith(x->y->x+y, 'c', 3)));
        System.out.println((m.insertWith(x->y->x+y, 'e', 9).isEqualTo(m.insert('e', 9))));


    }

    @Override
    public String toString() {
        return set.toString();
    }
}