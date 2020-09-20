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
