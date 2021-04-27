package list;
import static list.List.*;
public class Main {
    public static void main(String[] args) {
        List<Integer> list1 = list(2,5,7,2,8,6);
        List<Integer> list2 = list(5,7,2,8,6);
        int res = 0;

        for(int i = 0; i < 6;i++){
            if(list1.isEmpty()){

            }else {
                res++;
                list1 = list1.tail();
            }

        }



    }
}
