# 563. Binary Tree Tilt (easy)

Given a binary tree, return the tilt of the whole tree.

The tilt of a tree node is defined as the absolute difference between the sum of all left subtree node values and the sum of all right subtree node values. Null node has tilt 0.

The tilt of the whole tree is defined as the sum of all nodes' tilt.

```
Example:
Input: 
         1
       /   \
      2     3
Output: 1
Explanation: 
Tilt of node 2 : 0
Tilt of node 3 : 0
Tilt of node 1 : |2-3| = 1
Tilt of binary tree : 0 + 0 + 1 = 1
```

Note:

The sum of node values in any subtree won't exceed the range of 32-bit integer.
All the tilt values won't exceed the range of 32-bit integer.

## 我的作法 (failed)

### Java

#### 5/26 一刷

> 花了大概2小時...

以下的程式碼一直卡住, 算不出對的答案。
以下是failed的程式碼

```java=
class Solution {
    public int findTilt(TreeNode root) {
        if (root == null) { return 0; }
        
        if (root.left == null && root.right != null) {
            return getTilt(countTilt(root.right), 0);
        }  else if (root.left != null && root.right == null) {
            return getTilt(countTilt(root.left), 0);
        }
        return  getTilt(countTilt(root.left), countTilt(root.right));
    }
    
    private int countTilt(TreeNode node) {
        int tilt = 0;
        if (node.left == null && node.right == null) {
            return tilt + node.val;
        } else if (node.left == null && node.right != null) {
            tilt = node.val + countTilt(node.right); 
            return tilt;
        } else if (node.right == null && node.left != null) {
            tilt = node.val + countTilt(node.left);
            return tilt;
        } 
        
        return tilt + countTilt(node.left) + countTilt(node.right) + node.val;
    }
    
    private int getTilt(int x, int y) {
        return x - y < 0 ? (x-y) * -1 : (x-y);
    }
}
```

#### 5/27 二刷

> 5分鐘

```java=
class Solution {
    int tilt;
    public int findTilt(TreeNode root) {
        if (root == null) {
            return 0;
        }
        
        countTilt(root);
        return tilt;
    }
    
    private int countTilt(TreeNode root) {
        if (root == null) { return 0; }
        
        int left = countTilt(root.left);
        int right = countTilt(root.right);
        
        tilt += Math.abs(left-right);  // <- 到這裡有卡住, 需注意
        return left + right + root.val;
    }

}
```

#### 5/30 三刷

> 大概15分鐘...

```java=
class Solution {
    int tilt = 0;
    public int findTilt(TreeNode root) {
        countTilt(root);
        return tilt;
    }
    
    private int countTilt(TreeNode root) {
        
        if (root == null) {
            return 0;
        }
        int left = countTilt(root.left);
        int right = countTilt(root.right);
        
        tilt += Math.abs(left-right); // <- 一樣在這裡又卡住, 需要計算left和right的絕對值
        return root.val + left + right;
    }

}
```

## 參考做法

其核心概念為"計算每個子樹的tilt", 並且加總起來

通常這類題目需要做遞迴, 每做完一個子樹, 那就要計算與加總tilt值, 需要一個global的變數加總所有子樹的tilt值

使用recursion來走訪子樹時, 左子樹跑完後, 需要也把右子樹也跑完才能夠計算該節點的tilt值

用recursion的話, 直接就是看最左下角 (5), 如果若該節點都沒有任何子節點 (可看到null的節點會回傳0), 所以該節點的tilt值自然也會是0

![](https://i.imgur.com/rnLCJS7.png)

(5) 的右子樹也是一樣

![](https://i.imgur.com/2jp3RJd.png)

接著可以看到 (2) 節點, 左子樹回傳5回來, 右子樹為8, 故該節點tilt值為 |5 - 8| = 3 總tilt值為 3

![](https://i.imgur.com/Ciqdggh.png)

以此類推...


### Java

```java=
class Solution {
    int tilt = 0;
    public int findTilt(TreeNode root) {
        countTilt(root);
        return tilt;
    }
    
    private int countTilt(TreeNode node) {
        if (node == null) {
            return 0;
        }        
        
        int left = countTilt(node.left);
        int right = countTilt(node.right);
        tilt += cal(left, right);
        
        return left + right + node.val;
    }
    
    private int cal(int x, int y) {
        return x - y < 0 ? (x-y) * -1 : (x-y);
    }
}
```

## 心得

像這種需要用到recursion的題目, 建議是先釐清recursion走訪的邏輯, 再開始寫code會比較好
