package com.debapriyo.projects.stack;

import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

/**
 * @author Debapriyo Majumder (x086021)
 * @version 1.0
 * @since 2/4/2017
 */
public class Solution {
    private static final String POP = "POP";
    private static final String DUP = "DUP";
    private static final String PLUS = "+";
    private static final String MINUS = "-";
    private List<Integer> stack = new ArrayList<>();

    public int solution(String S) {
        // write your code in Java SE 8
        int result = -1;
        try {
            StringTokenizer st = new StringTokenizer(S, " ");
            while (st.hasMoreTokens()) {
                String token = st.nextToken();
                if (isNumber(token)) {
                    System.out.println("Pushed: " + push(token));
                }
                else if (POP.equals(token)) {
                    System.out.println("Popped: " + pop());
                }
                else if (DUP.equals(token)) {
                    System.out.println("Duplicated: " + dup());
                }
                else if (PLUS.equals(token)) {
                    System.out.println("Added: " + plus());
                }
                else if (MINUS.equals(token)) {
                    System.out.println("Subtracted: " + minus());
                }

                System.out.println("Stack: " + stack);
            }
            result = pop();
        }
        catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return result;
    }

    private int push(final String token) {
        Integer number = Integer.valueOf(token); // it has already been established that token is a number
        stack.add(number);
        return number;
    }

    private int pop() throws Exception {
        if (stack.size() < 1) {
            throw new Exception("Error: Cannot pop. Stack under run");
        }
        int removed = stack.get(stack.size() - 1);
        stack.remove(stack.size() - 1);
        return removed;
    }

    private int dup() throws Exception {
        if (stack.size() < 1) {
            throw new Exception("Error: Cannot dup. Stack under run");
        }
        int element = stack.get(stack.size() - 1);
        stack.add(element);
        return element;
    }

    private int plus() throws Exception {
        if (stack.size() < 2) {
            throw new Exception("Error: Cannot add. Stack has less than 2 elements");
        }
        int a = stack.get(stack.size() - 1);
        int b = stack.get(stack.size() - 2);
        stack.remove(stack.size() - 1);
        stack.remove(stack.size() - 1);
        int c = a + b;
        isValidInteger(c);
        stack.add(c);
        return c;
    }

    private int minus() throws Exception {
        if (stack.size() < 2) {
            throw new Exception("Error: Cannot subract. Stack has less than 2 elements");
        }
        int a = stack.get(stack.size() - 1);
        int b = stack.get(stack.size() - 2);
        stack.remove(stack.size() - 1);
        stack.remove(stack.size() - 1);
        int c = a - b;
        isValidInteger(c);
        stack.add(c);
        return c;
    }

    private boolean isNumber(final String token) {
        boolean isNumber = true;
        try {
            Integer i = Integer.parseInt(token);
        }
        catch (NumberFormatException nfe) {
            isNumber = false;
        }
        return isNumber;
    }

    private void isValidInteger(final int number) throws Exception {
        if (number < 0 || number > Math.pow(2, 20)) {
            throw new Exception("Error: Number not valid");
        }
    }
}