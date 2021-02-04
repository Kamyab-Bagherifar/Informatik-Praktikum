package queue;

import list.List;
import tuple.Tuple;

public interface Queue<A> {
	boolean isEqualTo(Queue<A> q);
	Queue<A> enQueueAll(List<A> es);
	List<A> toList();
}
