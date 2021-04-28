package list;
import java.lang.reflect.Type;

import static list.List.*;
public class Main {
    public static void main(String[] args) {
        List<Integer> list1 = list(1,2,3,4,5,6);
        List<Integer> list2 = list(2,3);
        List<Boolean> list3 = list(true,true);
        List<String> list4 = list("a","b","c");
        List<Boolean> list5 = list(true,true,false);
        List<List<Integer>> list6 = list(list(1),list(2));

        System.out.println(concatfoldr(list6));



    }
}
