# 129. Sum Root to Leaf Numbers (medium)

Given a binary tree containing digits from 0-9 only, each root-to-leaf path could represent a number.

An example is the root-to-leaf path 1->2->3 which represents the number 123.

Find the total sum of all root-to-leaf numbers.

Note: A leaf is a node with no children.

Example:
```
Input: [1,2,3]
    1
   / \
  2   3
Output: 25
Explanation:
The root-to-leaf path 1->2 represents the number 12.
The root-to-leaf path 1->3 represents the number 13.
Therefore, sum = 12 + 13 = 25.
```
Example 2:
```
Input: [4,9,0,5,1]
    4
   / \
  9   0
 / \
5   1
Output: 1026
Explanation:
The root-to-leaf path 4->9->5 represents the number 495.
The root-to-leaf path 4->9->1 represents the number 491.
The root-to-leaf path 4->0 represents the number 40.
Therefore, sum = 495 + 491 + 40 = 1026.
```

## 我的作法

使用Depth first search.

例如有 
```
    4
   / \
  9   0
 / \
5   1
```

先找
```
    4
   / 
  9   
 / 
5   
```

再找
```
    4
   / 
  9   
   \
    1
```

以此類推

若每次走到node沒有子節點時, 進行加總, 並且移除當前增加的數字


### Java

> 6/26 一刷 花10分鐘

```java=
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
        // 排除機車的edge case
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
```

```
110 / 110 test cases passed.
Status: Accepted
Runtime: 4 ms
Memory Usage: 40.1 MB
```

比其他人解答慢有點多

![](https://i.imgur.com/F0DO29V.png)
