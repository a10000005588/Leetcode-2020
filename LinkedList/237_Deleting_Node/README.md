# 237. Delete Node in a Linked List

Write a function to delete a node (except the tail) in a singly linked list, given only access to that node.

Given linked list -- head = [4,5,1,9], which looks like following:

```
Example 1:

Input: head = [4,5,1,9], node = 5
Output: [4,1,9]
Explanation: You are given the second node with value 5, the linked list should become 4 -> 1 -> 9 after calling your function.
```
```
Example 2:

Input: head = [4,5,1,9], node = 1
Output: [4,5,9]
Explanation: You are given the third node with value 1, the linked list should become 4 -> 5 -> 9 after calling your function.
```

## 參考做法

> 一刷 6/2 想了快半小時

偷喵了一下解答就恍然大悟, 以下圖解釋:
假如要抽換掉targetDeletingNode (5)

![](https://i.imgur.com/xrKkigx.png)

### Java

```java
/**
 * Definition for singly-linked list.
 * public class ListNode {
 *     int val;
 *     ListNode next;
 *     ListNode(int x) { val = x; }
 * }
 */
class Solution {
    public void deleteNode(ListNode targetDeletingNode) {
        // 要把當前的節點刪除的作法為: 把後面的值複製過來就對了
        targetDeletingNode.val = targetDeletingNode.next.val;
        // 把後面複製過來的給幹掉
        targetDeletingNode.next = targetDeletingNode.next.next;
    }
}
```
