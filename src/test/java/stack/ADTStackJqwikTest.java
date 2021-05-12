package stack;

import list.JqwikUtils;
import list.List;
import net.jqwik.api.*;
import org.junit.jupiter.api.Assertions;
import tuple.Tuple;


import java.io.Serializable;
import java.util.Arrays;

import static list.JqwikUtils.equalLists;
import static list.List.append;
import static list.List.list;
import static org.junit.jupiter.api.Assertions.*;
import static tuple.Tuple.tuple;
import static stack.ListStack.*;

public class ADTStackJqwikTest {

    final int maxSize = 10;

    @Provide
    <A> Arbitrary<A> as() {
        return (Arbitrary<A>) ints();
    }

    private Arbitrary<Integer> ints() {
        return Arbitraries.integers().between(0, 100);
    }

    private Arbitrary<String> strings() {
        return Arbitraries.strings().withCharRange('a', 'z').ofMinLength(2).ofMaxLength(5);
    }

    @Provide
    <A> Arbitrary<Stack<A>> stacks() {
        return stacks(as(), maxSize);
    }

    private <A> Arbitrary<Stack<A>> stacks(Arbitrary<A> xa, int maxSize) {
        return JqwikUtils.lists(xa, maxSize).map(xs -> stack(xs));
    }

    @Provide
    <A> Arbitrary<List<A>> lists() {
        return JqwikUtils.lists(as(), 5);
    }

    @Provide
    <A extends Serializable> Arbitrary<Tuple<Stack<A>, Stack<A>>> equalStacks() {
        return equalStacks(as(), maxSize);
    }

    private <A extends Serializable> Arbitrary<Tuple<Stack<A>, Stack<A>>> equalStacks(Arbitrary<A> xa, int maxSize) {
        return equalLists(xa, maxSize).map(t -> tuple(stack(t.fst), stack(t.snd)));
    }

    private <A> Stack<A> stack(A... data) {
        return stack(List.list(data));
    }

    private <A> Stack<A> stack(List<A> data) {
        return ListStack.<A>empty().pushAll(data);
    }

    @Property
    boolean stacksFromUnEqualArraysAreUnEqual(@ForAll Integer[] a1, @ForAll Integer[] a2) {
        Assume.that(!Arrays.equals(a1, a2));
        return !stack(a1).equals(stack(a2));
    }

    @Property
    <A> boolean stacksFromUnEqualJavaListsAreUnEqual(@ForAll java.util.List<A> a1, @ForAll java.util.List<A> a2) {
        Assume.that(!a1.equals(a2));
        return !stack(a1).equals(stack(a2));
    }

    @Property
    <A> boolean equalStacksAreEqual(@ForAll("equalStacks") Tuple<Stack<A>, Stack<A>> t) {
        return t.fst.equals(t.snd);
    }

    @Property
    <A> boolean testToList(@ForAll java.util.List<A> a) {
        return list(a).equals(stack(a).toList());
    }

    // isEmpty(empty)	= true
    @Example
    boolean isEmpty_empty() {

        return empty().isEmpty();
    }

    // ∀s:Stack<A>, ∀x:A : isEmpty(push(x,s)) = false
    @Property
    <A> boolean isEmpty_push(@ForAll("stacks") Stack<A> s, @ForAll("as") A x) {
        return !(s.push(x).isEmpty());

    }

    // ∀s:Stack<A>, ∀x:Integer : top(push(x,s)) = x
    @Property
    <A> boolean top_push(@ForAll("stacks") Stack<A> s, @ForAll("as") A x) {

        return s.push(x).top().equals(x);
    }

    // ∀s:Stack<A>, ∀x:Integer : pop(push(x,s)) = s
    @Property
    <A> boolean pop_push(@ForAll("stacks") Stack<A> s, @ForAll("as") A x) {

        return s.push(x).pop().isEqualTo(s);
    }

    // ∀s:Stack<A>, ∀x:Integer : popTop(push(x,s)) = (x,s)
    @Property
    <A> boolean popTop_push(@ForAll("stacks") Stack<A> s, @ForAll("as") A x) {

        return s.push(x).popTop().equals(new Tuple<>(x, s));
    }

    // ∀s:Stack<A> : push(top(s),pop(s))	 = s	, falls s nicht leer
    @Property
    <A> boolean push_top_pop(@ForAll("stacks") Stack<A> s) {
        Assume.that(!s.isEmpty());
        return s.pop().push(s.top()).isEqualTo(s);
    }

    // ∀s:Stack<A> : push(popTop(s))	 = s	, falls s nicht leer
    @Property
    <A> boolean push_popTop(@ForAll("stacks") Stack<A> s) {
        Assume.that(!s.isEmpty());
        return s.popTop().snd.push(s.popTop().fst).isEqualTo(s);

    }

    // ∀s:Stack<A> : popTop(s)	= top(s), pop(s), falls s nicht leer
    @Property
    <A> boolean popTop(@ForAll("stacks") Stack<A> s) {
        Assume.that(!s.isEmpty());
        return s.popTop().equals(new Tuple<>(s.top(), s.pop()));
    }

    // ∀s:Stack<A> : pushAll([],s) = s
    @Property
    <A> boolean pushAll(@ForAll("stacks") Stack<A> s) {

        return s.pushAll(list()).isEqualTo(s);
    }

    // // ∀s:Stack<A>, ∀xs:List<A> : pushAll(x:xs,s)= push(x,pushAll(xs,s)), falls s nicht leer
    @Property
    <A> boolean pushAll(@ForAll("stacks") Stack<A> s, @ForAll("lists") List<A> xs) {
        Assume.that(!s.isEmpty());
        return xs.isEmpty() || s.pushAll(xs).isEqualTo(s.push(xs.head()).pushAll(xs.tail()));

    }

    // toList(empty) = []
    @Example
    boolean toList() {

        return ListStack.empty().toList().equals(list());
    }

    // ∀s:Stack<A>, ∀x: A :  toList(push(x,s)) = x:toList(s)
    @Property
    <A> boolean toList(@ForAll("stacks") Stack<A> s, @ForAll("as") A x) {

        return s.push(x).toList().equals(append(s.toList(), list(x)));
    }

    // pop(empty)	= error
    @Example
    void pop_empty() {

        Assertions.assertThrows(IllegalStateException.class, () -> {
            ListStack.empty().pop();
        });
    }

    // top(empty)	= error
    @Example
    void top_empty() {

        Assertions.assertThrows(IllegalStateException.class, () -> {
            ListStack.empty().top();
        });
    }
}
