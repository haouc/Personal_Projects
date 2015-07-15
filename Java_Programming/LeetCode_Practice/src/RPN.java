/**
 * Created by haozhou on 6/28/15.
 */
import java.util.*;
public class RPN {
    public int evalRPN(String[] tokens) {
        if(tokens.length == 1) return Integer.valueOf(tokens[0]);
        if(tokens.length < 3) return 0;
        Set<String> set = new HashSet<String>(){{
            add("+");
            add("-");
            add("*");
            add("/");
        }};

        ArrayList<String> container = new ArrayList<>();
        for(int i = 0; i < tokens.length; i++){
            container.add(tokens[i]);
        }
        int j = 0;
        for(; j < container.size()-2;){
            if(set.contains(container.get(j+2))){
                int oOne = Integer.valueOf(container.get(j));
                int oTwo = Integer.valueOf(container.get(j+1));
                if(container.get(j+2).equals("+")){
                    int temp = oOne+oTwo;
                    container.remove(j+2);
                    container.remove(j+1);
                    container.remove(j);
                    container.add(j, String.valueOf(temp));
                }else if(container.get(j+2).equals("-")){
                    int temp = oOne-oTwo;
                    container.remove(j+2);
                    container.remove(j+1);
                    container.remove(j);
                    container.add(j, String.valueOf(temp));
                }else if(container.get(j+2).equals("*")){
                    int temp = oOne*oTwo;
                    container.remove(j+2);
                    container.remove(j+1);
                    container.remove(j);
                    container.add(j, String.valueOf(temp));
                }else if(container.get(j+2).equals("/")){
                    int temp = oOne/oTwo;
                    container.remove(j+2);
                    container.remove(j+1);
                    container.remove(j);
                    container.add(j, String.valueOf(temp));
                }
                if(j!=0) j--;
            }else{
                j++;
            }
        }
        System.out.println(j);
        return Integer.valueOf(container.get(j));
    }
    public static void main(String[] args){
//        String[] test = new String[]{"4", "13", "5", "/", "+"};
        String[] test = new String[]{"4"};

        System.out.print(new RPN().evalRPN(test));
    }
}
