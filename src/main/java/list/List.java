package list;


import fpinjava.TailCall;

import java.lang.reflect.Type;
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

    public abstract List<A> take(int n);

    public abstract List<A> drop(int n);

    public abstract A finde(Function<A, Boolean> f);

    public abstract A last();

    public abstract List<A> delete(A x);

    public abstract List<A> reverse();


    public abstract <B> B foldr(Function<A, Function<B, B>> f, B s);

    public abstract <B> B foldl(Function<B, Function<A, B>> f, B s);


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
        public <B> List<B> map(Function<A, B> f) {
            return list();
        }

        @Override
        public List<A> init() {
            throw new IllegalStateException("init called on empty list");
        }

        @Override
        public List<A> filter(Function<A, Boolean> f) {
            return list();
        }

        @Override
        public List<A> takeWhile(Function<A, Boolean> p) {
            return list();
        }

        @Override
        public List<A> dropWhile(Function<A, Boolean> p) {
            return list();
        }

        @Override
        public List<A> take(int n) {
            return list();
        }

        @Override
        public List<A> drop(int n) {
            return list();
        }

        @Override
        public A finde(Function<A, Boolean> f) {
            return null;
        }

        @Override
        public A last() {
            throw new IllegalStateException(" last called on empty list");
        }

        @Override
        public List<A> delete(A x) {
            return list();
        }

        @Override
        public List<A> reverse() {
            return list();
        }

        @Override
        public <B> B foldr(Function<A, Function<B, B>> f, B s) {
            return s;
        }

        @Override
        public <B> B foldl(Function<B, Function<A, B>> f, B s) {
            return s;
        }


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
        public boolean isEqualTo(List<A> xs) {
            return xs.head() == this.head && this.tail.isEqualTo(xs.tail());
        }

        @Override
        public int length() {
            return 1 + tail().length();
        }

        @Override
        public boolean elem(A x) {
            return x == head ? true : tail.elem(x);
        }

        @Override
        public boolean any(Function<A, Boolean> p) {
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
            return this.length() == 1 ? list() : new Cons<>(head, (tail.init()));
        }

        @Override
        public List<A> filter(Function<A, Boolean> p) {
            return p.apply(head) ? new Cons<>(head, (tail.filter(p))) : tail.filter(p);
        }

        @Override
        public List<A> takeWhile(Function<A, Boolean> p) {
            return (p.apply(head)) ? new Cons<>(head, (tail.takeWhile(p))) : list();

        }

        @Override
        public List<A> dropWhile(Function<A, Boolean> p) {
            return (p.apply(head)) ? tail.dropWhile(p) : new Cons<>(head, tail);
        }

        @Override
        public List<A> take(int n) {
            return n <= 0 ? list() : new Cons<>(head, (tail.take(n - 1)));
        }

        @Override
        public List<A> drop(int n) {
            return n <= 0 ? new Cons<>(head, tail) : (tail.drop(n - 1));
        }

        @Override
        public A finde(Function<A, Boolean> f) {
            return f.apply(head) ? head : tail.finde(f);
        }

        @Override
        public A last() {
            return tail.isEmpty() ? head : tail.last();
        }

        @Override
        public List<A> delete(A x) {
            return x == head ? tail : new Cons<>(head, (tail.delete(x)));
        }

        @Override
        public List<A> reverse() {
            return new Cons<>(head(), (tail.reverse()));
        }

        @Override
        public <B> B foldr(Function<A, Function<B, B>> f, B s) {
            return this.isEmpty() ? s : f.apply(this.head()).apply(this.tail().foldr(f, s));
        }

        @Override
        public <B> B foldl(Function<B, Function<A, B>> f, B s) {
            return this.isEmpty() ? s : this.tail().foldl(f, f.apply(s).apply(this.head()));
        }


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


    public static Double prod(List<Double> list) {
        return list.isEmpty() ? 1.0 : list.head() * prod(list.tail());

    }

    public static <A> List<A> append(List<A> xs, List<A> ys) {
        return xs.isEmpty() ? ys : new Cons<>(xs.head(), append(xs.tail(), ys));
    }

    public static Integer minimum(List<Integer> list) {
       /* return list.isEmpty() ? throw new IllegalStateException("minimum of empty list") : list.length()==1 ?
                list.head() : list.head() + minimum(list.tail());*/
        if (list.isEmpty()) {
            throw new IllegalStateException("minimum of empty list");
        } else if (list.length() == 1) {
            return list.head();
        } else {
            return list.head() + minimum(list.tail());
        }

    }

    public static Integer maximum(List<Integer> list) {
        if (list.isEmpty()) {
            throw new IllegalStateException("maximum of empty list");
        } else if (list.length() == 1) {
            return list.head();
        } else {
            return list.head() + maximum(list.tail());
        }

    }

    public static boolean and(List<Boolean> list) {
        return list.isEmpty() ? true : list.head() && and(list.tail());

    }

    public static boolean or(List<Boolean> list) {
        return list.isEmpty() ? false : list.head() || or(list.tail());

    }

    /*public static <A> List<A> reverse(List<A> xs) {
        return xs.isEmpty() ? list() : append(reverse(xs.tail()), list(xs.head()));
    }
*/
    public static <A> List<A> concat(List<A> xs, List<A> xss) {
        return xs.isEmpty() ? xss : new Cons<>(xs.head(), concat(xs.tail(), xss));
    }


    public static <A, B> B foldr(Function<A, Function<B, B>> f, B s, List<A> list) {
        return list.isEmpty() ? s : f.apply(list.head()).apply(foldr(f, s, list.tail()));
    }

    public static <A, B> B foldl(Function<B, Function<A, B>> f, B s, List<A> xs) {
        return xs.isEmpty() ? s : foldl(f, f.apply(s).apply(xs.head()), xs.tail());
    }

    public static Integer sumfoldr(List<Integer> list) {
        return foldr(x -> y -> x + y, 0, list);
    }

    public static Integer sumfoldl(List<Integer> list) {
        return foldl(x -> y -> x + y, 0, list);
    }

    public static Double prodfoldr(List<Double> list) {
        return foldr(x -> y -> x * y, 1.0, list);
    }

    public static Double prodfoldl(List<Double> list) {
        return foldl(x -> y -> x * y, 1.0, list);
    }

    public static Integer lengthfoldr(List<Integer> list) {
        return foldr(x -> n -> 1 + n, 0, list);
    }

    public static Integer lengthfoldl(List<Integer> list) {
        return foldl(x -> n -> 1 + n, 0, list);
    }

    public static <A> Boolean elemfoldr(List<A> list, A z) {
        return foldr(x -> y -> x.equals(z) || y, false, list);
    }

    public static <A> Boolean elemfoldl(List<A> list, A z) {
        return foldl(y -> x -> x.equals(z) || y, false, list);
    }


    public static <A> Boolean anyfoldr(List<A> list, Function<A, Boolean> p) {
        return foldr(x -> y -> p.apply(x) || y, false, list);
    }

    public static <A> Boolean anyfoldl(List<A> list, Function<A, Boolean> p) {
        return foldl(y -> x -> p.apply(x) || y, false, list);
    }

    public static <A> Boolean allfoldr(List<A> list, Function<A, Boolean> p) {
        return foldr(x -> y -> p.apply(x) && y, true, list);
    }

    public static <A> Boolean allfoldl(List<A> list, Function<A, Boolean> p) {
        return foldl(y -> x -> p.apply(x) && y, true, list);
    }

    public static Boolean andfoldr(List<Boolean> list) {
        return foldr(x -> y -> x && y, true, list);
    }

    public static Boolean andfoldl(List<Boolean> list) {
        return foldl(y -> x -> x && y, true, list);
    }

    public static Boolean orfoldr(List<Boolean> list) {
        return foldr(x -> y -> x || y, false, list);
    }

    public static Boolean orfoldl(List<Boolean> list) {
        return foldl(x -> y -> x || y, false, list);
    }

    public static <A> List<A> appendfoldr(List<A> list1, List<A> list2){
        return foldr(x -> l -> new Cons<>(x, l), list1, list2);
    }

    public static <A,B> List<B> mapfoldr(Function<A, B> f, List<A> list){
        return foldr(x-> xs -> xs.cons(f.apply(x)), list(), list);
    }

    public static <A> List<A> filterfoldr(Function<A, Boolean> p, List<A> list){
        return foldr(x-> xs -> p.apply(x) ? xs.cons(x) : xs, list(), list);
    }

    public static <A> String toStringfoldr(List<A> list) {
        return foldr(x -> s -> x + ", " + s, "",list);
    }

   /*public static <A> List<A> concat(List<List<A>> list){
        return foldr()
    }*/




    public static <A> List<A> reversefoldl(List<A> list) {
        return foldl(xs -> x -> xs.cons(x), list(), list);
    }






}