package stack.postfix;

import fpinjava.Function;
import list.List;
import stack.ListStack;
import stack.Stack;

import static stack.ListStack.*;
import static list.List.*;

public class Postfix {

    public static final Function<Double, Function<Double, Double>> add = x -> y -> x + y;
    public static final Function<Double, Function<Double, Double>> sub = x -> y -> x - y;
    public static final Function<Double, Function<Double, Double>> mul = x -> y -> x * y;
    public static final Function<Double, Function<Double, Double>> div = x -> y -> x / y;
    public static final Function<Double, Double> fact = x -> x == 1 || x == 0 ? 1 : x * Postfix.fact.apply(x - 1);
    public static final Function<Double, Function<Double, Double>> pow = x -> y -> Math.pow(x, y);

    public static double eval(String s) {
        return eval_(empty(), words(s));
    }


    public static double eval_(Stack<Double> s, List<String> expr) {

        if (expr.isEmpty()) return s.isEmpty() ? 0 : s.top();

        if ("+-*^/!".contains(expr.head())) {
            s = berechnung(s, expr.head());
        } else {
            s = s.push(Double.parseDouble(expr.head()));
        }
        System.out.println(s);
        return eval_(s, expr.tail());
    }

    public static Stack<Double> berechnung(Stack<Double> s, String ops) {
        double result = 0;
        switch (ops) {
            case ("+"):
                result = add.apply(s.pop().top()).apply(s.top());
                break;
            case ("-"):
                result = sub.apply(s.pop().top()).apply(s.top());
                break;
            case ("*"):
                result = mul.apply(s.pop().top()).apply(s.top());
                break;
            case ("/"):
                result = div.apply(s.pop().top()).apply(s.top());
                break;
            case ("^"):
                result = pow.apply(s.pop().top()).apply(s.top());
                break;
            case ("!"):
                result = fact.apply(s.top());
                //s = s.push(0.0);
                //break;
                return s.pop().push(result);


        }
        return s.pop().pop().push(result);

    }


}
