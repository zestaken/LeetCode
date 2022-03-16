package tree;

import java.util.ArrayList;
import java.util.Scanner;

public class RecoverBinarySearchTree99 {

    TreeNode mistake1 = null;
    TreeNode mistake2 = null;

    public void recoverTree(TreeNode root) {
        //二叉搜索树的特点：左边的值小于中间（根结点）的值 小于右边结点的值
        //利用二叉搜索树的特性，可以利用这个特性，使用中序遍历，依次比较前后值的大小，如果大小不对，则说明次序出现了问题
        TreeNode prev  = null;
        //Java的参数都是值传递，如果一个对象指向的是null，我们将其传入函数中并不能将null改成具体的对象地址
        //因为只能修改具体对象地址的内容，如果指向的是null，则这个对象并不存在，更无从修改
        InOrder(root, prev);
        if(mistake1 != null && mistake2 != null) {
            int temp = mistake1.val;
            mistake1.val = mistake2.val;
            mistake2.val = temp;
        }
    }

    /**
     * 前序遍历函数
     * @param node
     * @return
     */
    void InOrder(TreeNode node, TreeNode prev) {
        if(node == null) {
            //没有返回值，用return结束递归
            return;
        }
        if(node.left != null) {
           InOrder(node.left, prev);
        }
        int mid = node.val;
        if(prev != null && mid < prev.val) {
            if(mistake1 == null) {
                mistake1 = prev;
                mistake2 = node; 
            } else {
                mistake2 = node;
            }
        }
        prev = node;
        if(node.right != null) {
           InOrder(node.right, prev);
        }
    }

    public static void main(String[] args) {
        RecoverBinarySearchTree99 recoverBinarySearchTree = new RecoverBinarySearchTree99();
        ArrayList<Integer> inputs = new ArrayList<>();
        Scanner scanner = new Scanner(System.in);
        //输入：1 3 -1 -1 2 输入以非数字字符结束
        //结果：1 2 -1 -1 3
        while(scanner.hasNextInt()) {
            inputs.add(scanner.nextInt());
        }
        int[] inputs1 = new int[inputs.size()];
        for(int i = 0; i < inputs.size(); i++) {
            inputs1[i] = inputs.get(i);
        }
        TreeNode root = TreeNode.mkBT(inputs1);
        recoverBinarySearchTree.recoverTree(root);
    }

}
