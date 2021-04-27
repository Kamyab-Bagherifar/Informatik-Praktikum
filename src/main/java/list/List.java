package list;


import fpinjava.TailCall;

import java.security.PublicKey;
import java.util.function.Function;

import static fpinjava.TailCall.ret;
import static fpinjava.TailCall.sus;

public abstract class List<A> {


    public abstract A head();


    public abstract List<A> tail();


    public abstract boolean isEmpty();


    public abstract List<A> setHead(A h);

    public abstract boolean isEqualTo(List<A> xs);

    public abstract int length();

    public abstract boolean elem(A x);

    public abstract boolean any(Function<A, Boolean> p);

    public abstract boolean all(Function<A, Boolean> p);

    public abstract <B> List<B> map(Function<A, B> f);

    public abstract List<A> init();

    public abstract List<A> filter(Function<A, Boolean> f);

    public abstract List<A> takeWhile(Function<A, Boolean> p);

    public abstract List<A> dropWhile(Function<A, Boolean> p);

    public abstract  List<A> take(int n);

    public abstract  List<A> drop(int n);

    public abstract A finde(Function<A, Boolean> f);

    public abstract A last();

    public abstract List<A> delete(A x);








    // A.) Grundoperationen, cons = Construct
    public List<A> cons(A a) {
        return new Cons<>(a, this);
    }

    // A.) Grundoperationen, setHead
    public static <A> List<A> setHead(List<A> list, A h) {
        return list.setHead(h);
    }

    @SuppressWarnings("rawtypes")
    public static final List NIL = new Nil();

    private List() {
    }

    // B.) Rekursive Funktionen
    private static class Nil<A> extends List<A> {

        // toString methode
        public String toString() {
            return "[NIL]";
        }

        private Nil() {
        }

        public A head() {
            throw new IllegalStateException("head called on empty list");
        }

        public List<A> tail() {
            throw new IllegalStateException("tail called on empty list");
        }

        public boolean isEmpty() {
            return true;
        }

        @Override
        public List<A> setHead(A h) {
            throw new IllegalStateException("setHead called on empty list");
        }

        @Override
        public boolean isEqualTo(List<A> xs) {
            return xs.isEmpty();
        }

        @Override
        public int length() {
            return 0;
        }

        @Override
        public boolean elem(A x) {
            return false;
        }

        @Override
        public boolean any(Function<A, Boolean> p) {
            return false;
        }

        @Override
        public boolean all(Function<A, Boolean> p) {
            return true;
        }

        @Override
        public <B> List<B> map(Function<A, B> f) { return list(); }

        @Override
        public List<A> init() {
            throw new IllegalStateException("init called on empty list");
        }

        @Override
        public List<A> filter(Function<A, Boolean> f) { return list(); }

        @Override
        public List<A> takeWhile(Function<A, Boolean> p) { return list(); }

        @Override
        public List<A> dropWhile(Function<A, Boolean> p) { return list(); }

        @Override
        public List<A> take(int n) { return list(); }

        @Override
        public List<A> drop(int n) { return list(); }

        @Override
        public A finde(Function<A, Boolean> f) { return null; }

        @Override
        public A last() { throw new IllegalStateException(" last called on empty list"); }

        @Override
        public List<A> delete(A x) { return list(); }


    }

    // B.) Rekursive Funktionen
    private static class Cons<A> extends List<A> {

        private final A head;
        private final List<A> tail;

        private Cons(A head, List<A> tail) {
            this.head = head;
            this.tail = tail;
        }

        public A head() {
            return head;
        }

        public List<A> tail() {
            return tail;
        }

        public boolean isEmpty() {
            return false;
        }

        @Override
        public List<A> setHead(A h) {
            return new Cons<>(h, tail());
        }

        @Override
        public boolean isEqualTo(List<A> xs) { return xs.head() == this.head && this.tail.isEqualTo(xs.tail()); }

        @Override
        public int length() {
            return 1 + tail().length();
        }

        @Override
        public boolean elem(A x) {
            if (x == head) {
                return true;
            } else {
                return tail.elem(x);
            }
        }

