package queue;

public class OneListQJqwikTemplate extends ADTQueueJqwikTemplate {

	@Override
	protected <A> Queue<A> empty(){
		return OneListQ.empty();
	}
}
