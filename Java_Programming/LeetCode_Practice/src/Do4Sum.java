/**
 * Created by haozhou on 7/1/15.
 */

import java.util.*;

public class Do4Sum {
    public List<List<Integer>> fourSum(int[] nums, int target) {
        List<List<Integer>> result = new ArrayList<>();
        Map<Integer, ArrayList<Integer>> map = new HashMap<>();
        if (nums.length < 4) return result;
        Arrays.sort(nums);
        for (int i = 0; i < nums.length; i++) {
            if (!map.containsKey(nums[i])) {
                ArrayList<Integer> temp = new ArrayList<>();
                temp.add(i);
                map.put(nums[i], temp);
            } else {
                map.get(nums[i]).add(i);
            }
        }

        for (int i = 0; i < nums.length; i++) {
            int num1 = nums[i];
            int j = i + 1, k = nums.length - 1;
            while (j < k) {
                boolean mapItemCheck = false;
                int num2 = nums[j];
                int num3 = nums[k];
                int sum = num1 + num2 + num3;
//                if (map.containsKey(target - sum)) {
//                    ArrayList<Integer> check = new ArrayList<>(map.get(target - sum));
//                    if (check.contains(i)) check.remove((Integer) i);
//                    if (check.contains(j)) check.remove((Integer) j);
//                    if (check.contains(k)) check.remove((Integer) k);
//                    if (check.size() > 0) mapItemCheck = true;
//                }

                if (map.containsKey(target - sum) && !map.get(target-sum).contains(i) && !map.get(target-sum).contains(j) && !map.get(target-sum).contains(k)) {
                    ArrayList<Integer> list = new ArrayList<Integer>() {{
                        add(num1);
                        add(num2);
                        add(num3);
                        add(target - sum);
                    }};
                    Collections.sort(list);
                    result.add(list);
                    k--;
                    j++;
                } else {
                    if (k > j && sum > target) k--;
                    else if (j < k && sum < target) j++;
                }
            }
        }
        System.out.println("result size is: "+result.size());
        return result;
    }

    public static void main(String[] args) {
        int[] test = new int[]{1, 0, -1, 0, -2, 2};
        int target = 1;
        Do4Sum sum = new Do4Sum();
        sum.toString(sum.fourSum(test, target));
    }

    private void toString(List<List<Integer>> arr) {

        String[] output = new String[arr.size()];
        for (int i = 0; i < arr.size(); i++) {
            StringBuilder sb = new StringBuilder();
            for (int j = 0; j < arr.get(i).size(); j++) {
                sb.append(arr.get(i).get(j));
                sb.append(", ");
            }
            output[i] = sb.toString();
        }
        for (String str : output) {
            System.out.println(str);
        }
    }
}