        @Override
        public boolean any(Function<A , Boolean> p) {
                return p.apply(head) || tail().any(p);
        }

        @Override
        public boolean all(Function<A, Boolean> p) {
            return p.apply(head) && tail().any(p);
        }

        @Override
        public <B> List<B> map(Function<A, B> f) {
            return new Cons<>(f.apply(head()), tail().map(f));
        }

        @Override
        public List<A> init() {
            return this.length() == 1 ? list() : new Cons<>(head,(tail.init()));
        }

        @Override
        public List<A> filter(Function<A, Boolean> p) {
            if(p.apply(head)){
                return new Cons<>(head,(tail.filter(p)));
            }else {
                return tail.filter(p);
            }
        }

        @Override
        public List<A> takeWhile(Function<A, Boolean> p) {
            return (p.apply(head)) ? new Cons<>(head,(tail.takeWhile(p))) : list();

        }

        @Override
        public List<A> dropWhile(Function<A, Boolean> p) {
            return (p.apply(head)) ? tail.dropWhile(p) : new Cons<>(head,tail);
        }

        @Override
        public List<A> take(int n) {
            return n <= 0 ? list() : new Cons<>(head,(tail.take(n-1)));
        }

        @Override
        public List<A> drop(int n) {return n <= 0 ? new Cons<>(head, tail) : (tail.drop(n-1)); }

        @Override
        public A finde(Function<A, Boolean> f) { return f.apply(head) ? head : tail.finde(f);}

        @Override
        public A last() { return tail.isEmpty() ? head : tail.last();}

        @Override
        public List<A> delete(A x) { return x == head ? tail : new Cons<>(head,(tail.delete(x))); }




        public String toString() {
            return String.format("[%sNIL]", toString(new StringBuilder(), this).eval());
        }

        private TailCall<StringBuilder> toString(StringBuilder acc, List<A> list) {
            return list.isEmpty() ? ret(acc)
                    : sus(() -> toString(acc.append(list.head()).append(", "), list.tail()));
        }
    }

    @SuppressWarnings("unchecked")
    public static <A> List<A> list() {
        return NIL;
    }

    @SafeVarargs
    public static <A> List<A> list(A... a) {
        List<A> n = list();
        for (int i = a.length - 1; i >= 0; i--) {
            n = new Cons<>(a[i], n);
        }
        return n;
    }

    public static Integer sum(List<Integer> list) {
        if (list.isEmpty()) {
            return 0;
        } else {
            return list.head() + sum(list.tail());
        }
    }

    public static Double prod(List<Double> list) {
        if (list.isEmpty()) {
            return 1.0;
        } else {
            return list.head() * prod(list.tail());
        }
    }
    public static <A> List<A> append(List<A> xs, List<A> ys) {
        return
                xs.isEmpty() ? ys
                        : new Cons<>(xs.head(), append(xs.tail(), ys));
    }
    public static Integer minimum(List<Integer> list){
        if(list.isEmpty()){
            throw new IllegalStateException ("minimum of empty list");
        }else if(list.length() == 1){
            return list.head();
        }else {
            return list.head() + minimum(list.tail());
        }

    }
    public static Integer maximum(List<Integer> list){
        if(list.isEmpty()){
            throw new IllegalStateException ("maximum of empty list");
        }else if(list.length() == 1){
            return list.head();
        }else {
            return list.head() + maximum(list.tail());
        }

    }
    public static boolean and(List<Boolean> list){
        if(list.isEmpty()){
            return true;
        }else {
            return list.head() && and(list.tail());
        }
    }
    public static boolean or(List<Boolean> list){
        if(list.isEmpty()){
            return false;
        }else {
            return list.head() || and(list.tail());
        }

    }
    public static <A> List<A> reverse(List<A> xs) {
        return
                xs.isEmpty() ? list()
                        : append(reverse(xs.tail()), list(xs.head()));
    }
    public static <A> List<A> concat(List<A> xs , List<A> xss) {
        return xs.isEmpty() ?xss : new Cons<>(xs.head(), concat(xs.tail(), xss));
    }







}