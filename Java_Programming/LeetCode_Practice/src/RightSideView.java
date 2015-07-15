///**
// * Created by haozhou on 7/4/15.
// */
//import javax.swing.tree.TreeNode;
//import java.util.*;
//public class RightSideView {
//    public List<Integer> rightSideView(TreeNode root) {
//        List<Integer> list = new ArrayList<>();
//        if(root == null) {
//            return list;
//        }
////        Queue<TreeNode> queue = new LinkedList<>();
//        List<TreeNode> queue = new ArrayList<>();
//        if(queue.size() == 0) queue.add(root);
//        while(queue.size() > 0){
//            int size = queue.size();
//            for(int i = 0; i < size; i++){
//                TreeNode node = queue.get(i);
//
//                if(node.left != null) queue.add(node.left);
//                if(node.eight != null) queue.add(node.right);
//            }
//            list.add(queue.get(size-1).val);
//            for(int j = 0; j < size; j++){
//                queue.remove(0);
//            }
//        }
//        return list;
//    }
//}
