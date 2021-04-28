package list;
import java.lang.reflect.Type;

import static list.List.*;
public class Main {
    public static void main(String[] args) {
        List<Integer> list1 = list(2,8,6,1);
        List<Integer> list2 = list(10,9,8);
        List<Boolean> list3 = list(true,true);
        List<String> list4 = list("a","b","c");
        List<Boolean> list5 = list(true,true,false);
        System.out.println(toStringfoldr(list1));



    }
}
