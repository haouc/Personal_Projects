/**
 * Created by haozhou on 7/1/15.
 */
import java.util.*;
public class LetterCombination {
    public List<String> letterCombinations(String digits) {
        Map<Character, ArrayList<String>> map = new HashMap<Character, ArrayList<String>>(){{
            put('2', new ArrayList<String>(){{
                add("a");
                add("b");
                add("c");
            }});
            put('3', new ArrayList<String>(){{
                add("d");
                add("e");
                add("f");
            }});
            put('4', new ArrayList<String>(){{
                add("g");
                add("h");
                add("i");
            }});
            put('5', new ArrayList<String>(){{
                add("j");
                add("k");
                add("l");
            }});
            put('6', new ArrayList<String>(){{
                add("m");
                add("n");
                add("o");
            }});
            put('7', new ArrayList<String>(){{
                add("p");
                add("q");
                add("r");
                add("s");
            }});
            put('8', new ArrayList<String>(){{
                add("t");
                add("u");
                add("v");
            }});
            put('9', new ArrayList<String>(){{
                add("w");
                add("x");
                add("y");
                add("z");
            }});
        }};

        digits = digits.replaceAll("\\s", "");
        List<String> result = new ArrayList<>();
        ArrayList<ArrayList<String>> arr = new ArrayList<>();
        for(int i = 0; i < digits.length(); i++){
            if(map.containsKey(digits.charAt(i))){
                arr.add(map.get(digits.charAt(i)));
            }
        }
        if(digits.length() == 0) return result;
        else if(digits.length() == 1) return arr.get(0);
//        for(int j = 1; j < arr.size(); j++){
        while(arr.size() > 1){
            ArrayList<String> temp = new ArrayList<>();
            for(int k = 0; k < arr.get(0).size(); k++){
                for(int x = 0; x < arr.get(1).size(); x++){
                    temp.add(arr.get(0).get(k)+arr.get(1).get(x));
                }
            }
            arr.remove(1);
            arr.remove(0);
            arr.add(0, temp);
        }
        return arr.get(0);
    }

    public static void main(String[] args){
        String test = "23334";
        System.out.println(new LetterCombination().letterCombinations(test));
    }
}
