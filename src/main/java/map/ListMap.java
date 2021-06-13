package map;

import fpinjava.Function;
import fpinjava.Result;
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
    //WorstCase: O(2n)(insert) * O(n)(foldl)
    //BestCase: O(n)(insert) * O(1)(foldl)

    public static <K, V> Map<K, V> fromSet(Set<Entry<K, V>> s) {
        return new ListMap<>(s);
    }
    //O(1)


    public Set<Entry<K, V>> entrySet() {
        return this.set;
    }
    // O(1)

    @Override
    public <K1, V1> Map<K1, V1> fromEntrySet(Set<Entry<K1, V1>> s) {
        return new ListMap<>(s);
    }
    //O(1)


    public boolean isEqualTo(Map<K, V> o) {

        return this.size() == o.size() && this.isSubmapOf(o);

    }
    // WorstCase: O(2n)(Size) + O(1)(EntrySet) + O(n)(all) * O(n)(Equals) * O(2n)(lookup)
    //BestCase: O(2)(Size)  + O(1)(EntrySet) + O(1)(all) * O(1)(Equals) * O(1)(lookup)

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
    //WorstCase: O(1)(toList) + O(n)(map)
    //BestCase: O(1)(toList) + O(1)(Map, wenn der Set leer ist)


    @Override
    public List<K> keys() {
        return set.toList().map(x -> x.key);
    }
    //WorstCase: O(1)(toList) + O(n)(map)
    //BestCase: O(1)(toList) + O(1)(Map, wenn der Set leer ist)

    @Override
    public Set<K> keysSet() {
        return ListSet.fromList(this.keys());
    }
    //WorstCase: O(2n)(insert) * O(n)(foldl) + O(1)(toList) + O(n)(map)(keys)
    //BestCase: O(n)(insert) * O(1)(foldl) +  O(1)(toList) + O(1)(Map, wenn der Set leer ist)(keys)

    @Override
    public List<V> elems() {
        return set.toList().map(x -> x.value);
    }
    //WorstCase: O(1)(toList) + O(n)(map)
    //BestCase: O(1)(toList) + O(1)(Map, wenn der Set leer ist)

    @Override
    public boolean all(Function<K, Function<V, Boolean>> p) {
        return set.all(x -> p.apply(x.key).apply(x.value));
    }
    //worstCase: O(n)
    //BestCase: O(1)

    @Override
    public boolean allKeys(Function<K, Boolean> p) {
        return set.all(x -> p.apply(x.key));
    }
    //worstCase: O(n)
    //BestCase: O(1)

    @Override
    public boolean isSubmapOf(Map<K, V> m) {
        return this.entrySet().all(e -> Result.success(e.value).equals(m.lookUp(e.key)));
    }
    // WorstCase: O(1)(EntrySet) + O(n)(all) * O(n)(Equals) * O(2n)(lookup)
    //BestCase: O(1)(EntrySet) + O(1)(all) * O(1)(Equals) * O(1)(lookup)



    public boolean equals(Object o){
        return this == o || o instanceof Map && this.isEqualTo((Map<K, V>)  o);
    }

    @Override
    public <V2> V2 foldr(Function<V, Function<V2, V2>> f, V2 s) {
       return ListSet.foldr(x-> y -> f.apply(x.value).apply(y),s,this.entrySet());
    }
    //WorstCase: O(n)(foldr) + O(1) (EntrySet)
    //BestCase: O(1)(foldr) + O(1) (EntrySet)

    @Override
    public <V2> V2 foldl(Function<V2, Function<V, V2>> f, V2 s) {
        return ListSet.foldl(x-> y -> f.apply(x).apply(y.value), s, this.entrySet());
    }
    //WorstCase: O(n)(foldr) + O(1) (EntrySet)
    //BestCase: O(1)(foldr) + O(1) (EntrySet)


    @Override
    public Map<K, V> filter(Function<V, Boolean> p) {
        return fromSet(ListSet.filter(x-> p.apply(x.value), this.entrySet()));
    }
    //WorstCase: O(1) + O(n)(filter) + O(1)(entrySet)
    //BestCase: O(1) + O(1)(filter) + O(1)(entrySet)

    @Override
    public <V2> Map<K, V2> map(Function<V, V2> f) {
        return fromSet(ListSet.map(x-> new Entry<>(x.key, f.apply(x.value)), this.entrySet()));
    }
    //WorstCase: O(1) + O(n)(map) + O(1)(entrySet)
    //BestCase: O(1) + O(1)(map) + O(1)(entrySet)

    @Override
    public Map<K, V> union(Map<K, V> m) {
        return fromSet(m.entrySet().union(this.entrySet()));
    }
    //WorstCase: O(1)  + O(1)(entrySet) + O(n * n * 2n)(Union) + O(1)(entrySet)
    //BestCase: O(1)  + O(1)(entrySet) + O(1)(Union) + O(1)(entrySet)

    @Override
    public Map<K, V> intersection(Map<K, V> m) {
        return fromSet(this.entrySet().intersection(m.entrySet()));
    }
    //WorstCase: O(1)  + O(1)(entrySet) + O(n * 2n + 1 + n * n )(intersection) + O(1)(entrySet)
    //BestCase: O(1)  + O(1)(entrySet) + O(4)(intersection) + O(1)(entrySet)

    @Override
    public Result <V> lookUp(K key) {
        return  (V) this.entrySet().findEq(new Entry(key, null)) == null ?  Result.empty() : Result.success((V) this.entrySet().findEq(new Entry(key, null)).value);
    }

    @Override
    public Map<K, V> delete(K key) {
        return fromSet(this.entrySet().delete(new Entry<>(key, null)));
    }

    public static Map<String,Integer> wordMap(String s){
        return List.words(s).foldr(e-> mr -> mr.insertWith(x-> y -> x + y, e, 1), empty());

    }





    @Override
    public String toString() {
        return set.toString();
    }
}