package map;

import fpinjava.Function;
import list.List;
import set.ListSet;
import set.Set;
import tuple.Tuple;


public class ListMap<K, V> implements Map<K, V> {
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

        return list.foldl(x -> y -> x.insert(y.fst, y.snd), empty());
    }

    public static <K, V> Map<K, V> fromSet(Set<Entry<K, V>> s) {
        return new ListMap<>(s);
    }

    public static Map<String, Integer> wordMap(String s) {
        return new ListMap<>();
    }

    public Set<Entry<K, V>> entrySet() {
        return this.set;
    }

    @Override
    public <K1, V1> Map<K1, V1> fromEntrySet(Set<Entry<K1, V1>> s) {
        return new ListMap<>(s);
    }

    public boolean isEqualTo(Map<K, V> o) {

        return this.entrySet().isEqualTo(o.entrySet());
    }

    public Map<K, V> insert(K key, V value) {


        return fromSet(set.insert(new Entry<>(key, value)));
    }


    public Map<K, V> insertWith(Function<V, Function<V, V>> f, K key, V value) {
        if (this.get(key) == null) return this.insert(key, value);
        return this.insert(key, f.apply(value).apply(this.get(key)));

    }


    public boolean isEmpty() {
        return this.entrySet().isEmpty();
    }

    @Override
    public int size() {
        return this.entrySet().size();
    }

    @Override
    public boolean member(K key) {
        return this.entrySet().member(new Entry<>(key, null));
    }

    @Override
    public V get(K key) {
        Entry<K, V> e = set.findEq(new Entry<>(key, null));
        return e == null ? null : e.value;

    }


    @Override
    public List<Tuple<K, V>> toList() {
        return set.toList().map(x -> new Tuple<>(x.key, x.value));
    }

    @Override
    public List<K> keys() {
        return set.toList().map(x -> x.key);
    }

    @Override
    public Set<K> keysSet() {
        return ListSet.fromList(this.keys());
    }

    @Override
    public List<V> elems() {
        return set.toList().map(x -> x.value);
    }

    @Override
    public boolean all(Function<K, Function<V, Boolean>> p) {
        return set.all(x -> p.apply(x.key).apply(x.value));
    }

    @Override
    public boolean allKeys(Function<K, Boolean> p) {
        return set.all(x -> p.apply(x.key));
    }

    @Override
    public boolean isSubmapOf(Map<K, V> m) {
        return this.entrySet().isSubsetOf(m.entrySet());
    }
    public boolean equals(Object o){
        return this == o || o instanceof Map && this.isEqualTo((Map<K, V>)  o);
    }

    @Override
    public <V2> V2 foldr(Function<V, Function<V2, V2>> f, V2 s) {
       return ListSet.foldr(x-> y -> f.apply(x.value).apply(y),s,this.entrySet());
    }

    @Override
    public <V2> V2 foldl(Function<V2, Function<V, V2>> f, V2 s) {
        return ListSet.foldl(x-> y -> f.apply(x).apply(y.value), s, this.entrySet());
    }

    @Override
    public Map<K, V> filter(Function<V, Boolean> p) {
        return fromSet(ListSet.filter(x-> p.apply(x.value), this.entrySet()));
    }

    @Override
    public <V2> Map<K, V2> map(Function<V, V2> f) {
        return fromSet(ListSet.map(x-> new Entry<>(x.key, f.apply(x.value)), this.entrySet()));
    }

    @Override
    public Map<K, V> union(Map<K, V> m) {
        return fromSet(this.entrySet().union(m.entrySet()));
    }

    @Override
    public Map<K, V> intersection(Map<K, V> m) {
        return fromSet(this.entrySet().intersection(m.entrySet()));
    }

    /*public static Map<String,Integer> wordMap(String s){

    }*/



    public static void main(String[] args) {
        Map<String, Integer> m = fromList(List.list(new Tuple("a", 1), new Tuple("b", 2)));
        Map<String, Integer> m1 = fromList(List.list(new Tuple("b", 2), new Tuple("a", 1), new Tuple<>("c",10)));
        //System.out.println(m.filter(x-> x > 1));
        System.out.println(m.map(x-> x +  1));


    }

    @Override
    public String toString() {
        return set.toString();
    }
}