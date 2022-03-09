package tree;

import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;

public class DeleteNodesAndReturnForest1110 {
    public List<TreeNode> delNodes(TreeNode root, int[] to_delete) {
        //构造一个结果集方便查找需要删除的值
        Set<Integer> dels = new HashSet<>();
        for(int i : to_delete) {
            dels.add(i);
        }
        //构造一个储存树的集合
        List<TreeNode> list = new LinkedList<>();
        //先将整颗树加入集合中，如果根节点不会被删除，则不影响，如果需要被删除则在进入根节点的判断中将根节点移除
        list.add(root);

        del(root, dels, list);

        return list;

    }

    TreeNode del(TreeNode node, Set<Integer> dels, List<TreeNode> list) {
        if(node == null) {
            return null;
        }
        node.left = del(node.left, dels, list);
        node.right = del(node.right, dels, list);

        if(dels.contains(node.val)) {
            if(node.left != null) {
                list.add(node.left);
            }
            if(node.right != null) {
                list.add(node.right);
            }
            //如果一个这个要被删除的节点被加入了list中，则移除它(实质有这种可能的只有在根节点被删除的情况
            //其它节点都是从后往前加的，不会有这种情况，只有根节点是一开始就已经加入进去的。
            if(list.contains(node)) {
                list.remove(node);
            }
            //如果需要删除则用null填充节点的位置
            return null;
        }

        return node;
    }
}
