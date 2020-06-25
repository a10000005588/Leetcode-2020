# 707. Design Linked List

Design your implementation of the linked list. You can choose to use the singly linked list or the doubly linked list. A node in a singly linked list should have two attributes: val and next. val is the value of the current node, and next is a pointer/reference to the next node. If you want to use the doubly linked list, you will need one more attribute prev to indicate the previous node in the linked list. Assume all nodes in the linked list are 0-indexed.

Implement these functions in your linked list class:

get(index) : Get the value of the index-th node in the linked list. If the index is invalid, return -1.
addAtHead(val) : Add a node of value val before the first element of the linked list. After the insertion, the new node will be the first node of the linked list.
addAtTail(val) : Append a node of value val to the last element of the linked list.
addAtIndex(index, val) : Add a node of value val before the index-th node in the linked list. If index equals to the length of linked list, the node will be appended to the end of linked list. If index is greater than the length, the node will not be inserted.
deleteAtIndex(index) : Delete the index-th node in the linked list, if the index is valid.
 

Example:

Input: 
["MyLinkedList","addAtHead","addAtTail","addAtIndex","get","deleteAtIndex","get"]
[[],[1],[3],[1,2],[1],[1],[1]]
Output:  
[null,null,null,null,2,null,3]

Explanation:
MyLinkedList linkedList = new MyLinkedList(); // Initialize empty LinkedList
linkedList.addAtHead(1);
linkedList.addAtTail(3);
linkedList.addAtIndex(1, 2);  // linked list becomes 1->2->3
linkedList.get(1);            // returns 2
linkedList.deleteAtIndex(1);  // now the linked list is 1->3
linkedList.get(1);            // returns 3
 

Constraints:

0 <= index,val <= 1000
Please do not use the built-in LinkedList library.
At most 2000 calls will be made to get, addAtHead, addAtTail,  addAtIndex and deleteAtIndex.

## 我的作法

原本是先打算用 head, tail來實作, 但有遇到fail的情況

### code

```java=
class MyLinkedList {
    class Node {
        int val;
        Node next;

        Node(int val) {
            this.val = val;
        }
    }

    Node head;
    Node tail;
    int nodeNumber = 0;
    /** Initialize your data structure here. */
    public MyLinkedList() {
    }

    /** Get the value of the index-th node in the linked list. If the index is invalid, return -1. */
    public int get(int index) {
        int count = 0;
        // 從head開始走訪
        Node node = this.head;
        while(node != null) {
            if (index == count)
                return node.val;
            count++;
            node = node.next;
        }
        // 沒有找到符合的index:回傳-1
        return -1;
    }

    /** Add a node of value val before the first element of the linked list. After the insertion, the new node will be the first node of the linked list. */
    public void addAtHead(int val) {
        Node prevHead = this.head;
        // 初始化newHead的值
        Node newHead = new Node(val);
        // 將舊的head給新建立head的下一個位置
        newHead.next = prevHead;
        // 重新定義head
        this.head = newHead;

        // 如果tail是空的, 那跟head指向同一個node
        if (this.tail == null) {
            this.tail = newHead;
        }
        this.nodeNumber++;
    }

    /** Append a node of value val to the last element of the linked list. */
    public void addAtTail(int val) {
        // 初始化newTail的值
        Node newTail = new Node(val);
        this.nodeNumber++;
        // 如果head與tail都是null
        if (this.head == null && this.tail == null) {
            this.head = newTail;
            this.tail = newTail;
            return;
        }

        // 將舊的tail指向最新的tail
        this.tail.next = newTail;
        // 將新的tail取代現在tail的位置
        this.tail = newTail;
    }

    /** Add a node of value val before the index-th node in the linked list. If index equals to the length of linked list, the node will be appended to the end of linked list. If index is greater than the length, the node will not be inserted. */
    public void addAtIndex(int index, int val) {
        Node newNode = new Node(val);
        Node curNode = this.head; // 從head node開始走訪
        this.nodeNumber++;

        // 若index <= 0 放在head
        if (index <= 0) {
            this.addAtHead(val);
            return;
        }

        // 如果index與linkedlist長度相同或大於之, 直接插在最後一個
        if (index >= nodeNumber) {
            // 如果linkedList都沒有值
            if (this.head == null && this.tail == null) {
                this.head = newNode;
                this.tail = newNode;
                this.nodeNumber++;
                return;
            }
            this.addAtTail(val);
            return;
        }


        int count = 0;
        while(curNode != null) {
            // 如果已經走訪到target index的前一個, 開始執行
            if (count == index-1) {
                // 找到位置後, 將新的node的下一個指向原本curNode的下一個
                newNode.next = curNode.next;
                // 將curNode下一個改指向newNode
                curNode.next = newNode;
                break;
            }
            // 轉到下一個
            curNode = curNode.next;
            count++;
        }
    }

    /** Delete the index-th node in the linked list, if the index is valid. */
    public void deleteAtIndex(int index) {
        int count = 0;
        Node curNode = this.head;
        // 先處理若只有一個節點 (head與tail都指向同一個node)
        if (index == 0) {
            if (curNode.next == null) {
                this.head = null;
                this.tail = null;
                this.nodeNumber--;
                return;
            }
            if (curNode.next != null) {
                this.head = this.head.next;
                this.nodeNumber--;
                return;
            }
        }

        // 若有兩個節點以上
        while(curNode != null) {
            // 找出目標的上一個, 且下一個節點也有下一個節點, 那直接將上一個節點指向下下個節點
            if (count == (index-1) && curNode.next != null) {
                curNode.next = curNode.next.next;
                break;
            }

            // 如果到最後一個節點位置
            if (count == (index-1) && curNode.next == null) {
                curNode.next = null;
                // 重新定位tail, 將tail指向前一個
                this.tail = curNode;
                break;
            }
            curNode = curNode.next;
            count++;
        }
        this.nodeNumber--;
    }
}

/**
 * Your MyLinkedList object will be instantiated and called as such:
 * MyLinkedList obj = new MyLinkedList();
 * int param_1 = obj.get(index);
 * obj.addAtHead(val);
 * obj.addAtTail(val);
 * obj.addAtIndex(index,val);
 * obj.deleteAtIndex(index);
 */
```

