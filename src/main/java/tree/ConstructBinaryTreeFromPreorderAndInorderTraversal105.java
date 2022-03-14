package tree;

import java.util.HashMap;

public class ConstructBinaryTreeFromPreorderAndInorderTraversal105 {

    //重新构建一颗树的关键在于确定根结点，而由于前序遍历第一个遍历的就是根结点，其后依次为左右子树，所以可以先利用前序遍历确定根结点
    //因为中序遍历的根结点左右两边分别为左右子树，在根据前序遍历先确定了根结点的情况下，就可以确定左右子树
    //利用递归的思想，一个遍历的结果中只有三个组成部分：根结点，左子树，右子树。只要能够将三者区分开，就可以递归处理下去。
    public TreeNode buildTree(int[] preorder, int[] inorder) {
        //构造一个存储前序遍历的值的哈希表，便于按值查找前序遍历的元素
        HashMap<Integer, Integer> mapOfInOrder = new HashMap<>();
        for(int i = 0; i < inorder.length; i++) {
            mapOfInOrder.put(inorder[i], i);
        }
        TreeNode root = buildTreeHelper(mapOfInOrder, preorder, 0, inorder.length, 0);

        return root;
    }

    public TreeNode buildTreeHelper(HashMap mapOfInOrder, int[] PreOrder, int startOfInorder, int endOfInOrder, int startOfPreOrder) {
       if(startOfInorder >= endOfInOrder) {
           return null;
       }
       int mid = PreOrder[startOfPreOrder];
       int index = (int) mapOfInOrder.get(mid);
       //计算左子树的节点数量
       int leftLen = index - startOfInorder;
        //创建根节点
        TreeNode node = new TreeNode(mid);
        node.left = buildTreeHelper(mapOfInOrder, PreOrder, startOfInorder, index,  startOfPreOrder + 1);
        node.right = buildTreeHelper(mapOfInOrder, PreOrder, index + 1, endOfInOrder, startOfPreOrder + leftLen + 1);
       return node;
    }

    public static void main(String[] args) {
        int[] preorder = {4, 9, 20, 15, 7};
        int[] inorder = {9, 4, 15, 20, 7};

        TreeNode root = new ConstructBinaryTreeFromPreorderAndInorderTraversal105().buildTree(preorder, inorder);
        TreeNode node = root;
    }
}
