/**
 * Created by haozhou on 6/27/15.
 * This code outputs result. However, leetcode said the time complexity is over the limit when the program is dealing with big input data.
 */
import java.util.*;
public class MaxWater {
    public int maxArea(int[] height) {
        TreeMap<Integer, ArrayList<Integer>> tMap = new TreeMap<Integer, ArrayList<Integer>>();
        for(int i = 0; i < height.length; i++){
            if(!tMap.containsKey(height[i])){
                ArrayList<Integer> list = new ArrayList<Integer>();
                list.add(i);
                tMap.put(height[i], list);
            }else{
                tMap.get(height[i]).add(i);
            }
        }

        ArrayList<Integer> indexList = new ArrayList<Integer>();
        for(int j = 0; j < height.length; j++){
            indexList.add(j);
        }

        int maxArea = 0;

        for(Integer i: tMap.keySet()){
            if(tMap.get(i).size() > 1){
                for(int k = 0; k < tMap.get(i).size(); k++){
                    int dist = 0;
                    dist = Math.max(Math.abs(tMap.get(i).get(k)-indexList.get(0)), Math.abs(tMap.get(i).get(k)-indexList.get(indexList.size()-1)));
                    maxArea = (maxArea < (dist*i))?(dist*i):maxArea;
                    indexList.remove(tMap.get(i).get(k));
                }
            }else{
                int dist = 0;
                dist = Math.max(Math.abs(tMap.get(i).get(0) - indexList.get(0)), Math.abs(tMap.get(i).get(0)-indexList.get(indexList.size()-1)));
                maxArea = (maxArea < (dist*i))?(dist*i):maxArea;
                indexList.remove(tMap.get(i).get(0));
            }
        }
        return maxArea;
    }

    public static void main(String[] args){
        int[] test = new int[]{2,3,10,5,7,8,9};
        MaxWater thisWater = new MaxWater();
        System.out.print(thisWater.maxArea(test));
    }
}
