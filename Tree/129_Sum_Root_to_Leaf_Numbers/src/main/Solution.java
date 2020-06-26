/**
 * Definition for a binary tree node.
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode() {}
 *     TreeNode(int val) { this.val = val; }
 *     TreeNode(int val, TreeNode left, TreeNode right) {
 *         this.val = val;
 *         this.left = left;
 *         this.right = right;
 *     }
 * }
 */
class Solution {
    int sum = 0;
    String numStr = "";

    public int sumNumbers(TreeNode root) {
        if (root == null) return 0;
        traverse(root);
        return sum;
    }

    public void traverse(TreeNode node) {
        numStr += String.valueOf(node.val);
        // 直到沒有子節點, 進行加總
        if (node.left == null && node.right == null) {
            sum += Integer.parseInt(numStr);
            return;
        }

        if (node.left != null) {
            traverse(node.left);
            // 去掉上回合加入的digit
            numStr = numStr.substring(0, numStr.length() - 1);
        }

        if (node.right != null) {
            traverse(node.right);
            // 去掉上回合加入的digit
            numStr = numStr.substring(0, numStr.length() - 1);
        }
    }
}
