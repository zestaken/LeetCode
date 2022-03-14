package tree;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class AverageOfLevelsInBinaryTree637 {
    /**
     * 广度优先搜索法
     * @param root
     * @return
     */
    public List<Double> averageOfLevels1(TreeNode root) {
        //构造队列，利用其先进先出的特性实现广度遍历（一个层次的节点挨在一起依次输出）
        Queue<TreeNode> queue = new LinkedList<>();
        //记录最后和的集合
        List<Double> res = new ArrayList<>();
        //用来记录每一层节点的值
        Queue<Double> temp = new LinkedList<>();

        //遍历之前，先将树的根节点加入队列中
        queue.add(root);
        //用一个整型数组的两个值来分别记录队列总当前层节点的数量和下一层节点的数量
        int[] counts = {1, 0};
        while (!queue.isEmpty()) {
            //没弹出一个节点，队列中当前层的节点数量减一，然后将值加入临时记录值的队列中
            TreeNode node = queue.poll();
            temp.add((double) node.val);
            counts[0]--;

            //将当前层的左右节点加入队列中,并且没加入一个就将队列中下一层节点数加一。
            if (node.left != null) {
                queue.add(node.left);
                counts[1]++;
            }
            if (node.right != null) {
                queue.add(node.right);
                counts[1]++;
            }
            //队列中当前层数量为0时，说明当前层所有节点的值已经取出
            if (counts[0] == 0) {
                //说明当前层的节点已经全部弹出
                //计算当前层的平均值，并加入结果中
                double t = 0.0000;
                double n = temp.size();
                while (!temp.isEmpty()) {
                    t += temp.poll();
                }
                res.add(t / n);
               //将下一层的数量移动到当前层数量（下一层升级为当前层）
                counts[0] = counts[1];
                counts[1] = 0;
            }
        }
        return res;
    }

    /**
     * 深度优先搜索方法
     * @param root
     * @return
     */
    public List<Double> averageOfLevels(TreeNode root) {
       //存储每一层结点之和,脚标为i对应第i层
       List<Double> sums = new ArrayList<>();
       //存储每一层结点总数，对应关系相同
        List<Integer> counts = new ArrayList<>();
        //存储最后的结果，对应关系相同
        List<Double> res = new ArrayList<>();
       dfs(root, 0, sums, counts);
       for(int i = 0; i < sums.size(); i++) {
           res.add(sums.get(i) / counts.get(i));
       }
       return  res;
    }


    public void dfs(TreeNode root, int level, List<Double> sums, List<Integer> counts) {
        if(root == null) {
            return;
        }
        //如果level比sums的长度小，说明之前已经添加过这个层级的节点到sums中，累加即可
        //如果leven比sums的长度大，说明这是这个层级的第一个节点，直接加在最后面即可
        //因为是逐层深度递归搜索且添加值在进一步递归之前的，所以每一层的第一个节点出现时是按层级顺序的
        if(level < sums.size()) {
            sums.set(level, sums.get(level) + root.val);
            //当前层节点数加一
            counts.set(level, counts.get(level) + 1);
        } else {
            sums.add((double) root.val);
            //当前层节点数设置为一
            counts.add(1);
        }

        //对当前子树的左右子树递归搜索
        dfs(root.left, level + 1, sums, counts);
        dfs(root.right, level + 1, sums, counts);
    }
    public static void main(String[] args) {
        AverageOfLevelsInBinaryTree637 averageOfLevelsInBinaryTree = new AverageOfLevelsInBinaryTree637();
        int[] input1 = {3, 9, 20, -1,-1, 15,7};
        TreeNode root = TreeNode.mkBT(input1);
        List<Double> list = averageOfLevelsInBinaryTree.averageOfLevels(root);
        for(double i: list) {
            System.out.println(i + "");
        }
    }
}