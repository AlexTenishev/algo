package com.altenlab.algo.misc;

import java.util.Stack;

public class ExpressionParser {
    // supports only numbers and '+' and '-'
    public int calculate(final String expression) {
        char operation = ' ';
        int result = 0;

        String currentNumber = "";
        for( int i = 0; i < expression.length(); ++i ) {
            if( expression.charAt(i) >= '0' && expression.charAt(i) <= '9' ) {
                currentNumber += expression.charAt(i);
            } else {
                // if we're here i will always be > 0
                if( expression.charAt(i) == '-' && expression.charAt(i - 1) == '+' ) {
                    operation = '-';
                } else {
                    final int val = Integer.parseInt(currentNumber);
                    if (operation == ' ') {
                        result = val;
                        operation = expression.charAt(i);
                    } else {
                        if (operation == '-') {
                            result -= val;
                        } else {
                            result += val;
                        }
                        operation = expression.charAt(i);
                    }

                    currentNumber = "";
                }
            }
        }

        final int val = Integer.parseInt(currentNumber);
        if( operation == ' ' ) {
            result = val;
        } else {
            if( operation == '-' ) {
                result -= val;
            } else {
                result += val;
            }
        }

        return result;
    }


    private String parseRest(final String expression) {
        if( expression.isEmpty() ) {
            return "";
        }
        String prefix = "";
        String rest = "";
        for( int i = 0; i < expression.length(); ++i ) {
            if( expression.charAt(i) == '(' ) {
                rest = expression.substring(i+1);
                break;
            } else if( expression.charAt(i) == ')') {
                prefix = prefix.isEmpty() ? "" : "" + calculate(prefix);
                rest = expression.substring(i + 1);
                break;
            } else {
                prefix += expression.charAt(i);
            }
        }
//        prefix = prefix.isEmpty() ? "" : "" + calculate(prefix);
        // eliminate +- case
        rest = parseRest(rest);
        if( !prefix.isEmpty() && !rest.isEmpty() && prefix.charAt(prefix.length() - 1) == '+' && rest.charAt(0) == '-' ) {
            prefix = prefix.substring(0, prefix.length() - 1);
        }
        return prefix+rest;
    }

    public String reduceCalcParentheses(final String expression) {
        int closingBracketPos = expression.indexOf(')');
        if( -1 == closingBracketPos ) {
            return !expression.isEmpty() ? "" + calculate(expression) : "";
        }
        int openingBracket = expression.lastIndexOf('(', closingBracketPos - 1);
        final String prefix = expression.substring(0, openingBracket);
        final String toReduce = expression.substring(openingBracket + 1, closingBracketPos);
        final String postfix = expression.substring(closingBracketPos+1);
        return reduceCalcParentheses(prefix+calculate(toReduce)+postfix);
    }

//    // stack based solution
//    public String reduceCalcParentheses2(final String expression) {
//        final Stack<String> stack = new Stack<>();
//
//        String acc = "";
//        for( int i = 0; i < expression.length(); ++i ) {
//            final char ch = expression.charAt(i);
//            if( ch == '(' ) {
//                if( !acc.isEmpty() ) {
//                    stack.push(acc);
//                    acc = "";
//                }
//                stack.push(""+ch);
//            } else if( ch == ')' ) {
//                // pop all the way back until we meet '('
//            } else {
//                acc += ch;
//            }
//        }
////        int closingBracketPos = expression.indexOf(')', 0);
//
////        while( -1 != closingBracketPos ) {
////            int openingBracket = expression.lastIndexOf('(', closingBracketPos - 1);
////            int subres = calculate(expression.substring(openingBracket + 1, closingBracketPos)); // reduce
////            stack.push(subres);
//////            return !expression.isEmpty() ? "" + calculate(expression) : "";
////
////            closingBracketPos = expression.indexOf(')', closingBracketPos + 1);
////        }
////
//    }
    // supports +,-,(,) and numbers
    public int calculateWithParentheses(final String expression) {
        return calculate(reduceCalcParentheses(expression));
//        return calculate(parseRest(expression));
//        int result = 0;
//        String prefix = "";
////        String middle = "";
//        String postfix = "";
////        char operation = ' ';
//        for( int i = 0; i < expression.length(); ++i ) {
//            if( expression.charAt(i) == '(' ) {
//                prefix += calculateWithParentheses(expression.substring(i+1));
//                break;
//            } else if( expression.charAt(i) == ')') {
//                prefix = "" + calculate(prefix);
//            } else {
//                prefix += expression.charAt(i);
//            }
//        }
//        return prefix == "" ? 0 : calculate(prefix);
    }
//    public int calculateWithParentheses(final String expression) {
//        int result = 0;
//        String prefix = "";
//        char operation = ' ';
//        for( int i = 0; i < expression.length(); ++i ) {
//            if( expression.charAt(i) == '(' ) {
//                prefix += calculateWithParentheses(expression.substring(i+1));
//                break;
//            } else if( expression.charAt(i) == ')') {
//                prefix = "" + calculate(prefix);
//            } else {
//                prefix += expression.charAt(i);
//            }
//        }
//        return prefix == "" ? 0 : calculate(prefix);
//    }
//    private static ArrayList<String> scan(final String expression) {
//        ArrayList<String> parsed = new ArrayList<>();
//        String subExpr = "";
//        for( int i = 0; i < expression.length(); ++i ) {
//            if( expression.charAt(i) == '(' ) {
//                ArrayList<String> subResult = scan(expression.substring(i+1);
////                parsed.addAll(sub
//            } else if( expression.charAt(i) == ')' ) {
//            }
//        }

//    public static void main(String[] args) {
//        final int result = calculate(Solution.expression);
//        System.out.println("Result: "+result);
//
//    }
}
