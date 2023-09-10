package Task4;

import java.util.*;

public class Main {
    public static String expressionToRPN(String expr) {
        String current = "";
        Stack<Character> stack = new Stack<>();
        int priority;

        for (int i = 0; i < expr.length(); i++) {
            priority = getPriority(expr.charAt(i));
            if (priority == 0) {
                current += expr.charAt(i);
            } else if (priority == 1) {
                stack.push(expr.charAt(i));
            } else if (priority > 1) {
                current += ' ';
                while (!stack.empty()) {
                    if (getPriority(stack.peek()) >= priority) {
                        current += stack.pop();
                    } else {
                        break;
                    }
                }
                stack.push(expr.charAt(i));
            } else if (priority == -1) {
                current += ' ';
                while (getPriority(stack.peek()) != 1) {
                    current += stack.pop();
                }
                stack.pop();
            }
        }

        while (!stack.empty()) {
            current += stack.pop();
        }
        return current;
    }

    public static int rpnToAnswer(String rpn) {
        String opeand = new String();
        Stack<Integer> stack = new Stack<>();

        for (int i = 0; i < rpn.length(); i++) {
            if (rpn.charAt(i) == ' ') {
                continue;
            }
            if (getPriority(rpn.charAt(i)) == 0) {
                while (rpn.charAt(i) != ' ' && getPriority(rpn.charAt(i)) == 0) {
                    opeand += rpn.charAt(i++);
                    if (i == rpn.length()) ;
                    break;
                }
                stack.push(Integer.valueOf(opeand));
                opeand = new String();
            }
            if (getPriority(rpn.charAt(i)) > 1) {
                int a = stack.pop();
                int b = stack.pop();
                if (rpn.charAt(i) == '+') {
                    stack.push(b + a);
                }
                if (rpn.charAt(i) == '-') {
                    stack.push(b - a);
                }
                if (rpn.charAt(i) == '*') {
                    stack.push(b * a);
                }
                if (rpn.charAt(i) == '/') {
                    stack.push(b / a);
                }
            }
        }

        return stack.pop();
    }

    public static int getPriority(char token) {
        return switch (token) {
            case '*', '/' -> 3;
            case '+', '-' -> 2;
            case '(' -> 1;
            case ')' -> -1;
            default -> 0;
        };
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String expressionStr = scanner.next();
        scanner.close();

        //так как все выражения заканчиваются точкой, а точка нам не нужна,
        // уберем ее как крайний символ, а перед этим на всякий случай пробелы
        // перед и после выражения
        expressionStr = expressionStr.trim();
        expressionStr = expressionStr.substring(0,expressionStr.length() - 1);

        //Для решение задачи будем использовать "постфиксную нотацию"
        String rpn = expressionToRPN(expressionStr);

        //После перевода в нужный нам формат пробуем вычислить выражение
        //В случае ошибки выводим "WRONG"
        try {
            System.out.println(rpnToAnswer(rpn));
        } catch (Exception e) {
            System.out.println("WRONG");
        }
    }
}