:::info

26 / 57 test cases passed.

:::

## 參考作法

沒有用tail指向最後一個node

https://leetcode.com/problems/design-linked-list/discuss/213901/Simple-Java-solution

不過要注意的是這篇參考解答沒有處理 deleteAtIndex(0)的情況

### code

```java=
class MyLinkedList {
    class Node {
        int val;
        Node next;

        Node(int val) {
            this.val = val;
        }
    }

    Node head;
    int nodeNumber = 0;
    /** Initialize your data structure here. */
    public MyLinkedList() {
    }

    /** Get the value of the index-th node in the linked list. If the index is invalid, return -1. */
    public int get(int index) {
        if (index >= nodeNumber) return -1;
        // 從head開始走訪
        Node curNode = this.head;
        for (int i=0; i<index; i++) {
            curNode = curNode.next;
        }
        return curNode.val;
    }

    /** Add a node of value val before the first element of the linked list. After the insertion, the new node will be the first node of the linked list. */
    public void addAtHead(int val) {
        this.nodeNumber++;
        Node prevHead = this.head;
        // 初始化newHead的值
        Node newHead = new Node(val);
        // 將舊的head給新建立head的下一個位置
        newHead.next = prevHead;
        // 重新定義head
        this.head = newHead;
    }

    /** Append a node of value val to the last element of the linked list. */
    public void addAtTail(int val) {
        // 初始化newTail的值
        Node newNode = new Node(val);
        this.nodeNumber++;
        // 如果head與tail都是null
        if (this.head == null) {
            this.head = newNode;
            return;
        } else {
            Node curNode = this.head;
            while (curNode.next != null) {
                curNode = curNode.next;
            }
            curNode.next = newNode;
        }
    }

    /** Add a node of value val before the index-th node in the linked list. If index equals to the length of linked list, the node will be appended to the end of linked list. If index is greater than the length, the node will not be inserted. */
    public void addAtIndex(int index, int val) {
        if (index > nodeNumber) return;
        // 若index <= 0 放在head
        if (index <= 0) {
            this.addAtHead(val);
            return;
        }
        Node newNode = new Node(val);
        Node curNode = this.head; // 從head node開始走訪
        this.nodeNumber++;

        for (int i=0; i<index-1; i++) {
            curNode = curNode.next;
        }
        newNode.next = curNode.next;
        curNode.next = newNode;
    }

    /** Delete the index-th node in the linked list, if the index is valid. */
    public void deleteAtIndex(int index) {
        // 將不合規的index給排除掉
        if (index >= nodeNumber) return;

        this.nodeNumber--;
        Node curNode = this.head;

         // 處理deleteAtIndex(0)的情況
        if (index == 0) {
            head = head.next;
            return;
        }
        
        for (int i=0; i < index - 1; i++) {
            curNode = curNode.next;
        }
        curNode.next = curNode.next.next;
    }
}
```
