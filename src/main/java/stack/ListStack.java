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

    private ListStack(List<A> list) {
        this.list = list;
    }

    public static <A> Stack<A> empty() {
        return new ListStack<>();
    }

    /*public  <A> Stack<A> stack() {
        return new ListStack<>();
    }

    public  <A> Stack<A> stack(List<A> l) {
        return new ListStack<>(l);
    }

    public  <A> Stack<A> stack(A... z) {
        return new ListStack<>(list(z));
    }*/


    @Override
    public boolean isEmpty() {
        return list.isEmpty();
    }
    // O(1)

    @Override
    public Stack<A> push(A e) {
        return new ListStack<>(list.cons(e));
    }

    // O(1)


    @Override
    public Stack<A> pushAll(List<A> xs) {
        return xs.isEmpty() ? this : this.push(xs.head()).pushAll(xs.tail());

    //O(n)
    }

    @Override
    public Stack<A> pushAll(A... es) {

        return pushAll(list(es));
    //O(n)
    }

    @Override
    public Stack<A> pop() {
        if (this.isEmpty()) {
            throw new IllegalStateException("pop called on empty stack");
        } else {
            return new ListStack<>(this.toList().tail());
    //O(1)
        }

    }

    @Override
    public A top() {

        if (this.isEmpty()) {
            throw new IllegalStateException("pop called on empty stack");
        }
        else {
            return this.toList().head();

        }
    //O(1)
    }

    @Override
    public Tuple<A, Stack<A>> popTop() {

        return new Tuple<>(top(), pop());
    //O(1)
    }

    @Override
    public Tuple<List<A>, Stack<A>> popTopAll() {

        return new Tuple<>(list, empty());
    //O(1)
    }

    @Override
    public List<A> toList() {

        return list;
    //O(1)
    }

    @Override
    public boolean isEqualTo(Stack<A> s) {

        return list.equals(s.toList());
    //O(1) -> equals in List.java hat O(n) aber hier läuft nur einaml.
    }

    @Override
    public int size() {

        return list.length();
    //O(1) -> length in List.java hat O(n) aber hier läuft nur einaml.
    }


    @Override
    public String toString() {

        return list.toString();
    //O(1)
    }

    public boolean equals(Object o) {

        return this == o || o instanceof Stack && this.isEqualTo((Stack) o);
    //O(n)
    }
}
