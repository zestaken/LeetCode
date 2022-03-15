package tree;

import java.util.ArrayList;
import java.util.List;

public class BinaryTreePreorderTraversal144 {
    //通过递归的方法，先遍历根结点储存其值，再依次遍历左右子树
    //使用递归要注意的是，用于递归的方法必须有返回值（用于确定什么时候触底），但是返回值不一定非要使用(只用为结束递归的标志）
    public List<Integer> preorderTraversal(TreeNode root) {
        List<Integer> list = new ArrayList<>();
        preorderTraversalHelper(root, list);
        return list;
    }

   TreeNode preorderTraversalHelper(TreeNode node, List<Integer> list) {
        if(node == null) {
            return null;
        }
        list.add(node.val);
        preorderTraversalHelper(node.left, list);
        preorderTraversalHelper(node.right, list);
        return node;
    }

    public static void main(String[] args) {
        BinaryTreePreorderTraversal144 binaryTreePreorderTraversal = new BinaryTreePreorderTraversal144();
        int[] input = {1, -1, 2,-1, -1,3};
        TreeNode root = TreeNode.mkBT(input);
        List<Integer> list = binaryTreePreorderTraversal.preorderTraversal(root);
        for(int i : list) {
            System.out.println(i + " ");
        }

    }
}
