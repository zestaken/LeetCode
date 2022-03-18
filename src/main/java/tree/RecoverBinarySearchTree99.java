package tree;

import java.util.ArrayList;
import java.util.Scanner;

public class RecoverBinarySearchTree99 {

    //Java的参数都是值传递，如果一个对象指向的是null，我们将其传入函数中并不能将null改成具体的对象地址
    //因为只能修改具体对象地址的内容，如果指向的是null，则这个对象并不存在，更无从修改.所以需要把mistake1和2设置为全局变量，才能起到记录错误结点的作用
//    对于记录前面节点情况的prev指针来说，如果靠不停地在递归函数中传递，则在递归触底反弹的时候，则这个prev指针不能被传递给触底之后的指针
    //比如说在遍历完左子树的最后一个结点后，此时左子树的递归结束会反弹回根结点，正常来说根结点的prev指针应该指向左子树的最后一个结点
    //但是实际上左子树的最后一个结点并不能通过递归函数的参数的形式传递回来，所以表现为根结点没有前驱结点

    //记录有问题的结点
    TreeNode mistake1 = null;
    TreeNode mistake2 = null;
    //记录当前遍历结点的前驱结点
    TreeNode prev  = null;

    public void recoverTree(TreeNode root) {
//        二叉搜索树的特点：左边的值小于中间（根结点）的值 小于右边结点的值
//        利用二叉搜索树的特性，可以利用这个特性，使用中序遍历，依次比较前后值的大小，如果大小不对，则说明次序出现了问题
        InOrder(root);
        //交换有问题的两个结点
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
    void InOrder(TreeNode node) {
        if(node == null) {
            //没有返回值，用return结束递归
            return;
        }
        //先遍历左子树
        //左子树最先遍历，没有前驱结点
        InOrder(node.left);
        //遍历根结点
        int mid = node.val;
        //因为每一个结点都是相对意义上的根结点，所以在此判断该结点与前驱的关系
        if(prev != null && mid < prev.val) {
            if(mistake1 == null) {
                mistake1 = prev;
                mistake2 = node; 
            } else {
                mistake2 = node;
            }
        }
       //右子树的前驱结点是根结点
        prev = node;
        InOrder(node.right);
    }


    public static void main(String[] args) {
        RecoverBinarySearchTree99 recoverBinarySearchTree = new RecoverBinarySearchTree99();
        ArrayList<Integer> inputs = new ArrayList<>();
        Scanner scanner = new Scanner(System.in);
        //输入1：1 3 -1 -1 2 输入以非数字字符结束
        //结果1：3 1 -1 -1 2
        //输入2：2 1 -1 -1 3
        //结果2：3 1 -1 -1 2
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
//    TreeNode mistake1 = null;
//    TreeNode mistake2 = null;
//
//    public void recoverTree(TreeNode root) {
//        //二叉搜索树的特点：左边的值小于中间（根结点）的值 小于右边结点的值
//        //利用二叉搜索树的特性，可以利用这个特性，使用中序遍历，依次比较前后值的大小，如果大小不对，则说明次序出现了问题
//        TreeNode prev  = null;
//        //Java的参数都是值传递，如果一个对象指向的是null，我们将其传入函数中并不能将null改成具体的对象地址
//        //因为只能修改具体对象地址的内容，如果指向的是null，则这个对象并不存在，更无从修改
//        InOrder(root, prev);
//        if(mistake1 != null && mistake2 != null) {
//            int temp = mistake1.val;
//            mistake1.val = mistake2.val;
//            mistake2.val = temp;
//        }
//    }
//
//    /**
//     * 前序遍历函数
//     * @param node
//     * @return
//     */
//    void InOrder(TreeNode node, TreeNode prev) {
//        if(node == null) {
//            //没有返回值，用return结束递归
//            return;
//        }
//        if(node.left != null) {
//            InOrder(node.left, prev);
//            prev = node.left;
//        }
//        int mid = node.val;
//        if(prev != null && mid < prev.val) {
//            if(mistake1 == null) {
//                mistake1 = prev;
//                mistake2 = node;
//            } else {
//                mistake2 = node;
//            }
//        }
//        prev = node;
//        if(node.right != null) {
//            InOrder(node.right, prev);
//        }
//    }
