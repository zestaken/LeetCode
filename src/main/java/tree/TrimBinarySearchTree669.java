package tree;

import java.util.ArrayList;
import java.util.Scanner;

public class TrimBinarySearchTree669 {
    //递归的思想，先看根结点，如果根节点比最小值小，那么直接舍弃左子树以及根结点，再看右子树（对右子树又看作单独的树递归处理），同理根节点比最大值大，舍弃右子树
    //如果根结点符合条件，则直接再分别看左右子树，并用修剪后的左右子树来更新根结点原来的左右子树。
    public TreeNode trimBST(TreeNode root, int low, int high) {
        if(root == null) {
            return null;
        }
        //如果根结点比最小值小，则左子树肯定不符合要求，只能返回修建后的右子树
        if(root.val < low) {
            return trimBST(root.right, low, high);
        }
        if(root.val > high) {
            return trimBST(root.left, low, high);
        }
        //如果跟结点在范围内，则又分别修剪左右子树的,并用修剪后的结果来更新根结点的左右子树值
        root.left = trimBST(root.left, low, high);
        root.right = trimBST(root.right, low,high);
        //最后返回根结点
        return root;
    }

    public static void main(String[] args) {
        TrimBinarySearchTree669 trimBinarySearchTree = new TrimBinarySearchTree669();
        ArrayList<Integer> inputs = new ArrayList<>();
        Scanner scanner = new Scanner(System.in);
        while(scanner.hasNextInt()) {
            inputs.add(scanner.nextInt());
        }
        int[] inputs1 = new int[inputs.size()];
        for(int i = 0; i < inputs.size(); i++) {
           inputs1[i] = inputs.get(i);
        }
        TreeNode root  = TreeNode.mkBT(inputs1);
        trimBinarySearchTree.trimBST(root, 1, 3);

        System.out.println("end");
    }
}
