package map;

import fpinjava.Function;
import list.List;
import set.Set;
import set.SortedSet;
import set.TreeSet;
import tuple.Tuple;


public class TreeMap<K,V> implements Map<K,V> {
	private final SortedSet<Entry<K,V>> set;

	@SuppressWarnings("unchecked")
	private TreeMap(){
		this (TreeSet.empty());
	}

	private TreeMap(SortedSet<Entry<K,V>> s) {
		set = s;
	}

	public static <K,V> Map<K,V> empty() {
		return new TreeMap<>();
	}

	public static <K,V> Map<K,V> fromList(List<Tuple<K,V>> list) {
		return new TreeMap<>();
	}

	public static Map<String,Integer> wordMap(String s){
		return new TreeMap<>();
	}

	public boolean isEqualTo(Map<K, V> o){
		return false;
	}

	public Map<K,V> insert(K key, V value){
		return new TreeMap<>();
	}

	public Map<K, V> insertWith(Function<V, Function<V, V>> f, K key, V value) {
		return new TreeMap<>();
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
        return null;
    }

    @Override
    public Set<Entry<K, V>> entrySet() {
        return null;
    }

}
