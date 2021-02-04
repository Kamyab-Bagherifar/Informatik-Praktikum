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
		return new ListMap<>();
	}

	public static Map<String, Integer> wordMap(String s) {
		return new ListMap<>();
	}

	public boolean isEqualTo(Map<K, V> o) {
		return false;
	}

	public Map<K, V> insert(K key, V value) {
		return new ListMap<>();
	}

	public Map<K, V> insertWith(Function<V, Function<V, V>> f, K key, V value) {
		return new ListMap<>();
	}

	public boolean isEmpty() {
		return false;
	}
}