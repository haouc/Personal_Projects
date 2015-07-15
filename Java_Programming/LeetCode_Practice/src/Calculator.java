/**
 * Created by haozhou on 6/30/15.
 */

import java.util.*;

public class Calculator {
    public int calculate(String s) {
        s = s.replaceAll("\\s+", "");
        ArrayList<String> list = new ArrayList<>();
        int result = 0;

        for (int i = 0; i < s.length(); ) {
            if (Character.isDigit(s.charAt(i))) {
                int j = i;
                while (i < s.length() && Character.isDigit(s.charAt(i))) {
                    i++;
                }
                list.add(s.substring(j, i));
            } else {
                list.add(s.charAt(i) + "");
                i++;
            }
        }
        for (int i = 0; i < list.size(); i++) {
            if (list.contains("(")) {
                if (list.get(i).equals(")")) {
                    int end = i;
                    while (!list.get(i).equals("(")) {
                        i--;
                    }
                    int start = i;
                    ArrayList<String> temp = new ArrayList<>(list.subList(start + 1, end));
                    System.out.println(temp.toString());
                    int subResult = helper(temp);
                    list.subList(start, end).clear();
                    list.add(start, String.valueOf(subResult));
                    list.remove(start + 1);
                    System.out.println("list: " + list.toString());
                }
            }
        }

        return helper(list);
    }

    private int helper(ArrayList<String> list) {
        for (int i = 1; i < list.size() - 1; ) {
            if (list.get(i).equals("+")) {
                int result = Integer.valueOf(list.get(i - 1)) + Integer.valueOf(list.get(i + 1));
                list.remove(i + 1);
                list.remove(i);
                list.set(i - 1, String.valueOf(result));
            } else if (list.get(i).equals("-")) {
                int result = Integer.valueOf(list.get(i - 1)) - Integer.valueOf(list.get(i + 1));
                list.remove(i + 1);
                list.remove(i);
                list.set(i - 1, String.valueOf(result));
            }
        }
        return Integer.valueOf(list.get(0));
    }

    public static void main(String[] args) {
        String test = "(1+(4+5+2)-3)+(6+8)";
//        ArrayList<String> testArr = new ArrayList<String>(){{
//            add("3");
//            add("+");
//            add("2");
//            add("-");
//            add("2");
//            add("+");
//            add("3");
//            add("+");
//            add("2");
//            add("-");
//            add("2");
//        }};
        System.out.println(new Calculator().calculate(test));
    }
}
