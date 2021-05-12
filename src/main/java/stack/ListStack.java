package stack;

import list.List;
import tuple.Tuple;

import static list.List.append;
import static list.List.list;
import static tuple.Tuple.tuple;

public class ListStack<A> implements Stack<A> {
    final private List<A> list;

    private ListStack() {
        this.list = List.list();
    }

    public ListStack(List<A> list) {
        this.list = list;
    }

    public static <A> Stack<A> empty() {
        return new ListStack<>();
    }

    public static <A> Stack<A> stack() {
        return new ListStack<>();
    }

    public static <A> Stack<A> stack(List<A> l) {
        return new ListStack<>(l);
    }

    public static <A> Stack<A> stack(A... z) {
        return new ListStack<>(list(z));
    }


    @Override
    public boolean isEmpty() {
        return list.isEmpty();
    }

    @Override
    public Stack<A> push(A e) {
        return new ListStack<>(append(list, list(e)));
    }

    @Override
    public Stack<A> pushAll(List<A> xs) {
        return xs.isEmpty() ? this : this.push(xs.head()).pushAll(xs.tail());

    }

    @Override
    public Stack<A> pushAll(A... es) {

        return pushAll(list(es));
    }

    @Override
    public Stack<A> pop() {
        if (this.isEmpty()) {
            throw new IllegalStateException("pop called on empty stack");
        } else {
            return stack(this.toList().init());
        }

    }

    @Override
    public A top() {
        return this.toList().last();
    }

    @Override
    public Tuple<A, Stack<A>> popTop() {
        return new Tuple<>(top(), pop());
    }

    @Override
    public Tuple<List<A>, Stack<A>> popTopAll() {
        return new Tuple<>(list.reverse(), stack(list));
    }

    @Override
    public List<A> toList() {

        return list;
    }

    @Override
    public boolean isEqualTo(Stack<A> s) {
        return list.isEqualTo(s.toList());
    }

    @Override
    public int size() {
        return list.length();
    }


    @Override
    public String toString() {
        return list.toString();
    }

    public boolean equals(Object o) {
        if (!(o instanceof Stack)) return false;
        //Stack<A> test = (Stack) o;
        return this.isEqualTo((Stack) o);

    }
}
