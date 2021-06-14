package map;

import com.sun.jdi.Value;

import java.security.Key;
import java.util.Objects;

public class Entry<K,V> implements Comparable<Entry<K,V>> {
public final K key;
public final V value;

public Entry(K k, V v){
    key = k;
    value = v;
}
    public Entry(K k){
        key = k;
        value = null;
    }


    @Override
    public String toString() {
        return "(" + key + "," + value + ")";
    }

    @Override
    public boolean equals(Object o) {
    if(o == null) return false;
    if(!(o instanceof Entry)) return false;
    return this.key.equals(((Entry<?, ?>) o).key);
       }



   @Override
	public int compareTo(Entry<K,V> other) {
		return 0;
	}


}