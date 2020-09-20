class Solution {
    public void deleteNode(ListNode node) {
        // 要把當前的節點刪除的作法為: 把後面的值複製過來就對了
        node.val = node.next.val;
        // 把後面複製過來的給幹掉
        node.next = node.next.next;
    }
}
