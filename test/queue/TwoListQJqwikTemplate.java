package queue;

public class TwoListQJqwikTemplate extends ADTQueueJqwikTemplate {

	@Override
	protected <A> Queue<A> empty(){
		return TwoListQ.empty();
	}
}
