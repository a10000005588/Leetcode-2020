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