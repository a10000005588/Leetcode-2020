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


### Golang

嘗試用golang解dfs, 但一樣遇到leetcode上面若用global variable儲存當前最小高度，會發生不同測資會覆蓋掉彼此的最小高度

```go=
/**
 * Definition for a binary tree node.
 * type TreeNode struct {
 *     Val int
 *     Left *TreeNode
 *     Right *TreeNode
 * }
 */

var minLength = 100000000000000

func minDepth(root *TreeNode) int {

    if root == nil {
        return 0
    }
    
    currLength := 0
    dfs(root, currLength)
    
    return minLength
}

func dfs(root *TreeNode, curr int) {
    if root == nil {
        return
    }
    
    curr += 1
    
    if root.Left == nil && root.Right == nil {
        if minLength > curr {
            minLength = curr
        }
    }
    
    if root.Left != nil {
        dfs(root.Left, curr)
    }
    
    if root.Right != nil {
        dfs(root.Right, curr)
    }
}
```

> 該作法不建議使用

### Golang 

#### 參考作法

參考[Discussion大神的解法](https://leetcode.com/problems/minimum-depth-of-binary-tree/discuss/519346/Go)，不透過global variable, 而是用回傳的方式來得到最小高度

```go=
func minDepth(root *TreeNode) int {
    if root == nil {
        return 0
    }
    if root.Left == nil && root.Right == nil {
        return 1
    }
    if root.Left == nil {
        return 1 + minDepth(root.Right)
    }
    if root.Right == nil {
        return 1 + minDepth(root.Left)
    }
    return 1 + min(minDepth(root.Left), minDepth(root.Right))
}
func min(a int, b int) int {
    if a < b {
        return a
    }
    return b
}
```

#### 自我再度挑戰用DFS沒有用global (錯誤)

此版會有錯誤，以為第22~27行這邊，判斷說若root.Left 或 root.Right != nil, 就回傳自己那一邊的minDepth, 但這做法會有錯誤，因為會沒有比較到 另外一邊若不是 nil的高度
```go=
/**
 * Definition for a binary tree node.
 * type TreeNode struct {
 *     Val int
 *     Left *TreeNode
 *     Right *TreeNode
 * }
 */

func minDepth(root *TreeNode) int {
    // 若目前root為nil 高度為0
    if root == nil {
        return 0
    }
    
    // 若目前沒有子樹們，那就回傳1, 因為只有root自己
    if root.Left == nil && root.Right == nil {
        return 1
    }
    
    // 若left child存在, 回傳1+minDepth(root.left) (自己 + 左子樹的最小高度)
    if root.Left != nil {
        return 1 + minDepth(root.Left)
    }
    
    // 若right child存在, 回傳1+minDepth(root.left) (自己 + 右子樹的最小高度)
    if root.Right != nil {
        return 1 + minDepth(root.Right)
    }

    return 1+ min(minDepth(root.Left), minDepth(root.Right))
}

func min(x int, y int) int {
    if x > y {
        return y
    }
    return x
}
```

#### 自我再度挑戰用DFS沒有用global (正確)

```go=
/**
 * Definition for a binary tree node.
 * type TreeNode struct {
 *     Val int
 *     Left *TreeNode
 *     Right *TreeNode
 * }
 */

func minDepth(root *TreeNode) int {
    // 若目前root為nil 高度為0
    if root == nil {
        return 0
    }
    
    // 若目前沒有子樹們，那就回傳1, 因為只有root自己
    if root.Left == nil && root.Right == nil {
        return 1
    }
    
    // 若left child不存在, 回傳1+minDepth(root.right) (自己 + 右子樹的最小高度)
    if root.Left == nil {
        return 1 + minDepth(root.Right)
    }
    
    // 若right child不存在, 回傳1+minDepth(root.left) (自己 + 左子樹的最小高度)
    if root.Right == nil {
        return 1 + minDepth(root.Left)
    }

    return 1+ min(minDepth(root.Left), minDepth(root.Right))
}

func min(x int, y int) int {
    if x > y {
        return y
    }
    return x
}
```
```
Runtime: 256 ms, faster than 39.08% of Go online submissions for Minimum Depth of Binary Tree.
Memory Usage: 25.4 MB, less than 5.28% of Go online submissions for Minimum Depth of Binary Tree.
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

### Golang

2021/02/16, about 2 tomatoes

```go=
/**
 * Definition for a binary tree node.
 * type TreeNode struct {
 *     Val int
 *     Left *TreeNode
 *     Right *TreeNode
 * }
 */

type pair struct {
    node *TreeNode
    height int
}

func minDepth(root *TreeNode) int {
    // 若目前root為nil 高度為0
    if root == nil {
        return 0
    }
    
    // 使用queue存放當前的root, 透過golang的slice來實作
    queue := []pair{}
    
    // make a new pair for root
    rootPair := pair{node: root, height: 1}
    queue = append(queue, rootPair)
    
    for len(queue) != 0 {
        element := queue[0]
        elementNode := element.node
        // 將取出來的elemenet去掉
        queue = queue[1:]
        
        // 如果左右子樹都還有值，那就繼續找，將left, right塞進queue
        if elementNode.Left == nil && elementNode.Right == nil {
            return element.height
        }
        
        if elementNode.Left != nil {
            queue = push(queue, elementNode.Left, element.height)
        }
        
        if elementNode.Right != nil {
            queue = push(queue, elementNode.Right, element.height)
        }
    }
    return 0
}

func push(queue []pair, root *TreeNode, currHeight int) []pair {
    newElement := pair{node: root, height: currHeight + 1}
    newQueue := append(queue, newElement)
    return newQueue
}
```

```
Runtime: 212 ms, faster than 99.65% of Go online submissions for Minimum Depth of Binary Tree.
Memory Usage: 18.1 MB, less than 98.24% of Go online submissions for Minimum Depth of Binary Tree.
```