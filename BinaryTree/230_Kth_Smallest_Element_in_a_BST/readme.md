# 230. Kth Smallest Element in a BST

> (2020/05/20) 一刷

https://leetcode.com/problems/kth-smallest-element-in-a-bst/

給一個二元搜尋樹, 寫一個function來找到第kth小的元素

Note:
你可以認為k永遠符合 1 <= k <= BST總共的數目

範例:

```
Input: root = [3,1,4,null,2], k = 1
   3
  / \
 1   4
  \
   2
Output: 1
```
範例:
```$xslt
Input: root = [5,3,6,2,4,null,null,1], k = 3
       5
      / \
     3   6
    / \
   2   4
  /
 1
Output: 3
```

## 我的作法

知道BST的一個特性: 用in order traversal走訪該樹就可以得到從小到大的排序

## Java

```java=
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
```

