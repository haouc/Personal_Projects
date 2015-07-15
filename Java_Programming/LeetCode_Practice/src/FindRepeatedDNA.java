/**
 * Created by haozhou on 7/3/15.
 */
import java.util.*;
public class FindRepeatedDNA {
    public List<String> findRepeatedDnaSequences(String s) {

        /*The best way could be building the hash code to represent the string (10 chars). Rolling hash: hash = hash*A.size() + curr_char.*/
        final Map<Character, Integer> code = new HashMap<Character, Integer>(){
            {
                put('A', 1);
                put('T', 2);
                put('C', 3);
                put('G', 4);
            }
        };
        Set<String> result = new HashSet<>();
        Set<Integer> codeSet = new HashSet<>();
        final int index = (int)Math.pow(code.size(), 9);

        int hashCode = 0;

        for(int i = 0; i < s.length(); i++){
            if(i > 9){
                hashCode -= index*code.get(s.charAt(i-10));
            }
            hashCode = hashCode*code.size() + code.get(s.charAt(i));
            if(i > 8 && !codeSet.add(hashCode)){
                result.add(s.substring(i-9, i+1));
            }
        }
        return new ArrayList<String>(result);

        // Set<Integer> set = new HashSet<>();
        // List<String> list = new ArrayList<>();

        // for(int i = 0; i <= s.length()-10; i++){
        //     int j = i+10;
        //     int code = s.substring(i,j).hashCode();
        //     if(set.contains(code)){
        //         if(!list.contains(s.substring(i,j))){
        //             list.add(s.substring(i,j));
        //         }
        //     }else{
        //         set.add(code);
        //     }
        // }
        // return list;
    }
}
