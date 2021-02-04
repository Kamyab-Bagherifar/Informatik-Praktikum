package stack;

import list.List;
import tuple.Tuple;

import static list.List.list;
import static tuple.Tuple.tuple;

public class ListStack<A> implements Stack<A>{
	final private List<A> list;

	private ListStack(){
		this.list = List.list();
	}

	private ListStack(List<A> list) {
		this.list = list;
	}

	public static <A> Stack<A> empty() {
		return new ListStack<>();
	}

	@Override
	public Stack<A> pushAll(List<A> xs) {
		return new ListStack();
	}

	@Override
	public List<A> toList() {
		return list();
	}


}
