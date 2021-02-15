# 111. Minimum Depth of Binary Tree (easy)

Given a binary tree, find its minimum depth.

The minimum depth is the number of nodes along the shortest path from the root node down to the nearest leaf node.

Note: A leaf is a node with no children.

Example 1:

![pic01](./pics/pic01.jpg)
```
Input: root = [3,9,20,null,null,15,7]
Output: 2
```
Example 2:
```
Input: root = [2,null,3,null,4,null,5,null,6]
Output: 5
```

Constraints:

* The number of nodes in the tree is in the range [0, 105].
* -1000 <= Node.val <= 1000

### Companies

* Facebook: 2
* Adobe: 2
* Google: 2
* Apple: 2
* Amazon: 2
* Goldman Sachs: 2

## 我的解法 DFS

先用recursive走訪，為Depth First Search

再用一個global variable紀錄目前走到最底 (left, right node都是nil)時，
紀錄有最小值的depth

### Java

```java=
class Solution {
    public int minDepth(TreeNode root) {
        if (root == null) {
            return 0;
        }
        
        int ans = dfs(root, 1);
        return ans;
    }
    
    public int dfs(TreeNode root, int curr) {
        // 只有都沒有子樹的情況下，才能夠做統計
        if (root.left == null && root.right == null) {
            return curr;
        }
     
        curr +=1;
        
        int leftMin = Integer.MAX_VALUE;
        int rightMin = Integer.MAX_VALUE;
        if (root.left != null) {
            leftMin = dfs(root.left, curr);
        }
        if (root.right != null) {
            rightMin = dfs(root.right, curr);
        }
       
        // check left is less or not, if so , return it
        if (leftMin < rightMin) {
            return leftMin;
        }
        return rightMin;
    }
}
```

```
52 / 52 test cases passed.
Status: Accepted
Runtime: 5 ms
Memory Usage: 59.7 MB
```

## 朋友建議的解法 BFS

先將root丟到 queue內，
再取出來，若有left or right node 就再度放到queue內，
再用一個global variable紀錄目前走到最底 (left, right node都是nil)時，
紀錄有最小值的depth，若有最小值，直接reutrn. 因為是BFS走訪，遇到最小值的就是最佳解了。


### Java

```java=
class Solution {
    
    public int minDepth(TreeNode root) {
        List<Pair<TreeNode, Integer>> queue = new ArrayList<>();
        
        if (root == null) {
            return 0;
        }
        
        queue.add(new Pair(root, 1));
        
        int min_depth = Integer.MAX_VALUE;
        while (!queue.isEmpty()) {
            Pair<TreeNode, Integer> current = queue.get(0);
            root = current.getKey();
            int current_depth = current.getValue();
            
            // 去掉取出的root
            queue.remove(0);
            
            if ((root.left == null) && (root.right == null)) {
                min_depth = min(min_depth, current_depth);
            }
            
            if (root.left != null) {
                queue.add(new Pair(root.left, current_depth + 1));
            }
            
            if (root.right != null) {
                queue.add(new Pair(root.right, current_depth + 1));
            }
        }
        
        return min_depth;
    }
    
    private int min(int x, int y) {
        if (x > y) return y;
        return x;
    }
}
```

```
52 / 52 test cases passed.
Status: Accepted
Runtime: 54 ms
Memory Usage: 53.8 MB
```

