package map;

import list.List;
import tuple.Tuple;


public class ListMapJqwikTest extends ADTMapJqwikTest {

	@Override
	protected <K extends Comparable<K>,V> Map<K,V> empty() {
		return ListMap.empty();
	}

	@Override
  protected <K extends Comparable<K>,V> Map<K,V> fromList(List<Tuple<K,V>> list) {
		return ListMap.fromList(list);
	}
	
	@Override
  protected Map<String,Integer> wordFreq(String s) {
		return ListMap.wordMap(s);
	}
	
}
