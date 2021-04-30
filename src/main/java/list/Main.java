package list;
import java.lang.reflect.Type;

import static list.List.*;
public class Main {
    public static void main(String[] args) {
        List<Integer> list1 = list(1);
        List<Integer> list2 = list(7,8);
        List<String> list3 = list("a","","b");
        List<String> list4 = list("c","","d");
        List<Boolean> list5 = list(true,true,false);
        List<List<Integer>> list6 = list(list(1),list(2));
        String test1 = "Hello Hallo Moin Salam";
        System.out.println(sum(list2));
        System.out.println(list1.concatMapExercise(x-> List.list(x, x+x)));
        System.out.println(euler1Problem());
        System.out.println(list1.equals(list1));
        System.out.println((int) 1.6);
        System.out.println(range(10,1));
        System.out.println(minimum(list1));
        System.out.println(list1.all(x -> x < 1));


    }
}
