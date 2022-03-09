package tree;

import org.junit.jupiter.api.Test;
import java.util.List;

public class DeleteNodesAndReturnForestTest {

    @Test
    public void test() {
        DeleteNodesAndReturnForest1110 deleteNodesAndReturnForest = new DeleteNodesAndReturnForest1110();

        //测试用例一
        int[] input1 = {1, 2, 3, 4,5, 6, 7};
        TreeNode root1 = TreeNode.mkBT(input1);
        int[] del1 = {3, 5};
        int[] tree1 = {1, 2, -1, 4};
        int[] tree2 = {6};
        int[] tree3 = {7};

        List<TreeNode> treeNodes1 = deleteNodesAndReturnForest.delNodes(root1, del1);
        for(TreeNode node : treeNodes1) {
            System.out.println("---");
            for(int i : node.getNumsBFS()) {
                System.out.print(i + " ");
            }
            System.out.println();
        }
//        assert Arrays.equals(treeNodes1.get(0).getNumsBFS(), tree1);
//        assert Arrays.equals(treeNodes1.get(1).getNumsBFS(), tree2);
//        assert Arrays.equals(treeNodes1.get(2).getNumsBFS(), tree3);
    }
}
