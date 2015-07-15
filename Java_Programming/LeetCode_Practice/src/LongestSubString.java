import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by haozhou on 7/9/15.
 */
public class LongestSubString {
    public int lengthOfLongestSubstring(String s) {
        Map<Character, ArrayList<Integer>> map = new HashMap<>();

        for(int i = 0; i < s.length(); i++){
            Character c = s.charAt(i);
            if(map.containsKey(c)){
                map.get(c).add(i);
            }else{
                ArrayList<Integer> arr = new ArrayList<>();
                arr.add(i);
                map.put(c, arr);
            }
        }

        int max = 0;
        for(Map.Entry<Character, ArrayList<Integer>> entry: map.entrySet()){
            if(entry.getValue().size() > 1){
                if(max == 0) max = entry.getValue().get(1)-entry.getValue().get(0);
                else max = Math.min(max, (entry.getValue().get(1)-entry.getValue().get(0)));
            }
        }
        return (max == 0)?s.length():max;
    }
    public static void main (String[] args){
        String test = "aab";
        System.out.println(new LongestSubString().lengthOfLongestSubstring(test));
    }
}
