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
    public static final Function<Double, Double> fact = x -> x == 1 || x == 0 ? 0 : x * Postfix.fact.apply(x - 1);
    public static final Function<Double, Function<Double, Double>> pow = x -> y -> Math.pow(x, y);

    public static double eval(String s) {
        return eval_(stack(), words(s));
    }

    public static double eval_(Stack<Double> s, List<String> expr) {
        return eval_(s, expr, stack());
    }

    public static double eval_(Stack<Double> s, List<String> expr, Stack<String> ops) {
        //Stack<String> ops = stack();
        if (expr.isEmpty()) return s.isEmpty() ? 0 : s.top();

        if ("+-*^/!".contains(expr.last())) {
            if (s.size() == 2) {

            } else {
                ops = ops.push(expr.last());
            }

        } else {
            double d = Double.parseDouble(expr.head());
            s = s.push(d);
            if (s.size() == 2) {
                double res = berechnung(s, ops);
                s = s.pop().pop().push(res);
                ops = ops.pop();
            }

        }

        return eval_(s, expr.init());

    }

    public static double berechnung(Stack<Double> s, Stack<String> ops) {
        double result = 0;
        switch (ops.top()) {
            case ("+"):
                result = add.apply(s.top()).apply(s.pop().top());
                break;
            case ("-"):
                result = sub.apply(s.top()).apply(s.pop().top());
                break;
            case ("*"):
                result = mul.apply(s.top()).apply(s.pop().top());
                break;
            case ("/"):
                result = div.apply(s.top()).apply(s.pop().top());
                break;
            case ("^"):
                result = pow.apply(s.top()).apply(s.pop().top());
                break;
        }
        return result;

    }


}
