package list;
import fpinjava.Function;
import stack.ListStack;
import stack.Stack;
import set.ListSet.*;
import static list.List.*;
import static stack.ListStack.*;
import static stack.postfix.Postfix.*;
import static stack.postfix.Postfix.*;
import static  set.ListSet.*;
public class Main {
    public static final Function<Integer, Function<Integer, Integer>> add = x -> y -> x + y;
    public static void main(String[] args) {
        List<Integer> list1 = list(1,2,3,4);
        List<Integer> list2 = list(4,56,477,444,77);
        List<String> list3 = list("1 2 3 4");
        String test = "1 2 3";
        System.out.println(wordSet("Elfen helfen Elfen"));
        //Stack<Integer> test1 = empty();
        //System.out.println(test1.push(1).push(2).top());
        //System.out.println(test1.top());
        //System.out.println(set(1,2,3,4).all(x -> x < 5));
        //System.out.println(set(1,2).isEqualTo(set(1,2)));
        //System.out.println(set(2).disjoint(set(1,2)));
        System.out.println(foldr((x -> y -> add.apply(x).apply(y)), 0, set(1,2,3,4)));
        System.out.println(foldl((x -> y -> add.apply(x).apply(y)), 0, set(1,2,3,4)));
        //System.out.println(filter((x-> (Integer) x > 2), set(1,2,3,4)));
        // System.out.println("filter:" + list1.filter(x -> x%2 == 1));
        //System.out.println(map(((x-> (Integer) x + 1)), set(10,11,12,13)));
        System.out.println(set(1,2,3,4).union(set(5,6,7,8,3)));
        System.out.println(set(1,2,3,4).intersection(set(5,1,4,9,2)));
        System.out.println(set(1,2,3).equals(set(1,2)));
        System.out.println(fromList(list(1)));


/*
        Stack<Integer> test = empty();
        //System.out.println(test.push(1).push(2));
        *//*System.out.println(stack());
        System.out.println(stack(list(1,2,3,4)));*//*
        System.out.println(stack(1,2,3,4).popTopAll());
        //System.out.println(stack(list1).push(10));
        System.out.println(stack(3,4,5).isEqualTo(stack(3,4,5).push(6)));
        System.out.println(stack(list2).toString());*/


        //System.out.println(stack(6,7,8).popTopAll());




        /*System.out.println(stack(1,2,3,4).pushAll(5,6));
        System.out.println(stack(list(1,2,3,4)).pushAll(list(1,2,3,4)));*/

       /* System.out.println(stack(1,2,3,4).push(5).toString());
        System.out.println(stack(1,2,3).isEqualTo(stack(1,2,3)));
        System.out.println(stack(1,2,3).isEqualTo(stack(1,2,3).push(5)));
        System.out.println(stack(5,6,7).push(8).top());
        System.out.println(stack(6,7,8).popTopAll());
        System.out.println("toStringR:" + toStringfoldr(list1));
        System.out.println("toString:" + list1.toString());*/

       /* System.out.println(stack(list1));
        System.out.println(stack(6,7,8,9));*/







    }
}
