# 707. Design Linked List

Design your implementation of the linked list. You can choose to use a singly or doubly linked list.
A node in a singly linked list should have two attributes: val and next. val is the value of the current node, and next is a pointer/reference to the next node.
If you want to use the doubly linked list, you will need one more attribute prev to indicate the previous node in the linked list. Assume all nodes in the linked list are 0-indexed.

Implement the MyLinkedList class:

* MyLinkedList() 
  * Initializes the MyLinkedList object.
* int get(int index)
  * Get the value of the indexth node in the linked list. If the index is invalid, return -1.
* void addAtHead(int val) 
  * Add a node of value val before the first element of the linked list. After the insertion, the new node will be the first node of the linked list.
* void addAtTail(int val) 
  * Append a node of value val as the last element of the linked list.
* void addAtIndex(int index, int val) 
  * Add a node of value val before the indexth node in the linked list. If * index equals the length of the linked list, the node will be appended to the end of the linked list. If index is greater than the length, the node will not be inserted.
* void deleteAtIndex(int index) 
  * Delete the indexth node in the linked list, if the index is valid.

Example 1:
```
Input
["MyLinkedList", "addAtHead", "addAtTail", "addAtIndex", "get", "deleteAtIndex", "get"]
[[], [1], [3], [1, 2], [1], [1], [1]]
Output
[null, null, null, null, 2, null, 3]

Explanation
MyLinkedList myLinkedList = new MyLinkedList();
myLinkedList.addAtHead(1);
myLinkedList.addAtTail(3);
myLinkedList.addAtIndex(1, 2);    // linked list becomes 1->2->3
myLinkedList.get(1);              // return 2
myLinkedList.deleteAtIndex(1);    // now the linked list is 1->3
myLinkedList.get(1);              // return 3
```

## 參考做法

參考這篇分享的大大，然後實作Java與C++版本

https://leetcode.com/problems/design-linked-list/discuss/150999/C%2B%2B-simple-solution-beats-97.27!

### Java

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

### Cpp

MylinkedList.h

```c++=
class Node {
  public:
    int val;
    Node *next;
    Node(int val) {
      this->val = val;
      next = nullptr;
    }
};

class MyLinkedList {
  int size;
  Node *head;

  public:
    MyLinkedList();
    int get(int);
    void addAtHead(int);
    void addAtTail(int);
    void addAtIndex(int, int);
    void deleteAtIndex(int);
};
```

MyLinkedList.cpp

```c++=
#include "MyLinkedList.h"

/** Initialize your data structure here. */
MyLinkedList::MyLinkedList() {
    size = 0;
    head = new Node(0);
}

int MyLinkedList::get(int index) {
    if(index >= size) return -1;  // 如果查詢的index超出目前的linkedlist大小，return -1

    Node *temp = head->next;  // 製作一個head下一個的指標，為儲存值得開始
    for(int i=0; i<index; i++) temp = temp->next; // 移動index次

    return temp->val;
}

/** Add a node of value val before the first element of the linked list. After the insertion, the new node will be the first node of the linked list. */
void MyLinkedList::addAtHead(int val) {
    Node *temp = head->next;
    head->next = new Node(val); // 將head改成 new node.
    // 將new node (head->next) 的下一個(head->next->next)改成原本的head node
    head->next->next = temp;

    size++;
}

/** Append a node of value val to the last element of the linked list. */
void MyLinkedList::addAtTail(int val) {
    Node *temp = head;
    while(temp->next != nullptr) temp = temp->next; // 移動到tail
    temp->next = new Node(val);
    size++; // 記得將大小+1
}

/** Add a node of value val before the index-th node in the linked list. If index equals to the length of linked list, the node will be appended to the end of linked list. If index is greater than the length, the node will not be inserted. */
void MyLinkedList::addAtIndex(int index, int val) {
    if (index > size) return; // 超過linkedlist大小，忽略
    Node *temp = head;

    for(int i=0; i<index; i++) temp = temp->next;


    Node *temp1 = temp->next; // 取得指定index node的下一個node or nullptr
    temp->next = new Node(val); // 將指定index node下一個換上新的node
    temp->next->next = temp1; // 新的node的下一個指向 原本的下一個
    size++;
}

void MyLinkedList::deleteAtIndex(int index) {
    if (index >= size) return; // 超出size大小，不做任何事
    Node *temp = head;

    for(int i=0; i<index; i++) temp = temp->next;
    Node *temp1 = temp->next; // 取得指定的index node位置
    temp->next = temp1->next; // 將temp的下一個指向 刪除node的下一個
    temp1->next = nullptr; // 將刪除的node指向null
    size--; // 調整size大小-1
    delete temp1; // 回收記憶體空間
}
```
