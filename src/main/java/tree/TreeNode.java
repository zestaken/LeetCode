package tree;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;

/**
 * 树节点的定义
 */
public class TreeNode {
     int val;
     TreeNode left;
     TreeNode right;

     TreeNode() {}

     TreeNode(int val) { this.val = val; }

     TreeNode(int val, TreeNode left, TreeNode right) {
         this.val = val;
         this.left = left;
         this.right = right;
   }

    /**
     * 输入值的数组，生成二叉树（null用-1替代）,仅适用于完全二叉树(不是完全二叉树空的位置用null结点填充
     * @param nums
     * @return
     */
    public static TreeNode mkBT(int[] nums) {
        //当数组长度为0时，直接返回空
         if(nums.length == 0) {
             return null;
         }
        //先根据数组依次生成节点，存储到集合中
        ArrayList<TreeNode> treeNodes = new ArrayList<>();
        for(int i : nums) {
            if(i == -1) {
                treeNodes.add(null);
            } else {
                treeNodes.add(new TreeNode(i));
            }
         }
        //遍历前一半的节点（因为只有这些节点不是叶子节点）
        // 根据子节点与根节点的下标对应关系，找到每一个非叶子节点的左右子节点并连接
         for(int i = 0; i < nums.length; i++) {
             TreeNode cur = treeNodes.get(i);
             if(cur == null) {
                 continue;
             }
             //减一是为了与数组下标对应
             if(2 * (i + 1) - 1 < nums.length) {
                 cur.left = treeNodes.get(2 * (i + 1) - 1);
             }
             if(2 * (i + 1) + 1 - 1 < nums.length) {
                 cur.right = treeNodes.get(2 * (i + 1) + 1 - 1);
             }
         }
        //返回总的根节点（就是集合中的第一个节点）
         return treeNodes.get(0);
    }


    /**
     * 广度优先遍历二叉树
     * @return
     */
    public int[] getNumsBFS(){
        ArrayList<Integer> numsBFS = new ArrayList<>();
        Queue<TreeNode> queue = new LinkedList<>();

        queue.add(this);
        while(!queue.isEmpty()) {
            TreeNode node1 = queue.poll();
            numsBFS.add(node1.val);

            int flag = 0;
            if(node1.left != null) {
                queue.add(node1.left);
            } else {
                flag = 1;
            }
            if(node1.right != null) {
                queue.add(node1.right);
            } else {
                if(flag != 1){
                    numsBFS.add(-1);
                }
            }
        }

        int[] nums = new int[numsBFS.size()];
        for(int i = 0; i < numsBFS.size(); i++) {
            nums[i] = numsBFS.get(i);
        }

        return nums;
    }
}
