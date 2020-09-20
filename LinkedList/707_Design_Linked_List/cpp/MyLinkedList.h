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