/**
 * Created by haozhou on 6/29/15.
 */
import java.util.*;
public class CalculatorII {
    public int calculate(String s) {
        if(s.length() < 3) return 0;
        s = s.replaceAll("\\s+", "");
        List<String> list = new ArrayList<>();
//        Set<Character> set = new HashSet<>();
        char[] arr = s.toCharArray();
        for(Character c: arr){
            list.add(c+"");
        }

        for(int i = 1; i < list.size()-1;){
            int result = 0;
//            System.out.println(list.get(i));
            System.out.println(list.get(i));
            if(list.get(i).equals("*") || list.get(i).equals("/")){
                if(list.get(i).equals("*")) {
                    result = Integer.valueOf(list.get(i-1))*Integer.valueOf(list.get(i + 1));
                }else if(list.get(i).equals("/")){
                    result = Integer.valueOf(list.get(i-1))/Integer.valueOf(list.get(i+1));
                }
                System.out.println("  " + result);

                list.set(i - 1, String.valueOf(result));
                list.remove(i + 1);
                list.remove(i);
//                list.add(i-1, (char)(result+48));
//                System.out.println("  index" + i);
            }else{
                i++;
            }


        }
        System.out.print("BREAK:");
        for(int index = 0; index < list.size(); index++){
            System.out.print(list.get(index));
        }
        for(int j = 1; j < list.size()-1; ){
            int result = 0;
            if(list.get(j).equals("-") || list.get(j).equals("+")){
                if(list.get(j).equals("-")){
                    result = Integer.valueOf(list.get(j-1))-Integer.valueOf(list.get(j+1));
                }else if(list.get(j).equals("+")){
                    result = Integer.valueOf(list.get(j-1))+Integer.valueOf(list.get(j+1));
                }
                list.set(j-1, String.valueOf(result));
                list.remove(j+1);
                list.remove(j);
            }else{
                j++;
            }
        }
        System.out.print("BREAK:");
        for(int index = 0; index < list.size(); index++){
            System.out.print(list.get(index));
        }

        return Integer.valueOf(list.get(0));
    }

    public static void main(String[] args){
        String test = " 3+5/ 3 -1 +7*3/2 +2";
        System.out.println("\n" + new CalculatorII().calculate(test));
    }
}
