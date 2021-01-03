# 1379. Find a Corresponding Node of a Binary Tree in a Clone of That Tree

Given two binary trees original and cloned and given a reference to a node target in the original tree.

The cloned tree is a copy of the original tree.

Return a reference to the same node in the cloned tree.

Note that you are not allowed to change any of the two trees or the target node and the answer must be a reference to a node in the cloned tree.

Follow up: Solve the problem if repeated values on the tree are allowed.

 
Example 1:

![](pics/ex1.png)

```
Input: tree = [7,4,3,null,null,6,19], target = 3
Output: 3
Explanation: In all examples the original and cloned trees are shown. The target node is a green node from the original tree. The answer is the yellow node from the cloned tree.
```
Example 2:

![](pics/ex2.png)

```
Input: tree = [7], target =  7
Output: 7
```
Example 3:
![](pics/ex3.png)
```
Input: tree = [8,null,6,null,5,null,4,null,3,null,2,null,1], target = 4
Output: 4
```
Example 4:
![](pics/ex4.png)

```
Input: tree = [1,2,3,4,5,6,7,8,9,10], target = 5
Output: 5
```
Example 5:
![](pics/ex5.png)

```
Input: tree = [1,2,null,3], target = 2
Output: 2
```

Constraints:
```
The number of nodes in the tree is in the range [1, 10^4].
The values of the nodes of the tree are unique.
target node is a node from the original tree and is not null.
```

## 我的作法

用遞迴的方式，從左子樹開始找，若都沒有在找右子樹

### Cpp version 1 (faild)

```cpp=
class Solution {
public:
    TreeNode* findTargetNode(TreeNode* rootNode, TreeNode* target) {
        if (rootNode == nullptr) {
            return nullptr;
        }
    
        if (rootNode->val == target->val) {
            return rootNode;
        }
    
        TreeNode* leftNode = findTargetNode(rootNode->left, target);
        TreeNode* rightNode = findTargetNode(rootNode->right, target);
        if (leftNode != nullptr) {
            return leftNode;
        }
        if (rightNode != nullptr) {
            return rightNode;
        }
        // 這裡缺了return statement...
    }
    
    
    TreeNode* getTargetCopｓy(TreeNode* original, TreeNode* cloned, TreeNode* target) {
        TreeNode* ans = findTargetNode(cloned, target);
        return ans;
    }
};
```

目前這版本會遇到
```
Line 31: Char 1: error: non-void function does not return a value in all control paths [-Werror,-Wreturn-type]
```

### Cpp version 2 (follow Cpp version 1)

參考: [[C++/Python] The devil is in the detail: DFS - Stopping searching at desired node value](https://leetcode.com/problems/find-a-corresponding-node-of-a-binary-tree-in-a-clone-of-that-tree/discuss/998549/C%2B%2BPython-The-devil-is-in-the-detail%3A-DFS-Stopping-searching-at-desired-node-value)

參考了此版才發現，自己的version 1只要稍微修改一下return 的邏輯就好了

```cpp=

class Solution {
public:
   
    TreeNode* findTargetNode(TreeNode* rootNode, TreeNode* target) {
        if (rootNode == nullptr) {
            return nullptr;
        }
    
        if (rootNode->val == target->val) {
            return rootNode;
        }
    
        TreeNode* leftNode = findTargetNode(rootNode->left, target);
        TreeNode* rightNode = findTargetNode(rootNode->right, target);
        if (leftNode != nullptr) {
            return leftNode;
        }
        // 一定會有解答，所以若沒有在left, 那就回傳right的結果
        return rightNode;
    }
    
    
    TreeNode* getTargetCopy(TreeNode* original, TreeNode* cloned, TreeNode* target) {
        TreeNode* ans = findTargetNode(cloned, target);
        return ans;
    }
};
```