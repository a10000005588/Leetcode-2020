import java.util.ArrayList;

/**
 * Definition for a binary tree node.
 * public class src.main.TreeNode {
 *     int val;
 *     src.main.TreeNode left;
 *     src.main.TreeNode right;
 *     src.main.TreeNode() {}
 *     src.main.TreeNode(int val) { this.val = val; }
 *     src.main.TreeNode(int val, src.main.TreeNode left, src.main.TreeNode right) {
 *         this.val = val;
 *         this.left = left;
 *         this.right = right;
 *     }
 * }
 */
public class Solution {
    ArrayList<Integer> list = new ArrayList<>();

    public int kthSmallest(TreeNode root, int k) {
        // 取得從小到大的list
        InOrderTraversal(root);
        return list.get(k-1);
    }

    private void InOrderTraversal(TreeNode root) {
        // 先走訪左邊
        if (root.left != null) {
            InOrderTraversal(root.left);
        }

        // 到左邊的底了 將值加進來
        this.list.add(root.val);

        if (root.right != null) {
            InOrderTraversal(root.right);
        }
    }

    public static void main(String[] args) {
    }
}
