import java.util.LinkedList;
import java.util.List;
import java.util.Set;

/**
 * Created by haozhou on 7/4/15.
 */
public class WordLadder {
    public int ladderLength(String beginWord, String endWord, Set<String> wordDict) {
        // List<String> queue = new ArrayList<>();
        LinkedList<String> queue = new LinkedList<>();

        int step = 0;

        if(queue.isEmpty()) queue.add(beginWord);
        if(!wordDict.isEmpty()) wordDict.add(endWord);
        else return step;


        while(!queue.isEmpty()){
            // ArrayList<String> thisStep = new ArrayList<>();
            LinkedList<String> thisStep = new LinkedList<>();

            step++;

            while(!queue.isEmpty()){
                String curr = queue.poll();
                if(curr.equals(endWord)){
                    return step;
                }
                for(int i = 0; i < curr.length(); i++){
                    for(char j = 'a'; j <= 'z'; j++){
                        String test = curr.substring(0,i)+j+curr.substring(i+1, curr.length());
                        if(wordDict.contains(test)){
                            thisStep.add(test);
                            wordDict.remove(test);
                        }
                    }
                }
                queue.remove(0);
            }
            queue = thisStep;
        }
        return 0;
    }

}
