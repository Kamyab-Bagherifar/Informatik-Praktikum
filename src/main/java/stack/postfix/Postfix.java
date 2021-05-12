package stack.postfix;

import list.List;
import stack.Stack;
import static stack.ListStack.*;
import static list.List.*;

public class Postfix {
    public double eval(String s){
        return eval_(stack(), words(s));
    }
    public double eval_(Stack<Double> s, List<String> expr) {
        if(expr.isEmpty()) return 0;
        s.push((expr.last()));


    }

}
